[toc]

mybatis与数据库进行交互有两种方式，一种传统方式，一种mapper代理方式。通过对两种方式的分析我们需要掌握以下内容：
- 传统方式
1. MyBatis如何加载解析配置文件？
2. MyBatis如何解析SQL、设置参数以及执行SQL的？
3. MyBatis如何封装返回结果集？

- mapper代理方式
1. MyBatis底层如何产生代理对象？
2. 当代理对象调用方法时它又是如何执行到底层的JDBC代码的？

# 1 传统方式源码剖析

类似于Spring、MyBatis等灵活性和可拓展性都很高的开源框架都提供了很多配置项，开发人员需要在使用时提供相应的配置信息，实现相应的需求。MyBatis中的配置文件主要有两个，分别是`mybatis-config.xml`和映射配置文件。现在主流的配置方式除了使用XML配置文件，还会配合注解进行配置。

## 1.1 初始化流程

Mybatis初始化的主要工作就是加载并解析`mybatis-config.xml`配置文件、映射配置文件以及相关的注解信息。在引入了MyBatis依赖的项目中我们可以通过以下两行代码执行初始化操作。
```Java
InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//这一行代码就是初始化的开始
SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
```
Mybatis的初始化入口就是SqlSessionFactoryBuilder.build()方法。再来看看build()方法里做了什么。
```Java
    // 1.我们最初调用的build
    public SqlSessionFactory build(InputStream inputStream) {
        //调用了重载方法
        return build(inputStream, null, null);
    }
    
    // 2.调用的重载方法
    public SqlSessionFactory build(InputStream inputStream, String environment, Properties properties) {
        try {
            // 创建 XMLConfigBuilder, XMLConfigBuilder是专门解析mybatis的配置文件的类
            XMLConfigBuilder parser = new XMLConfigBuilder(inputStream, environment, properties);
            // 执行 XML 解析
            // 创建 DefaultSqlSessionFactory 对象。parse()方法返回的是Configuration对象。
            return build(parser.parse());
        } catch (Exception e) {
            throw ExceptionFactory.wrapException("Error building SqlSession.", e);
        } finally {
            ErrorContext.instance().reset();
            try {
                inputStream.close();
            } catch (IOException e) {
                // Intentionally ignore. Prefer previous error.
            }
        }
    }
```
从上面的源码可以看出，build()方法很简单，只是调用了一下重载的build()方法，具体细节可以看注释。接下来我们开始分析XMLConfigBuilder的parse()方法以及Configuration对象。MyBatis在初始化的时候，会将MyBatis的配置信息全部加载到内存中，使用Configuration对象实例来存储。Configuration对象的结构和xml配置文件几乎相同，也就是说XML文件中的`properties`（属性）、`setting`（设置）、`typeAliases`（类型别名）和`typeHandlers`（类型处理器）等配置标签在Configuration对象中都有对应的属性来封装它们。也就是说，初始化配置文件信息的本质就是创建Configuration对象，将解析的XML数据封装到Configuration内部属性中。
```Java
    /**
     * 解析 XML 成 Configuration 对象。
     *
     * @return Configuration 对象
     */
    public Configuration parse() {
        // 若已解析，抛出 BuilderException 异常
        if (parsed) {
            throw new BuilderException("Each XMLConfigBuilder can only be used once.");
        }
        // 标记已解析
        parsed = true;
        ///parser是XPathParser解析器对象，读取节点内数据，<configuration>是MyBatis配置文件中的顶层标签
        // 解析 XML configuration 节点
        parseConfiguration(parser.evalNode("/configuration"));
        return configuration;
    }

    /**
     * 解析 XML
     *
     * 具体 MyBatis 有哪些 XML 标签，参见 《XML 映射配置文件》http://www.mybatis.org/mybatis-3/zh/configuration.html
     *
     * @param root 根节点
     */
    private void parseConfiguration(XNode root) {
        try {
            //issue #117 read properties first
            // 解析 <properties /> 标签
            propertiesElement(root.evalNode("properties"));
            // 解析 <settings /> 标签
            Properties settings = settingsAsProperties(root.evalNode("settings"));
            // 加载自定义的 VFS 实现类
            loadCustomVfs(settings);
            // 解析 <typeAliases /> 标签
            typeAliasesElement(root.evalNode("typeAliases"));
            // 解析 <plugins /> 标签
            pluginElement(root.evalNode("plugins"));
            // 解析 <objectFactory /> 标签
            objectFactoryElement(root.evalNode("objectFactory"));
            // 解析 <objectWrapperFactory /> 标签
            objectWrapperFactoryElement(root.evalNode("objectWrapperFactory"));
            // 解析 <reflectorFactory /> 标签
            reflectorFactoryElement(root.evalNode("reflectorFactory"));
            // 赋值 <settings /> 到 Configuration 属性
            settingsElement(settings);
            // read it after objectFactory and objectWrapperFactory issue #631
            // 解析 <environments /> 标签
            environmentsElement(root.evalNode("environments"));
            // 解析 <databaseIdProvider /> 标签
            databaseIdProviderElement(root.evalNode("databaseIdProvider"));
            // 解析 <typeHandlers /> 标签
            typeHandlerElement(root.evalNode("typeHandlers"));
            // 解析 <mappers /> 标签 
            mapperElement(root.evalNode("mappers"));
        } catch (Exception e) {
            throw new BuilderException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
    }
```
从源码中可以看到，程序是通过parseConfiguration()实现了对`mybatis-config.xml`配置文件中的每个节点进行解析的，每个节点的解析过程都封装成了一个单独的方法，代码清爽简洁。</br>

