package MultiThreadFutureTest2;

public interface Future<T> {
    T get() throws InterruptedException;
}
