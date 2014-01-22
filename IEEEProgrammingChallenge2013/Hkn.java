import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ieeeHkn {

        public static void main(String[] args) throws IOException {
                BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
                StringTokenizer ipTokens = new StringTokenizer(bReader.readLine(), ",");
                int min = Integer.parseInt(ipTokens.nextToken());
                int max = Integer.parseInt(ipTokens.nextToken());
                int count = 0;
                for (int i = min; i <= max; i++){
                        if (isPalindrome(new String(Integer.toBinaryString(i))))
                                count++;
                }
                System.out.println(count);
                
        }

        public static boolean isPalindrome(String bin) {
                String rev = new StringBuilder(bin).reverse().toString();
                return bin.equals(rev);
        }
}