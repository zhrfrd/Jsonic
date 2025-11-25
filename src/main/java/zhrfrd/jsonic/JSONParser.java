package zhrfrd.jsonic;

import java.util.ArrayList;
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
            case TRUE, FALSE, NULL -> parseLiteral();
            default -> throw new IllegalStateException("Invalid token: " + currentToken.getTokenType());
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
        advance();   // Skip '{'
        Map<String, Object> map = new HashMap<>();

        while (true) {
            String key = parseString();
            advance();   // Skip ':'
            Object value = parseValue();
            map.put(key, value);

            if (currentToken.getTokenType() == TokenType.COMMA) {
                advance();   // Skip ','
                continue;
            }

            advance();   // Skip '}'
            return map;
        }
    }

    private List<Object> parseArray() {
        advance();   // Skip '['
        List<Object> list = new ArrayList<>();

        while (true) {
            list.add(parseValue());

            if (currentToken.getTokenType() == TokenType.COMMA) {
                advance();   // Skip ','
                continue;
            }

            advance();   // Skip ']'
            return list;
        }
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

    /**
     * Parse the current {@link Token} as a {@code JSON} number and advance.
     * @return a {@link Number} representing the value of the current token ({@link Long} for integers, {@link Double} for decimals).
     */
    private Number parseNumber() {
        String rawNumber = currentToken.getTokenValue();
        advance();

        if (rawNumber.contains(".")) {
            return Double.valueOf(rawNumber);
        }

        return Long.valueOf(rawNumber);
    }

    /**
     * <p>
     * Parses the current {@link Token} as a {@code JSON} literal and advances.
     * </p>
     * Supported JSON literals are {@code TRUE}, {@code FALSE} and {@code NULL}.
     * @return The corresponding Java representation of the {@code JSON} literal:
     *         {@code Boolean} for {@code TRUE}/{@code FALSE}, or {@code null} for {@code NULL}.
     * @throws RuntimeException If the current token is not a valid literal.
     */
    private Object parseLiteral() {
        return switch (currentToken.getTokenType()) {
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
            default -> throw new RuntimeException("Invalid literal: " + currentToken.getTokenType());
        };
    }
}
