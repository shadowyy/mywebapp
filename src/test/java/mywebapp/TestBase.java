package mywebapp;

import java.util.List;

/**
 * Created by alice on 2016/11/7 10:51
 */
public class TestBase {
    public static void main(String[] args) throws CloneNotSupportedException {
////        PType t=getEnum(PType.class,1);
////        System.out.println(t);
//        Wine w1=new Wine();
//        Wine w2=(Wine)w1.clone();
////        System.out.println(w1.getName());
////        System.out.println(w2.getName());
//        w2.setName("xxx");
//        //String虽然是引用复制，但是却是immutable类型，所以不会改变原值，只能再去新建一个对象
//        System.out.println(w1.getName());
//        System.out.println(w2.getName());

        //Set<String> set = Sets.newHashSet();
        //set.add("1");
        //set.add("2");
        //set.add("2");
        //
        //String[] arr = {"3", "4"};
        //arr = set.toArray(arr);
        //System.out.println(Arrays.toString(arr));

        //ParameterizedTypeReference parameterizedTypeReference=new ParameterizedTypeReference<List<Wine>>(){};
        //TypeToken typeToken=new TypeToken<List<Wine>>(){};
        com.google.common.reflect.TypeToken typeToken2=new com.google.common.reflect.TypeToken<List<Wine>>(){};

    }

    public static <T> T getEnum(Class<T> clazz, int index) {
        T[] c = clazz.getEnumConstants();
        return c[index];
    }

    public enum PType {A, B, C, D}

}

class Wine implements Cloneable {
    int degree;
    String name = "法国白兰地";

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 覆写clone()方法
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
