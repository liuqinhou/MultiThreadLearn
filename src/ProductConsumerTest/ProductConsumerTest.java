package ProductConsumerTest;

public class ProductConsumerTest {

    private int i = 0;
    final private Object LOCK = new Object();
    //final private Object LOCK2 = new Object();
    private volatile boolean isProduced = false;

    public void produce() {
        synchronized (LOCK) {
            System.out.println("P 先运行");
            if (isProduced) {
                try {
                    System.out.println("P 开始生产");
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println("P->  " + i);
                LOCK.notify();
                isProduced = true;
            }
            System.out.println("P 生产结束");
        }
    }

    public void consumer() {
        synchronized (LOCK) {
            System.out.println("C 先运行");
            if (isProduced) {
                System.out.println("C->  " + i);
                LOCK.notify();
                isProduced = false;
            } else {
                try {
                    System.out.println("C 等待生产");
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C 消费结束");
        }
    }

    public static void main(String[] args) {
        ProductConsumerTest test = new ProductConsumerTest();
        new Thread("producer ") {
            @Override
            public void run() {
                while(true) {
                    test.produce();
                }
            }
        }.start();
        new Thread("consumer ") {
            @Override
            public void run() {
                while(true) {
                    test.consumer();
                }
            }
        }.start();
    }


}
