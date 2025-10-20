package doko;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public abstract class MultiThreadApp {
    private static final ExecutorService GLOBAL_EXECUTER = Executors.newVirtualThreadPerTaskExecutor();

    protected static ExecutorService Executer() {
        return GLOBAL_EXECUTER;
    }

    public static void shutdown() {
        GLOBAL_EXECUTER.shutdown();
    }
}
