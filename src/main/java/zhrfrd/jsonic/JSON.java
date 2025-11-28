package zhrfrd.jsonic;

import zhrfrd.jsonic.internal.JSONParser;

import java.lang.reflect.Array;
import java.util.Map;

public class JSON {
    private final Object jsonValue;

    public JSON(String jsonString) {
        JSONParser jsonParser = new JSONParser(jsonString);
        jsonValue = jsonParser.parse(); // store the parsed result
    }

    public String getString() {
        return jsonValue.toString();
    }

    public Object getObject() {
        if (jsonValue instanceof Map<?, ?> map) {
            return map;
        }

        throw new IllegalStateException("JSON value is not an object.");
    }

    public Array getArray() {
        return null;
    }
}
