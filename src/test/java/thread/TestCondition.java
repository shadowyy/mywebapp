package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shadowyy
 * @version 2017/8/26 16:15
 */
public class TestCondition {
    public static void main(String[] args) throws Exception {
        TestCondition testCondition = new TestCondition();
        testCondition.test1();
    }

    private void test1() {
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition condition = reentrantLock.newCondition();

        Thread thread = new Thread(() -> {
            try {
                reentrantLock.lock();
                System.out.println("我要等一个新信号" + this);
                condition.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("拿到一个信号！！" + this);
            reentrantLock.unlock();
        }, "waitThread1");

        thread.start();

        Thread thread1 = new Thread(() -> {
            reentrantLock.lock();
            System.out.println("我拿到锁了");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();
            System.out.println("我发了一个信号！！");
            reentrantLock.unlock();
        }, "signalThread");

        thread1.start();
    }
}
