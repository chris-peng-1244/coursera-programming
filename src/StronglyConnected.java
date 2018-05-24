import java.util.*;

public class StronglyConnected {
  private static int clock = 1;
  private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
    ArrayList<Integer>[] reversedGraph = reverseGraph(adj);
    int[] used = new int[adj.length];
    Map<Integer, Integer> postOrders = dfs(reversedGraph, used);
    List<Integer> ordersReversed = new ArrayList<>(postOrders.keySet());
    ordersReversed.sort(Collections.reverseOrder());
    used = new int[adj.length];
    int componentCounter = 0;
    for (Integer order: ordersReversed) {
      int vertex = postOrders.get(order);
      if (used[vertex] == 0) {
        simpleExplore(adj, vertex, used);
        componentCounter++;
      }
    }
    return componentCounter;
  }

  private static Map<Integer, Integer> dfs(ArrayList<Integer>[] adj, int[] used) {
    Map<Integer, Integer> orders = new HashMap<>();
    for (int i = 0; i < adj.length; i++) {
      if (used[i] == 0) {
        explore(adj, i, used, orders);
      }
    }
    return orders;
  }

  private static void explore(ArrayList<Integer>[] adj, int i, int[] used, Map<Integer, Integer> orders) {
    used[i] = 1;
    for (Integer v: adj[i]) {
      if (used[v] == 0) {
        explore(adj, v, used, orders);
      }
    }
    orders.put(clock++, i);
  }

  private static void simpleExplore(ArrayList<Integer>[] adj, int i, int[] used) {
    used[i] = 1;
    for (Integer v: adj[i]) {
      if (used[v] == 0) {
        simpleExplore(adj, v, used);
      }
    }
  }

  private static ArrayList<Integer>[] reverseGraph(ArrayList<Integer>[] adj) {
    ArrayList<Integer>[] reversedGraph = (ArrayList<Integer>[])new ArrayList[adj.length];
    for (int i = 0; i < adj.length; i++) {
      reversedGraph[i] = new ArrayList<>();
    }
    for (int i = 0; i < adj.length; i++) {
      for (Integer v: adj[i]) {
        reversedGraph[v].add(i);
      }
    }
    return reversedGraph;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
    for (int i = 0; i < n; i++) {
      adj[i] = new ArrayList<Integer>();
    }
    for (int i = 0; i < m; i++) {
      int x, y;
      x = scanner.nextInt();
      y = scanner.nextInt();
      adj[x - 1].add(y - 1);
    }
    System.out.println(numberOfStronglyConnectedComponents(adj));
  }
}

