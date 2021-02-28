[toc]

# MyBatis源码分析之整体架构

## 1 ORM简介
ORM全称：Object/Relation Mapping 对象-关系映射</br>

在日常开发过程中，我们都是使用**面向对象**的思维实现业务逻辑，但是在设计数据库表或者操作数据库记录，则需要通过**关系型**思维方式思考问题。应用程序与关系型数据库之间进行交互时，数据在对象和关系结构中的表、列、字段等之间进行转换。

### 1.1 JDBC
JDBC全称：Java DataBase Connectivity，Java数据库链接</br>

JDBC是每个学Java Web的开发人员都会学习的，它是Java与数据库交互的统一API，实际上它分为两组API，一组是面向Java应用程序开发人员的API，另一组是面向数据库驱动程序开发人员的API。前者是一个标准的Java API且独立于各个厂家的数据库实现，后者是数据库驱动程序开发人员用于编写数据库驱动，是前者的底层支持，一般与具体的数据产品相关。</br>

#### 1.1.1 传统JDBC的操作步骤
1. 注册数据库驱动类；
2. 通过DriverManager获取数据库链接；
3. 通过数据库链接创建Statement、PreparedStatement对象；
4. 通过Statement、PreparedStatement对象执行SQL语句，得到ResultSet对象；
5. 解析ResultSet中的数据，转换成JavaBean对象；
6. 关闭ResultSet、Statement对象以及数据库链接，释放资源。

JDBC代码实例：

```Java
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8", "root", "root");
            // 定义sql语句，？表示占位符
            String sql = "select * from user where username = ?";
            // 获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数，从1开始
            preparedStatement.setString(1, "tom");
            // 向数据库发起执行sql请求
            resultSet = preparedStatement.executeQuery();
            // 遍历结果集
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                // 封装返回对象
                user.setId(id);
                user.setUsername(username);
            }
            System.out.println(user);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
```
#### 1.1.2 传统JDBC操作存在的问题
1. **数据库链接频繁创建、销毁**造成系统资源浪费；
2. **重复性代码**很多，不利于维护；
3. SQL语句存在**硬编码**，不利于维护，实际开发中SQL变化的可能较大，SQL变化会导致Java代码变化；
4. 使用Statement想占位符中设置参数值存在**硬编码**，实际开发中where条件变化会导致Java代码变化；
5. 对结果集解析存在**硬编码**（查询列名），SQL变化导致解析代码变化，不利于维护。

### 1.2 ORM框架的出现
为了解决传统JDBC开发中存在的上诉问题，ORM框架应运而生。如图所示，**ORM框架的主要功能就是根据映射配置文件，完成数据在对象模型与关系模型之间的映射，同时也屏蔽了上诉重复的代码，只暴露简单的API供开发人员使用。**

```
graph LR
Java程序-->映射配置文件
映射配置文件-->Java程序
映射配置文件-->数据库
数据库-->映射配置文件
```

解决方案：
1. 数据库链接频繁创建、销毁：**连接池**
2. SQL语句和参数硬编码：**配置文件**
3. 手动封装结果集：**反射、内省**

## 2 常见持久层框架
### 2.1 Hibernate
Hibernate通过hbm.xml映射文件维护Java类与数据库表的映射关系。
- **优点**
1. Hibernate通过其简洁的API以及统一的HQL语句，帮助上层程序屏蔽掉底层数据库的差异。使得应用程序可以在MySql或Oracle之间无感切换；
2. Hibernate的API没有侵入性，业务逻辑不需要继承Hibernate任何接口；
3. Hibernate默认了一级缓存和二级缓存，这有利于提高系统性能，缓解数据库压力；
4. 支持透明的持久化、延迟加载、由对象模型生成数据库表等。
- **缺点**
1. 数据库本身有自己的组织方式，并不是数据库中所有的概念都能在面向对象的世界里找到合适的映射，例如：索引、存储过程，函数等；
2. 我们很难修改Hibernate生成的SQL语句，当数据量较大、数据库结构比较复杂的时候，Hibernate生成的SQL语句会非常复杂，而且要让生成的SQL语句使用正确的索引也比较困难，着就会导致大量慢查询的出现；
3. Hibernate对批处理的支持并不是很友好。

