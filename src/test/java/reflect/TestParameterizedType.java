package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 引入Type的原因
 *
 * 为了程序的扩展性，最终引入了Type接口作为 Class, TypeVariable<D>, ParameterizedType, GenericArrayType, WildcardType这几种类型的总的父接口
 * 这样可以用Type类型的参数来接受以上五种子类的实参或者返回值类型就是Type类型的参数。统一了与泛型有关的类型和原始类型Class
 *
 * @author shadowyy
 * @version 2017/9/5 18:05
 */
public class TestParameterizedType {
    Map<String, String> map;

    public static void main(String[] args) throws Exception {
        Field f = TestParameterizedType.class.getDeclaredField("map");
        System.out.println(f.getGenericType() instanceof ParameterizedType);  // true
        ParameterizedType pType = (ParameterizedType) f.getGenericType();
        System.out.println(pType.getRawType());                               // interface java.util.Map
        for (Type type : pType.getActualTypeArguments()) {
            System.out.println(type);
        }
        System.out.println(pType.getOwnerType());                             // 返回是谁的member null
    }
}