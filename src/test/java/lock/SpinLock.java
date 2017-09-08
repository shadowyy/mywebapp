package lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author shadowyy
 * @version 2017/9/8 11:41
 */
public class SpinLock {
    private AtomicReference<Thread> sign =new AtomicReference<>();

    public void lock(){
        while (!sign.compareAndSet(null,Thread.currentThread())){

        }
    }

    public  void unlock(){
        sign.compareAndSet(Thread.currentThread(),null);
    }

}
