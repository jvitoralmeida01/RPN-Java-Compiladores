public class Token {
    public String type;
    public String lexeme;
    
    public Token(String type, String lexeme){
        this.type = type;
        this.lexeme = lexeme;
    }

    public String stringfy(){
        return "Token [type=" + this.type + ", lexeme=" + this.lexeme + "]";
    }
}
