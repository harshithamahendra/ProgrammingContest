import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Acadox {

        static Set<String> unary = new TreeSet<>();

        static Set<String> binary = new TreeSet<>();

        static ArrayList<Integer> operands = new ArrayList<Integer>();

        static {
                unary.add("~");
                binary.add("+");
                binary.add("-");
                binary.add("&");
                binary.add("|");
                binary.add("X");
        }

        public static Integer pop() {
                Integer num =  operands.get(operands.size() - 1);
                operands.remove(operands.size() - 1);
                return num; //check
        }

        public static void push(Integer number) {
                operands.add(number);
        }

        public static boolean isEmpty() {
                return operands.size() == 0; 
        }
        public static Integer unaryOperations (String operator, Integer inputNumber) {
                return ~ inputNumber;
        }

        public static Integer binaryOperator (String operator, Integer ip2, Integer ip1) {        
                switch (operator) {
                case "+" :
                        return ip1 + ip2 > 0XFFFF ? 0XFFFF : ip1 + ip2;
                case "-" :
                        return ip1 - ip2 < 0 ? 0 : ip1 - ip2;
                case "&" :
                        return ip1 & ip2;
                case "|" :
                        return ip1 | ip2;
                case "X" :
                        return ip1 ^ ip2;
                }
                return -1;
        }
        public static void main(String[] args) throws IOException {
                BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
                StringTokenizer inputTokens = new StringTokenizer(bReader.readLine());
                if (inputTokens.countTokens() > 20) 
                        System.out.println("ERROR");
                else {
                        while(inputTokens.hasMoreTokens()) {
                                String input = inputTokens.nextToken();
                                if (unary.contains(input)) {
                                        Integer inp =  pop();
                                        if(isEmpty()) {
                                                System.out.println("ERROR");
                                                return;
                                        }                
                                        push(unaryOperations(input, inp));
                                }
                                        
                                else if (binary.contains(input)) {
                                        if(isEmpty()) {
                                                System.out.println("ERROR");
                                                return;
                                        }
                                        Integer inp1 = pop();
                                        if(isEmpty()) {
                                                System.out.println("ERROR");
                                                return;
                                        }
                                        Integer inp2 = pop();
                                        push(binaryOperator(input, inp1 , inp2));
                                }
                                else if (checkValidInput(input))
                                        push(Integer.parseInt(input, 16));
                                else {
                                        System.out.println("ERROR");
                                        return;
                                }
                        }
                        //String myStringRepOfInt = String.format("%04x",(0xFF & pop()));
                        String answer = Integer.toHexString(pop());
                        if (isEmpty())
                                System.out.println(padding(answer.length()) + answer.toUpperCase());
                        else
                                System.out.println("ERROR");
                }
        }
        
        public static boolean checkValidInput(String input) {
                try {
                        Integer.parseInt(input, 16);
                        return true;
                } catch (NumberFormatException nfe) {
                        return false;
                }
        }
        public static String padding(int StringSize) {
                String zeros = "";
                while (StringSize < 4) {
                        zeros += "0";
                        StringSize++;
                }
                return zeros;
        }
}