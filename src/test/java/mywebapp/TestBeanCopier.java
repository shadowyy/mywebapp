package mywebapp;

import com.google.common.collect.Lists;
import com.shadow.utils.BeanCopier;
import com.shadow.utils.SimpleBeanCopier;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * Created by madness on 2016/11/16 0:11
 */
public class TestBeanCopier {
    public static void main(String[] args) {
        List<A> listA= Lists.newArrayList();
        List<B> listB= Lists.newArrayList();

        BeanCopier<A, B> beanCopier = new SimpleBeanCopier<>(A.class, B.class);
        listB = Lists.transform(listA, beanCopier);
        listA = Lists.transform(listB, beanCopier.reverse());
    }
}

class A2BBeanCopier extends BeanCopier<A, B> {

    @PostConstruct
    public void init() {
        setSourceClass(A.class);
        setTargetClass(B.class);
        super.init();
    }

    @Override
    public B afterCopy(A source, B target) {
        target.setF5("aaa");
        //Call some service
        return target;
    }
}

class A {

    private int f1;

    private double f2;

    private String f3;

    private Date f4;

    private String f5;

    public int getF1() {
        return f1;
    }

    public void setF1(int f1) {
        this.f1 = f1;
    }

    public double getF2() {
        return f2;
    }

    public void setF2(double f2) {
        this.f2 = f2;
    }

    public String getF3() {
        return f3;
    }

    public void setF3(String f3) {
        this.f3 = f3;
    }

    public Date getF4() {
        return f4;
    }

    public void setF4(Date f4) {
        this.f4 = f4;
    }

    public String getF5() {
        return f5;
    }

    public void setF5(String f5) {
        this.f5 = f5;
    }
}

class B {

    private int f1;

    private double f2;

    private String f3;

    private Date f4;

    private String f5;

    public int getF1() {
        return f1;
    }

    public void setF1(int f1) {
        this.f1 = f1;
    }

    public double getF2() {
        return f2;
    }

    public void setF2(double f2) {
        this.f2 = f2;
    }

    public String getF3() {
        return f3;
    }

    public void setF3(String f3) {
        this.f3 = f3;
    }

    public Date getF4() {
        return f4;
    }

    public void setF4(Date f4) {
        this.f4 = f4;
    }

    public String getF5() {
        return f5;
    }

    public void setF5(String f5) {
        this.f5 = f5;
    }
}
