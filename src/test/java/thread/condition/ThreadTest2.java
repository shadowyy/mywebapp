package thread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在Condition中，用await()替换wait()，用signal()替换notify()，用signalAll()替换notifyAll()
 *
 * @author shadowyy
 * @version 2017/9/4 11:17
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(() -> threadExecute(business, "sub")).start();
        threadExecute(business, "main");
    }

    public static void threadExecute(Business business, String threadType) {
        for (int i = 0; i < 100; i++) {
            try {
                if ("main".equals(threadType)) {
                    business.main(i);
                } else {
                    business.sub(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Business {
    private boolean bool = true;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public /*synchronized*/ void main(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (bool) {
                condition.await();//this.wait();
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("main thread seq of " + i + ", loop of " + loop);
            }
            bool = true;
            condition.signal();//this.notify();
        } finally {
            lock.unlock();
        }
    }

    public /*synchronized*/ void sub(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (!bool) {
                condition.await();//this.wait();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("sub thread seq of " + i + ", loop of " + loop);
            }
            bool = false;
            condition.signal();//this.notify();
        } finally {
            lock.unlock();
        }
    }
}
