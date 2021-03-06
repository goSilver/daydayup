## MySQL是怎么运行的
### 一、字符集和比较规则
1. `字符集`指的是某个字符范围的编码规则。
2. `比较规则`是针对某个字符集中的字符比较大小的一种规则。
3. 在MySQL中，一个字符集可以有若干种比较规则，其中有一个默认的比较规则，一个比较规则必须对应一个字符集。
4. 查看MySQL中查看支持的字符集和比较规则的语句如下：
```sql
SHOW (CHARACTER SET|CHARSET) [LIKE 匹配的模式];
SHOW COLLATION [LIKE 匹配的模式];
```
5. MySQL有四个级别的字符集和比较规则
- 服务器级别：`character_set_server`表示服务器级别的字符集，`collation_server`表示服务器级别的比较规则。
- 数据库级别：创建和修改数据库时可以指定字符集和比较规则
```sql
CREATE DATABASE 数据库名
    [[DEFAULT] CHARACTER SET 字符集名称]
    [[DEFAULT] COLLATE 比较规则名称];
  
ALTER DATABASE 数据库名
    [[DEFAULT] CHARACTER SET 字符集名称]
    [[DEFAULT] COLLATE 比较规则名称];
```
`character_set_database`表示当前数据库的字符集，`collation_database`表示当前默认数据库的比较规则，这两个系统变量是只读的，不能修改。如果没有指定当前默认数据库，则变量与相应的服务器级系统变量具有相同的值。
- 表级别：创建和修改表的时候指定表的字符集和比较规则
```sql
CREATE TABLE 表名 (列的信息)
    [[DEFAULT] CHARACTER SET 字符集名称]
    [COLLATE 比较规则名称]];
  
ALTER TABLE 表名
    [[DEFAULT] CHARACTER SET 字符集名称]
    [COLLATE 比较规则名称];
```
- 列级别：创建和修改列定义的时候可以指定该列的字符集和比较规则
```sql
CREATE TABLE 表名(
    列名 字符串类型 [CHARACTER SET 字符集名称] [COLLATE 比较规则名称],
    其他列...
);
  
ALTER TABLE 表名 MODIFY 列名 字符串类型 [CHARACTER SET 字符集名称] [COLLATE 比较规则名称];
```
6. 比较规则的作用通常体现比较字符串大小的表达式以及对某个字符串列进行排序中。
 
### 二、InnoDB记录存储结构
1. 页是MySQL中磁盘和内存交互的基本单位，也是MySQL是管理存储空间的基本单位。
2. 指定和修改行格式的语法如下：
```sql
CREATE TABLE 表名 (列的信息) ROW_FORMAT=行格式名称

ALTER TABLE 表名 ROW_FORMAT=行格式名称
```
3. InnoDB目前定义了4种行格式
- COMPACT行格式

