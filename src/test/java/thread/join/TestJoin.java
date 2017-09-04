package thread.join;

/**
 * main 线程调用t.join时，必须能够拿到线程t对象的锁，如果拿不到它是无法wait的
 * 刚开的例子t.join(1000)不是说明了main线程等待1 秒，如果在它等待之前，其他线程获取了t对象的锁，它等待时间可不就是1毫秒了
 *
 * @author shadowyy
 * @version 2017/9/4 14:04
 */
public class TestJoin extends Thread{

    public static void main(String[] args) {
        Thread t = new Thread(new RunnableTest());
        new ThreadTest(t).start();
        t.start();
        try {
            t.join();
            System.out.println("joinFinish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class RunnableTest implements Runnable {

    public void run() {
        try {
            System.out.println("Begin sleep");
            Thread.sleep(2000);
            System.out.println("End sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadTest extends Thread {

    Thread thread;

    public ThreadTest(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        //synchronized (thread) {
            System.out.println("getObjectLock");
            try {
                Thread.sleep(9000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("ReleaseObjectLock");
        //}
    }
}