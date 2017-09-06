package gc;

/**
 * @author shadowyy
 * @version 2017/9/6 14:18
 */
public class Main {
    public static void main(String[] args) throws Exception {
        MyObject object1 = new MyObject();
        MyObject object2 = new MyObject();

        object1.object = object2;
        object2.object = object1;

        object1 = null;
        object2 = null;
    }

    private static class MyObject{
        private MyObject object;
    }
}
