import java.util.*;
import java.io.*;

public class tree_orders {
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

	public class TreeOrders {
		int n;
		int[] key, left, right;
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			for (int i = 0; i < n; i++) { 
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			}
		}

		List<Integer> inOrder() {
			ArrayList<Integer> result = new ArrayList<>();
      if (n == 0) {
      	return result;
      }
      inOrderTraversal(0, result);
      return result;
		}

		private void inOrderTraversal(int tree, List<Integer> result) {
			if (tree == -1) {
				return;
			}
			inOrderTraversal(left[tree], result);
			result.add(key[tree]);
			inOrderTraversal(right[tree], result);
		}

		List<Integer> preOrder() {
			ArrayList<Integer> result = new ArrayList<>();
                        // Finish the implementation
                        // You may need to add a new recursive method to do that

			preOrderTraversal(0, result);
			return result;
		}

		private void preOrderTraversal(int tree, List<Integer> result) {
			if (tree == -1) {
				return;
			}
			result.add(key[tree]);
			preOrderTraversal(left[tree], result);
			preOrderTraversal(right[tree], result);
		}

		List<Integer> postOrder() {
			ArrayList<Integer> result = new ArrayList<>();

			postOrderTraversal(0, result);
			return result;
		}

		private void postOrderTraversal(int tree, List<Integer> result) {
			if (tree == -1) {
				return;
			}
			postOrderTraversal(left[tree], result);
			postOrderTraversal(right[tree], result);
			result.add(key[tree]);
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, () -> {
                try {
                    new tree_orders().run();
                } catch (IOException e) {
                }
            }, "1", 1 << 26).start();
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
	}
}
