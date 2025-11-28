package zhrfrd.jsonic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JSONTest {
    @Test
    void parseString_stringInputted_returnsParsedString() {
        JSON json = new JSON("\"Hello World\"");
        assertEquals("Hello World", json.getString(), "Should parse a JSON string correctly");
    }

    @Test
    void parseString_missingClosingQuote_throwsException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("\"Hello World");
            json.getString();
        }, "Parsing a string without closing quote should throw an exception");
    }
}