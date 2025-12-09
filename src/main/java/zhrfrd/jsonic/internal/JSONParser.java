package zhrfrd.jsonic.internal;

import zhrfrd.jsonic.*;

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
     * @return The parsed {@code JSON} object representing the {@code JSON} value.
     */
    public JSONValue parse() {
        return parseValue();
    }

    /**
     * Parse the current {@link Token} and return its corresponding {@code JSON} value.
     * @return The parsed {@code JSON} object representing the {@code JSON} value.
     * @throws IllegalStateException If the current {@link TokenType} cannot be parsed.
     */
    private JSONValue parseValue() {
        return switch (currentToken.getTokenType()) {
            case OPEN_BRACE -> parseObject();
            case OPEN_BRACKET -> parseArray();
            case STRING -> parseString();
            case NUMBER -> parseNumber();
            case TRUE, FALSE, NULL -> parseLiteral();
            case EOF -> throw new IllegalArgumentException("Empty or invalid JSON input");   // JSONNull.INSTANCE;
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
     * Parse the current {@link Token} as a {@code JSON} object and advance.
     * @return A {@code Map} representing the parsed {@code JSON} object, with specific key-value pair.
     */
    private JSONValue parseObject() {
        advance();   // Skip '{'
        Map<String, JSONValue> map = new HashMap<>();

        if (currentToken.getTokenType() == TokenType.CLOSE_BRACE) {
            advance();   // Skip '}'
            return new JSONObject(map);
        }

        while (true) {
            String key = parseString().asString();

            if (currentToken.getTokenType() != TokenType.COLON) {
                throw new IllegalStateException("Invalid token: " + currentToken.getTokenType() + ". Expected ':'.");
            }

            advance();   // Skip ':'
            JSONValue value = parseValue();
            map.put(key, value);

            if (currentToken.getTokenType() == TokenType.COMMA) {
                advance();   // Skip ','
                continue;
            }

            if (currentToken.getTokenType() == TokenType.CLOSE_BRACE) {
                advance();   // Skip '}'
                return new JSONObject(map);
            }

            throw new IllegalStateException("Invalid token: " + currentToken.getTokenType() + ". Expected ',' or '}'.");
        }
    }

    /**
     * Parse the current {@link Token} as a {@code JSON} list and advance.
     * @return A {@code List} representing the parsed {@code JSON} array.
     */
    private JSONValue parseArray() {
        advance();   // Skip '['
        List<JSONValue> list = new ArrayList<>();

        if (currentToken.getTokenType() == TokenType.CLOSE_BRACKET) {
            advance();   // Skip ']'
            return new JSONArray(list);
        }

        while (true) {
            list.add(parseValue());

            if (currentToken.getTokenType() == TokenType.COMMA) {
                advance();   // Skip ','
                continue;
            }

            if (currentToken.getTokenType() == TokenType.CLOSE_BRACKET) {
                advance();   // Skip ']'
                return new JSONArray(list);
            }

            throw new IllegalStateException("Invalid token: " + currentToken.getTokenType() + ". Expected ',' or ']'.");
        }
    }

    /**
     * Parse the current {@link Token} as a {@code JSON} string and advance.
     * @return The {@code String} value of the current {@link Token}.
     */
    private JSONString parseString() {
        String value = currentToken.getTokenValue();

        // Only advance if there are more tokens.
        if (indexToken + 1 < tokens.size()) {
            advance();
        }

        return new JSONString(value);
    }

    /**
     * Parse the current {@link Token} as a {@code JSON} number and advance.
     * @return The {@link Number} representing the value of the current token.
     */
    private JSONValue parseNumber() {
        String rawNumber = currentToken.getTokenValue();
        advance();
        Number number;

        if (rawNumber.contains(".")) {
            number = Double.valueOf(rawNumber);
        } else {
            long longValue = Long.parseLong(rawNumber);

            if (longValue >= Integer.MIN_VALUE && longValue <= Integer.MAX_VALUE)
                number = (int) longValue;
            else
                number = longValue;
        }

        return new JSONNumber(number);
    }

    /**
     * Parses the current {@link Token} as a {@code JSON} literal and advances.
     * @return The corresponding Java representation of the {@code JSON} literal:
     *         {@code Boolean} for {@code TRUE}/{@code FALSE}, or {@code null} for {@code NULL}.
     * @throws RuntimeException If the current token is not a valid literal.
     */
    private JSONValue parseLiteral() {
        return switch (currentToken.getTokenType()) {
            case TRUE -> {
                advance();
                yield JSONBoolean.TRUE;
            }
            case FALSE -> {
                advance();
                yield JSONBoolean.FALSE;
            }
            case NULL -> {
                advance();
                yield JSONNull.INSTANCE;
            }
            default -> throw new RuntimeException("Invalid literal: " + currentToken.getTokenType());
        };
    }
}
