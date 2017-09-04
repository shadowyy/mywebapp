package design_pattern.observerPattern;

import java.util.HashSet;
import java.util.Set;

/**
 * 抽象主题角色类
 *
 * @author yy
 * @version 2016/12/22 12:03
 */
public abstract class Subject {
    //主题的状态
    private int state;

    //用来保存注册的观察者对象
    private Set<Observer> set = new HashSet<>();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        System.out.println("subject变化为：" + state);
        this.state = state;
        //状态发生改变，通知各个观察者
        //推模型，知道observer需要什么参数
        // set.forEach(s -> s.setState(state));
        //拉模型，直接传递subject本身
        set.forEach(s -> s.notice(this));

    }

    public void addObserver(Observer observer) {
        if (set.contains(observer)) {
            System.out.println("obeserver has been added!");
        } else {
            System.out.println("add an observer" + observer);
            set.add(observer);
        }
    }

    public void delObserver(Observer observer) {
        if (set.contains(observer)) {
            System.out.println("delete an observer" + observer);
            set.remove(observer);
        } else {
            System.out.println("no observer finded");
        }

    }
}
