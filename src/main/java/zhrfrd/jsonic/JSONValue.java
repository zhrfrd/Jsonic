package zhrfrd.jsonic;

/**
 * Represents a generic JSON value in the Jsonic library.
 * @see JSONObject
 * @see JSONArray
 * @see JSONString
 * @see JSONNumber
 * @see JSONBoolean
 * @see JSONNull
 */
public interface JSONValue {
    /**
     * Returns the specific JSON type represented by this value.
     * @return The {@link JSONType} of this JSON value.
     */
    JSONType getType();

    /**
     * Returns this value as a {@link JSONObject}.
     * @return this value as a {@code JSONObject}.
     * @throws IllegalStateException if this value is not a JSON object.
     */
    default JSONObject asObject() {
        throw new IllegalStateException("Not a JSON object!");
    }

    /**
     * Returns this value as a {@link JSONArray}.
     * @return This value as a {@code JSONArray}.
     * @throws IllegalStateException if this value is not a JSON array.
     */
    default JSONArray asArray() {
        throw new IllegalStateException("Not a JSON array!");
    }

    /**
     * Returns this value as a {@code String}.
     * @return The underlying string value.
     * @throws IllegalStateException if this value is not a JSON string.
     */
    default String asString() {
        throw new IllegalStateException("Not a JSON string!");
    }

    /**
     * Returns this value as a {@link JSONNumber}.
     * @return The underlying numeric value.
     * @throws IllegalStateException if this value is not a JSON number.
     */
    default Number asNumber() {
        throw new IllegalStateException("Not a JSON number!");
    }

    /**
     * Returns this value as a {@code Boolean}.
     * @return The underlying boolean value.
     * @throws IllegalStateException if this value is not a JSON boolean.
     */
    default Boolean asBoolean() {
        throw new IllegalStateException("Not a JSON boolean!");
    }

    /**
     * Indicates whether this value represents JSON {@code null}.
     * @return {@code true} if this value is JSON {@code null}, {@code false} otherwise.
     */
    default boolean isNull() {
        return false;
    }
}
