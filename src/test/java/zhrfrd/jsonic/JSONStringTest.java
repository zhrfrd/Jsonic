package zhrfrd.jsonic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JSONStringTest {
    @Test
    void getType_shouldReturnTheJSONTypeStringWhenParsingAJSONStringValue() {
        JSONValue jsonValue = JSON.parse("\"Hello\"");
        assertEquals(JSONType.STRING, jsonValue.getType());
    }

    @Test
    void asString_shouldReturnExactSameStringWhenParsingStringWithNewLine() {
        JSONValue jsonValue = JSON.parse("\"Hello \n How are you?\"");
        assertEquals("Hello \n How are you?", jsonValue.asString());
    }

    @Test
    void asString_shouldReturnTheStringWhenParsingAJSONStringValue() {
        JSONValue jsonValue = JSON.parse("\"Hello\"");
        assertEquals("Hello", jsonValue.asString());
    }

    @Test
    void asString_shouldReturnAnEmptyStringWhenParsingAnEmptyJSONStringValue() {
        JSONValue jsonValue = JSON.parse("\"\"");
        assertEquals("", jsonValue.asString());
    }

    @Test
    void asString_shouldThrowAnExceptionWhenParsingAnEmptyJSONValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            JSONValue jsonValue = JSON.parse("");
            assertEquals("", jsonValue.asString());
        }, "Parsing an empty json and calling getString() should throw an exception.");
    }

    @Test
    void asString_shouldThrowAnExceptionWhenParsingAStringWithoutClosingQuote() {
        assertThrows(IllegalStateException.class, () -> {
            JSONValue jsonValue = JSON.parse("Hello\"");
            jsonValue.asString();
        }, "Parsing an json string with missing closing quotes and calling asString() should throw an exception.");
    }
}