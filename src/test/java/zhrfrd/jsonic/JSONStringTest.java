package zhrfrd.jsonic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONStringTest {
    @Test
    void testParseString() {
        JSONValue v = JSON.parse("\"hello\"");
        assertEquals("hello", v.asString());
        assertEquals(JSONType.STRING, v.getType());
    }
}
