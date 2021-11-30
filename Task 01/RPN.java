import java.util.Stack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main (String[] args) throws IOException{
        Stack<String> stack = new Stack<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String arg;
        Boolean keep_going = true;

        System.out.println("\nDigite a operação no formato " + "\033[1;32m" + "RPN" + "\033[0m" + ", use os caracteres: " + "\033[1;32m" + "(+, -, *, /)" + "\033[1;33m" + "\nOBS:" + "\033[0m" + " Para executar a operação basta clicar " + "\033[1;33m" + "ENTER" + "\033[0m" + " em uma linha vazia" + "\033[1;32m");

        while(keep_going){
            arg = reader.readLine();
            if(isNumber(arg)){
                stack.push(arg);
            }else if(isOperation(arg)){
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                String result = operate(arg, a, b);
                stack.push(result);
            }else{
                keep_going = false;               
            }
        }
        if(stack.size() == 1){System.out.println("Resultado: " + "\033[0m"+ stack.pop());}
        else{System.out.println("\033[1;31m" + "Operação inválida!" + "\033[0m");}
    }

    public static boolean isNumber(String s){
        if(s.matches("[0-9]+")){
            return true;
        }else{
            return false;
        }
    }
    public static boolean isOperation(String s){
        if(s.matches("[+-/*]")){
            return true;
        }else{
            return false;
        }
    }

    public static String operate(String op, int a, int b){
        switch(op){
            case "+":
            return ""+(a+b);
            case "-":
            return ""+(a-b);
            case "*":
            return ""+(a*b);
            case "/":
            return ""+(a/b);
            default:
            return "";    
        }
    }
}