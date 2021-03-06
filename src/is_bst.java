import java.util.*;
import java.io.*;

public class is_bst {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean isBinarySearchTree() {
        	  if (tree.length == 0) {
        	      return true;
            }
            List<Integer> result = new ArrayList<>();
            inOrderTraversal(0, result);
            int prev = result.get(0);
            for (int i = 1; i < result.size(); i++) {
                if (result.get(i) <= prev) {
                    return false;
                }
                prev = result.get(i);
            }
            return true;
        }

        private void inOrderTraversal(int index, List<Integer> result)
        {
            if (index == -1) {
                return;
            }
            Node node = tree[index];
            inOrderTraversal(node.left, result);
            result.add(node.key);
            inOrderTraversal(node.right, result);
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new is_bst().run();
            } catch (IOException e) {
            }
        }, "1", 1 << 26).start();
    }
    private void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
