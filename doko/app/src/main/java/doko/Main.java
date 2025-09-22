package doko;

import picocli.CommandLine;
import picocli.CommandLine.Option;

final class Command implements Runnable { 
    @Option(names={"--netaddr"})
    private String _netAddr = "192.168.11";

    public void run() {
        try {
            var appInstance = App.instance();
            appInstance.run(new AppParams(_netAddr));
            App.shutdown();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            App.shutdown();
        }
    }
}

public final class Main {
    public static void main(String[] args) {
        new CommandLine(new Command()).execute(args);
    }    
}
