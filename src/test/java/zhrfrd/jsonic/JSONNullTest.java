package zhrfrd.jsonic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JSONNullTest {
    @Test
    void testParseNull() {
        JSONValue v = JSON.parse("null");
        assertTrue(v.isNull());
        assertEquals(JSONType.NULL, v.getType());
    }
}
