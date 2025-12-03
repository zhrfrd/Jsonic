package zhrfrd.jsonic;

/**
 * Represents a JSON string value within the Jsonic library.
 */
public final class JSONString implements JSONValue {
    private final String value;

    /**
     * Create a new {@code JSONString} instance.
     * @param value The string content to represent as a JSON value.
     */
    public JSONString(String value) {
        this.value = value;
    }

    @Override
    public JSONType getType() {
        return JSONType.STRING;
    }

    @Override
    public String asString() {
        return value;
    }

    @Override
    public String toString() {
        return "\"" + value + "\"";
    }
}
