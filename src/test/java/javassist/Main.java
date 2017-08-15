package javassist;

/**
 * @author yy
 * @version 2017/2/20 2:10
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        //取得需要反编译的jar文件，设定路径
        pool.insertClassPath("D:/jrebel.jar");
        //取得需要反编译修改的文件，注意是完整路径
        CtClass cc1 = pool.get("com.zeroturnaround.javarebel.dS");
        // CtClass cc1 = pool.get("com.zeroturnaround.javarebel.tR");
        //5.6.2 CtClass cc1 = pool.get("com.zeroturnaround.javarebel.tP");
        // CtClass[] params = new CtClass[2];
        //方法对应的参数
        // params[0] = pool.get("com.zeroturnaround.bundled.org.bouncycastle.crypto.params.RSAKeyParameters");
        // params[1] = pool.get("com.zeroturnaround.licensing.UserLicense");
        //取得需要修改的方法
        // CtMethod method = cc1.getDeclaredMethod("a", params);
        CtMethod method = cc1.getDeclaredMethod("c");
        //插入修改项，我们让他直接返回true(注意：根据方法的具体返回值返回，因为这个方法返回值是boolean，所以直接return true;)
        method.insertBefore("return true;");
        //写入保存
        cc1.writeFile("d:/");
        //jar uvf jrebel.jar com/zeroturnaround/javarebel/tR.class

        CtClass ctClass = pool.get("com.zeroturnaround.javarebel.SDKLicensingImpl");
        CtMethod ctMethod = ctClass.getDeclaredMethod("c");

    }
}
