package MultiThreadFutureTest;

public class SyncInvoke {


    public static void main(String[] args) throws InterruptedException {
        String result = get();
        System.out.println(result);
    }

    //同步的方式获取数据
    private static String get() throws InterruptedException {
        Thread.sleep(10 * 1000);
        return "FINISH";
    }
}
