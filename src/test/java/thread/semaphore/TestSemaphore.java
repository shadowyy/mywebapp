package thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author shadowyy
 * @version 2017/9/4 9:56
 */
public class TestSemaphore {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(5);
        for (int index = 0; index < 20; index++) {
            final int NO = index;
            Runnable runnable = () -> {
                try {
                    semaphore.acquire();
                    System.out.println("Accessing:" + NO);
                    Thread.sleep((long) (Math.random() * 10000));
                    semaphore.release();
                    System.out.println("------------------" + semaphore.availablePermits());//指的是当前信号灯库中有多少个可以被使用
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }
}