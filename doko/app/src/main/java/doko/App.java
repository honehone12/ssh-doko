package doko;

import java.util.concurrent.Executors;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public final class App {
    private static final int BATCH_SIZE = 4;

    private static ExecutorService globalExecutor = Executors.newCachedThreadPool();

    private static Deque<String> getAllTargets() {
        var targets = new ArrayDeque<String>();
        // this range should be configured by command line or gui
        for (int i = 2; i < 256; i++) {
            var addr = String.format("192.168.11.%d", i);
            targets.add(addr);
        }
        return targets;
    }

    private static List<List<String>> createBatches() {
        var batches = new ArrayList<List<String>>();
        var targets = getAllTargets();
        var total = targets.size();
        var futureSize = (total + BATCH_SIZE - 1) / BATCH_SIZE;

        for (int i = 0; i < futureSize; i++) {
            var bath = new ArrayList<String>();

            for (int j = 0; j < BATCH_SIZE; j++) {
                if (targets.isEmpty()) {
                    break;
                }
                var addr = targets.removeFirst();
                bath.add(addr);
            }
            
            batches.add(bath);
        }

        return batches;
    }

    public static void run() {
        System.out.println("scanning ssh port...\n");

        var batches = createBatches();
        var n = batches.size();
        var futures = new CompletableFuture[n];

        for (int i = 0; i < n; i++) {
            var b = batches.get(i);
            var fut = CompletableFuture.runAsync(() -> Scanner.ScanList(b), globalExecutor);
            futures[i] = fut;
        }

        var all = CompletableFuture.allOf(futures);
        all.join();

        System.out.println("\ndone");
    }

    public static void shutdown() {
        globalExecutor.shutdown();
    }
}
