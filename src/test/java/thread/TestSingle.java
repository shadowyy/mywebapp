package thread;

/**
 * 单例
 *
 * @author shadowyy
 * @version 2017/8/24 19:30
 */
public class TestSingle {
    public static void main(String[] args) throws Exception {
        DCLSingleton.getInstance();
    }
}

/**
 * 饿汉式
 * <p>
 * 1.资源利用效率不高
 * 2.Single实例的创建依赖参数或配置文件，则在getInstance()之前必须调用某个方法来设置这些参数，但在设置之前，可能已经new了Single实例，这种情况下，饿汉式的写法是无法使用的
 */
class Singleton {
    private Singleton() {
    }

    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
}


/**
 * 懒汉式（同步方法）
 * <p>
 * 每次调用getInstance（）方法时，都要同步，而且很多时候的同步是没必要的，这将会极大地拖垮性能（尤其在需要多次调用getInstance方法的地方，
 * 当第一次创建了LazySingleton实例后，instance便不再为null，这样后面每次调用getInstance进入方法体后，却便发现自己什么也不用做，
 * 而每次调用getInstnce都要同步，需要切换到内核，这样便很浪费资源，每次做很大开销进入方法体，却发现自己什么也不用做）。
 */
class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

/**
 * DCL双重检查加锁（同步方法段）
 * 不加volatile线程有可能得到一个不为null，但是构造不完全的对象。
 * <p>
 * instance = new LazySingleton()
 * 1、栈内存开辟空间给help引用
 * 2、堆内存开辟空间准备初始化对象
 * 3、初始化对象
 * 4、栈中引用指向这个堆内存空间地址指令
 *
 * 重排之后可能会是1、2、4、3；这样重排之后对单个线程来说效果是一样的，所以JVM
 * 认为是合法的重排序，但是在多线程环境下就会出问题，这里到4的时候help已经指向了一块堆内存！=null ，只是这块堆内存还没初始化就直接返回了，使用的时候抛NullPointException。
 * <p>
 * 在java5以前，volatile原语不怎么强大，只能保证对象的可见性
 * 但在java5之后，volatile语义加强了，被volatile修饰的对象，将禁止该对象上的读写指令重排序
 * 这样，就保证了线程B读对象时，已经初始化完全了
 */
class DCLSingleton {
    private volatile static DCLSingleton instance = null;//volatile保证每次都去instance都从主内存读取

    private DCLSingleton() {
    }

    public static DCLSingleton getInstance() {
        if (instance == null) {//第一层判断主要是为了避免不必要的同步
            synchronized (DCLSingleton.class) {
                if (instance == null) {//第二层的判断则是为了在null的情况下创建实例
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}


/**
 * static内部类单例模式
 * 类级内部类（有static修饰的成员内部类）相当于其外部类的成员，只有在第一次使用时才会被装载，而不会在类加载器加载其外部类的时候被装载，而且只会被加载一次。因此，资源利用率高
 */
class StaticSingleton {
    private StaticSingleton() {
    }

    private static class InstanceHolder {
        private static final StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance() {
        return InstanceHolder.instance;
    }
}
