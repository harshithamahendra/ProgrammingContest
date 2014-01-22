import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class ProblemAm {

        static int[][] risk;
        
        static char[][] direction;
        
        static int minIndex;

        public static int computeMinCost() {
                int minCost = 0;
                for (int i = 1; i < risk.length; i ++) {
                        for (int j = 0; j < risk[0].length; j++) {
                                minCost =  Integer.MAX_VALUE;
                                if (j != 0) {
                                        if (minCost > risk[i - 1][j - 1]) {
                                                direction[i][j] = 'L';
                                                minCost = risk[i - 1][j - 1];
                                        }
                                } 
                                if (minCost > risk[i - 1][j]) {
                                        direction[i][j] = 'D';
                                        minCost =  risk[i - 1][j];
                                }
                                if (j != risk[0].length - 1) {
                                        if (minCost > risk[i - 1][j + 1]) {
                                                direction[i][j] = 'R';
                                                minCost = risk[i - 1][j + 1];
                                        }
                                }
                                risk[i][j] = minCost + risk[i][j]; 
                        }
                }
                
                int minRisk = risk[risk.length - 1][0];
                minIndex = 0;
                for (int i = 1; i < risk[0].length - 1; i++) {
                        if (minRisk > risk[risk.length - 1][i]) {
                                minRisk = risk[risk.length - 1][i]; 
                                minIndex = i;
                        }                                
                }
                return minRisk;
        }
        
        public static void main(String[] args) throws IOException {
                BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
                StringTokenizer ipTokens = new StringTokenizer(bReader.readLine());
                int m = Integer.parseInt(ipTokens.nextToken()), n = Integer.parseInt(ipTokens.nextToken());
                risk = new int[m][n];
                direction = new char[m][n];
                for (int i = 0; i < m; i++) {
                        StringTokenizer rowTokens = new StringTokenizer(bReader.readLine());
                        for (int j = 0; j < n; j++) {
                                risk[i][j] =  Integer.parseInt(rowTokens.nextToken());
                        }
                }
                int minCost = computeMinCost();
                System.out.println("Minimum risk path = " + tracePath());
                System.out.println("Risks along the path = " + minCost);
        }

        public static String tracePath() {
                String trace = "";
                trace = ("[" + (risk.length - 1) + "," + (minIndex) + "]") + trace;
                for (int i = risk.length - 1, j = minIndex; i > 0 ;) {
                        if (direction[i][j] == 'L') {
                                j = j - 1;
                                i = i - 1;
                                trace = ("[" + (i) + "," + (j) + "]") + trace;
                        }
                        else if(direction[i][j] == 'R') {
                                j = j + 1;
                                i = i - 1;
                                trace = ("[" + (i) + "," + (j) + "]") + trace;
                                
                        } else {
                                i = i - 1;
                                trace = ("[" + (i) + "," + (j) + "]") + trace;
                        }
                }
                return trace;
        }
}