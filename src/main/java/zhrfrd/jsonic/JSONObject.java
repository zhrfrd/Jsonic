package zhrfrd.jsonic;

import java.util.Map;

/**
 * Represents a JSON object value within the Jsonic library.
 */
public final class JSONObject implements JSONValue {
    private final Map<String, JSONValue> map;

    /**
     * Create a new {@code JSONObject} with the given key–value mapping.
     * @param map The mapping of field names to JSON values.
     */
    public JSONObject(Map<String, JSONValue> map) {
        this.map = Map.copyOf(map);
    }

    /**
     * Return the raw JSON value associated with the specified key.
     * @param key The field name to retrieve.
     * @return The corresponding {@link JSONValue}.
     */
    public JSONValue get(String key) {
        return map.get(key);
    }

    /**
     * Return the value associated with the given key as a string.
     * @param key The field name to retrieve.
     * @return The string value.
     */
    public String getString(String key) {
        return map.get(key).asString();
    }

    /**
     * Return the value associated with the given key as a number.
     * @param key The field name to retrieve.
     * @return The numeric value.
     */
    public Number getNumber(String key) {
        return map.get(key).asNumber();
    }

    /**
     * Return the value associated with the given key as a boolean.
     * @param key The field name to retrieve.
     * @return The boolean value.
     */
    public Boolean getBoolean(String key) {
        return map.get(key).asBoolean();
    }

    /**
     * Return the value associated with the given key as a {@link JSONArray}.
     * @param key The field name to retrieve.
     * @return The JSON array value.
     */
    public JSONArray getArray(String key) {
        return map.get(key).asArray();
    }

    /**
     * Returns the value associated with the given key as a {@code JSONObject}.
     * @param key The field name to retrieve.
     * @return The JSON object value.
     */
    public JSONObject getObject(String key) {
        return map.get(key).asObject();
    }

    @Override
    public JSONType getType() {
        return JSONType.OBJECT;
    }

    @Override
    public JSONObject asObject() {
        return this;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
