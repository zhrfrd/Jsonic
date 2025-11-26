package zhrfrd.jsonic.internal;

public enum TokenType {
    // Structural tokens
    OPEN_BRACE,   // [
    CLOSE_BRACE,   // ]
    OPEN_BRACKET,   // {
    CLOSE_BRACKET,   // }
    COLON,
    COMMA,

    // Value tokens
    STRING,
    NUMBER,
    TRUE,
    FALSE,
    NULL,

    EOF
}
