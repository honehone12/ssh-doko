package doko;

import picocli.CommandLine;
import picocli.CommandLine.Option;

final class Command implements Runnable {

    @Option(names={"--netaddr"})
    private String netAddr = "192.268.11";

    public void run() {
        App.run(new AppParams(netAddr));
    }
}

public final class Main {
    public static void main(String[] args) {
        new CommandLine(new Command()).execute(args);
        App.shutdown();
    }    
}
