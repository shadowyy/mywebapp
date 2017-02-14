package mywebapp;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import org.junit.Test;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alice on 2016/11/16 13:24
 */
public class TestFunction {
    @Test
    public void testFunctionApply() {
        Function<Date, String> function = new Function<Date, String>() {
            @Override
            public String apply(Date input) {
                return new SimpleDateFormat("yyyy-MM-dd").format(input);
            }
        };
        System.out.println(function.apply(new Date()));

    }

    @Test
    public void testFunctionsForMap() {
        Map<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("love", 1);
                put("miss", 2);
            }
        };
        Function<String, Integer> function = Functions.forMap(map);
        //调用apply方法，可以通过key获取相应的value
        System.out.println(function.apply("love"));
        //当apply的key值在Map里不存在时，会抛出异常
        //我们可以通过forMap重载的另一个方法避免异常，当Key不存在时，设置一个默认值
        function = Functions.forMap(map, 0);
        System.out.println(function.apply("like"));//can't find this key
    }

    /**
     * 多个Function进行组合，这时就需要用到compose
     */
    @Test
    public void testFunctionsCompose() {
        //+1
        Function<Integer, Integer> f1 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer input) {
                return input + 1;
            }
        };
        //平方
        Function<Integer, Integer> f2 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer input) {
                return input * input;
            }
        };
        /**
         * Warning：这里compose方法的参数位置不能颠倒，
         * Function<A, C> compose(Function<B, C> g, Function<A, ? extends B> f)
         * 传入Function<B,C>、Function<A, ? extends B>组合成Function<A, C>
         */
        //先+1再平方
        Function<Integer, Integer> r1 = Functions.compose(f2, f1);
        //先平方再+1
        Function<Integer, Integer> r2 = Functions.compose(f1, f2);
        System.out.println(r1.apply(1));//4
        System.out.println(r2.apply(1));//2
    }

    @Test
    public void testImmutableList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        //对原有的List进行包装，相当于原有List的视图，快照,不够安全.
        List<String> readList = Collections.unmodifiableList(list);
        //readList.addObserver("d");//报错，java.lang.UnsupportedOperationException
        list.add("d");//改变原有List   视图也一起改变  不报错
        System.out.println(readList);

        //Guava
        //对比查看  初始化List guava对只读设置安全可靠 并且相对简单
        List<String> immutableList = ImmutableList.of("a", "b", "c");
        //immutableList.addObserver("d");//报错，java.lang.UnsupportedOperationException
        list.add("d");//改变原有List，视图不改变
        System.out.println(immutableList);
    }

    /**
     * 使用ComparisonChain进行比较
     */
    @Test
    public void testComparisonChain() {
        Student student = new Student("peida", 23, 80);
        Student student1 = new Student("aida", 23, 36);
        Student student2 = new Student("jerry", 24, 90);
        Student student3 = new Student("peida", 23, 80);

        System.out.println("==========equals===========");
        System.out.println(student.equals(student2));
        System.out.println(student.equals(student1));
        System.out.println(student.equals(student3));

        System.out.println("==========hashCode===========");
        System.out.println(student.hashCode());
        System.out.println(student1.hashCode());
        System.out.println(student3.hashCode());
        System.out.println(student2.hashCode());

        System.out.println("==========toString===========");
        System.out.println(student.toString());
        System.out.println(student1.toString());
        System.out.println(student2.toString());
        System.out.println(student3.toString());

        System.out.println("==========compareTo===========");
        System.out.println(student.compareTo(student1));
        System.out.println(student.compareTo(student3));
        System.out.println(student2.compareTo(student1));
        System.out.println(student2.compareTo(student));

        System.out.println("==========StudentComparator===========");
        StudentComparator sc = new StudentComparator();
        System.out.println(sc.compare(student, student1));
        System.out.println(sc.compare(student, student3));
        System.out.println(sc.compare(student2, student3));
    }
}

class Student implements Comparable<Student> {
    public String name;
    public int age;
    public int score;

    Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                score == student.score &&
                com.google.common.base.Objects.equal(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, age, score);
    }


    @Override
    public int compareTo(Student stu) {
        return ComparisonChain.start()
                .compare(name, stu.name)
                .compare(age, stu.age)
                .compare(score, stu.score, Ordering.natural().nullsLast())
                .result();
    }
}

class StudentComparator implements Comparator<Student>, Serializable {
    @Override
    public int compare(Student s1, Student s2) {
        return ComparisonChain.start()
                .compare(s1.name, s2.name)
                .compare(s1.age, s2.age)
                .compare(s1.score, s2.score)
                .result();
    }
}

