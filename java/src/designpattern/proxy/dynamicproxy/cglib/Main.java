package designpattern.proxy.dynamicproxy.cglib;

import designpattern.proxy.staticproxy.RealSubject;

/**
 * @author csh
 * @date 2020/8/29 15:14
 */
public class Main {
    public static void main (String[] args) {
        CGlibSubject cglibSubject = new CGlibSubject();
        RealSubject instance = (RealSubject) cglibSubject.getInstance(new RealSubject());
        instance.doSomething();
    }
}
