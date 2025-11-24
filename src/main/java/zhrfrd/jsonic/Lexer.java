package zhrfrd.jsonic;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String jsonString;
    private char currentChar;
    private int indexChar;
    private List<Token> tokens = new ArrayList<>();

    /**
     * Initialize a new {@code Lexer} used to convert the provided {@code JSON} text into a sequence of {@link Token} objects.
     * @param jsonString the {@code JSON} input as a {@code String}.
     */
    public Lexer(String jsonString) {
        this.jsonString = jsonString;
        currentChar = jsonString.charAt(0);
    }

    /**
     * Scan the {@code JSON} input string and convert it into a sequence of lexical tokens.
     * For each recognized {@link Token}, the corresponding {@link Token} instance is added to the
     * internal token list.
     * <p>
     * If an unrecognized character is encountered {@link RuntimeException} is thrown.
     */
    public void scanJSON() {
        while (currentChar != '\0') {
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
            } else if (currentChar == '-' || Character.isDigit(currentChar)) {
                readNumber();
            } else if (Character.isLetter(currentChar)) {
                readLiteral();
            }

            throw new RuntimeException("Unexpected character: " + currentChar);
        }
    }

    /**
     * Moves the lexer forward by one character in the input {@code JSON} string.
     * <p>
     * It should be called after consuming a character during tokenization.
     */
    private void advance() {
        currentChar = jsonString.charAt(indexChar);
        indexChar ++;
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
                throw new RuntimeException("Invalid literal: " + literal);
        }
    }
}