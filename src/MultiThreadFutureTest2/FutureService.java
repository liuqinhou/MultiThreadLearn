package MultiThreadFutureTest2;

import java.util.function.Consumer;

public class FutureService {

    public <T> Future<T> submit(final FutureTask<T> task, Consumer<T> consumer) {
        AsynFuture<T> asynFuture = new AsynFuture<>();

        new Thread( () -> {
            T result = task.call();
            asynFuture.done(result);
            consumer.accept(result);
        }).start();

        return  asynFuture;
    }
}
