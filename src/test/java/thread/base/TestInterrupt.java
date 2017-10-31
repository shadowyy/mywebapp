package thread.base;

/**
 * @author shadowyy
 * @version 2017/10/9 23:35
 */
public class TestInterrupt {
    public static void main(String[] args) {
        //tPointIsInterrupted();
        ThreadInterrupted();
    }


    private static void tPointIsInterrupted() {
        Thread t = Thread.currentThread();

        print(t);//false
        t.interrupt();
        print(t);//true 线程一旦被中断，isInterrupted()方法便会返回 true
        try {
            Thread.sleep(1000);
            System.out.println("was NOT interrupted");
        } catch (InterruptedException e) {
            System.out.println("was interrupted");
        }
        print(t);//false 而一旦 sleep()方法抛出异常，它将清空中断标志，此时isInterrupted()方法将返回 false
    }

    private static void print(Thread t) {
        System.out.println(t.isInterrupted());
        System.out.println(t.isInterrupted());
        System.out.println(t.isInterrupted());
        System.out.println("---------");
    }

    //与 isInterrupted()不同，它将自动重置中断状态为 false，第二次调用 Thread.interrupted()方法，总是返回 false，除非中断了线程
    private static void ThreadInterrupted() {
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());//true
        System.out.println(Thread.interrupted());//false
        System.out.println(Thread.interrupted());//false
    }

    //总结:
    // t.isInterrupted()在InterruptedException异常抛出之前为true，抛出之后返回false
    //Thread.interrupted()中断后第一次调用为true，之后都为false，即自动重置中断状态


}
