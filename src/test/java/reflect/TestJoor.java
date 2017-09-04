package reflect;

import org.joor.Reflect;

import java.io.File;
import java.util.ArrayList;


/**
 * @author shadowyy
 * @version 2017/8/31 1:15
 */
public class TestJoor {

    public static void main(String[] args) {
        Reflect reflectForString = Reflect.on(String.class).create("Hello doctor who");
        String world = reflectForString.call("substring", 6).call("toString").get();
        System.out.println(world);

        char pathSeparatorChar = Reflect.on(File.class).create("/sdcard/droidyue.com").field("pathSeparatorChar").get();

        String setValue = Reflect.on(File.class).create("/sdcard/drodiyue.com").set("path", "fakepath").get("path");

        ArrayList arrayList = new ArrayList();
        arrayList.add("Hello");
        arrayList.add("World");
        int value = Reflect.on(arrayList).call("hugeCapacity", 12).get();



    }
}
