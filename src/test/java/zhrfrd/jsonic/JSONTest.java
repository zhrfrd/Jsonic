package zhrfrd.jsonic;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JSONTest {
    @Test
    void getString_stringInputted_returnsParsedString() {
        JSON json = new JSON("\"Hello World\"");
        assertEquals("Hello World", json.getString(), "Should parse a JSON string correctly");
    }

    @Test
    void getString_stringInputtedWithNewline_returnsParsedString() {
        JSON json = new JSON("\"Hello \n World\"");
        assertEquals("Hello \n World", json.getString(), "Should parse a JSON string correctly");
    }

    @Test
    void getString_openAndCloseBrackets_throwException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("{}");
            json.getString();
        }, "Parsing an empty object and calling getString() method should throw an exception");
    }

    @Test
    void getString_missingClosingQuote_throwsException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("\"Hello World");
            json.getString();
        }, "Parsing a string without closing quote should throw an exception");
    }

    @Test
    void getObject_emptyObject_returnParsedObject() {
        JSON json = new JSON("{}");
        Object obj = json.getObject();
        assertInstanceOf(Map.class, obj, "Parsed object should be a Map");

        Map<?, ?> map = (Map<?, ?>) obj;
        assertTrue(map.isEmpty(), "Parsed map should be empty");
    }
}