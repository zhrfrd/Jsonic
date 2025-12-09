package zhrfrd.jsonic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JSONArrayTest {
    @Test
    void size_shouldReturnZeroWhenParsingAnEmptyArray() {
        JSONArray arr = JSON.parse("[]").asArray();
        assertEquals(0, arr.size());
    }

    @Test
    void get_shouldReturnTheNumberAtTheIndexSpecifiedOfAnArrayOfNumbers() {
        JSONArray arr = JSON.parse("[1, 2, 3]").asArray();

        assertEquals(1, arr.get(0).asNumber());
        assertEquals(2, arr.get(1).asNumber());
        assertEquals(3, arr.get(2).asNumber());
    }

    @Test
    void get_shouldReturnTheValueAtTheIndexSpecifiedOfAMixedArray() {
        JSONArray arr = JSON.parse("[1, \"Hello\", true, null]").asArray();

        assertEquals(1, arr.get(0).asNumber());
        assertEquals("Hello", arr.get(1).asString());
        assertEquals(true, arr.get(2).asBoolean());
        assertTrue(arr.get(3).isNull());
    }

    @Test
    void get_shouldReturnTheValueAtTheIndexSpecifiedOfANestedArray() {
        JSONArray arr = JSON.parse("[[1,2], [3,4]]").asArray();

        JSONArray a = arr.get(0).asArray();
        JSONArray b = arr.get(1).asArray();

        assertEquals(1, a.get(0).asNumber());
        assertEquals(4, b.get(1).asNumber());
    }
}
