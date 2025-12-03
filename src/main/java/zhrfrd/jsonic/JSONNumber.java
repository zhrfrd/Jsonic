package zhrfrd.jsonic;

/**
 * Represents a generic JSON number in the Jsonic library.
 */
public final class JSONNumber implements JSONValue {
    private final Number value;

    /**
     * Create a new {@code JSONNumber}.
     * @param value The value associated to the new {@code JSONNumber} created.
     */
    public JSONNumber(Number value) {
        this.value = value;
    }

    @Override
    public JSONType getType() {
        return JSONType.NUMBER;
    }

    @Override
    public Number asNumber() {
        return value;
    }
}
