package thread.concurrent;

import java.util.Timer;

/**
 * @author shadowyy
 * @version 2017/8/15 11:21
 */
public class ScheduledExecutorServiceTest {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Timer timer = new Timer();
        int delay = 1000;
        int period = 2000;
        //timer.schedule(new OKTask(), delay * 2, period);

    }

    private static void test2() {

    }



}
