package thread;

/**
 * @author shadowyy
 * @version 2017/8/19 12:01
 */
public class ConsumerAndProducer {

    private final int MAX_PRODUCT = 3;
    private final int MIN_PRODUCT = 1;
    private int product = 3;

    /**
     * 生产者生产出来的产品交给店员
     */
    public synchronized void produce() {
        if (this.product >= MAX_PRODUCT) {
            try {
                wait();
                System.out.println("产品已满,请稍候再生产");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        this.product++;
        System.out.println("生产者生产第" + this.product + "个产品.");
        notifyAll();   //通知等待区的消费者可以取出产品了
    }

    /**
     * 消费者从店员取产品
     */
    public synchronized void consume() {
        if (this.product <= MIN_PRODUCT) {
            try {
                wait();
                System.out.println("缺货,稍候再取");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        System.out.println("消费者取走了第" + this.product + "个产品.");
        this.product--;
        notifyAll();   //通知等待去的生产者可以生产产品了
    }

    public static void main(String[] args) throws Exception {
        ConsumerAndProducer consumerAndProducer = new ConsumerAndProducer();
        consumerAndProducer.produce();
        consumerAndProducer.consume();

    }
}
