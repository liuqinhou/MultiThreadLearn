package MultiThreadFutureTest;

public interface Future<T> {
    T get() throws InterruptedException;
}
