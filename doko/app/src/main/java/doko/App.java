package doko;

public class App {
    public static void run() {
        for (int i = 2; i < 256; i++) {
            var addr = String.format("192.168.11.%d", i);
            Scanner.Scan(addr);
        }

        System.out.println("done");
    }
}
