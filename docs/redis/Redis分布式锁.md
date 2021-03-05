**锁** 是一种用来解决多个执行线程 **访问共享资源** 错误或数据不一致问题的工具。
## 1、为何需要分布式锁
一般情况下，我们使用分布式锁主要有两个场景：

1. **避免不同节点重复相同的工作**：比如用户执行了某个操作有可能不同节点会发送多封邮件；
1. **避免破坏数据的正确性**：如果两个节点在同一条数据上同时进行操作，可能会造成数据错误或不一致的情况出现；



## 2、Java 中实现的常见方式
锁的本质：**同一时间只允许一个用户操作**。所以理论上，能够满足这个需求的工具我们都能够使用，比较常见的有以下三种实现方式。

1. **基于MySQL的锁**
   1. MySQL 本身有自带的悲观锁 `for update` 关键字；
   1. 利用**唯一索引**，可以自己实现悲观/乐观锁来达到目的；




2. **基于Zookeeper临时有序节点**
   1. Zookeeper 允许临时创建**临时有序**的子节点，这样客户端获取节点列表时，就能够当前子节点列表中的**序号**判断是否能够获得锁，如果是**第一个**节点则获取到锁，如果不是则**监听**前一个节点。




3. **基于Redis单线程**
   1. 由于 Redis 是**单线程，**所以命令会以串行的方式执行，并且本身提供了像 `SETNX(set if not exists)` 这样的指令，本身具有互斥性；



## 3、Redis 分布式锁的问题

1. **锁超时**

假设现在我们有两台平行的服务 A B，其中 A 服务在 **获取锁之后** 由于未知神秘力量突然 **挂了**，那么 B 服务就永远无法获取到锁了。
![](https://cdn.nlark.com/yuque/0/2021/png/2548312/1614957386607-1da3c7fd-20cc-415a-8a72-ffa6e0cc80d5.png#align=left&display=inline&height=320&margin=%5Bobject%20Object%5D&name=&originHeight=451&originWidth=1080&size=0&status=done&style=none&width=766)

所以我们需要额外设置一个超时时间，来保证服务的可用性。


但是另一个问题随即而来：**如果在加锁和释放锁之间的逻辑执行得太长，以至于超出了锁的超时限制**，也会出现问题。因为这时候第一个线程持有锁过期了，而临界区的逻辑还没有执行完，与此同时第二个线程就提前拥有了这把锁，导致临界区的代码不能得到严格的串行执行。


为了避免这个问题，**Redis 分布式锁不要用于较长时间的任务**。如果真的偶尔出现了问题，造成的数据小错乱可能就需要人工的干预。


有一个稍微安全一点的方案是 **将锁的 `value` 值设置为一个随机数**，释放锁时先匹配随机数是否一致，然后再删除 key，这是为了 **确保当前线程占有的锁不会被其他线程释放**，除非这个锁是因为过期了而被服务器自动释放的。


但是匹配 `value` 和删除 `key` 在 Redis 中并不是一个原子性的操作，也没有类似保证原子性的指令，所以可能需要使用像 Lua 这样的脚本来处理了，因为 Lua 脚本可以 **保证多个指令的原子性执行**。


**延伸的讨论：GC 可能引发的安全问题**
Martin Kleppmann 曾与 Redis 之父 Antirez 就 Redis 实现分布式锁的安全性问题进行过深入的讨论，其中有一个问题就涉及到 **GC**。


熟悉 Java 的同学肯定对 GC 不陌生，在 GC 的时候会发生 **STW(Stop-The-World)**，这本身是为了保障垃圾回收器的正常执行，但可能会引发如下的问题：


![](https://cdn.nlark.com/yuque/0/2021/jpeg/2548312/1614957650803-56759597-e268-4b10-9c35-6c3733ab7324.jpeg#align=left&display=inline&height=405&margin=%5Bobject%20Object%5D&name=&originHeight=630&originWidth=1080&size=0&status=done&style=none&width=695)


服务 A 获取了锁并设置了超时时间，但是服务 A 出现了 STW 且时间较长，导致了分布式锁进行了超时释放，在这个期间服务 B 获取到了锁，待服务 A STW 结束之后又恢复了锁，这就导致了 **服务 A 和服务 B 同时获取到了锁**，这个时候分布式锁就不安全了。


不仅仅局限于 Redis，Zookeeper 和 MySQL 有同样的问题。


2. **单点、多点问题**

如果 Redis 采用单机部署模式，那就意味着当 Redis 故障了，就会导致整个服务不可用。


而如果采用主从模式部署，我们想象一个这样的场景：_服务 A_ 申请到一把锁之后，如果作为主机的 Redis 宕机了，那么 _服务 B_ 在申请锁的时候就会从从机那里获取到这把锁，为了解决这个问题，Redis 作者提出了一种 **RedLock 红锁** 的算法 _(Redission 同 Jedis)。_
```java
// 三个 Redis 集群
RLock lock1 = redissionInstance1.getLock("lock1");
RLock lock2 = redissionInstance2.getLock("lock2");
RLock lock3 = redissionInstance3.getLock("lock3");

RedissionRedLock lock = new RedissionLock(lock1, lock2, lock2);
lock.lock();
// do something....
lock.unlock();
```






