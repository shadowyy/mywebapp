package design_pattern.proxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Method;

/**
 * @author shadowyy
 * @version 2017/9/5 13:49
 */
public class TestCglib {
    public static void main(String[] args) throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dao.class);
        enhancer.setCallback(new DaoProxy());
        Dao dao = (Dao) enhancer.create();
        dao.update();
        dao.select();

        System.out.println("-----------------------------------------");
        enhancer.setSuperclass(Dao.class);
        enhancer.setCallbacks(new Callback[]{new DaoProxy(), new DaoAnotherProxy(), NoOp.INSTANCE});
        enhancer.setCallbackFilter(new DaoFilter());
        Dao dao2 = (Dao)enhancer.create();
        dao2.update();
        dao2.select();
    }
}

class Dao {
    public void update() {
        System.out.println("PeopleDao.update()");
    }

    public void select() {
        System.out.println("PeopleDao.select()");
    }
}

class DaoProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Before Method Invoke");
        proxy.invokeSuper(obj, args);
        System.out.println("After Method Invoke");
        return obj;
    }
}

class DaoAnotherProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("StartTime=[" + System.currentTimeMillis() + "]");
        proxy.invokeSuper(obj, args);
        System.out.println("EndTime=[" + System.currentTimeMillis() + "]");
        return obj;
    }
}

class DaoFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        if ("select".equals(method.getName())) {
            return 1;
        }
        return 0;
    }
}