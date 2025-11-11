package doko;

import picocli.CommandLine.Option;

final class Command implements Runnable { 
    @Option(names={"--netaddr"}, description="format like '192.168.11'")
    private String _netAddr = "192.168.11";

    public void run() {
        if (!NetAddr.isV4Format(_netAddr)) {
            System.err.println("invalid net address, format should be something like '192.168.11'");
        }
        
        try {
            final var appInstance = App.instance();
            appInstance.run(new AppParams(_netAddr));
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            App.shutdown();
        }
    }
}
