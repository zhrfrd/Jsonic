package zhrfrd.jsonic;

import zhrfrd.jsonic.internal.JSONParser;

import java.lang.reflect.Array;

public class JSON {
    private JSONParser jsonParser;

    public JSON(String jsonString) {
        jsonParser = new JSONParser(jsonString);
        jsonParser.parse();
    }

    public String getString() {
        return null;
    }

    public Object getObject() {
        return null;
    }

    public Array getArray() {
        return null;
    }
}
