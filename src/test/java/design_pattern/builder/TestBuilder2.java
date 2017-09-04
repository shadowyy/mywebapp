package design_pattern.builder;

import com.google.gson.Gson;

/**
 * @author shadowyy
 * @version 2017/9/4 17:02
 */
public class TestBuilder2 {
    public static void main(String[] args) throws Exception {
        ABulider aBulider = new ABulider();
        aBulider.setA1("a1").setA2("a2");
        A a = aBulider.create();
        System.out.println(new Gson().toJson(a));
    }
}

class ABulider {
    private String a1;
    private String a2;

    public ABulider setA1(String a1) {
        this.a1 = a1;
        return this;
    }

    public ABulider setA2(String a2) {
        this.a2 = a2;
        return this;
    }

    public A create() {
        A a = new A();
        a.setA1(a1);
        a.setA2(a2);
        return a;
    }


}

class A {
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private String a5;

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getA4() {
        return a4;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

    public String getA5() {
        return a5;
    }

    public void setA5(String a5) {
        this.a5 = a5;
    }
}
