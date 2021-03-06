## MySQL
[toc]
### 1、MySQL体系架构
MySQL Server架构自顶向下分为网络连接层、服务层、存储引擎层和系统文件层。

1.1 网络连接层
- 客户端连接器：提供与MySQL服务器建立连接的支持。

1.2 服务层

服务层是MySQL的核心，主要包括连接池、SQL接口、解析器、查询优化器、缓存和系统管理和控制工具。
- 连接池：负责存储和管理客户端与数据库的连接，一个线程负责一个连接；
- 系统管理和控制工具：备份恢复、安全管理、集群管理等；
- SQL接口：用于接受客户端发送的各种SQL命令，并且返回用户需要查询的结果；
- 解析器：负责将请求的SQL解析生成一个“解析树”，然后根据MySQL的规则检查解析树是否合法；
- 优化器：当解析树通过语法检查后，将由优化器将其转化成执行计划，然后与存储引擎交互；
- 缓存：缓存机制由一系列小缓存组成的。比如表缓存、记录缓存、权限缓存、引擎缓存等。如果查询缓存有命中的查询结果，查询语句就可以直接去查询缓存中取数据。
- 
1.3 存储引擎层

存储引擎负责MySQL中数据的存储与提取，与底层系统文件进行交互。MySQL存储引擎是插件式的，服务器中的查询执行引擎通过接口与存储引擎进行通信，接口屏蔽了不同存储引擎之间的差异。
1.4 系统文件层
该层负责将数据库的数据日志存储在文件系统之上，并完成与存储引擎的交互，是文件的物理存储层。主要包含日志文件，数据文件，配置文件，pid文件，socket文件等。

### 2、MySQL运行机制
2.1 **建立连接**（Connectors&Connection Pool），通过客户端/服务器通信协议与MySQL建立连接。MySQL客户端与服务端的通信方式是 “ 半双工 ”。对于每一个MySQL的连接，时刻都有一个线程状态来标识这个连接正在做什么。
通讯机制：
- 全双工：能同时发送和接收数据，例如平时打电话。
- 半双工：指的某一时刻，要么发送数据，要么接收数据，不能同时。例如早期对讲机
- 单工：只能发送数据或只能接收数据。例如单行道

2.2 **查询缓存**（Cache&Buffer），这是MySQL的一个可优化查询的地方，如果开启了查询缓存且在查询缓存过程中查询到完全相同的SQL语句，则将查询结果直接返回给客户端；如果没有开启查询缓存或者没有查询到完全相同的SQL语句则会由解析器进行语法语义解析，并生成“解析树”。

2.3 **解析器**（Parser）将客户端发送的SQL进行语法解析，生成"解析树"。预处理器根据一些MySQL规则进一步检查“解析树”是否合法，例如这里将检查数据表和数据列是否存在，还会解析名字和别名，看看它们是否有歧义，最后生成新的“解析树”。

2.4 **查询优化器**（Optimizer）根据“解析树”生成最优的执行计划。MySQL使用很多优化策略生成最优的执行计划，可以分为两类：静态优化（编译时优化）、动态优化（运行时优化）。

2.5 **查询执行引擎**负责执行 SQL 语句，此时查询执行引擎会根据 SQL 语句中表的存储引擎类型，以及对应的API接口与底层存储引擎缓存或者物理文件的交互，得到查询结果并返回给客户端。若开启用查询缓存，这时会将SQL语句和结果完整地保存到查询缓存（Cache&Buffer）中，以后若有相同的SQL语句执行则直接返回结果。如果开启了查询缓存，先将查询结果做缓存操作返回结果过多，采用增量模式返回

### 3、MySQL存储引擎
存储引擎在MySQL的体系架构中位于第三层，负责MySQL中的数据的存储和提取，是与文件打交道的子系统，它是根据MySQL提供的文件访问层抽象接口定制的一种文件访问机制，这种机制就叫作存储引擎。

3.1 InnoDB和MyISAM对比
InnoDB和MyISAM是使用MySQL时最常用的两种引擎类型，我们重点来看下两者区别。

- 事务和外键
InnoDB支持事务和外键，具有安全性和完整性，适合大量insert或update操作
MyISAM不支持事务和外键，它提供高速存储和检索，适合大量的select查询操作

- 锁机制
InnoDB支持行级锁，锁定指定记录。基于索引来加锁实现。
MyISAM支持表级锁，锁定整张表。

- 索引结构
InnoDB使用聚集索引（聚簇索引），索引和记录在一起存储，既缓存索引，也缓存记录。
MyISAM使用非聚集索引（非聚簇索引），索引和记录分开。

- 并发处理能力
MyISAM使用表锁，会导致写操作并发率低，读之间并不阻塞，读写阻塞。
InnoDB读写阻塞可以与隔离级别有关，可以采用多版本并发控制（MVCC）来支持高并发

- 存储文件
InnoDB表对应两个文件，一个.frm表结构文件，一个.ibd数据文件。InnoDB表最大支持64TB；
MyISAM表对应三个文件，一个.frm表结构文件，一个MYD表数据文件，一个.MYI索引文件。从MySQL5.0开始默认限制是256TB。

