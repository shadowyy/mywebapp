package thread.concurrent.queue;

/**
 * @author shadowyy
 * @version 2017/9/4 11:28
 */
public interface SimpleQueueDemo<E> {
    void put(E e);

    E take();
}
