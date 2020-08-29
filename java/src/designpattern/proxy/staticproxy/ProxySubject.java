package designpattern.proxy.staticproxy;

/**
 * 代理类
 *
 * @author csh
 * @date 2020/8/29 12:51
 */
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
