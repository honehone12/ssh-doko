package doko;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public abstract class MultiThreadService {
    private static final ExecutorService GLOBAL_EXECUTER = Executors.newCachedThreadPool();

    protected static ExecutorService Executer() {
        return GLOBAL_EXECUTER;
    }

    public static void shutdown() {
        GLOBAL_EXECUTER.shutdown();
    }
}
