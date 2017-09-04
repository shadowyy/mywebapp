package design_pattern.observerPattern;

/**
 * 抽象观察者
 *
 * @author yy
 * @version 2016/12/22 12:09
 */
public abstract class Observer {
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        System.out.println("观察者状态更新为：" + state);
        this.state = state;
    }

    /**
     * 拉模型
     *
     * @param subject
     */
    public void notice(Subject subject) {
        System.out.println("观察者得到的subject为：" + subject);
        this.state = subject.getState();
    }


}
