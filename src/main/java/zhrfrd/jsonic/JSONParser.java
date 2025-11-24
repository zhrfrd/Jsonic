package zhrfrd.jsonic;

public class JSONParser {
    String jsonString;
    Lexer lexer;

    public JSONParser(String jsonString) {
        this.jsonString = jsonString;
        lexer = new Lexer(this.jsonString);
    }
}
