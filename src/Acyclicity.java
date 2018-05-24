import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Acyclicity {

  private static int clock = 1;

  private static class Vertex {
    int index;
    boolean visited = false;
    int preOrder = 0;
    int postOrder = 0;

    public Vertex(int index) {
      this.index = index;
    }
  }

  private static int acyclic(ArrayList<Integer>[] adj) {
    Vertex[] vertices = new Vertex[adj.length];
    for (int i = 0; i < adj.length; i++) {
      vertices[i] = new Vertex(i);
    }
    dfs(adj, vertices);
    for (int i = 0; i < adj.length; i++) {
      int postOrder = vertices[i].postOrder;
      for (Integer v: adj[i]) {
        if (vertices[v].postOrder > postOrder) {
          return 1;
        }
      }
    }
    return 0;
  }

  private static void dfs(ArrayList<Integer>[] adj, Vertex[] vertices) {
    for (int i = 0; i < adj.length; i++) {
      if (!vertices[i].visited) {
        explore(adj, i, vertices);
      }
    }
  }

  private static void explore(ArrayList<Integer>[] adj, int v, Vertex[] vertices) {
    vertices[v].visited = true;
    vertices[v].preOrder = clock++;
    for (Integer vertex: adj[v]) {
      if (!vertices[vertex].visited) {
        explore(adj, vertex, vertices);
      }
    }
    vertices[v].postOrder = clock++;
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
    }
    System.out.println(acyclic(adj));
  }
}