程序在解析`<mappers>`节点时，会创建XMLMapperBuilder对象加载映射文件，如果映射配置文件配置的是相应的Mapper接口，也会加载相应的Mapper接口，解析其中的注解并向MapperRegistry完成注册。在解析`<mapper>`标签的时，程序也会完成对SQL的解析，并将解析后的SQL节点信息封装成MappedStatement对象（描述一条SQL语句）,然后存入Configuration对象中的一个HashMap类型的属性mappedStatements中，map的key即是`${namespace}.${id}`（全限定类名 + 方法名），value对应的MappedStatement对象。<br>

在解析SQL节点中，有两个难点，一个是解析动态SQL，另一个是解析`<include>`节点，此处只讲整体解析流程，这两个知识点后面以面试题形式单独文章分析。
```Java
    /**
     * MappedStatement 映射
     *
     * KEY：`${namespace}.${id}`
     */
    protected final Map<String, MappedStatement> mappedStatements = new StrictMap<>("Mapped Statements collection");
```
MappedStatement包含了SQL节点的很多属性，其中比较重要的字段如下：
```Java
/**
 * 映射的语句，每个 <select />、<insert />、<update />、<delete /> 对应一个 MappedStatement 对象
 *
 * @author Clinton Begin
 */
public final class MappedStatement {

    /**
     * 节点中的id属性（包括命名空间前缀）
     */
    private String resource;
    
    /**
     * SqlSource 对象，对应一条SQL语句
     */
    private SqlSource sqlSource;
    
    /**
     * SQL 语句类型（INSERT、UPDATE、DELETE、SELECT或FLUSH）
     */
    private SqlCommandType sqlCommandType;

    // 其他字段
    ··· ···
}
```

解析标签的细节这里不一一介绍，感兴趣可以阅读《MyBatis技术内幕》。以上，便是MyBatis加载解析配置文件的整体流程。

## 1.2 执行SQL流程
初始化完成以后，程序就可以准备接收参数执行SQL了，在分析MyBatis执行SQL流程之前，需要先介绍两个接口：SqlSession接口和Executor接口。
1. SqlSession
SqlSession是一个接口，它有两个实现类：DefaultSqlSession（默认）和SqlSessionManager（弃用，不做介绍）。SqlSession是MyBatis中用于和数据库交互的顶层类，通常将它与ThreadLocal绑定，因为在控制事务时需要保证一个事务里的数据库操作都是在一个链接里执行的，一个会话使用一个SqlSession，并且在使用完毕后需要close。</br>
```Java
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;
    private final Executor executor;
    ··· ···
}    
```
SqlSession中的两个最重要的参数，configuration与初始化时的相同，Executor为执行器。</br>

2. Executor
Executor也是一个接口，其中定义了数据库操作的基本方法，常用的实现类有三个：BatchExecutor（重用语句并执行批量更新）、ReuseExecutor（重用预处理语句preparedStatement）、SimpleExecutor（普通的执行器，默认）。</br>

