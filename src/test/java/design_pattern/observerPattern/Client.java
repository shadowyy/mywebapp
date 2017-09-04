package design_pattern.observerPattern;

/**
 * 客户端
 *
 * @author yy
 * @version 2016/12/22 13:29
 */
public class Client {

    public static void main(String[] args) {
        //创建主题对象
        Subject subject = new ConcreteSubject();
        System.out.println(subject);
        //创建观察者对象
        Observer o1 = new ConcreteObserver();
        Observer o2 = new ConcreteObserver();
        System.out.println(o1.getState());
        System.out.println(o2.getState());

        //将观察者对象登记到主题对象上
        subject.addObserver(o1);
        subject.addObserver(o1);
        subject.addObserver(o2);
        //改变主题对象的状态
        subject.setState(1);
        System.out.println(o1.getState());
        System.out.println(o2.getState());
    }

}