3.2 Undo Log
- 介绍
Undo：意为撤销或取消，以撤销操作为目的，返回指定某个状态的操作。

Undo Log：数据库事务开始之前，会将要修改的记录存放到 Undo 日志里，当事务回滚时或者数据库崩溃时，可以利用 Undo日志，撤销未提交事务对数据库产生的影响。

Undo Log产生和销毁：Undo Log在事务开始前产生；事务在提交时，并不会立刻删除undo
log，innodb会将该事务对应的undo log放入到删除列表中，后面会通过后台线程purge thread进行回收处理。Undo Log属于逻辑日志，记录一个变化过程。例如执行一个delete，undolog会记
录一个insert；执行一个update，undolog会记录一个相反的update。

Undo Log存储：undo log采用段的方式管理和记录。在innodb数据文件中包含一种rollback segment回滚段，内部包含1024个undo log segment。

- 作用
实现事务的原子性
Undo Log 是为了实现事务的原子性而出现的产物。事务处理过程中，如果出现了错误或者用户执行了 ROLLBACK 语句，MySQL 可以利用 Undo Log中的备份将数据恢复到事务开始之前的状态。

实现多版本并发控制（MVCC）
Undo Log 在 MySQL InnoDB 存储引擎中用来实现多版本并发控制。事务未提交之前，Undo Log保存了未提交之前的版本数据，Undo Log中的数据可作为数据旧版本快照供其他并发事务进行快照读。

&&&&图

事务A手动开启事务，执行更新操作，首先会把更新命中的数据备份到 Undo Buffer 中。
事务B手动开启事务，执行查询操作，会读取 Undo 日志数据返回，进行快照读

3.3 Redo Log
- 介绍
Redo：顾名思义就是重做。以恢复操作为目的，在数据库发生意外时重现操作。

Redo Log：指事务中修改的任何数据，将最新的数据备份存储的位置（Redo Log），被称为重做日志。

Redo Log 的生成和释放：随着事务操作的执行，就会生成Redo Log，在事务提交时会将产生Redo Log写入Log Buffer，并不是随着事务的提交就立刻写入磁盘文件。等事务操作的脏页写入到磁盘之后，Redo Log 的使命也就完成了，Redo Log占用的空间就可以重用（被覆盖写入）。

- 工作原理
Redo Log 是为了实现事务的持久性而出现的产物。防止在发生故障的时间点，尚有脏页未写入表的 IBD 文件中，在重启 MySQL 服务的时候，根据 Redo Log进行重做，从而达到事务的未入磁盘数据进行持久化这一特性。

3.4 Binlog
Binlog记录模式

Redo Log 是属于InnoDB引擎所特有的日志，而MySQL Server也有自己的日志，即 Binary
log（二进制日志），简称Binlog。Binlog是记录所有数据库表结构变更以及表数据修改的二进制
日志，不会记录SELECT和SHOW这类操作。Binlog日志是以事件形式记录，还包含语句所执行的
消耗时间。开启Binlog日志有以下两个最重要的使用场景。
主从复制：在主库中开启Binlog功能，这样主库就可以把Binlog传递给从库，从库拿到
Binlog后实现数据恢复达到主从数据一致性。
数据恢复：通过mysqlbinlog工具来恢复数据。

3.5 Redo Log和Binlog区别
- Redo Log是属于InnoDB引擎功能，Binlog是属于MySQL Server自带功能，并且是以二进制
文件记录。
- Redo Log属于物理日志，记录该数据页更新状态内容，Binlog是逻辑日志，记录更新过程。
- Redo Log日志是循环写，日志空间大小是固定，Binlog是追加写入，写完一个写下一个，不
会覆盖使用。
- Redo Log作为服务器异常宕机后事务数据自动恢复使用，Binlog可以作为主从复制和数据恢
复使用。Binlog没有自动crash-safe能力。

### 4、MySQL索引原理
1. 索引类型

索引可以提升查询速度，会影响where查询，以及order by排序。MySQL索引类型如下：
 - 从索引存储结构划分：B Tree索引、Hash索引、FULLTEXT全文索引、R Tree索引
 - 从应用层次划分：普通索引、唯一索引、主键索引、复合索引
 - 从索引键值类型划分：主键索引、辅助索引（二级索引）
 - 从数据存储和索引键值逻辑关系划分：聚集索引（聚簇索引）、非聚集索引（非聚簇索引）

2. 普通索引

这是最基本的索引类型，基于普通字段建立的索引，没有任何限制。

3. 唯一索引

与"普通索引"类似，不同的就是：索引字段的值必须唯一，但允许有空值。在创建或修改表时追加唯一约束，就会自动创建对应的唯一索引。

4. 复合索引
单一索引是指索引列为一列的情况，即新建索引的语句只实施在一列上；用户可以在多个列上建立索
引，这种索引叫做组复合索引（组合索引）。复合索引可以代替多个单一索引，相比多个单一索引复合
索引所需的开销更小。

索引同时有两个概念叫做窄索引和宽索引，窄索引是指索引列为1-2列的索引，宽索引也就是索引列超
过2列的索引，设计索引的一个重要原则就是能用窄索引不用宽索引，因为窄索引往往比组合索引更有
效。