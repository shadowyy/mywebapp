package design_pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author shadowyy
 * @version 2017/10/12 8:05
 */
public class Test {
    public static void main(String[] args) throws Exception {
        //Class<?> proxyClass = Proxy.getProxyClass(Test.class.getClassLoader(), Inf.class);
        //final Constructor<?> cons = proxyClass.getConstructor(InvocationHandler.class);
        //final InvocationHandler ih = new XXHandler(new A());
        //Inf helloWorld = (Inf) cons.newInstance(ih);
        //helloWorld.print();


        Inf xx = (Inf) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class<?>[]{Inf.class}, new XXHandler(new A()));
        xx.print();
    }
}

interface Inf {
    void print();
}

class A implements Inf {
    @Override
    public void print() {
        System.out.println("dasdad");
    }
}

class XXHandler implements InvocationHandler {
    private Object target;

    public XXHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk proxy");
        return method.invoke(target, args);
    }
}
