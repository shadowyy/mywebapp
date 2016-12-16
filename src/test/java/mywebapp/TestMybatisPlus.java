package mywebapp;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;

import javax.crypto.Cipher;
import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by alice on 2016/11/26 22:34
 * MybatisPlugins文件位置：
 * C:\Users\{user}\.IntelliJIdea2016.3\config\plugins\mybatis_plus\lib\mybatis_plus.jar
 */
public class TestMybatisPlus {
    /**
     * 注册机
     * C:\Users\alice\.IntelliJIdea2016.3\config\options\mybatis.xml
     */
    @Test
    public void gen() throws Exception {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
        keygen.initialize(512);
        KeyPair kp = keygen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
        System.out.println("KEY:\n" + bytesToHexString(publicKey.getEncoded()) + "\n");
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        System.out.println("RESULT:\n" + bytesToHexString(cipher.doFinal("alice".getBytes(Charset.forName("UTF-8")))) + "\n");
    }

    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 使用javassist进行破解
     */
    @Test
    public void javassistCrack() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass c = pool.get("com.seventh7.mybatis.util.JavaUtils");
        CtMethod m = c.getDeclaredMethod("refValid");
        m.setBody("{ validated = true; valid = true; return valid; }");
        c.writeFile();

        CtClass cc = pool.get("com.seventh7.mybatis.service.JavaService");
        CtMethod mm = cc.getDeclaredMethod("stop");
        mm.setBody("{ return; }");
        cc.writeFile();

    }
}