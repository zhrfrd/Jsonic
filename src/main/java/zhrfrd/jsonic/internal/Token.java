package zhrfrd.jsonic.internal;

/**
 * <p>
 * Represents a single lexical token produced by the {@link Lexer}.
 * </p>
 * A token consists of:
 * <ul>
 *     <li>A {@link TokenType} indicating the category of the token</li>
 *     <li>A {@code tokenValue} (optional), used for tokens that carry data such as strings, numbers, or literals.</li>
 * </ul>
 * @param tokenType The type of the {@link Token}. Must not be {@code null}.
 * @param tokenValue The textual value of the {@link Token}, or {@code null} if the token does not carry a value.
 */
public record Token(TokenType tokenType, String tokenValue) {
    TokenType getTokenType() {
        return tokenType;
    }

    String getTokenValue() {
        return tokenValue;
    }
}
