package doko;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class Scanner {
    private static final int SSH_PORT = 22;
    private static final int TIME_OUT = 250;

    public void Scan(String addr) {
        try {
            final var iAddr = InetAddress.getByName(addr);
            final var soc = new Socket();
            soc.connect(new InetSocketAddress(iAddr, SSH_PORT), TIME_OUT);
            soc.close();
            final var host = iAddr.getHostName();
            System.out.println(addr + " -> " + host);
        } catch (Exception e) {
            return;
        }
    }

    public void ScanList(List<String> addrs) {
        for (String addr : addrs) {
            Scan(addr);
        }
    }
}