介绍完SqlSession和Executor，接下来通过传统方式来分析SQL执行流程。首先，看到以下代码，便是触发SQL执行的语句。
```Java
SqlSession sqlSession = factory.openSession();
List<User> list = sqlSession.selectList("com.silver.mapper.UserMapper.getUserByName");
```
### 1.2.1 获取SqlSession
我们通过factory工厂对象的openSession()方法获取到SqlSession，打开openSession()方法，代码如下：
```Java
    // 6. 进入openSession方法
    @Override
    public SqlSession openSession() {
        //getDefaultExecutorType()传递的是SimpleExecutor
        return openSessionFromDataSource(configuration.getDefaultExecutorType(), null, false);
    }
    
    // 7. 进入openSessionFromDataSource。
    // ExecutorType 为Executor的类型，TransactionIsolationLevel为事务隔离级别，autoCommit是否开启事务
    // openSession的多个重载方法可以指定获得的SeqSession的Executor类型和事务的处理
    private SqlSession openSessionFromDataSource(ExecutorType execType, TransactionIsolationLevel level, boolean autoCommit) {
        Transaction tx = null;
        try {
            // 获得 Environment 对象
            final Environment environment = configuration.getEnvironment();
            // 创建 Transaction 对象
            final TransactionFactory transactionFactory = getTransactionFactoryFromEnvironment(environment);
            tx = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);
            // 创建 Executor 对象
            final Executor executor = configuration.newExecutor(tx, execType);
            // 创建 DefaultSqlSession 对象
            return new DefaultSqlSession(configuration, executor, autoCommit);
        } catch (Exception e) {
            // 如果发生异常，则关闭 Transaction 对象
            closeTransaction(tx); // may have fetched a connection so lets call close()
            throw ExceptionFactory.wrapException("Error opening session.  Cause: " + e, e);
        } finally {
            ErrorContext.instance().reset();
        }
    }
```
### 1.2.2 执行SqlSession接口
获取到SqlSession过后，接下来就是执行SqlSession中的接口，也就是传统方式代码的第二行，参数为全限定类名+方法名，接收到参数后，会从Configuration对象的Mapped Statement容器中取到对应的MappedStatement对象，然后交由Executor来执行。selectList()重载方法源码如下：
```Java
    //8.进入selectList方法，多个重载方法
    @Override
    public <E> List<E> selectList(String statement) {
        return this.selectList(statement, null);
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        return this.selectList(statement, parameter, RowBounds.DEFAULT);
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        try {
            // 根据传入的全限定类名+方法名从映射的Map中获得 MappedStatement 对象
            MappedStatement ms = configuration.getMappedStatement(statement);
            // 调用Executor中的方法来处理
            // RowBounds用来处理分页逻辑
            return executor.query(ms, wrapCollection(parameter), rowBounds, Executor.NO_RESULT_HANDLER);
        } catch (Exception e) {
            throw ExceptionFactory.wrapException("Error querying database.  Cause: " + e, e);
        } finally {
            ErrorContext.instance().reset();
        }
    }
```
### 1.2.3 执行Executor接口
继续上面的源码，进入executor.query()方法。
```Java
    //此方法在SimpleExecutor的父类BaseExecutor中实现
    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException {
        //根据传入的参数动态获得SQL语句，最后返回用BoundSql对象表示
        BoundSql boundSql = ms.getBoundSql(parameter);
        //为本次查询创建缓存的Key
        CacheKey key = createCacheKey(ms, parameter, rowBounds, boundSql);
        // 查询
        return query(ms, parameter, rowBounds, resultHandler, key, boundSql);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey key, BoundSql boundSql) throws SQLException {
        ErrorContext.instance().resource(ms.getResource()).activity("executing a query").object(ms.getId());
        // 已经关闭，则抛出 ExecutorException 异常
        if (closed) {
            throw new ExecutorException("Executor was closed.");
        }
        // 清空本地缓存，如果 queryStack 为零，并且要求清空本地缓存。
        if (queryStack == 0 && ms.isFlushCacheRequired()) {
            clearLocalCache();
        }
        List<E> list;
        try {
            // queryStack + 1
            queryStack++;
            // 从一级缓存中，获取查询结果
            list = resultHandler == null ? (List<E>) localCache.getObject(key) : null;
            // 获取到，则进行处理
            if (list != null) {
                handleLocallyCachedOutputParameters(ms, key, parameter, boundSql);
            // 获得不到，则从数据库中查询
            } else {
                list = queryFromDatabase(ms, parameter, rowBounds, resultHandler, key, boundSql);
            }
        } finally {
            // queryStack - 1
            queryStack--;
        }
        if (queryStack == 0) {
            // 执行延迟加载
            for (DeferredLoad deferredLoad : deferredLoads) {
                deferredLoad.load();
            }
            // issue #601
            // 清空 deferredLoads
            deferredLoads.clear();
            // 如果缓存级别是 LocalCacheScope.STATEMENT ，则进行清理
            if (configuration.getLocalCacheScope() == LocalCacheScope.STATEMENT) {
                // issue #482
                clearLocalCache();
            }
        }
        return list;
    }
    
    // 从数据库中读取操作
    private <E> List<E> queryFromDatabase(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey key, BoundSql boundSql) throws SQLException {
        List<E> list;
        // 在缓存中，添加占位对象。此处的占位符，和延迟加载有关，可见 `DeferredLoad#canLoad()` 方法
        localCache.putObject(key, EXECUTION_PLACEHOLDER);
        try {
            // 执行读操作
            list = doQuery(ms, parameter, rowBounds, resultHandler, boundSql);
        } finally {
            // 从缓存中，移除占位对象
            localCache.removeObject(key);
        }
        // 添加到缓存中
        localCache.putObject(key, list);
        // 暂时忽略，存储过程相关
        if (ms.getStatementType() == StatementType.CALLABLE) {
            localOutputParameterCache.putObject(key, parameter);
        }
        return list;
    }
    
    // SimpleExecutor中实现父类的doQuery()抽象方法
    @Override
    public <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        Statement stmt = null;
        try {
            Configuration configuration = ms.getConfiguration();
            // 传入参数创建StatementHanlder对象来执行查询
            StatementHandler handler = configuration.newStatementHandler(wrapper, ms, parameter, rowBounds, resultHandler, boundSql);
            // 创建jdbc中的statement对象
            stmt = prepareStatement(handler, ms.getStatementLog());
            // 执行 StatementHandler  ，进行读操作
            return handler.query(stmt, resultHandler);
        } finally {
            // 关闭 StatementHandler 对象
            closeStatement(stmt);
        }
    }
    
    // 初始化 StatementHandler 对象
    private Statement prepareStatement(StatementHandler handler, Log statementLog) throws SQLException {
        Statement stmt;
        // 获得 Connection 对象
        Connection connection = getConnection(statementLog);
        // 创建 Statement 或 PrepareStatement 对象
        stmt = handler.prepare(connection, transaction.getTimeout());
        // 设置 SQL 上的参数，例如 PrepareStatement 对象上的占位符
        handler.parameterize(stmt);
        return stmt;
    }
