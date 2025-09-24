package doko;

public class NetAddr {
    private static final String IP_V4_FMT = "^((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)\\.){2}(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)$";

    public static boolean isV4Format(String netAddr) {
        return netAddr.matches(IP_V4_FMT);
    }
}
