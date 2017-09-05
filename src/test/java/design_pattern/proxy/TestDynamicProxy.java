package design_pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理：因为 Java 的单继承特性（每个代理类都继承了 Proxy 类），只能针对接口创建代理类，不能针对类创建代理类
 *
 * @author shadowyy
 * @version 2017/9/5 11:08
 */
public class TestDynamicProxy {
    public static void main(String[] args) throws Exception {
        Sub sub = new SubImpl();
        sub.print();

        Sub proxySub = DynProxyFactory.getInstance(sub);
        proxySub.print();

        Sub proxySub2 = DynProxyFactory.getInstance(new SubImpl2());
        proxySub2.print();

        System.exit(0);
    }
}

interface Sub {
    void print();
}

class SubImpl implements Sub {
    @Override
    public void print() {
        System.out.println("xixi");
    }
}

class SubImpl2 implements Sub {
    @Override
    public void print() {
        System.out.println("haha");
    }
}

class DynamicProxyHandler implements InvocationHandler {
    private Object delegate;

    public DynamicProxyHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long stime = System.currentTimeMillis();
        //利用反射机制将请求分派给委托类处理。Method的invoke返回Object对象作为方法执行结果。
        method.invoke(delegate, args);//因为示例程序没有返回值，所以这里忽略了返回值处理
        long ftime = System.currentTimeMillis();
        System.out.println("执行任务耗时" + (ftime - stime) + "毫秒");
        return null;
    }
}

class DynProxyFactory {
    public static Sub getInstance(Sub sub) {
        return (Sub) Proxy.newProxyInstance(sub.getClass().getClassLoader(), new Class[]{Sub.class}, new DynamicProxyHandler(sub));
    }
}