```
Executor.query()方法几经转折，最后会创建一个StatementHandler对象，然后将必要的参数传递给StatementHandler，使用StatementHandler来完成对数据库的查询，最终返回List结果集。从以上代码可以看出，Executor的功能和作用是：
1. 根据传递的参数，完成SQL语句的动态解析，生成BoundSql对象，供StatementHandler使用；
2. 为查询创建缓存，以提高性能；
3. 创建JDBC的Statement连接对象，床底给StatementHandler对象，返回List查询结果。

### 1.2.4 StatementHandler接口
StatementHandler接口主要完成两个工作：
1. 对于JDBC的PreparedStatement类型的对象，创建的过程中，我们使用的是SQL语句字符串会包含若干个？占位符。后面介绍如何对占位符设值，StatementHandler通过parameterize()方法对Statement进行设值；
2. StatementHandler通过List query(Statement statement, ResultHandler resultHandler)方法来完成执行Statement和将Statement对象返回resultSet封装成List。</br>

进入到StatementHandler的parameterize(statement)方法的实现：
```java
    @Override
    public void parameterize(Statement statement) throws SQLException {
        //使用ParameterHandler对象来完成对Statement的设值
        parameterHandler.setParameters((PreparedStatement) statement);
    }
    
        @SuppressWarnings("Duplicates")
    @Override
    public void setParameters(PreparedStatement ps) {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        // 遍历 ParameterMapping 数组
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            for (int i = 0; i < parameterMappings.size(); i++) {
                // 获得 ParameterMapping 对象
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    // 获得值
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    if (boundSql.hasAdditionalParameter(propertyName)) { // issue #448 ask first for additional params
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else {
                        MetaObject metaObject = configuration.newMetaObject(parameterObject);
                        value = metaObject.getValue(propertyName);
                    }
                    // 获得 typeHandler、jdbcType 属性
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    JdbcType jdbcType = parameterMapping.getJdbcType();
                    if (value == null && jdbcType == null) {
                        jdbcType = configuration.getJdbcTypeForNull();
                    }
                    // 设置 ? 占位符的参数
                    try {
                        typeHandler.setParameter(ps, i + 1, value, jdbcType);
                    } catch (TypeException | SQLException e) {
                        throw new TypeException("Could not set parameters for mapping: " + parameterMapping + ". Cause: " + e, e);
                    }
                }
            }
        }
    }
