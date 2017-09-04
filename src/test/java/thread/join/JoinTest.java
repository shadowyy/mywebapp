package thread.join;

/**
 * thread.Join把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程
 * 比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B
 *
 * @author shadowyy
 * @version 2017/9/4 14:12
 */
public class JoinTest implements Runnable {

    public static int a = 0;

    public void run() {
        for (int k = 0; k < 5; k++) {
            a = a + 1;
        }
    }

    public static void main(String[] args) throws Exception {
        //test1();
        test2();
    }

    private static void test1() {
        Runnable r = new JoinTest();
        Thread t = new Thread(r);
        t.start();
        //t.join(); //加入join()
        //注意循环体内一定要有实际执行语句，否则编译器或JVM可能优化掉你的这段代码，视这段代码为无效
        for (int i = 0; i < 1; i++) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println(a);
    }

    private static void test2() {
        Thread t = new Thread(new RunnableImpl());
        t.start();
        try {
            //t.join(1000);// 不管t线程什么时候结束，1秒后都会执行
            t.join();// 一直等到t线程执行完毕
            System.out.println("joinFinish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

class RunnableImpl implements Runnable {

    public void run() {
        try {
            System.out.println("Begin sleep");
            Thread.sleep(5000);
            System.out.println("End sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}