package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.WildcardType;
import java.util.List;

/**
 * WildcardType
 * <p>
 * 该接口表示通配符泛型, 比如? extends Number 和 ? super Integer 它有如下方法:
 * Type[] getUpperBounds(): 获取范型变量的上界
 * Type[] getLowerBounds(): 获取范型变量的下界
 * <p>
 * 再写几个边界的例子:
 * <p>
 * List<? extends Number>, 上界为class java.lang.Number, 属于Class类型
 * List<? extends List<T>>, 上界为java.util.List<T>, 属于ParameterizedType类型
 * List<? extends List<String>>, 上界为java.util.List<java.lang.String>, 属于ParameterizedType类型
 * List<? extends T>, 上界为T, 属于TypeVariable类型
 * List<? extends T[]>, 上界为T[], 属于GenericArrayType类型
 * 它们最终统一成Type作为数组的元素类型
 *
 * @author shadowyy
 * @version 2017/9/6 9:58
 */
public class TestWildcardType {
    private List<? extends Number> a;  // // a没有下界, 取下界会抛出ArrayIndexOutOfBoundsException
    private List<? super String> b;

    public static void main(String[] args) throws Exception {
        Field fieldA = TestWildcardType.class.getDeclaredField("a");
        Field fieldB = TestWildcardType.class.getDeclaredField("b");
        ParameterizedType pTypeA = (ParameterizedType) fieldA.getGenericType();
        ParameterizedType pTypeB = (ParameterizedType) fieldB.getGenericType();
        // 再从范型里拿到通配符类型
        WildcardType wTypeA = (WildcardType) pTypeA.getActualTypeArguments()[0];
        WildcardType wTypeB = (WildcardType) pTypeB.getActualTypeArguments()[0];

        // 方法测试
        System.out.println(wTypeA.getUpperBounds()[0]);   // class java.lang.Number
        System.out.println(wTypeB.getLowerBounds()[0]);   // class java.lang.String
        System.out.println(wTypeA);// 看看通配符类型到底是什么, 打印结果为: ? extends java.lang.Number
    }
}
