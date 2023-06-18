package designPattern.proxy.staticproxy;

/**
 * @author csh
 * @date 2020/8/29 12:56
 */
public class Main {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();

        ProxySubject proxySubject = new ProxySubject(realSubject);

        /*
         *输出：
         * 代理类调用委托类方法之前
         * RealSubject.doSomething()
         * 代理类调用委托类方法之后
         */
        proxySubject.doSomething();
    }
}
