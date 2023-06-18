package designPattern.proxy.staticproxy;

/**
 * 委托类
 *
 * @author csh
 * @date 2020/8/29 12:50
 */
public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        // 委托类执行操作
        System.out.println("RealSubject.doSomething()");
    }
}
