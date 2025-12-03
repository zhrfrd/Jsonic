package zhrfrd.jsonic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONNumberTest {
    @Test
    void testParseInteger() {
        JSONValue v = JSON.parse("42");
        assertEquals(42, v.asNumber());
        assertEquals(JSONType.NUMBER, v.getType());
    }

    @Test
    void testParseLargeNumber() {
        JSONValue v = JSON.parse("999999999999");
        assertEquals(999999999999L, v.asNumber());
    }

    @Test
    void testParseDouble() {
        JSONValue v = JSON.parse("3.14");
        assertEquals(3.14, v.asNumber());
    }
}
