package doko;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NetAddrTest {
    @Test void testIsV4Format() {
        assertTrue(NetAddr.isV4Format("192.168.1"));
        assertTrue(NetAddr.isV4Format("10.0.0"));
        assertTrue(NetAddr.isV4Format("172.16.0"));
        assertTrue(NetAddr.isV4Format("255.255.255"));
        assertTrue(NetAddr.isV4Format("0.0.0"));

        assertFalse(NetAddr.isV4Format("192.168.1.1"));
        assertFalse(NetAddr.isV4Format("192.168"));
        assertFalse(NetAddr.isV4Format("192.168.1."));
        assertFalse(NetAddr.isV4Format(".192.168.1"));
        assertFalse(NetAddr.isV4Format("192.168.1.a"));
        assertFalse(NetAddr.isV4Format("192.168.256"));
        assertFalse(NetAddr.isV4Format("192.168.-1"));
        assertFalse(NetAddr.isV4Format(""));
        assertTrue(NetAddr.isV4Format("192.168.11"));
    }
}
