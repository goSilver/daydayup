[toc]

最近在学习MyBatis源码，了解到MyBatis里之所以只需要开发者编写Mapper接口即可执行SQL，就是因为JDK的动态代理在背后默默为我们做了很多事情。但是我自己对动态代理还只是一知半解，于是手机整理资料学习，整理了这篇笔记。</br>

说到动态代理，首先要讲的就是设计模式中的代理模式，而对于代理，根据创建代理类的时间点，又可以分为静态代理和动态代理。

# 1. 代理模式
**代理模式（Proxy）**，为其他对象提供一种代理以控制对这个对象的访问。他的特征是代理类与委托类实现相同的接口，代理类主要负责为委托类预处理消息、过滤消息、把消息转发给委托类以及事后处理消息等。代理类与委托类质检通常会存在关联关系，一个代理类的对象与一个委托类的对象关联，代理类的对象本身并不真正实现服务，而是通过调用委托类的对象的相关方法，来提供特定的服务。简单来说就是，我们访问实际对象时，是通过代理对象来访问的，代理模式就是在访问实际对象时引入一定程度的间接性，因为这种间接性，使我们可以附加多种用途。</br>

Tips：
- 委托类：指的是代理模式中的被代理对象
- 代理类：指的是生成的代表委托类的一个角色

Java代理模式实现方式，主要有以下五种方法
1. 静态代理，由开发者编辑代理类代码，实现代理模式。在编译器就生成了代理类。
2. 基于JDK实现动态代理，通过JDK提供的工具方法Proxy.newProxyInstance()动态构建全新的代理类字节码文件并实例化对象返回，这个代理类继承Proxy类，并持有InvocationHandler接口引用。JDK动态代理是由Java内部的反射机制来实例化代理对象，并代理的调用委托类方法。
3. 基于CGLib动态代理模式，原理是继承被代理类生成字代理类，不用实现接口，只需要被代理类是非final类即可。CGLib动态代理底层是借助asm字节码技术。
4. 基于AspectJ实现动态代理。修改目标类的字节，织入代理的字节，在程序编译的时候插入动态代理的字节码，不会生成全新的Class文件。
5. 基于instrumentation实现动态代理。修改目标类的字节码、类加载的时候动态拦截去修改，基于javaagent实现`-javaagent:spring-instrument-4.3.8.RELEASE.jar`，类加载的时候插入动态代理的字节码，不会生成全新的Class文件。


# 2. 静态代理
静态代理是代理类在编译器就创建好了，不是编译器生成的代理类，而是我们手动创建的类。在编译时就已经将接口、本代理类和代理类确定下来。软件设计模式中所指的代理一般就是说的静态代理。

######## 代理模式结构图

Subject类，定义了RealSubject和Proxy的共用接口，这样就在任何使用RealSubject的地方都可以使用Proxy。
```Java
public interface Subject {

    /**
     * doSomething()
     */
    void doSomething();

}
```
RealSubject类，定义Proxy所代表的真实实体。
```Java
public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        // 委托类执行操作
        System.out.println("RealSubject.doSomething()");
    }
}
```
ProxySubject类，保存一个引用使得代理可以访问实体，并提供一个与Subject的接口相同的接口，这样代理就可以用来替代实体。
```Java
public class ProxySubject implements Subject {

    private RealSubject realSubject;

    /**
     * 向代理类中注入委托类对象
     *
     * @param realSubject 委托类对象
     */
    public ProxySubject(RealSubject realSubject){
        this.realSubject = realSubject;
    }

    /**
     * 代理类执行操作
     */
    @Override
    public void doSomething() {
        System.out.println("代理类调用委托类方法之前");
        realSubject.doSomething();
        System.out.println("代理类调用委托类方法之后");
    }
}
```
测试第一种方式，不使用代理类，直接使用简单委托类执行。
```Java
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        
        realSubject.doSomething();
    }
```
输出：
```Java
    RealSubject.doSomething()
```
测试第二种方式，使用代理类，执行增强逻辑。
```Java
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();

        ProxySubject proxySubject = new ProxySubject(realSubject);
        
        proxySubject.doSomething();
    }
```
输出：
```Java
代理类调用委托类方法之前
RealSubject.doSomething()
代理类调用委托类方法之后
```
我们在创建代理对象时，通过构造器塞入一个目标对象，然后在代理对象的方法内部调用目标对象同名方法，并在调用前后做增强逻辑。也就是说，代理对象 = 增强代码 + 目标对象。有了代理对象后，就不用原对象了。</br>

