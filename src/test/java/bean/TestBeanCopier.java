package bean;

import com.shadow.util.json.GsonUtil;
import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shadowyy
 * @version 2017/8/24 19:14
 */
public class TestBeanCopier {
    public static void main(String[] args) throws Exception {
        //System.out.println(GsonUtil.toJson(BeanCopyUtil.copy(new A("aaa", new C("cccc")), B.class)));
        //System.out.println(GsonUtil.toJson(BeanCopyUtil.copy(new A("a"), B.class)));
        //System.out.println(GsonUtil.toJson(BeanCopyUtil.copy(new A("aaa", new C("cccc")), B.class, new Converter() {
        //    /**
        //     * 属性描述
        //     *
        //     * @param value 属性的value
        //     * @param target 属性class
        //     * @param context 属性set方法
        //     * @return
        //     */
        //    @Override
        //    public Object convert(Object value, Class target, Object context) {
        //        return null;
        //    }
        //})));
        //
        //List<A> aList = GsonUtil.fromJson("[{\"name\":\"a1\"}, {\"name\":\"a2\",\"c\":{\"val\":\"dasdad\"} }]", ArrayList.class, A.class);
        //System.out.println(GsonUtil.toJson(aList));

        Map<String, A> map = new HashMap<>();
        map.put("a1", new A("a1"));
        map.put("a2", new A("a2", new C("dasada")));
        //String str="{\"a1\":{\"name\":\"a1\"}, \"a2\":{\"name\":\"a2\",\"c\":{\"val\":\"dasdad\"}} }";
        Type type = new ParameterizedTypeReference<HashMap<String, A>>() {}.getType();//type instanceof sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
        Map<String, A> aMap = GsonUtil.fromJson(GsonUtil.toJson(map), HashMap.class, new Type[]{String.class, A.class});
        System.out.println(GsonUtil.toJson(aMap));
    }
}