```
从以上代码可以看出，StatementHandler的parameterize(Statement) 方法调用了ParameterHandlerጱsetParameters(statement) 方法，ParameterHandlerጱsetParameters(Statement)根据我们输入的参数，对statement对象的? 占位符进行赋值。进入到StatementHandler 的List query(Statement statement, ResultHandler resultHandler)方法的实现：
```Java
    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        PreparedStatement ps = (PreparedStatement) statement;
        // 1、执行查询
        ps.execute();
        // 2、交由ResultHandler处理返回结果
        return resultSetHandler.handleResultSets(ps);
    }
```
## 1.3 封装返回结果集
从query()方法我们可以看出，StatementHandler的List query(Statement statement, ResultHandler resultHandler)方法的实现，是调用了ResultSetHandler的handleResultSets(Statement) 方法。ResultSetHandler的handleResultSets(Statement) 方法会将Statement语句执行后得到的resultSet结果集转换成List结果集。
```java
    // 处理 {@link java.sql.ResultSet} 结果集
    @Override
    public List<Object> handleResultSets(Statement stmt) throws SQLException {
        ErrorContext.instance().activity("handling results").object(mappedStatement.getId());

        // 多 ResultSet 的结果集合，每个 ResultSet 对应一个 Object 对象。而实际上，每个 Object 是 List<Object> 对象。
        // 在不考虑存储过程的多 ResultSet 的情况，普通的查询，实际就一个 ResultSet ，也就是说，multipleResults 最多就一个元素。
        final List<Object> multipleResults = new ArrayList<>();

        int resultSetCount = 0;
        // 获得首个 ResultSet 对象，并封装成 ResultSetWrapper 对象
        ResultSetWrapper rsw = getFirstResultSet(stmt);

        // 获得 ResultMap 数组
        // 在不考虑存储过程的多 ResultSet 的情况，普通的查询，实际就一个 ResultSet ，也就是说，resultMaps 就一个元素。
        List<ResultMap> resultMaps = mappedStatement.getResultMaps();
        int resultMapCount = resultMaps.size();
        validateResultMapsCount(rsw, resultMapCount); // 校验
        while (rsw != null && resultMapCount > resultSetCount) {
            // 获得 ResultMap 对象
            ResultMap resultMap = resultMaps.get(resultSetCount);
            // 处理 ResultSet ，将结果添加到 multipleResults 中
            handleResultSet(rsw, resultMap, multipleResults, null);
            // 获得下一个 ResultSet 对象，并封装成 ResultSetWrapper 对象
            rsw = getNextResultSet(stmt);
            // 清理
            cleanUpAfterHandlingResultSet();
            // resultSetCount ++
            resultSetCount++;
        }

        // 因为 `mappedStatement.resultSets` 只在存储过程中使用，本系列暂时不考虑，忽略即可
        String[] resultSets = mappedStatement.getResultSets();
        if (resultSets != null) {
            while (rsw != null && resultSetCount < resultSets.length) {
                ResultMapping parentMapping = nextResultMaps.get(resultSets[resultSetCount]);
                if (parentMapping != null) {
                    String nestedResultMapId = parentMapping.getNestedResultMapId();
                    ResultMap resultMap = configuration.getResultMap(nestedResultMapId);
                    handleResultSet(rsw, resultMap, null, parentMapping);
                }
                rsw = getNextResultSet(stmt);
                cleanUpAfterHandlingResultSet();
                resultSetCount++;
            }
        }

        // 如果是 multipleResults 单元素，则取首元素返回
        return collapseSingleResultList(multipleResults);
    }
```
# 2 mapper代理方式
回顾一下Mapper代理方式的写法：
```java
    public static void main(String[] args) {
        // 前三步相同
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = factory.openSession();

        // 这里不再调用SqlSession的API，而是获取了接口对象，调用接口中的方法
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.getUserByName("tom");
    }
```
思考一个问题，通常的Mapper接口我们都没有写实现类却可以正常使用，这是为什么呢？答案就是：**动态代理**。</br>

开始之前介绍一下MyBatis初始化时对接口的处理：MapperRegistry是Configuration中的一个属性，它内部维护一个HashMap用于存放mapper接口的工厂类，每个接口对应一个工厂类。mappers中可以配置接口的包路径，或者某个具体的接口类。
```xml
<mappers>
 <mapper class="com.silver.mapper.UserMapper"/>
 <package name="com.silver.mapper"/>
