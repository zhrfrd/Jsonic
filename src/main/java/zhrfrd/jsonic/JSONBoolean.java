package zhrfrd.jsonic;

/**
 * Represents a JSON boolean value within the Jsonic library.
 */
public final class JSONBoolean implements JSONValue {
    public static final JSONBoolean TRUE = new JSONBoolean(true);
    public static final JSONBoolean FALSE = new JSONBoolean(false);
    private final boolean value;

    /**
     * Create a new {@code JSONBoolean}.
     * @param value The boolean value to create the {@code JSONBoolean}.
     */
    private JSONBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public JSONType getType() {
        return JSONType.BOOLEAN;
    }

    @Override
    public Boolean asBoolean() {
        return value;
    }
}
