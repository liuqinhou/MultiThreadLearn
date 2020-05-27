package MultiThreadFutureTest2;


import java.util.function.Consumer;

/**
 * Future  代表的是未来返回数据的一个凭据
 * FutureTask  将你的调用逻辑进行了隔离
 * FutureService  桥接 Future和FutureTask
 *
 */

public class AsyncInvoke {

    public static void main(String[] args) throws InterruptedException {
        FutureService futureService = new FutureService();
        Future future = futureService.submit(() -> {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        }, System.out::println);


        System.out.println("do other thing");
        Thread.sleep(2000);
        System.out.println("++++++++++++++++++++++++++");
    }
}
