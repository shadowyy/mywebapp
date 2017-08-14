package javassist;

/**
 * @author shadowyy
 * @version 2017/8/14 20:48
 */
public class TestGen {
    public static void main(String[] args) throws Exception {
        ClassPool pool=ClassPool.getDefault();
        CtClass cc=pool.makeClass("com.ccnode.codegenerator.validate.PaidValidator");

        pool.insertClassPath("C:\\Program Files\\JetBrains\\IntelliJ IDEA 2017.2.1\\lib\\openapi.jar");
        pool.importPackage("com.intellij.openapi.project.Project");

        CtMethod m1=CtMethod.make("public static boolean validate(Project project){return true;}",cc);
        CtMethod m2=CtMethod.make("public static boolean isValid(){return true;}",cc);

        cc.addMethod(m1);
        cc.addMethod(m2);

        CtConstructor constructor=new CtConstructor(new CtClass[]{},cc);
        constructor.setBody("{super();}");
        cc.addConstructor(constructor);

        cc.writeFile("d:/src");


    }

}