</mappers>
```
当解析mappers标签时，它会判断如果解析到的是mapper配置文件时，会再将对应配置文件中的增删改查标签一一封装成MappedStatement对象，存入mappedStatements中。当解析到的是接口时，会建此接口对应的MapperProxyFactory对象，存入HashMap中，key为接口的字节码对象，value为此接口对应的MapperProxyFactory对象。
## 2.1 mapper动态代理
进入sqlSession.getMapper(UserMapper.class)中：
```java
    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }
    
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        // 获得 MapperProxyFactory 对象
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        // 不存在，则抛出 BindingException 异常
        if (mapperProxyFactory == null) {
            throw new BindingException("Type " + type + " is not known to the MapperRegistry.");
        }
        /// 通过动态代理工厂生成实例。
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new BindingException("Error getting mapper instance. Cause: " + e, e);
        }
    }
    
    //MapperProxyFactory类中的newInstance方法
    public T newInstance(SqlSession sqlSession) {
        // 创建了JDK动态代理的invocationHandler接口的实现类mapperProxy
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface, methodCache);
        // 调用了重载方法
        return newInstance(mapperProxy);
    }
    
    @SuppressWarnings("unchecked")
    protected T newInstance(MapperProxy<T> mapperProxy) {

        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
```
## 2.2 invoke()方法
在动态代理返回了实例后，我们就可以直接调用mapper类中的方法了，但代理对象调用方法，真正执行的是在MapperProxy中的invoke方法中：
```java
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            // 如果是 Object 定义的方法，直接调用
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);

            } else if (isDefaultMethod(method)) {
                return invokeDefaultMethod(proxy, method, args);
            }
        } catch (Throwable t) {
            throw ExceptionUtil.unwrapThrowable(t);
        }
        // 获得 MapperMethod 对象
        final MapperMethod mapperMethod = cachedMapperMethod(method);
        // 重点在这：MapperMethod最终调用了执行的方法
        return mapperMethod.execute(sqlSession, args);
    }
    
    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result;
        //判断mapper中的方法类型，最终调用的还是SqlSession中的方法
        switch (command.getType()) {
            case INSERT: {
                // 转换参数
                Object param = method.convertArgsToSqlCommandParam(args);
                // 执行 INSERT 操作
                // 转换 rowCount
                result = rowCountResult(sqlSession.insert(command.getName(), param));
                break;
            }
            case UPDATE: {
                // 转换参数
                Object param = method.convertArgsToSqlCommandParam(args);
                // 转换 rowCount
                result = rowCountResult(sqlSession.update(command.getName(), param));
                break;
            }
            case DELETE: {
                // 转换参数
                Object param = method.convertArgsToSqlCommandParam(args);
                // 转换 rowCount
                result = rowCountResult(sqlSession.delete(command.getName(), param));
                break;
            }
            case SELECT:
                // 无返回，并且有 ResultHandler 方法参数，则将查询的结果，提交给 ResultHandler 进行处理
                if (method.returnsVoid() && method.hasResultHandler()) {
                    executeWithResultHandler(sqlSession, args);
                    result = null;
                // 执行查询，返回列表
                } else if (method.returnsMany()) {
                    result = executeForMany(sqlSession, args);
                // 执行查询，返回 Map
                } else if (method.returnsMap()) {
                    result = executeForMap(sqlSession, args);
                // 执行查询，返回 Cursor
                } else if (method.returnsCursor()) {
                    result = executeForCursor(sqlSession, args);
                // 执行查询，返回单个对象
                } else {
                    // 转换参数
                    Object param = method.convertArgsToSqlCommandParam(args);
                    // 查询单条
                    result = sqlSession.selectOne(command.getName(), param);
                    if (method.returnsOptional() &&
                            (result == null || !method.getReturnType().equals(result.getClass()))) {
                        result = Optional.ofNullable(result);
                    }
                }
                break;
            case FLUSH:
                result = sqlSession.flushStatements();
                break;
            default:
                throw new BindingException("Unknown execution method for: " + command.getName());
        }
        // 返回结果为 null ，并且返回类型为基本类型，则抛出 BindingException 异常
        if (result == null && method.getReturnType().isPrimitive() && !method.returnsVoid()) {
            throw new BindingException("Mapper method '" + command.getName()
                    + " attempted to return null from a method with a primitive return type (" + method.getReturnType() + ").");
        }
        // 返回结果
        return result;
    }
```
