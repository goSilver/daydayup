package designpattern.proxy.dynamicproxy.jdk;

import designpattern.proxy.staticproxy.RealSubject;
import designpattern.proxy.staticproxy.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 *
 * @author csh
 * @date 2020/8/29 13:34
 */
public class Main {

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

        /*
         * 输出：
         * 动态代理调用委托类方法之前
         * RealSubject.doSomething()
         * 动态代理调用委托类方法之后
         */
        // 执行目标方法
        proxyInstance.doSomething();
    }
}
