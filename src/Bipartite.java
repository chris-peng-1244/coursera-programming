import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
  private static int bipartite(ArrayList<Integer>[] adj) {
    //write your code here
    int INFINITY = adj.length + 1;
    int[] dist = new int[adj.length];
    int[] color = new int[adj.length];
    for (int i = 0; i < adj.length; i++) {
      dist[i] = INFINITY;
      color[i] = -1;
    }
    dist[0] = 0;
    color[0] = 0;
    Queue<Integer> queue = new LinkedList<>();
    queue.add(0);
    while (!queue.isEmpty()) {
      int u = queue.remove();
      int nextColor = color[u] == 0 ? 1 : 0;
      ArrayList<Integer> vertices = adj[u];
      for (Integer v: vertices) {
        if (dist[v] == INFINITY) {
          queue.add(v);
          dist[v] = dist[u] + 1;
          color[v] = nextColor;
        } else if (color[v] == color[u]) {
          return 0;
        }
      }
    }
    return 1;
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
      adj[y - 1].add(x - 1);
    }
    System.out.println(bipartite(adj));
  }
}

