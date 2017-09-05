package design_pattern.proxy;

/**
 *
 *
 * @author shadowyy
 * @version 2017/9/5 10:48
 */
public class TestStaticProxy {
    public static void main(String[] args) throws Exception {
        new SubjectProxy(new RealSubject()).printInfo();
    }
}

interface Subject {
    void printInfo();
}

class RealSubject implements Subject {
    @Override
    public void printInfo() {
        System.out.println("printInfo");
    }
}

class SubjectProxy implements Subject {
    private Subject subject;

    public SubjectProxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void printInfo() {
        System.out.println(System.currentTimeMillis());
        subject.printInfo();
        System.out.println(System.currentTimeMillis());
    }
}





