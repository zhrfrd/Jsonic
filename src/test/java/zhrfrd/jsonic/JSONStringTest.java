package zhrfrd.jsonic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONStringTest {
    @Test
    void asString_shouldReturnTheStringWhenParsingAJSONStringValue() {
        JSONValue jsonValue = JSON.parse("\"Hello\"");
        assertEquals("Hello", jsonValue.asString());
    }

    @Test
    void getType_shouldReturnTheJSONTypeStringWhenParsingAJSONStringValue() {
        JSONValue jsonValue = JSON.parse("\"Hello\"");
        assertEquals(JSONType.STRING, jsonValue.getType());
    }
//
//    @Test
//    void getString_string_returnsParsedString() {
//        JSON json = new JSON("\"Hello World\"");
//        assertEquals("Hello World", json.getString(), "Should parse a JSON string correctly.");
//    }
//
//    @Test
//    void getString_stringWithNewline_returnsParsedString() {
//        JSON json = new JSON("\"Hello \n World\"");
//        assertEquals("Hello \n World", json.getString(), "Should parse a JSON string correctly.");
//    }
//
//    @Test
//    void getString_emptyInput_throwException() {
//        assertThrows(IllegalStateException.class, () -> {
//            JSON json = new JSON("");
//            json.getString();
//        }, "Parsing an empty json and calling getString() method should throw an exception.");
//    }
//
//    @Test
//    void getString_emptyObject_throwException() {
//        assertThrows(IllegalStateException.class, () -> {
//            JSON json = new JSON("{}");
//            json.getString();
//        }, "Parsing an empty object and calling getString() method should throw an exception.");
//    }
//
//    @Test
//    void getString_emptyArray_throwException() {
//        assertThrows(IllegalStateException.class, () -> {
//            JSON json = new JSON("[]");
//            json.getString();
//        }, "Parsing an empty array and calling getString() method should throw an exception.");
//    }
//
//    @Test
//    void getString_literal_throwException() {
//        assertThrows(IllegalStateException.class, () -> {
//            JSON json = new JSON("TRUE");
//            json.getString();
//        }, "Parsing a literal and calling getString() method should throw an exception.");
//    }
//
//    @Test
//    void getString_number_throwException() {
//        assertThrows(IllegalStateException.class, () -> {
//            JSON json = new JSON("42");
//            json.getString();
//        }, "Parsing a literal and calling getString() method should throw an exception.");
//    }
//
//    @Test
//    void getString_missingClosingQuote_throwsException() {
//        assertThrows(IllegalStateException.class, () -> {
//            JSON json = new JSON("\"Hello World");
//            json.getString();
//        }, "Parsing a string without closing quote should throw an exception.");
//    }
}