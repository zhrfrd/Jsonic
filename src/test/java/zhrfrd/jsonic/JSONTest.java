package zhrfrd.jsonic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JSONTest {
    @Test
    void parseString_stringInputted_correctlyParsed() {
        JSON json = new JSON("\"Hello World\"");
        assertEquals("Hello World", json.getString(), "Should parse a JSON string correctly");
    }
}