package guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.shadow.domain.User;
import org.junit.Test;

/**
 * @author shadowyy
 * @version 2017/8/21 20:03
 */
public class TestCache {

    @Test
    public void test1() throws Exception {
        LoadingCache<String, String> cacheBuilder = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return "hello " + key + "!";
            }
        });
        //LoadingCache<String, String> cacheBuilder = CacheBuilder.newBuilder().build((CacheLoader) (key) -> {
        //    return "hello " + key + "!";
        //});

        //System.out.println("jerry value:" + cacheBuilder.apply("jerry"));
        System.out.println("jerry value:" + cacheBuilder.get("jerry"));
        System.out.println("peida value:" + cacheBuilder.get("peida"));
        //System.out.println("peida value:" + cacheBuilder.apply("peida"));
        //System.out.println("lisa value:" + cacheBuilder.apply("lisa"));
        cacheBuilder.put("harry", "ssdded");
        System.out.println("harry value:" + cacheBuilder.get("harry"));
        User user = new User();
        User user2 = new User();
        user.equals(user2);
    }

}
