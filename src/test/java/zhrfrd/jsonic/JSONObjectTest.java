package zhrfrd.jsonic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JSONObjectTest {
    @Test
    void testEmptyObject() {
        JSONObject obj = JSON.parse("{}").asObject();
        assertNotNull(obj);
    }

    @Test
    void testSimpleObject() {
        JSONObject obj = JSON.parse("{\"name\":\"Bubba\",\"age\":25}").asObject();

        assertEquals("Bubba", obj.getString("name"));
        assertEquals(25, obj.getNumber("age"));
    }

    @Test
    void testNestedObject() {
        JSONObject obj = JSON.parse("""
            {"user": {"id": 10, "enabled": true}}
        """).asObject();

        JSONObject user = obj.getObject("user");
        assertEquals(10, user.getNumber("id"));
        assertEquals(true, user.getBoolean("enabled"));
    }

    @Test
    void testMissingKeyReturnsNull() {
        JSONObject obj = JSON.parse("{\"a\":1}").asObject();
        assertNull(obj.get("missing"));
    }

    @Test
    void testInvalidTypeThrows() {
        JSONObject obj = JSON.parse("{\"x\":10}").asObject();
        JSONValue x = obj.get("x");

        assertThrows(IllegalStateException.class, x::asString);
        assertThrows(IllegalStateException.class, x::asArray);
    }
}
