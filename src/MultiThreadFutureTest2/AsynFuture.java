package MultiThreadFutureTest2;

public class AsynFuture<T> implements Future<T> {

    private volatile boolean done = false;

    private T result;

    public void done(T result) {
        synchronized (this) {
            this.result = result;
            this.done = true;
            System.out.println("notify the waitset's thread to be runnable ");
            this.notifyAll();
        }
    }

    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            if(!done) {
                System.out.println("waiting for result");
                this.wait();
            }
        }
        return result;
    }
}
