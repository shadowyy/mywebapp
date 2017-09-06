package reflect;

import java.lang.reflect.Field;

/**
 * @author shadowyy
 * @version 2017/9/6 14:06
 */
public class TestFieldAccessible {
    public static class MyClass {
        private String name;
    }

    public static void main(String[] args) throws Exception {
        MyClass myClass = new MyClass();
        Field field1 = myClass.getClass().getDeclaredField("name");
        field1.setAccessible(true);
        System.out.println(field1.get(myClass));
        Field field2 = myClass.getClass().getDeclaredField("name");
        System.out.println(field2.get(myClass));
    }
}
