package reflect;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * TypeVariable
 *
 * 类型变量, 范型信息在编译时会被转换为一个特定的类型, 而TypeVariable就是用来反映在JVM编译该泛型前的信息
 * 它的声明是这样的: public interface TypeVariable<D extends GenericDeclaration> extends Type
 * 也就是说它跟GenericDeclaration有一定的联系, 我是这么理解的:
 * TypeVariable是指在GenericDeclaration中声明的<T> <C extends Collection>这些东西中的那个变量T、C; 它有如下方法:
 * <p>
 * Type[] getBounds(): 获取类型变量的上边界, 若未明确声明上边界则默认为Object
 * D getGenericDeclaration(): 获取声明该类型变量实体
 * String getName(): 获取在源码中定义时的名字
 *
 * 注意:
 * 类型变量在定义的时候只能使用extends进行(多)边界限定, 不能用super;
 * 为什么边界是一个数组? 因为类型变量可以通过&进行多个上边界限定，因此上边界有多个
 *
 * @author shadowyy
 * @version 2017/9/6 9:26
 */
public class TestTypeVariable<K extends Comparable & Serializable, V> {
    K key;
    V value;

    public static void main(String[] args) throws Exception {
        // 获取字段的类型
        Field fk = TestTypeVariable.class.getDeclaredField("key");
        Field fv = TestTypeVariable.class.getDeclaredField("value");
        TypeVariable keyType = (TypeVariable) fk.getGenericType();
        TypeVariable valueType = (TypeVariable) fv.getGenericType();

        // getName()获取在源码中定义时的名字
        System.out.println(keyType.getName());                 // K
        System.out.println(valueType.getName());               // V

        // getGenericDeclaration()获取声明该类型变量实体
        System.out.println(keyType.getGenericDeclaration());   // class com.test.TestTypeVariable
        System.out.println(valueType.getGenericDeclaration()); // class com.test.TestTypeVariable

        // getBounds()获取类型变量的上边界
        System.out.println("K 的上界:");                        // 有两个
        for (Type type : keyType.getBounds()) {                // interface java.lang.Comparable
            System.out.println(type);                          // interface java.io.Serializable
        }
        System.out.println("V 的上界:");                        // 没明确声明上界的, 默认上界是 Object
        for (Type type : valueType.getBounds()) {              // class java.lang.Object
            System.out.println(type);
        }
    }
}
