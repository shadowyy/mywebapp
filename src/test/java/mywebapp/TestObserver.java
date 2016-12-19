package mywebapp;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by alice on 2016/12/12 21:53
 */
public class TestObserver {

    public static void main(String[] args) {
        NumObservable number = new NumObservable();
        number.addObserver(new NumObserver());
        number.setData(1);
        number.setData(2);
        number.setData(3);
    }
}

/**
 * 被观察者
 */
class NumObservable extends Observable {
    private int data = 0;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
        setChanged();
        notifyObservers();

    }
}

/**
 * 观察者
 */
@SuppressWarnings({"unchecked"})
class NumObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        NumObservable my = (NumObservable) o;
        System.out.println("Data has changed to " + my.getData());
    }
}

