package doko;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Scanner {
    private static final int SSH_PORT = 22;
    private static final int TIME_OUT = 100;

    public static void Scan(String addr) {
        try {
            System.out.println("trying " + addr);
            var iAddr = InetAddress.getByName(addr);
            var soc = new Socket();
            soc.connect(new InetSocketAddress(iAddr, SSH_PORT), TIME_OUT);
            soc.close();
            var host = iAddr.getHostName();
            System.out.println(addr + " -> " + host);
        } catch (Exception e) {
            return;
        }
    }
}
