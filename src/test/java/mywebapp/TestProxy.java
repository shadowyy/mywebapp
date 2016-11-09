package mywebapp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class TestProxy {
    public static void main(String[] args) {
        ProxyHandler proxy = new ProxyHandler();
        Subject sub = (Subject) proxy.bind(new RealSubject());
        sub.doSomething();
    }
}

class ProxyHandler implements InvocationHandler {
    private Object target;

    /**
     * 绑定委托对象，并返回代理类
     * @param target 委托对象
     * @return
     */
    public Object bind(Object target) {
        this.target = target;
        //绑定该类实现的所有接口，取得代理类
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("haha");
        Object result = method.invoke(target, args);
        System.out.println("+1s");
        return result;
    }
}

interface Subject {
    void doSomething();
}

class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("call doSomething()");
    }
}