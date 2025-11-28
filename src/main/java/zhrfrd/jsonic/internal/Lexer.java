package zhrfrd.jsonic.internal;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts a raw {@code JSON} string into a sequence of {@link Token} objects.
 */
public class Lexer {
    private final String jsonString;
    private final List<Token> tokens = new ArrayList<>();
    private char currentChar;
    private int indexChar;

    /**
     * Initialize a new {@code Lexer} used to convert the provided {@code JSON} text into a sequence of {@link Token} objects.
     * @param jsonString the {@code JSON} input as a {@code String}.
     */
    public Lexer(String jsonString) {
        this.jsonString = jsonString;
        currentChar = jsonString.charAt(0);
        scanJSON();
    }

    /**
     * Scan the {@code JSON} input string and convert it into a sequence of lexical tokens.
     * For each recognized {@link Token}, the corresponding {@link Token} instance is added to the
     * internal token list.
     */
    private void scanJSON() {
        while (indexChar < jsonString.length()) {
            if (Character.isWhitespace(currentChar)) {
                advance();
                continue;
            }

            // Structural tokens
            switch (currentChar) {
                case '{':
                    tokens.add(new Token(TokenType.OPEN_BRACKET, "{"));
                    advance();
                    continue;
                case '}':
                    tokens.add(new Token(TokenType.CLOSE_BRACKET, "}"));
                    advance();
                    continue;
                case '[':
                    tokens.add(new Token(TokenType.OPEN_BRACE, "["));
                    advance();
                    continue;
                case ']':
                    tokens.add(new Token(TokenType.CLOSE_BRACE, "]"));
                    advance();
                    continue;
                case ':':
                    tokens.add(new Token(TokenType.COLON, ":"));
                    advance();
                    continue;
                case ',':
                    tokens.add(new Token(TokenType.COMMA, ","));
                    advance();
                    continue;
            }

            // Value tokens
            if (currentChar == '"') {
                readString();
                continue;
            } else if (currentChar == '-' || Character.isDigit(currentChar)) {
                readNumber();
                continue;
            } else if (Character.isLetter(currentChar)) {
                readLiteral();
                continue;
            }

            throw new IllegalStateException("Invalid character: " + currentChar);
        }
    }

    /**
     * Moves the lexer forward by one character in the input {@code JSON} string.
     * <p>
     * It should be called after consuming a character during tokenization.
     */
    private void advance() {
        indexChar ++;

        if (indexChar < jsonString.length()) {
            currentChar = jsonString.charAt(indexChar);
        } else {
            currentChar = '\0';
        }
    }

    /**
     * Reads a JSON string literal starting at the opening quotation mark.
     * <p>
     * The collected characters are stored as the string value of a {@link TokenType#STRING}
     * {@link Token}, which is then added to the token list.
     */
    private void readString() {
        // TODO: Need to handle escape characters
        advance();   // Skip first '"'
        StringBuilder sb = new StringBuilder();

        while (currentChar != '"') {
            sb.append(currentChar);
            advance();

        }

        advance();   // Skip last '"'
        System.out.println(sb);
        tokens.add(new Token(TokenType.STRING, sb.toString()));
    }

    /**
     * Reads a numeric value from the JSON string.
     * <p>
     * The collected characters are stored as the string value of a {@link TokenType#NUMBER}
     * {@link Token}, which is then added to the token list.
     */
    private void readNumber() {
        // TODO: Improve the way numbers are read following the JSON standard
        StringBuilder sb = new StringBuilder();

        while (currentChar != ' ' && currentChar != '\n') {
            sb.append(currentChar);
            advance();
        }

        tokens.add(new Token(TokenType.NUMBER, sb.toString()));
    }

    /**
     * Reads a literal value from the JSON string and adds the corresponding token.
     * <p>
     * This method parses the literals {@code true}, {@code false}, and {@code null}.
     * The collected characters are stored as the string value of a {@link TokenType#TRUE}, {@link TokenType#FALSE}, {@link TokenType#NULL}
     * {@link Token}, which is then added to the token list.
     * @throws RuntimeException if the parsed literal is not one of {@code true}, {@code false}, or {@code null}.
     */
    private void readLiteral() {
        StringBuilder sb = new StringBuilder();

        while (Character.isLetter(currentChar)) {
            sb.append(currentChar);
            advance();
        }

        String literal = sb.toString().toLowerCase();

        switch (literal) {
            case "true":
                tokens.add(new Token(TokenType.TRUE, "true"));
                break;
            case "false":
                tokens.add(new Token(TokenType.FALSE, "false"));
                break;
            case "null":
                tokens.add(new Token(TokenType.NULL, "null"));
                break;
            default:
                throw new IllegalStateException("Invalid literal: " + literal);
        }
    }

    /**
     * Returns all tokens produced by this {@code Lexer}.
     * @return The list of generated {@link Token} objects.
     */
    protected List<Token> getTokens() {
        return tokens;
    }
}