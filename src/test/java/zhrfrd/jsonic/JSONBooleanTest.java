package zhrfrd.jsonic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONBooleanTest {
    @Test
    void testParseTrue() {
        JSONValue v = JSON.parse("true");
        assertEquals(true, v.asBoolean());
        assertEquals(JSONType.BOOLEAN, v.getType());
    }

    @Test
    void testParseFalse() {
        JSONValue v = JSON.parse("false");
        assertEquals(false, v.asBoolean());
        assertEquals(JSONType.BOOLEAN, v.getType());
    }
}
