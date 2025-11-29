package zhrfrd.jsonic;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JSONTest {
    @Test
    void getString_string_returnsParsedString() {
        JSON json = new JSON("\"Hello World\"");
        assertEquals("Hello World", json.getString(), "Should parse a JSON string correctly.");
    }

    @Test
    void getString_stringWithNewline_returnsParsedString() {
        JSON json = new JSON("\"Hello \n World\"");
        assertEquals("Hello \n World", json.getString(), "Should parse a JSON string correctly.");
    }

    @Test
    void getString_emptyInput_throwException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("");
            json.getString();
        }, "Parsing an empty json and calling getString() method should throw an exception.");
    }

    @Test
    void getString_emptyObject_throwException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("{}");
            json.getString();
        }, "Parsing an empty object and calling getString() method should throw an exception.");
    }

    @Test
    void getString_emptyArray_throwException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("[]");
            json.getString();
        }, "Parsing an empty array and calling getString() method should throw an exception.");
    }

    @Test
    void getString_literal_throwException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("TRUE");
            json.getString();
        }, "Parsing a literal and calling getString() method should throw an exception.");
    }

    @Test
    void getString_number_throwException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("42");
            json.getString();
        }, "Parsing a literal and calling getString() method should throw an exception.");
    }

    @Test
    void getString_missingClosingQuote_throwsException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("\"Hello World");
            json.getString();
        }, "Parsing a string without closing quote should throw an exception.");
    }

    @Test
    void getObject_emptyObject_returnParsedObject() {
        JSON json = new JSON("{}");
        Object obj = json.getObject();
        assertInstanceOf(Map.class, obj, "Parsed object should be a Map.");

        Map<?, ?> map = (Map<?, ?>)obj;
        assertTrue(map.isEmpty(), "Parsed map should be empty.");
    }

    @Test
    void getObject_emptyObjectWithMissingClosingBrace_throwException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("{");
            json.getObject();
        }, "Parsing an empty object with missing closing brace and calling getObject() method should throw an exception.");
    }

    @Test
    void getObject_emptyObjectWithMissingOpeningBrace_throwException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("}");
            json.getObject();
        }, "Parsing an empty object with missing opening brace and calling getObject() method should throw an exception.");
    }

    @Test
    void getObject_nonEmptyObjectWithMissingClosingBrace_throwException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("{1:2");
            json.getObject();
        }, "Parsing an empty object with missing last brace and calling getObject() method should throw an exception.");
    }

    @Test
    void getObject_nonEmptyObjectStringToString_returnParsedObject() {
        JSON json = new JSON("{\"a\" : \"b\"}");
        Object obj = json.getObject();
        assertInstanceOf(Map.class, obj, "Parsed object should be a Map.");

        Map<?, ?> map = (Map<?, ?>)obj;
        assertEquals(1, map.size(), "Object should contain exactly 1 entry.");
        assertTrue(map.containsKey("a"), "Object should contain key 'a'.");
        assertEquals("b", map.get("a"), "Value for key 'a' should be 'b'.");
    }

    @Test
    void getObject_nonEmptyObjectStringToInteger_returnParsedObject() {
        JSON json = new JSON("{\"a\" : 3}");
        Object obj = json.getObject();
        assertInstanceOf(Map.class, obj, "Parsed object should be a Map.");

        Map<?, ?> map = (Map<?, ?>)obj;
        assertEquals(1, map.size(), "Object should contain exactly 1 entry.");
        assertTrue(map.containsKey("a"), "Object should contain key 'a'.");
        assertEquals(3, map.get("a"), "Value for key 'a' should be 3.");
    }

    @Test
    void getObject_nonEmptyObjectStringToDouble_returnParsedObject() {
        JSON json = new JSON("{\"a\" : 3.5}");
        Object obj = json.getObject();
        assertInstanceOf(Map.class, obj, "Parsed object should be a Map.");

        Map<?, ?> map = (Map<?, ?>)obj;
        assertEquals(1, map.size(), "Object should contain exactly 1 entry.");
        assertTrue(map.containsKey("a"), "Object should contain key 'a'.");
        assertEquals(3.5, map.get("a"), "Value for key 'a' should be 3.5.");
    }

    @Test
    void getObject_string_throwException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("\"Hello\"");
            json.getObject();
        }, "Parsing a string and calling getObject() method should throw an exception.");
    }

    @Test
    void getObject_emptyInput_throwException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("");
            json.getObject();
        }, "Parsing an empty json and calling getObject() method should throw an exception.");
    }

    @Test
    void getObject_emptyArray_throwException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("[]");
            json.getObject();
        }, "Parsing an empty array and calling getObject() method should throw an exception.");
    }

    @Test
    void getObject_literal_throwException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("FALSE");
            json.getObject();
        }, "Parsing a literal and calling getObject() method should throw an exception.");
    }

    @Test
    void getObject_number_throwException() {
        assertThrows(IllegalStateException.class, () -> {
            JSON json = new JSON("42");
            json.getObject();
        }, "Parsing a literal and calling getObject() method should throw an exception.");
    }
}