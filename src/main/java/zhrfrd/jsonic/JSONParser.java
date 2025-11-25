package zhrfrd.jsonic;

import java.util.List;

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
     * Parse the current {@link Token} and return its corresponding Java representation.
     * @return The parsed Java object representing the {@code JSON} value.
     * @throws IllegalStateException If the current {@link TokenType} cannot be parsed.
     */
    public Object parse() {
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

    private Object parseObject() {
        return null;
    }

    private Object parseArray() {
        return null;
    }

    private Object parseString() {
        return null;
    }

    private Object parseNumber() {
        return null;
    }
}