**静态代理的缺陷**
开发者需要手动为目标类编写对应的代理类，而且要对类中的每个方法都编写增强逻辑的代码，如果当前系统中已经存在成百上千个类，工作量太大了，且重复代码过多。所以，有没有什么方法能让我们少写或者不写代理类，却能完成代理功能？

# 3. 动态代理
基于接口实现。

静态代理是代理类在代码运行前已经创建好，并生成class文件；动态代理类是代理类在程序运行时创建的代理模式。动态代理类的代理类并不是在Java代码中定义的，而是在运行时根据我们在Java代码中的“指示”动态生成的。相比于静态代理，动态代理的优势在于可以很方便的对代理类的函数进行统一的处理，而不用修改每个代理类中的方法。
## 3.1 JDK动态代理
Java的`java.lang.reflect`包下提供了Proxy类和一个 InvocationHandler 接口，这个类Proxy定义了生成JDK动态代理类的方法`getProxyClass(ClassLoader loader,Class<?>... interfaces)`生成动态代理类,返回class实例代表一个class文件。可以保存该 class 文件查看jdk生成的代理类文件长什么样。该生成的动态代理类继承Proxy类，(重要特性) ，并实现公共接口。InvocationHandler这个接口，是被动态代理类回调的接口，我们所有需要增加的针对委托类的统一增强逻辑都增加到invoke()方法里面，在调用委托类接口方法之前或之后。</br>

例子。任然使用上面静态代理里的类，只不过这次我们不会再用到代理类ProxySubject，而是让JDK去帮我们生成代理类。方法如下：
```Java
    public static void main(String[] args) {

        // 实例化目标对象
        Subject subject = new RealSubject();

        // 获取代理对象
        Subject proxyInstance = (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 增强逻辑
                        System.out.println("动态代理调用委托类方法之前");
                        Object invoke = method.invoke(subject, args);
                        System.out.println("动态代理调用委托类方法之后");
                        return invoke;
                    }
                });

        // 执行目标方法
        proxyInstance.doSomething();
    }
```
输出：
```Java
动态代理调用委托类方法之前
RealSubject.doSomething()
动态代理调用委托类方法之后
```
Jdk为我们的生成了一个叫$Proxy0（这个名字后面的0是编号，有多个代理类会一次递增）的代理类，这个类文件时默认不会保存在文件，放在内存中的，我们在创建代理对象时，就是通过反射获得这个类的构造方法，然后创建代理对象实例。通过对这个生成的代理类源码的查看，我们很容易能看出，动态代理实现的具体过程。</br>

我们可以对 InvocationHandler看做一个中介类，中介类持有一个被代理对象，被Proxy类回调。在invoke方法中调用了被代理对象的相应方法。通过聚合方式持有被代理对象的引用，把客户端对invoke的调用最终都转为对被代理对象的调用。</br>

客户端代码通过代理类引用调用接口方法时，通过代理类关联的中介类对象引用来调用中介类对象的invoke方法，从而达到代理执行被代理对象的方法。也就是说，动态代理Proxy类提供了模板实现，对外提供扩展点，外部通过实现InvocationHandler接口将被代理类纳入JDK代理类Proxy。</br>

