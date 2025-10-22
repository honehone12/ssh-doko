package doko;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

class AppTest {
    @Test void testGetAllTargets() {
        var app = App.instance();
        var targets = app.getAllTargets("192.168.1");
        assertEquals(254, targets.size());
        assertEquals("192.168.1.2", targets.getFirst());
        assertEquals("192.168.1.255", targets.getLast());
    }

    @Test void testCreateBatches() {
        var app = App.instance();
        var batches = app.createBatches("192.168.1");
        assertFalse(batches.isEmpty());

        int totalItems = 0;
        for (List<String> batch : batches) {
            assertFalse(batch.isEmpty());
            totalItems += batch.size();
        }
        assertEquals(254, totalItems);
    }
}