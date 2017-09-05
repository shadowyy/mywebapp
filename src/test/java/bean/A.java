package bean;

/**
 * @author shadowyy
 * @version 2017/9/5 15:35
 */
public class A {
    private String name;
    private C c;

    public A(String name) {
        this.name = name;
    }

    public A(String name, C c) {
        this.name = name;
        this.c = c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }
}
