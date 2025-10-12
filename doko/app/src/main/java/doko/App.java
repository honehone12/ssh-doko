package doko;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class App extends MultiThreadApp {
    private static final int BATCH_SIZE = 4;
    private static final App INSTANCE = new App();

    private App() {}

    public static App instance() {
        return INSTANCE;
    }

    private Deque<String> getAllTargets(String netAddr) {
        var targets = new ArrayDeque<String>();
        for (int i = 2; i < 256; i++) {
            var addr = String.format("%s.%d", netAddr, i);
            targets.add(addr);
        }
        return targets;
    }

    private List<List<String>> createBatches(String netAddr) {
        var batches = new ArrayList<List<String>>();
        final var targets = getAllTargets(netAddr);
        final var total = targets.size();
        final var futureSize = (total + BATCH_SIZE - 1) / BATCH_SIZE;

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

    public void run(AppParams params) {
        System.out.println("scanning ssh port...\n");

        final var scanner = new Scanner();
        final var batches = createBatches(params.netAddr());
        final var n = batches.size();
        var futures = new CompletableFuture[n];

        for (int i = 0; i < n; i++) {
            final var b = batches.get(i);
            final var fut = CompletableFuture.runAsync(() -> scanner.ScanList(b), Executer());
            futures[i] = fut;
        }

        final var all = CompletableFuture.allOf(futures);
        all.join();

        System.out.println("\ndone");
    }
}
