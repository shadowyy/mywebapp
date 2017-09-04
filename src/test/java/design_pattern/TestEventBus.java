package design_pattern;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;

public class TestEventBus {
    @Test
    public void test() throws Exception {
        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();
        eventBus.register(listener);//注册任何类，只要有@Subscribe注释
        eventBus.post(new Event(100));
        eventBus.post(1);
        eventBus.post(new Event(300));
        eventBus.post(2);
        eventBus.post("haha");
        System.out.println("LastMessage:" + listener.getLastMessage());
    }
}


class Event {
    private final int message;

    public Event(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }
}

class EventListener {
    public int lastMessage = 0;

    @Subscribe
    public void listen1(Event event) {
        lastMessage = event.getMessage();
        System.out.println("listen1-Event:" + lastMessage);
    }

    @Subscribe
    public void listen2(Integer event) {
        lastMessage = event;
        System.out.println("listen2-Integer:" + event);
    }

    @Subscribe
    public void listen3(Event event) {
        lastMessage = event.getMessage();
        System.out.println("listen3-Event:" + lastMessage);
    }

    /**
     * 如果没有消息订阅者监听消息， EventBus将发送DeadEvent消息，这时我们可以通过log的方式来记录这种状态
     * @param deadEvent
     */
    @Subscribe
    public void listen4(DeadEvent deadEvent) {
        System.out.println("listen4-DeadEvent:" + deadEvent);
    }

    public int getLastMessage() {
        return lastMessage;
    }
}



