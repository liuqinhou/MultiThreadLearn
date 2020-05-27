package ProductConsumerTest;

import java.util.stream.Stream;

public class MultiProducerConsumerTest {

    private int i = 0;
    final private Object LOCK = new Object();
    //final private Object LOCK2 = new Object();
    private volatile boolean isProduced = false;

    public void produce() {
        synchronized (LOCK) {
           // System.out.println("P 先运行");
            if (isProduced) {
                try {
                    //System.out.println("P 开始生产");
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println(Thread.currentThread().getName() + "##" + i);
                LOCK.notifyAll();
                isProduced = true;
            }
            //System.out.println("P 生产结束");
        }
    }

    public void consumer() {
        synchronized (LOCK) {
           // System.out.println("C 先运行");
            if (isProduced) {
                System.out.println(Thread.currentThread().getName() + "##" + i);
                LOCK.notifyAll();
                isProduced = false;
            } else {
                try {
                  //  System.out.println("C 等待生产");
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
           // System.out.println("C 消费结束");
        }
    }

    public static void main(String[] args) {
        MultiProducerConsumerTest test = new MultiProducerConsumerTest();
        Stream.of("P1", "P2", "P3").forEach(n -> new Thread(n) {
            @Override
            public void run() {
                while (true) {
                    test.produce();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start());

        Stream.of("C1", "C2", "C3").forEach(n -> new Thread(n) {
            @Override
            public void run() {
                while (true) {
                    test.consumer();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start());
    }
}
