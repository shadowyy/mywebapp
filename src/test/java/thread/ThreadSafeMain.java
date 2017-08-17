package thread;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shadowyy
 * @version 2017/8/17 14:28
 */
public class ThreadSafeMain {
    private static int count = 0;

    public static void main(String[] args) throws Exception {

        test2();
        //int[] arr = new int[10];
        //arr[1] = 11;
        //arr[8] = 111;
        //System.out.println(Joiner.on(",").join(new ArrayList<>(Arrays.asList(arr))));//不正确
        //
        //List<String> list=new ArrayList<>();
        //list.add("1");
        //list.add("2");
        //list.add("3");
        //System.out.println(Joiner.on(",").join(list));
    }


    private static void test1() {
        Thread thread1 = new Thread(() -> System.out.println(getCount()));
        Thread thread2 = new Thread(() -> System.out.println(getCount()));
        Thread thread3 = new Thread(() -> System.out.println(getCount()));
        Thread thread4 = new Thread(() -> System.out.println(getCount()));


        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    private static void test2() {
        ExecutorService executor = Executors.newWorkStealingPool(10);

        List<Callable<String>> callables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            callables.add(() -> {
                List<Integer> list=new ArrayList<>();
                for (int j = 0; j < 10; j++) {
                    list.add(getCount());
                }
                return Thread.currentThread().getName() + ",get num:" + Joiner.on(",").join(list);
            });
        }

        try {
            executor.invokeAll(callables).stream().map(f -> {
                try {
                    return f.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new IllegalStateException(e);
                }
            }).forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int getCount() {
        return count++;
    }
}
