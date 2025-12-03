package zhrfrd.jsonic;

import java.util.List;

/**
 * Represents a JSON array value within the Jsonic library.
 */
public final class JSONArray implements JSONValue {
    private final List<JSONValue> list;

    /**
     * Create a new {@code JSONArray}.
     * @param list The list of {@link JSONValue} objects to form the {@code JSONArray}.
     */
    public JSONArray(List<JSONValue> list) {
        this.list = List.copyOf(list); // immutable
    }

    /**
     * Get the raw JSON value at the specified index.
     * @param index The index of the value to retrieve.
     * @return The corresponding {@code JSONValue}.
     */
    public JSONValue get(int index) {
        return list.get(index);
    }

    /**
     * Get the {@code JSONArray} size.
     * @return The integer representing how many elements are contained in the {@code JSONArray}.
     */
    public int size() {
        return list.size();
    }

    @Override
    public JSONType getType() {
        return JSONType.ARRAY;
    }

    @Override
    public JSONArray asArray() {
        return this;
    }
}
