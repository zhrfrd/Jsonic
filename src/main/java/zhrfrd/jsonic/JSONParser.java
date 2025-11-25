package zhrfrd.jsonic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONParser {
    private final List<Token> tokens;
    private int indexToken = 0;
    private Token currentToken;

    public JSONParser(String jsonString) {
        Lexer lexer = new Lexer(jsonString);
        tokens = lexer.getTokens();
        currentToken = tokens.get(indexToken);
    }

    /**
     * Entry point for parsing a {@code JSON} value from the list of tokens.
     * @return The parsed Java object representing the {@code JSON} value.
     */
    public Object parse() {
        return parseValue();
    }

    /**
     * Parse the current {@link Token} and return its corresponding Java representation.
     * @return The parsed Java object representing the {@code JSON} value.
     * @throws IllegalStateException If the current {@link TokenType} cannot be parsed.
     */
    public Object parseValue() {
        return switch (currentToken.getTokenType()) {
            case OPEN_BRACKET -> parseObject();
            case OPEN_BRACE -> parseArray();
            case STRING -> parseString();
            case NUMBER -> parseNumber();
            case TRUE -> {
                advance();
                yield true;
            }
            case FALSE -> {
                advance();
                yield false;
            }
            case NULL -> {
                advance();
                yield null;
            }
            default -> throw new IllegalStateException("Invalid token type: " + currentToken.getTokenType());
        };
    }

    /**
     * Advance the {@code JSONParser} to the next {@link Token} in the list of tokens.
     */
    private void advance() {
        indexToken ++;
        currentToken = tokens.get(indexToken);
    }

    /**
     * Parses the current {@link Token} as a {@code JSON} {@code Object} and advance.
     * @return A {@code Map} representing the parsed {@code JSON} object, with specific key-value pair.
     */
    private Object parseObject() {
        advance();   // Skip first '{'
        Map<String, Object> map = new HashMap<>();

        while (true) {
            String key = parseString();
            advance();   // Skip ':'
            Object value = parseValue();
            map.put(key, value);

            if (currentToken.getTokenType() == TokenType.COMMA) {
                advance();
                continue;
            }

            advance();   // Skip '}'
            return map;
        }
    }

    private Object parseArray() {
        return null;
    }

    /**
     * Parses the current {@link Token} as a {@code JSON} {@code String} and advance.
     * @return The {@code String} value of the current {@link Token}.
     */
    private String parseString() {
        String value = currentToken.getTokenValue();
        advance();
        return value;
    }

    private Object parseNumber() {
        return null;
    }
}
