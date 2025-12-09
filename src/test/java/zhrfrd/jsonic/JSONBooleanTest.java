package zhrfrd.jsonic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONBooleanTest {
    @Test
    void asBoolean_shouldReturnTheBooleanTrueIfTrueIsParsed() {
        JSONValue value = JSON.parse("true");
        assertEquals(true, value.asBoolean());
    }

    @Test
    void asBoolean_shouldReturnTheBooleanFalseIfFalseIsParsed() {
        JSONValue value = JSON.parse("false");
        assertEquals(false, value.asBoolean());
    }


}