### 2.2 JPA
**JPA仅仅是一个持久化的规范，它并没有提供具体实现**。其他持久化厂商会提供JPA规范的具体实现，例如：Hibernate、EclipseLink等都提供了JPA规范的具体实现。

### 2.3 Spring JDBC
1. 严格来说，Spring JDBC并不能算是一个ORM框架，它仅仅是使用模板模式对原生JDBC进行了一层非常薄的封装。使用Spring JDBC可以帮助开发人员屏蔽创建数据库链接对、Statement对象、异常处理以及事务管理的重复性代码，提高开发效率；
2. Spring JDBC中没有映射文件、对象查询语句和缓存等概念，而是直接执行原生SQL语句；
3. 是Spring Framework的基础模块之一，天生与Spring无缝集成。

### 2.4 MyBatis
1. MyBatis和前面几种持久化框架一样，可以帮助开发人员屏蔽底层重复性的原生JDBC代码；
2. 相较于Hibernate，MyBatis更加轻量级，可控性更高，开发人员可以在映射配置文件中自己编写待执行的SQL语句，这样就可以对SQL进行定制的优化，选择合适的索引以及指定查询返回的列；
3. MyBatis还提供了强大的**动态SQL**功能。我们只需要要映射配置文件中编写好动态SQL语句，MyBatis就根据执行时传入的实际参数拼写出完整的、可执行的SQL语句。
 
### 2.5 几种框架的比较
1. 从性能角度来看：Hibernate生成的SQL语句难以优化，Spring JDBC和MyBatis直接使用原生SQL语句，优化空间较大；MyBatis和Hibernate有设计良好的缓存机制；三者都可以与第三方数据源配合使用；
2. 从可移植性角度来看：Hibernate帮助开发人员屏蔽的底层数据库的方言，而Spring JDBC和MyBatis在该方面没有做支持，但是实践中很少有项目会频繁切换底层使用的数据库产品，所以这一点不是很重要；
3. 从开发效率的角度来看：Hibernate和MyBatis都提供了XML映射配置文件和注解两种方式实现映射，Spring JDBC则是通过ORM化的Callback的方式进行映射。

## 3 MyBatis整体架构
MyBatis的整体架构分为三层，分别是基础支持层、核心处理层和接口层。
- **基础支持层**：负责最基础的功能支撑，包括链接管理、事务管理、配置加载和缓存处理等，这些都是公用的东西，将它们抽取出来成为最基础组件。为上层逻辑提供支撑。
- **核心处理层**：负责具体的SQL查找、SQL解析、SQL执行和结果集解析等。它的主要功能是根据调用的请求参数完成一次数据库操作。
- **接口层**：提供给外部使用的API接口，开发人员通过这些接口来操作数据库。接口层收到请求就会调用核心处理层来完成具体的数据处理。

MyBatis和数据库交互有两种方式：
1. 使用MyBatis提供的原生API；
2. 通过Mapper接口，**代理**方式。

