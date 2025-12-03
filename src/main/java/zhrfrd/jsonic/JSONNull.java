package zhrfrd.jsonic;

/**
 * Represents the JSON {@code null} literal.
 * <p>
 * This class is implemented as a singleton because all occurrences of
 * {@code null} in JSON are equivalent and carry no additional state.
 * Use {@link #INSTANCE} to obtain the single shared instance.
 * </p>
 */
public final class JSONNull implements JSONValue {
    /**
     * The single instance of {@code JSONNull}.
     */
    public static final JSONNull INSTANCE = new JSONNull();

    /**
     * Private constructor to enforce the singleton pattern.
     */
    private JSONNull() {}

    @Override
    public JSONType getType() {
        return JSONType.NULL;
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
