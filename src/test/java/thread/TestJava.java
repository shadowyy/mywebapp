package thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author shadowyy
 * @version 2017/8/7 15:17
 */
public class TestJava {

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
        //test6();
        test7();
    }

    private static void test0() throws Exception {
        Executors.newCachedThreadPool();
    }

    private static void test1() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });
        //shutdown()等待当前正在运行的任务完成，而shutdownNow()中断所有运行任务接着立刻关闭executor
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }

    }

    private static void test2() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        //Future和底层executor服务是紧耦合的，记住，每个没有终止的future，你关闭executor，将抛出异常。
        Future<Integer> future = executor.submit(() -> {
            TimeUnit.SECONDS.sleep(1);
            return 123;
        });
        System.out.println("future done? " + future.isDone());
        Integer result = null;
        try {
            //超时时间
            result = future.get(2, TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("future done? " + future.isDone());
        System.out.print("result: " + result);

    }

    private static void test3() {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<String>> callables = Arrays.asList(() -> "task1", () -> "task2", () -> "task3");
        try {
            executor.invokeAll(callables).stream().map(f -> {
                try {
                    return f.get();
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }).forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 另外一个批量提交callable任务的方法是invokeAny()，与invokeAll()方法稍微有点不同，返回第一个callable任务执行完成的结果。
     */
    private static void test4() {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<String>> callables = Arrays.asList(callable("task1", 2), callable("task2", 1), callable("task3", 3));
        String result = null;
        try {
            result = executor.invokeAny(callables);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);//task2
    }

    private static Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }

    private static void test5() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);//3秒后执行

        try {
            TimeUnit.MILLISECONDS.sleep(1337);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Future类提供的方法，还提供getDelay()方法，其返回剩余时间，且并行执行。
        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaining Delay: %sms", remainingDelay);
    }

    /**
     * 为了调度任务周期性执行，executor提供两个方法，scheduleAtFixedRate() 和 scheduleWithFixedDelay()
     * 第一个方法能够使用固定的时间频率执行任务，如下面代码示例的每秒1次。
     */
    private static void test6() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        int initialDelay = 0;
        int period = 1;
        executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
    }

    /**
     * 该方法接受一个初始延迟参数，即第一次执行之前需等待的时间。
     * 需要注意的是scheduleAtFixedRate()方法没有考虑实际执行任务的时间，所以你指定1秒周期，但是任务需要2秒，那么线程执行则更快
     */
    private static void test7() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Scheduling: " + System.nanoTime());
            }
            catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };

        executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);

    }


}