**JDK动态代理特点总结**
1. 生成的代理类：$Proxy0 extends Proxy implements Subject，我们看到代理类继承了Proxy类，Java的继承机制决定了JDK动态代理类们无法实现对 类 的动态代理。所以也就决定了java动态代理只能对接口进行代理。
2. 每个生成的动态代理实例都会关联一个调用处理器对象，可以通过 Proxy 提供的静态方法 getInvocationHandler去获得代理类实例的调用处理器对象。在代理类实例上调用其代理的接口中所声明的方法时，这些方法最终都会由调用处理器的 invoke 方法执行。
3. 代理类的根类 java.lang.Object 中有三个方法也同样会被分派到调用处理器的 invoke 方法执行，它们是 hashCode，equals 和 toString，可能的原因有：一是因为这些方法为 public 且非 final 类型，能够被代理类覆盖；二是因为这些方法往往呈现出一个类的某种特征属性，具有一定的区分度，所以为了保证代理类与委托类对外的一致性，这三个方法也应该被调用处理器分派到委托类执行。

**JDK动态代理的不足**
JDK动态代理的代理类字节码在创建时，需要实现业务实现类所实现的接口作为参数。如果业务实现类是没有实现接口而是直接定义业务方法的话，就无法使用JDK动态代理了。(JDK动态代理重要特点是代理接口)并且，如果业务实现类中新增了接口中没有的方法，这些方法是无法被代理的（因为无法被调用）。动态代理只能对接口产生代理，不能对类产生代理。
## 3.2 CGLib动态代理
基于继承。

CGlib是针对类来实现代理的，他的原理是对代理的目标类生成一个子类，并覆盖其中方法实现增强，因为底层是基于创建被代理类的一个子类，所以它避免了JDK动态代理类的缺陷。但因为采用的是继承，所以不能对final修饰的类进行代理。final修饰的类不可继承。

例子。
```Java
public class CGlibSubject implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        // 设置回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGlib动态代理调用委托类方法之前");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("CGlib动态代理调用委托类方法之后");
        return result;
    }
}
```
测试：
```Java
    public static void main (String[] args) {
        CGlibSubject cglibSubject = new CGlibSubject();
        RealSubject instance = (RealSubject) cglibSubject.getInstance(new RealSubject());
        instance.doSomething();
    }
```
输出：
```Java
CGlib动态代理调用委托类方法之前
RealSubject.doSomething()
CGlib动态代理调用委托类方法之后
```

**CGlib动态代理特点总结**
1. CGlib可以传入接口也可以传入普通的类，接口使用实现的方式,普通类使用会使用继承的方式生成代理类；
2. 由于是继承方式,如果是static方法,private方法,final方法等描述的方法是不能被代理的；
3. 做了方法访问优化，使用建立方法索引的方式避免了传统JDK动态代理需要通过Method方法反射调用；
4. 提供callback 和filter设计，可以灵活地给不同的方法绑定不同的callback。编码更方便灵活；
5. CGLIB会默认代理Object中equals,toString,hashCode,clone等方法。比JDK代理多了clone。

# 4. 总结
1. 静态代理是通过在代码中显式编码定义一个业务实现类的代理类，在代理类中对同名的业务方法进行包装，用户通过代理类调用被包装过的业务方法；

2. JDK动态代理是通过接口中的方法名，在动态生成的代理类中调用业务实现类的同名方法；

3. CGlib动态代理是通过继承业务类，生成的动态代理类是业务类的子类，通过重写业务方法进行代理；

4. 静态代理在编译时产生class字节码文件，可以直接使用，效率高。动态代理必须实现InvocationHandler接口，通过invoke调用被委托类接口方法是通过反射方式，比较消耗系统性能，但可以减少代理类的数量，使用更灵活。cglib代理无需实现接口，通过生成类字节码实现代理，比反射稍快，不存在性能问题，但cglib会继承目标对象，需要重写方法，所以目标对象不能为final类。

**参考文章**：
1. [太好了！总算有人把动态代理、CGlib、AOP都说清楚了！](https://cloud.tencent.com/developer/article/1461796)
2. [Java 动态代理作用是什么？](https://www.zhihu.com/question/20794107)

# 5. 代码仓库
https://github.com/goSilver/daydayup/tree/master/java/src/designpattern/proxy