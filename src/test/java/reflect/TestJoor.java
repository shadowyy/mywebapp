package reflect;

import org.joor.Reflect;


/**
 * @author shadowyy
 * @version 2017/8/31 1:15
 */
public class TestJoor {
    public static void main(String[] args) {
        Reflect reflectForString = Reflect.on(String.class).create("Hello doctor who");
        String world = reflectForString.call("substring", 6).call("toString").get();
    }
}
