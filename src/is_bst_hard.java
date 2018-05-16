import java.util.*;
import java.io.*;

public class is_bst_hard {
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
        	return isBinarySearchTreeUntil(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        boolean isBinarySearchTreeUntil(int index, int min, int max) {
            if (index == -1) {
            	return true;
            }
            Node node = tree[index];

            if (node.key < min || node.key > max ) {
            	return false;
            }

            if (node.left != -1 && node.key == Integer.MIN_VALUE) {
            	return false;
            }
            int newMax = node.key == Integer.MIN_VALUE ? Integer.MIN_VALUE : node.key - 1;
            return isBinarySearchTreeUntil(node.left, min, newMax)
		            && isBinarySearchTreeUntil(node.right, node.key, max);
        }

    }

    static public void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new is_bst_hard().run();
            } catch (IOException e) {
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