![](https://user-gold-cdn.xitu.io/2019/3/12/169710e8fafc21aa?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

- Redundant行格式

![](https://user-gold-cdn.xitu.io/2019/3/12/169710e9ca9cbeb5?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

- Dynamic和Compressed行格式

4. 一个页一般是16KB，当记录中的数据太多，当前页放不下的时候，会把多余的数据存储到其他页中，这种现象称为行溢出。

### 三、InnoDB数据页结构
1. InnoDB为了不同的目的而设计了不同类型的页，我们把用于存放记录的页叫做数据页。

2. 一个数据页可以被大致划分为7个部分，分别是

- `File Header`，表示页的一些通用信息，占固定的38字节。
- `Page Header`，表示数据页专有的一些信息，占固定的56个字节。
- `Infimum` + `Supremum`，两个虚拟的伪记录，分别表示页中的最小和最大记录，占固定的26个字节。
- `User Records`：真实存储我们插入的记录的部分，大小不固定。
- `Free Space`：页中尚未使用的部分，大小不确定。
- `Page Directory`：页中的某些记录相对位置，也就是各个槽在页面中的地址偏移量，大小不固定，插入的记录越多，这个部分占用的空间越多。
- `File Trailer`：用于检验页是否完整的部分，占用固定的8个字节。

3. 每个记录的头信息中都有一个`next_record`属性，从而使页中的所有记录串联成一个单链表。

4. InnoDB会把页中的记录划分为若干个组，每个组的最后一个记录的地址偏移量作为一个槽，存放在`Page Directory`中，所以在一个页中根据主键查找记录是非常快的，分为两步：

- 通过二分法确定该记录所在的槽。

- 通过记录的`next_record`属性遍历该槽所在的组中的各个记录。

5. 每个数据页的`File Header`部分都有上一个和下一个页的编号，所以所有的数据页会组成一个双链表。

6. 为保证从内存中同步到磁盘的页的完整性，在页的首部和尾部都会存储页中数据的校验和和页面最后修改时对应的`LSN`值，如果首部和尾部的校验和和`LSN`值校验不成功的话，就说明同步过程出现了问题。

### 四、B+树索引
1. 每个索引都对应一棵B+树，B+树分为好多层，最下边一层是叶子节点，其余的是内节点。所有用户记录都存储在B+树的叶子节点，所有目录项记录都存储在内节点。

2. InnoDB存储引擎会自动为主键（如果没有它会自动帮我们添加）建立`聚簇索引`，聚簇索引的叶子节点包含完整的用户记录。

3. 我们可以为自己感兴趣的列建立`二级索引`，二级索引的叶子节点包含的用户记录由`索引列` + `主键`组成，所以如果想通过二级索引来查找完整的用户记录的话，需要通过回表操作，也就是在通过二级索引找到主键值之后再到聚簇索引中查找完整的用户记录。

4. B+树中每层节点都是按照索引列值从小到大的顺序排序而组成了双向链表，而且每个页内的记录（不论是用户记录还是目录项记录）都是按照索引列的值从小到大的顺序而形成了一个单链表。如果是联合索引的话，则页面和记录先按照联合索引前边的列排序，如果该列值相同，再按照联合索引后边的列排序。

5. 通过索引查找记录是从B+树的根节点开始，一层一层向下搜索。由于每个页面都按照索引列的值建立了`Page Directory`（页目录），所以在这些页面中的查找非常快。

### 五、B+树索引的使用
1. B+树索引在空间和时间上都有代价，所以没事儿别瞎建索引。

2. B+树索引适用于下边这些情况：

- 全值匹配
- 匹配左边的列
- 匹配范围值
- 精确匹配某一列并范围匹配另外一列
- 用于排序
- 用于分组
- 在使用索引时需要注意下边这些事项：

3. 只为用于搜索、排序或分组的列创建索引
- 为列的基数大的列创建索引
- 索引列的类型尽量小
- 可以只对字符串值的前缀建立索引
- 只有索引列在比较表达式中单独出现才可以适用索引
- 为了尽可能少的让`聚簇索引`发生页面分裂和记录移位的情况，建议让主键拥有`AUTO_INCREMENT`属性。
- 定位并删除表中的重复和冗余索引
- 尽量使用`覆盖索引`进行查询，避免`回表`带来的性能损耗。

### 五、单表访问方法（access method）
MySQL查询的执行方式大致分为以下两种：
- 使用全表扫描进行查询
- 使用索引进行查询
- - 针对主键或唯一二级索引的等值查询
- - 针对普通二级索引的等值查询
- - 针对索引列的范围查询
- - 直接扫描整个索引

1. const
通过**主键或者唯一二级索引**列来定位一条记录的访问方法定义为：`const`，意思是常数级别的，代价是可以忽略不计的。不过这种`const`访问方法只能在主键列或者唯一二级索引列和一个常数进行等值比较时才有效，如果主键或者唯一二级索引是由多个列构成的话，索引中的每一个列都需要与常数进行等值比较，这个`const`访问方法才有效（这是因为只有该索引中全部列都采用等值比较才可以定位唯一的一条记录）。

对于唯一二级索引来说，查询该列为`NULL`值的情况比较特殊，比如这样：
```sql
SELECT * FROM single_table WHERE key2 IS NULL;
```
因为唯一二级索引列并不限制 `NULL` 值的数量，所以上述语句可能访问到多条记录，也就是说
上边这个语句不可以使用`const`访问方法来执行。

2. ref
搜索条件为**二级索引**列与常数等值比较，采用二级索引来执行查询的访问方法称为：`ref`。普通二级索引并不限制索引列值的唯一性，所以可能找到多条对应的记录，也就是说使用二级索引来执行查询的代价取决于等值匹配到的二级索引记录条数。如果匹配的记录较少，则回表的代价还是比较低的。
- 二级索引列值为NULL的情况
不论是普通的二级索引，还是唯一二级索引，它们的索引列对包含`NULL`值的数量并不限制，所以我们采用`key IS NULL`这种形式的搜索条件最多只能使用ref的访问方法，而不是`const`的访问方法。

- 对于某个包含多个索引列的二级索引来说，只要是最左边的连续索引列是与常数的等值比较就可能采用`ref`的访问方法，但是如果最左边的连续索引列并不全部是等值比较的话，它的访问方法就不能称为`ref`了。

3. ref_or_null
有时候我们不仅想找出某个二级索引列的值等于某个常数的记录，还想把该列的值为NULL的记录也找出来，就像下边这个查询：
```sql
SELECT * FROM single_table WHERE key1 = 'abc' OR key1 IS NULL;
```
当使用二级索引而不是全表扫描的方式执行该查询时，这种类型的查询使用的访问方法就称为`ref_or_null`。

4. range
利用索引进行范围匹配的访问方法称之为：`range`。

5. index
采用遍历二级索引记录的执行方式称之为：`index`。

6. all
使用全表扫描执行查询的方式称之为：`all`。

### 六、Explain
`EXPLAIN`语句输出的各个列的作用先大致罗列一下：
列名 | 描述
---|---
id| 在一个大的查询语句中每个SELECT关键字都对应一个唯一的id
select_type | SELECT关键字对应的那个查询的类型
table | 表名
partitions | 匹配的分区信息
type | 针对单表的访问方法
possible_keys | 可能用到的索引
key | 实际上使用的索引
key_len | 实际使用到的索引长度
ref | 当使用索引列等值查询时，与索引列进行等值匹配的对象信息
rows | 预估的需要读取的记录条数
filtered | 某个表经过搜索条件过滤后剩余记录条数的百分比
Extra | 一些额外的信息

1. id
查询语句中每出现一个`SELECT`关键字，设计MySQL的大叔就会为它分配一个唯一的`id`值。
- 一个`SELECT`关键字后面只有一个表，只有一个id
- 一个`SELECT`关键字后面有多个表，会有多个相同的id
- 对于子查询，每个`SELECT`关键字都会对应一个唯一的id，id值越大表示执行顺序越靠前
- 特别注意，查询优化器可能会对子查询的查询语句进行重写，从而转换为连接查询
- `UNION`子句会生成一个临时表，于是会出现一个id为`NULL`的列

2. select_type
3. 

### 七、缓存Buffer Pool
1. Buffer Pool内部组成
控制块 + 碎片 + 缓存页
![](https://user-gold-cdn.xitu.io/2019/3/2/1693e86e2b9d6dd1?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

2. free链表
把所有空闲的缓存页对应的控制块作为一个节点放到一个链表中，这个链表也可以被称作`free链表`（或者说空闲链表）

3. flush链表
凡是修改过的缓存页对应的控制块都会作为一个节点加入到一个链表中，因为这个链表节点对应的缓存页都是需要被刷新到磁盘上的，所以也叫`flush链表`。

4. LRU链表
- 简单LRU链表
这个链表是为了按照最近最少使用的原则去淘汰缓存页的，所以这个链表可以被称为`LRU链表`（LRU的英文全称：Least Recently Used）。当我们需要访问某个页时，可以这样处理`LRU链表`：

    - 如果该页不在`Buffer Pool`中，在把该页从磁盘加载到`Buffer Pool`中的缓存页时，就把该缓存页对应的控制块作为节点塞到链表的头部。

    - 如果该页已经缓存在`Buffer Pool`中，则直接把该页对应的控制块移动到`LRU链表`的头部。

存在的问题：
- 预读：InnoDB认为执行当前的请求可能之后会读取某些页面，就预先把它们加载到Buffer Pool中，这些预读的页都会放到LRU链表的头部，但是如果此时Buffer Pool的容量不太大而且很多预读的页面都没有用到的话，这就会导致处在LRU链表尾部的一些缓存页会很快的被淘汰掉，也就是所谓的劣币驱逐良币，会大大降低缓存命中率。
- 全表扫描
全表扫描的语句执行的频率也不高，每次执行都要把Buffer Pool中的缓存页换一次血，这严重的影响到其他查询对 Buffer Pool的使用，从而大大降低了缓存命中率。

- 划分区域的LRU链表
针对简单LRU链表的缺陷，将LRU链表按照一定比例分成两截：
    - 一部分存储使用频率非常高的缓存页，所以这一部分链表也叫做热数据，或者称young区域。

    - 另一部分存储使用频率不是很高的缓存页，所以这一部分链表也叫做冷数据，或者称old区域。
    
再优化：只有被访问的缓存页位于young区域的1/4的后边，才会被移动到LRU链表头部，这样就可以降低调整LRU链表的频率，从而提升性能。