import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;


class Graph {
    int V; // vertices
    int E; // edges
    Vector<Character> [] adjacent;

    public Graph() {
        V = 26; // all alphabets A-Z
        E = 0;
        adjacent = (Vector<Character>[]) new Vector[V];
        for(int v = 0; v < V; v++) {
            adjacent[v] = new Vector<Character>();
        }
    }

    int V() {
        return V;
    }

    int E() {
        return E;
    }

    // undirected graph
    void addEdge(Character v, Character w) {
        E++;
        adjacent[v - 'A'].add(w);
        adjacent[w - 'A'].add(v);
    }

    Iterable<Character> adj(Character v) {
        return adjacent[v - 'A'];
    }
}

public class ProblemAs {

    /**
     * @param args
     */
    Stack<Character> currPath = new Stack<Character>();
    Set<Character> visited = new HashSet<Character>();
    ArrayList<StringBuffer>  allpaths = new ArrayList<StringBuffer>();
    int totalRoutes = 0;
    int totalshortestPaths = 0;
    String shortestPath = "";

    ProblemAs(Graph G, Character source, Character destination) {
        dfs(G, source, destination);
        ArrayList<String> strpaths = new ArrayList<String>();
        int min = Integer.MAX_VALUE;
        for(StringBuffer sb : allpaths) {
            strpaths.add(sb.toString());
            if(sb.length() < min)
                min = sb.length();
        }
        Collections.sort(strpaths);
        int shortestPathLength = min;
        if(totalRoutes > 0) {
            for(String s : strpaths) {
                if(s.length() == shortestPathLength) {
                    shortestPath = s;
                    break;
                }
            }
            if(shortestPathLength > 0) {
                for(String str : strpaths) {
                    //System.out.println(str);
                    if(str.length() == shortestPathLength)
                        totalshortestPaths += 1;
                }
            }
        }
    }

    void dfs(Graph G, Character source, Character Destination) {

        currPath.push(source);
        visited.add(source);

        if(source.equals(Destination)) {
            totalRoutes += 1;
            StringBuffer temp = new StringBuffer();
            for(Character c : currPath) {
                temp.append(c);
            }
            allpaths.add(temp);
        }
        else {
            for(Character w : G.adj(source)) {
                if(!visited.contains(w)) dfs(G, w, Destination);
            }
        }

        currPath.pop();
        visited.remove(source);

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char destination = br.readLine().charAt(0), source = 'F';
        String ip;
        Graph g = new Graph();
        int count = 1;
        while(!(ip = br.readLine()).equals("A A") ) {
            StringTokenizer sz = new StringTokenizer(ip, " ");
            g.addEdge(sz.nextToken().charAt(0), sz.nextToken().charAt(0));
            count++;
            if(count > 9)
                break;
        }
        //System.out.println("In here");
        ProblemAs sln = new ProblemAs(g, source, destination);

        if(sln.totalRoutes > 0) {
            System.out.println("Total Routes: " + sln.totalRoutes);
            System.out.println("Shortest Route Length: " + sln.shortestPath.length());
            System.out.print("Shortest Route after Sorting of Routes of length " + sln.shortestPath.length()  + ": " );
            for(int i = 0; i < sln.shortestPath.length(); i++) {
                System.out.print(sln.shortestPath.charAt(i)+" ");
            }
            System.out.println();
        }
        else {
            System.out.println("No Route Available from F to " + destination);
        }

    }

}