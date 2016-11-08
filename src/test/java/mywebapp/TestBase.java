package mywebapp;

/**
 * Created by alice on 2016/11/7 10:51
 */
public class TestBase {
    public static void main(String[] args){
        PType t=getEnum(PType.class,1);
        System.out.println(t);
    }
    public static <T> T getEnum(Class<T> clazz,int index){
        T[] c=clazz.getEnumConstants();
        return c[index];
    }
    public enum PType{A,B,C,D};
}
