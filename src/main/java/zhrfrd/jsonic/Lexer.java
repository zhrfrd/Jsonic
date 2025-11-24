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

    public void traverseJSON() {
        while (currentChar != '\0') {
            if (Character.isWhitespace(currentChar)) {
                advance();
                continue;
            }

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

            if (currentChar == '"') {
                readString();
            } else if (currentChar == '-' || Character.isDigit(currentChar)) {
                readNumber();
            } else if (Character.isLetter(currentChar)) {
                readLiteral();
            }
        }
    }

    private void advance() {
        currentChar = jsonString.charAt(indexChar);
        indexChar ++;
    }

    private void readString() {

    }

    private void readNumber() {
    }

    private void readLiteral() {
    }
}