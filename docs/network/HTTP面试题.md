## 1、HTTP 和 HTTPS 的区别
1. HTTP：超文本传输协议（Hypertext Transfer Protocol），是一种在计算机世界里专门在两点之间传输文字、图片、音频、视频等超文本数据的**约定和规范**。是网络模型中的**应用层**的协议之一。



![](https://cdn.nlark.com/yuque/0/2021/png/2548312/1615121202286-5a68efb0-7865-400d-8fce-8341b13d5602.png#align=left&display=inline&height=279&margin=%5Bobject%20Object%5D&name=&originHeight=279&originWidth=388&size=0&status=done&style=none&width=388)


2. HTTPS：安全超文本传输协议（Hypertext Transfer Protocol Secure），并不是一个新的应用层协议，本质就是**HTTP+TLS/SSL协议组合而成**，安全性的保证正是TLS/SSL所做的工作。



![](https://cdn.nlark.com/yuque/0/2021/png/2548312/1615121178831-5fdb4eba-b103-41cf-821d-74f6f7beae2c.png#align=left&display=inline&height=287&margin=%5Bobject%20Object%5D&name=&originHeight=287&originWidth=470&size=0&status=done&style=none&width=470)


3. 主要区别：
   1. 最简单的，HTTP在地址栏里的请求地址是以`http://`开头，而HTTPS在地址栏里的协议是以`https://`开头。
   1. **HTTP是未经安全加密的协议**，它的传输过程容易被攻击者监听，数据容易被窃取，发送方和接受方容易被伪造；**而HTTPS是安全的协议**，它通过密钥交换算法、签名算法、对称加密算法、摘要算法能够解决上面这些问题。
   1. HTTP的默认端口是`80`，而HTTPS的默认端口是`443`。



## 2、HTTPS的工作原理
![](https://cdn.nlark.com/yuque/0/2021/png/2548312/1615121238910-821d9b36-0514-4e43-9467-88525ba691a7.png#align=left&display=inline&height=266&margin=%5Bobject%20Object%5D&name=&originHeight=266&originWidth=697&size=0&status=done&style=none&width=697)


TLS旨在为Internet提供通信安全的加密协议。TLS握手是启动和使用TLS加密的通信会话的过程。在TLS握手期间，Internet中的通信双方会彼此交换信息，验证密码套件，交换会话密钥。


每当用户通过HTTPS导航到具体的网站并发送请求时，就会进行TLS握手。除此之外，每当其他任何通信使用HTTPS时，也会发生TLS握手。


TLS具体的握手过程会根据所使用的**密钥交换算法**的类型和双方支持的**密码套件**而不同。以下以**RSA非对称加密**来讨论这个过程。整个TLS通信流程图如下：


![](https://cdn.nlark.com/yuque/0/2021/png/2548312/1615121079306-d1401f64-835d-4837-8ecf-0b692f16f313.png#align=left&display=inline&height=821&margin=%5Bobject%20Object%5D&name=&originHeight=821&originWidth=658&size=0&status=done&style=none&width=658)




- 在进行通信前，首先会进行HTTP的三次握手，握手完成后，再进行TLS的握手过程。
- **ClientHello**：客户端通过向服务器发送**hello**消息来发起握手过程。这个消息中会夹带着客户端支持的_TLS版本号、客户端支持的密码套件、以及一串客户端随机数_。
- **ServerHello**：在客户端发送hello消息后，服务器会发送一条消息，这条消息包含了服务器的_SSL证书、服务器选择的密码套件和服务器生成的随机数_。
- **认证**（Authentication）：客户端的证书颁发机构会认证SSL证书，然后发送**Certificate**报文，报文中包含公开密钥证书。最后服务器发送**ServerHelloDone**作为hello请求的响应。第一部分握手阶段结束。
- **加密阶段**：在第一阶段握手完成后，客户端会发送**ClientKeyExchange**作为响应，这个响应中包含了一种称为`The premaster secret`的密钥字符串，这个字符串就是上面公开密钥证书进行加密的字符串。随后客户端会发送**ChangeCipherSepc**，告诉服务端使用私钥解密这个`premaster secret`的字符串，然后客户端发送**Finished**告诉服务端自己发送完成了。
- 实现了安全的非对称加密：然后，服务器再发送**ChangeSipherSpec**和**Finished**告诉客户端解密完成，至此实现了RSA的非对称加密。



## 3、HTTP GET和POST的区别
HTTP中包括许多方法，GET和POST是HTTP中最常用的两个方法。

1. **GET**方法一般用于请求，相当是一个pull（拉）的动作；**POST**方法一般用于form表单的提交，相当于是一个push（推）的动作。
1. **GET**方法是不安全的，因为请求参数会拼接在URL后面；**POST**方法是把参数放在请求体body中，用户不可见。
1. **GET**请求的URL有长度限制；**POST**请求没有长度限制。
1. **GET**请求会被浏览器主动cache；**POST**不会，除非手动设置。
1. **GET**请求在发送过程中会产生一个TCP数据包，而**POST**请求会产生两个。
   1. 对于GET请求：浏览器会把**http header和data一并发送**出去，服务器响应200；
   1. 对于POST请求：浏览器**先发送header**，服务器响应**100（continue）**，浏览器**再发送data**，服务器响应200。



## 4、地址栏输入URL发生了什么？
![](https://cdn.nlark.com/yuque/0/2021/png/2548312/1615124772334-08e3fd89-618b-4c74-aa61-6358383660ea.png#align=left&display=inline&height=127&margin=%5Bobject%20Object%5D&name=&originHeight=127&originWidth=608&size=0&status=done&style=none&width=608)
面试常考题。

1. 查询DNS，获取域名对应的**IP**
   1. 检查**浏览器缓存**、检查**本地hosts文件**是否有这个网址的映射，如果有，就调用这个IP地址映射，解析完成。
   1. 如果没有，则查找**本地DNS解析器缓存**是否有这个网址的映射，如果有，返回映射，解析完成。
   1. 如果没有，则查找填写或分配的首选DNS服务器，称为**本地DNS服务器**。服务器接收到查询时：如果要查询的域名包含在本地配置区域资源中，返回解析结果，查询结束，此解析具有权威性。如果要查询的域名不由本地DNS服务器区域解析，但服务器缓存了此网址的映射关系，返回解析结果，查询结束，此解析不具有权威性。
   1. 如果本地DNS服务器也失效：
      1. 如果未采用转发模式（**迭代**），本地DNS就把请求发至13台根DNS，根DNS服务器收到请求后，会判断这个域名（如.com）是谁来授权管理，并返回一个负责该顶级域名服务器的IP，本地DNS服务器收到顶级域名服务器IP信息后，继续向该顶级域名服务器IP发送请求，该服务器如果无法解析，则会找到负责这个域名的下一级DNS服务器（如[http://baidu.com](http://baidu.com)）的IP给本地DNS服务器，循环往复直至查询到映射，将解析结果返回本地DNS服务器，再由本地DNS服务器返回解析结果，查询完成。
      1. 如果采用转发模式（**递归**），则此DNS服务器就会把请求转发至上一级DNS服务器，如果上一级DNS服务器不能解析，则继续向上请求。最终将解析结果依次返回本地DNS服务器，本地DNS服务器再返回给客户机，查询完成。
2. 得到目标服务器的IP地址及端口号（http **80**端口，https **443**端口），会调用系统库函数socket，请求一个TCP流套接字。客户端向服务器发送HTTP请求报文：
   1. 应用层：客户端发送HTTP请求报文。
   1. 传输层：（加入源端口、目的端口）建立连接。实际发送数据之前，三次握手客户端和服务器建立起一个TCP连接。
   1. 网络层：（加入IP头）路由寻址。
   1. 数据链路层：（加入frame头）传输数据。
   1. 物理层：物理传输bit。
3. 服务器端经过物理层→数据链路层→网络层→传输层→应用层，解析请求报文，发送HTTP响应报文。
3. 关闭连接，TCP四次挥手。
3. 客户端解析HTTP响应报文，浏览器开始渲染HTML。



## 5、HTTP状态码
当浏览者访问一个网页时，浏览者的浏览器会向网页所在服务器发出请求。当浏览器接收并显示网页前，此网页所在的服务器会返回一个包含HTTP状态码的信息头（server header）用以响浏览器的请求。


下面是常见的HTTP状态码：

- 200 - 请求成功
- 301 - 资源（网页等）被永久转移到其它URL
- 404 - 请求的资源（网页等）不存在
- 500 - 内部服务器错误



更多详细状态码：[菜鸟教程 - HTTP状态码](https://www.runoob.com/http/http-status-codes.html)


## 6、select 和 epoll的区别
参考博客：[select和epoll 原理概述&优缺点比较](https://blog.csdn.net/jiange_zh/article/details/50811553)
**






