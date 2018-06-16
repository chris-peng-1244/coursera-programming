import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    private static class BellmanFordResult {
        ArrayList<Integer> unreachable;
        boolean hasNegativeCircle;

        BellmanFordResult(ArrayList<Integer> unreachable) {
            this.unreachable = unreachable;
            hasNegativeCircle = false;
        }
    }

    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        BellmanFordResult result = bellmanFord(adj, cost, 0);
        if (result.hasNegativeCircle) {
            return 1;
        }

        for (int i: result.unreachable) {
            if (bellmanFord(adj, cost, i).hasNegativeCircle) {
                return 1;
            }
        }
        return 0;
    }

    private static BellmanFordResult bellmanFord(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s) {
        BellmanFordResult result = new BellmanFordResult(new ArrayList<>(adj.length));
        Long[] dist = new Long[adj.length];

        for (int i = 0; i < adj.length; i++) {
            dist[i] = null;
        }

        dist[s] = 0L;
        for (int i = 0; i < adj.length ; i++) {
            for (int v = 0; v < adj.length; v++) {
                for (int j = 0; j < adj[v].size(); j++) {
                    int u = adj[v].get(j);
                    int w = cost[v].get(j);
                    boolean relaxed = relax(v, u, w, dist);
                    if (relaxed && i == adj.length - 1) {
                        result.hasNegativeCircle = true;
                    }
                }
            }
        }
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == null) {
                result.unreachable.add(i);
            }
        }
        return result;
    }

    private static boolean relax(int v, int u, int w, Long[] dist) {
        if (dist[v] == null) {
            return false;
        }
        if (dist[u] == null || (dist[u] > dist[v] + w)) {
            dist[u] = dist[v] + w;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}

