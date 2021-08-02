### 🚲概述
在日常工作中，我们会有时会开慢查询去记录一些执行时间比较久的SQL语句，找出这些SQL语句并不意味着完事了，些时我们常常用到explain这个命令来查看一个这些SQL语句的执行计划，查看该SQL语句有没有使用上了索引，有没有做全表扫描，这都可以通过explain命令来查看。所以我们深入了解MySQL的基于开销的优化器，还可以获得很多可能被优化器考虑到的访问策略的细节，以及当运行SQL语句时哪种策略预计会被优化器采用。
```sql
-- 实际SQL，查找用户名为Jefabc的员工
select * from emp where name = 'Jefabc';
-- 查看SQL是否使用索引，前面加上explain即可
explain select * from emp where name = 'Jefabc';
```
![](https://cdn.nlark.com/yuque/0/2021/png/2548312/1626509358040-b8a79c29-90f3-44e4-bb02-32a0ae6fb8d7.png#align=left&display=inline&height=47&margin=%5Bobject%20Object%5D&name=&originHeight=47&originWidth=746&size=0&status=done&style=none&width=746)
**Expain出来的信息有10列：**

| 列名 | 描述 |
| --- | --- |
| id | 选择标识符 |
| select_type | 表示查询的类型 |
| table | 输出结果集的表 |
| partitions | 匹配的分区 |
| type | 表示表的连接类型 |
| possible_keys | 表示查询时，可能使用的索引 |
| key | 表示实际使用的索引 |
| key_len | 索引字段的长度 |
| ref | 列与索引的比较 |
| rows
 | 扫描出的行数(估算的行数) |
| filtered | 按表条件过滤的行百分比 |
| Extra | 执行情况的描述和说明 |

### 1 id
SELECT识别符。这是SELECT的查询序列号。


我的理解是SQL执行的顺序的标识，SQL从大到小的执行

1. id相同时，执行顺序由上至下
1. 如果是子查询，id的序号会递增，id值越大优先级越高，越先被执行
1. id如果相同，可以认为是一组，从上往下顺序执行；在所有组中，id值越大，优先级越高，越先执行。
### 2 select_type
示查询中每个select子句的类型

| 类型 | 描述 |
| --- | --- |
| SIMPLE | 简单SELECT，不使用UNION或子查询等 |
| PRIMARY | 子查询中最外层查询，查询中若包含任何复杂的子部分，最外层的select被标记为PRIMARY |
| UNION | UNION中的第二个或后面的SELECT语句 |
| DEPENDENT UNION | UNION中的第二个或后面的SELECT语句，取决于外面的查询 |
| UNION RESULT | UNION的结果，union语句中第二个select开始后面所有select |
| SUBQUERY | 子查询中的第一个SELECT，结果不依赖于外部查询 |
| DEPENDENT SUBQUERY | 子查询中的第一个SELECT，依赖于外部查询 |
| DERIVED | 派生表的SELECT, FROM子句的子查询 |
| UNCACHEABLE SUBQUERY | 一个子查询的结果不能被缓存，必须重新评估外链接的第一行 |

### 3 table
显示这一步所访问数据库中表名称（显示这一行的数据是关于哪张表的），有时不是真实的表名字，可能是简称，例如上面的e，d，也可能是第几步执行的结果的简称。
### 4 type
对表访问方式，表示MySQL在表中找到所需行的方式，又称“访问类型”。**至少要达到range级别，要求是ref。**
常用的类型有：** ALL、index、range、 ref、eq_ref、const、system、NULL（从左到右，性能从差到好）**

| 类型 | 描述 |
| --- | --- |
| ALL | 最坏的情况,全表扫描 |
| index | 和全表扫描一样。只是扫描表的时候按照索引次序进行而不是行，先读索引，再读实际的行。主要优点就是避免了排序, 但是开销仍然非常大。如在Extra列看到Using index，说明正在使用覆盖索引，只扫描索引的数据，它比按索引次序全表扫描的开销要小很多。 |
| range | 范围扫描，一个有限制的索引扫描。key 列显示使用了哪个索引。当使用=、 <>、>、>=、<、<=、IS NULL、<=>、BETWEEN 或者 IN 操作符,用常量比较关键字列时,可以使用 range。 |
| ref | 一种索引访问，它返回所有匹配某个单个值的行。此类索引访问只有当使用非唯一性索引或唯一性索引非唯一性前缀时才会发生。这个类型跟eq_ref不同的是，它用在关联操作只使用了索引的最左前缀，或者索引不是UNIQUE和PRIMARY KEY。ref可以用于使用=或<=>操作符的带索引的列。 |
| eq_ref | 最多只返回一条符合条件的记录。使用唯一性索引或主键查找时会发生 （高效） |
| const | 当确定最多只会有一行匹配的时候，MySQL优化器会在查询前读取它而且只读取一次，因此非常快。当主键放入where子句时，mysql把这个查询转为一个常量（高效） |
| system | 1、只有一条数据的系统表
2、或衍生表只能有一条数据的主查询 |
| NULL | MySQL在优化过程中分解语句，执行时甚至不用访问表或索引，例如从一个索引列里选取最小值可以通过单独索引查找完成。 |

### 5 possible_keys
指出MySQL能使用哪个索引在表中找到记录，查询涉及到的字段上若存在索引，则该索引将被列出，但不一定被查询使用（该查询可以利用的索引，如果没有任何索引显示 null）
**
该列完全独立于EXPLAIN输出所示的表的次序。这意味着在possible_keys中的某些键实际上不能按生成的表次序使用。
如果该列是NULL，则没有相关的索引。在这种情况下，可以通过检查WHERE子句看是否它引用某些列或适合索引的列来提高你的查询性能。如果是这样，创造一个适当的索引并且再次用EXPLAIN检查查询。
### 6 Key
key列显示MySQL实际决定使用的键（索引），必然包含在possible_keys中。


如果没有选择索引，键是NULL。要想强制MySQL使用或忽视possible_keys列中的索引，在查询中使用FORCE INDEX、USE INDEX或者IGNORE INDEX。
### 7 key_len
表示索引中使用的字节数，可通过该列计算查询中使用的索引的长度（key_len显示的值为索引字段的最大可能长度，并非实际使用长度，即key_len是根据表定义计算而得，不是通过表内检索出的）


不损失精确性的情况下，长度越短越好。
[ken_len的计算](https://www.jb51.net/article/110381.htm)。
### 8 ref
列与索引的比较，表示上述表的连接匹配条件，即哪些列或常量被用于查找索引列上的值
### 9 rows
估算出结果集行数，表示MySQL根据表统计信息及索引选用情况，估算的找到所需的记录所需要读取的行数。
### 10 Extra
该列包含MySQL解决查询的详细信息,有以下几种情况：

| 类型 | 描述 |
| --- | --- |
| Using index | 此查询使用了覆盖索引(Covering Index)，即通过索引就能返回结果，无需回表。 |
| Using where | 不用读取表中所有信息，仅通过索引就可以获取所需数据，这发生在对表的全部的请求列都是同一个索引的部分的时候，表示mysql服务器将在存储引擎检索行后再进行过滤 |
| Using temporary | 表示MySQL需要使用临时表来存储结果集，常见于排序和分组查询，常见 group by ; order by |
| Using filesort | 当Query中包含 order by 操作，而且无法利用索引完成的排序操作称为“文件排序” |
| Using join buffer | 该值强调了在获取连接条件时没有使用索引，并且需要连接缓冲区来存储中间结果。如果出现了这个值，那应该注意，根据查询的具体情况可能需要添加索引来改进能。 |
| Impossible where | 这个值强调了where语句会导致没有符合条件的行（通过收集统计信息不可能存在结果）。 |
| Select tables optimized away | 这个值意味着仅通过使用索引，优化器可能仅从聚合函数结果中返回一行 |
| No tables used | Query语句中使用from dual 或不含任何from子句 |



