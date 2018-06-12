import java.util.*;

public class Dijkstra {

    private static class QueueKey implements  Comparable {
        private int key;
        private int value;

        QueueKey(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Object o) {
            return value - ((QueueKey) o).value;
        }
    }

    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int[] dist = new int[adj.length];
        int[] prev = new int[adj.length];
        for (int i = 0; i < adj.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        Queue<QueueKey> queue = new PriorityQueue<>();
        queue.add(new QueueKey(s, 0));

        while (!queue.isEmpty()) {
            QueueKey key = queue.poll();
            int u = key.key;
            ArrayList<Integer> vertices = adj[u];
            for (int i = 0; i < vertices.size(); i++) {
                int w =  cost[u].get(i);
                int v = vertices.get(i);
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    prev[v] = u;
                    queue.add(new QueueKey(v, dist[v]));
                }
            }
        }
        return dist[t] == Integer.MAX_VALUE ? -1 : dist[t];
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

