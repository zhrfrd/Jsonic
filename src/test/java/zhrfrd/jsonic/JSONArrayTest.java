package zhrfrd.jsonic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JSONArrayTest {

    @Test
    void testEmptyArray() {
        JSONArray arr = JSON.parse("[]").asArray();
        assertEquals(0, arr.size());
    }

    @Test
    void testSimpleArray() {
        JSONArray arr = JSON.parse("[1, 2, 3]").asArray();

        assertEquals(3, arr.size());
        assertEquals(1, arr.get(0).asNumber());
        assertEquals(2, arr.get(1).asNumber());
        assertEquals(3, arr.get(2).asNumber());
    }

    @Test
    void testMixedArray() {
        JSONArray arr = JSON.parse("[1, \"hi\", true, null]").asArray();

        assertEquals(1, arr.get(0).asNumber());
        assertEquals("hi", arr.get(1).asString());
        assertEquals(true, arr.get(2).asBoolean());
        assertTrue(arr.get(3).isNull());
    }

    @Test
    void testNestedArrays() {
        JSONArray arr = JSON.parse("[[1,2], [3,4]]").asArray();

        JSONArray a = arr.get(0).asArray();
        JSONArray b = arr.get(1).asArray();

        assertEquals(1, a.get(0).asNumber());
        assertEquals(4, b.get(1).asNumber());
    }

    @Test
    void testArrayInObject() {
        JSONObject obj = JSON.parse("{\"numbers\":[10,20]}").asObject();

        JSONArray arr = obj.getArray("numbers");
        assertEquals(10, arr.get(0).asNumber());
    }
}