![](https://img2020.cnblogs.com/blog/1527720/202008/1527720-20200826164613586-273661913.png)

### 3.1 基础支持层
基础支持层包含整个MyBatis的基础模块，这些模块为核心处理层的功能提供了良好的支撑。
- **反射模块** </br>
该模块对Java原生的反射进行了良好的封装，提供了更加简洁易用的API，方便上层使用，并且对反射操作进行了一系列优化，例如缓存了类的元数据，提高了反射操作的性能。
- **类型转换模块** </br>
MyBatis为了简化配置文件提供了别名机制，该机制是类型转换模块的主要功能之一。类型转换模块的另一个功能是实现了JDBC数据类型和Java数据类型之间的转换。在为SQL语句绑定实参时，会将数据由Java类型转换为JDBC类型；在处理结果集时，会将数据由JDBC类型转换为Java类型。
- **日志模块** </br>
提供详细的日志输出以及集成多种第三方日志框架。
- **资源加载模块** </br>
资源加载模块主要是对类加载器进行封装，确定类加载器的使用顺序，并提供了加载类文件以及其他资源文件的功能。
- **解析器模块** </br>
解析器模块有两个功能：一个功能是对XPath进行封装，为MyBatis初始化时解析mybatis-config.xml配置文件以及映射配置文件提供支持；另一个功能是为处理动态SQL语句中的占位符提供支持。
- **数据源模块** </br>
MyBatis自身提供了相应的数据源实现，也提供了与第三方数据源集成的接口，这些功能都位于数据源模块中。
- **事务管理** </br>
MyBatis对数据库中的事务进行了抽象，其自身提供了相应的事务接口的简单实现。在很多场景中，MyBatis会与Spring框架集成，并由Spring框架管理事务。
- **缓存模块** </br>
MyBatis中提供了一级缓存和二级缓存，这两级缓存都是依赖于基础支持层中的缓存模块实现的。值得注意的是，MyBatis中自带的这两级缓存于MyBatis以及整个应用是运行在同一个JVM中的，共享同一块堆内存。如果这两级缓存中的数据量较大时，则可能影响系统中其他功能的运行，所以需要缓存大量数据时，优先考虑使用Redis、Mencache等缓存产品，MyBatis也提供了和Redis集成的接口。

- **Binding模块** </br>
Binding模块将用户自定义的Mapper接口与映射配置文件关联起来，系统可以通过调用自定义Mapper接口中的方法执行相应的SQL语句。值得注意的是，我们无须为自定义Mapper接口提供实现，MyBatis会自动为其创建动态代理对象。在有些场景中，自定义Mapper接口可以完全替代映射配置文件，但有的映射规则和SQL语句的定义还是写在映射配置文件里比较方便，比如动态SQL的定义。

从MyBatis源码的package命名上就可以很清楚的知道对应模块的代码实现在哪里，结构清晰明了。
![](https://img2020.cnblogs.com/blog/1527720/202008/1527720-20200826164535843-1829421260.png)

### 3.2 核心处理层
核心处理层中实现了MyBatis的核心处理流程，起住包括MyBatis的初始化以及完成一次数据库操作的涉及的全部流程。用我自己的话来理解就是，基础支持层就是我们日常开发中的各种utils，而核心处理层则是我们的业务逻辑代码。后续文章着重分析核心处理层的源码。
- **配置解析** </br>
在MyBatis初始化过程中，会加载mybatis-config.xml配置文件、映射配置文件以及Mapper接口中的注解信息，解析后的配置信息会形成相应的对象并保存到**Configuration**对象中。
- **SQL解析与scripting模块** </br>
根据用户传入的实参，解析映射文件中定义的动态SQL节点，并形成数据库可执行的SQL语句。之后会处理SQL语句中的占位符，绑定用户传入的实参。
- **SQL执行** </br>
SQL语句的执行涉及多个组件，其中比较重要的是Executor、SatementHandler、ParameterHandler和ResultSetHandler。Executor主要负责维护一级缓存和二级缓存，并提供事务管理相关的操作，它会将数据库相关操作委托给StatementHandler完成。StatementHandler首先通过ParameterHandler完成SQL语句的实参绑定，然后通过java.sql.Statement对象执行SQL语句并得到结果集，最后通过ResultSetHandler完成结果集解析并返回。
![](https://img2020.cnblogs.com/blog/1527720/202008/1527720-20200826164632016-84851393.png)

- **插件** </br>
MyBatis提供的插件接口，可以通过添加用户自定义插件的方式对MyBatis进行扩展。

### 3.3 接口层
接口层相对简单，其核心是SqlSession接口，该接口中定义了MyBatis暴露给应用程序调用的API，也就是业务系统与MyBatis交互的桥梁。接口层在收到调用请求时，会调用核心处理层的相应的模块来完成具体的数据库操作。

## 4 总结
本篇首先介绍了ORM框架出现的背景。然后介绍了Hibernate、JPA、Spring JDBC和MyBatis这些常见持久层框架的优缺点。最后讲了下MyBatis的整体架构，并简单介绍了MyBatis的基础支持层、核心处理层和接口层中的主要模块。后续文章重点分析核心处理层中的源码实现逻辑。