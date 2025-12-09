package zhrfrd.jsonic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONValueTest {
    @Test
    void getType_shouldReturnTheValueTypeOfTheObjectPassed() {
        JSONValue value = JSON.parse("false");
        assertEquals(JSONType.BOOLEAN, value.getType());
    }
}
