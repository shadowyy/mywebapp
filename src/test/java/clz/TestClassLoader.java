package clz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author shadowyy
 * @version 2017/8/24 13:50
 */
public class TestClassLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestClassLoader.class);

    public static void main(String[] args) throws Exception {
        //System.out.println(System.getProperty("sun.boot.class.path"));

        String path = "D:\\deploy\\JDBC\\ClassLoader\\build\\classes\\classloader\\LocalClass.class";
        ManageClassLoader mc = new ManageClassLoader();
        while (true) {
            Class c = mc.loadClass(path);
            Object o = c.newInstance();
            Method m = c.getMethod("getName");
            m.invoke(o);
            System.out.println(c.getClassLoader());
            Thread.sleep(5000);
        }
    }

}

class DynamicClassLoader extends ClassLoader {
    public Class<?> findClass(byte[] b) throws ClassNotFoundException {
        return defineClass(null, b, 0, b.length);
    }
}

class ManageClassLoader {
    DynamicClassLoader dc = null;

    Long lastModified = 0l;
    Class c = null;

    //加载类， 如果类文件修改过加载，如果没有修改，返回当前的
    public Class loadClass(String name) throws ClassNotFoundException, IOException {
        if (isClassModified(name)) {
            dc = new DynamicClassLoader();
            return c = dc.findClass(getBytes(name));
        }
        return c;
    }

    //判断是否被修改过
    private boolean isClassModified(String filename) {
        boolean returnValue = false;
        File file = new File(filename);
        if (file.lastModified() > lastModified) {
            returnValue = true;
        }
        return returnValue;
    }

    // 从本地读取文件
    private byte[] getBytes(String filename) throws IOException {
        File file = new File(filename);
        long len = file.length();
        lastModified = file.lastModified();
        byte raw[] = new byte[(int) len];
        FileInputStream fin = new FileInputStream(file);
        int r = fin.read(raw);
        if (r != len) {
            throw new IOException("Can't read all, " + r + " != " + len);
        }
        fin.close();
        return raw;
    }
}