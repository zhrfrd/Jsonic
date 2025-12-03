package zhrfrd.jsonic;

import zhrfrd.jsonic.internal.JSONParser;

/**
 * Entry point for working with the Jsonic JSON library.
 * <p>This class  cannot be instantiated.</p>
 */
public final class JSON {
    private JSON() {}

    /**
     * Parses the given JSON string and returns the resulting {@link JSONValue}.
     * @param jsonString The JSON input string to parse.
     * @return The parsed JSON representation.
     * @throws IllegalArgumentException if the input is not valid JSON.
     */
    public static JSONValue parse(String jsonString) {
        JSONParser parser = new JSONParser(jsonString);
        return parser.parse();
    }

    /**
     * Converts a {@link JSONValue} into its JSON string representation.
     * @param value The JSON value to stringify.
     * @return The JSON-formatted string representing the {@link JSONValue}.
     */
    public static String stringify(JSONValue value) {
        return value.toString();
    }
}
