package zhrfrd.jsonic;

public class Token {
    public final TokenType tokenType;
    public final String tokenValue;

    public Token(TokenType tokenType, String tokenValue) {
        this.tokenType = tokenType;
        this.tokenValue = tokenValue;
    }

    // TODO: This code is only for debugging. REMOVE!!!
    @Override
    public String toString() {
        return tokenType + (tokenValue != null ? "(" + tokenValue + ")" : "");
    }
}
