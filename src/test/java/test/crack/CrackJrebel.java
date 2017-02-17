package test.crack;

import com.zeroturnaround.licensing.UserLicense;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * jrebel破解
 *
 * @author yy
 * @version 2017/2/15 3:26
 */
public class CrackJrebel {
    // public static void main(String[] args) {
    //     rebuild(dataMap);
    //     byte[] myBytes = serialize(dataMap);
    //     UserLicense myLicense = new UserLicense();
    //     myLicense.setLicense(myBytes);
    //     myLicense.setSignature(trailLicense.getSignature());
    //     serialize(myLicense, new FileOutputStream("out/jrebel.lic"));
    // }
    //
    // public static void rebuild(Map dataMap) {
    //     dataMap.clear();
    //     dataMap.put("Comment", "##### Cracked by zoakerc, For Study! Unlimited! Enjoy! #####");
    //     dataMap.put("Name", "cnblogs zoakerc");
    //     dataMap.put("Organization", "www.cnblogs.com/zoakerc");
    //     dataMap.put("Product", "JRebel");
    //     dataMap.put("Seats", "Unlimited");
    //     dataMap.put("commercial", "true");
    //     dataMap.put("uid", "www.cnblogs.com/zoakerc/p/4882480.html");
    //     dataMap.put("version", "1.7");
    //     dataMap.put("GeneratedOn", new Date());
    //     dataMap.put("GeneratedBy", "cnblogs zoakerc");
    // }
    //
    //
    // private static void echoMap(Map dataMap) {...}
    //
    // private static void serialize(Object obj, OutputStream outputStream) {...}
    //
    // private static byte[] serialize(Object obj) {...}
    //
    // private static <T> T deserialize(final InputStream inputStream) {...}
    //
    // private static <T> T deserialize(byte[] objectData) {...}、


    public static void main(String[] args) throws Exception {
        UserLicense userLicense = UserLicense.loadInstance(new File("d:/jrebel.lic"));
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(userLicense.getLicense()));
        Map dataMap = (Map) ois.readObject();
        System.out.println(dataMap);
    }
}
