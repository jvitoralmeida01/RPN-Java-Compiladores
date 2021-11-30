import java.util.Stack;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main (String[] args) throws IOException{
        ArrayList<Token> tokens =  new ArrayList<Token>();
        Stack<String> stack = new Stack<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\nDigite a operação no formato " + "\033[1;32m" + "RPN" + "\033[0m" + ", use os caracteres: " + "\033[1;32m" + "(+, -, *, /)" + "\033[1;33m" + "\nOBS:" + "\033[0m" + " Para executar a operação basta clicar " + "\033[1;33m" + "ENTER" + "\033[0m" + " em uma linha vazia" + "\033[1;32m");

        String arg;
        while(true){
            arg = reader.readLine();
            if(arg != null && arg != ""){
                try {
                    tokens.add(tokenize(arg));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                break;               
            }
        }

        Token tkn;
        for(int i = 0; i < tokens.size(); i++){
            tkn = tokens.get(i);
            if(tkn.type == "NUM"){
                stack.push(tkn.lexeme);
            }else{
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                if(tkn.type == "PLUS"){       
                    String result = Integer.toString(a + b);
                    stack.push(result);
                }else if(tkn.type == "MINUS"){
                    String result = Integer.toString(a - b);
                    stack.push(result);
                }else if(tkn.type == "TIMES"){
                    String result = Integer.toString(a * b);
                    stack.push(result);
                }else if(tkn.type == "DIVIDE"){
                    String result = Integer.toString(a / b);
                    stack.push(result);
                }
            }
        }

        if(stack.size() == 1){
            System.out.println("Resultado: " + "\033[0m"+ stack.pop());
            System.out.println("\033[1;33m" + "Tokens:" + "\033[0m");
            for(int i = 0; i < tokens.size(); i++){
                System.out.println(tokens.get(i).stringfy());
            }
        }
        else{System.out.println("\033[1;31m" + "Operação inválida!" + "\033[0m");}
    }

    public static Token tokenize(String s) throws Exception{
        Token token;
        if(isNumber(s)){ token = new Token("NUM", s); }
        else{ token = new Token(operationFrom(s), s); }
        return token;
    }

    public static boolean isNumber(String s){
        if(s.matches("[0-9]+")){
            return true;
        }else{
            return false;
        }
    }
    public static String operationFrom(String s) throws Exception{
        if(s.equals("+")){
            return "PLUS";
        }else if(s.equals("-")){
            return "MINUS";
        }else if(s.equals("*")){
            return "TIMES";
        }else if(s.equals("/")){
            return "DIVIDE";
        }else{
            throw new Exception("Unexpected character: " + s);
        }
        
    }
}