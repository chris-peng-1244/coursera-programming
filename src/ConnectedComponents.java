import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int componentCounter = 0;
        if (adj.length == 0) {
            return componentCounter;
        }
        Set<Integer> visited = new HashSet<>();
        for (int v = 0; v < adj.length; v++) {
            if (!visited.contains(v)) {
                explore(adj, v, visited);
                componentCounter++;
            }
        }
        return componentCounter;
    }

    private static void explore(ArrayList<Integer>[] adj, int vertex, Set<Integer> visited) {
        for (Integer v: adj[vertex]) {
            if (!visited.contains(v)) {
                visited.add(v);
                explore(adj, v, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }
}

