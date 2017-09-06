package thread.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shadowyy
 * @version 2017/9/6 11:10
 */
public class TestConcurrentHashMap {

    private final ConcurrentHashMap<String, Object> map1 = new ConcurrentHashMap<String, Object>();
    private final ReentrantLock lock = new ReentrantLock();
    public Object getSomeObject1(String key) {
        Object value = map1.get(key);
        if (value == null) {
            try {
                lock.lock();
                value = map1.get(key);
                if (value == null) {
                    value = retrieveSomeObjectFromService(key);
                    map1.put(key, value);
                }
            } finally {
                lock.unlock();
            }
        }
        return value;
    }

    private final ConcurrentHashMap<String, Object> map2 = new ConcurrentHashMap<String, Object>();
    public Object getSomeObject2(String key) {
        Object value = map2.get(key);
        if (value == null) {
            synchronized (map2) {
                value = map2.get(key);
                if (value == null) {
                    value = retrieveSomeObjectFromService(key);
                    map2.put(key, value);
                }
            }
        }
        return value;
    }


    public Object retrieveSomeObjectFromService(String key){
        return new Object();
    }

    public static void main(String[] args) throws Exception {

    }
}
