import java.io.*;
import java.util.*;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private List<LinkedList<String>> elems;
    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;

    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
         out.flush();
    }

    private void processQuery(Query query) {
        switch (query.type) {
            case "add":
                if (!find(query.s))
                    add(query.s);
                break;
            case "del":
                if (find(query.s))
                    del(query.s);
                break;
            case "find":
                writeSearchResult(find(query.s));
                break;
            case "check":
                LinkedList<String> chains = check(query.ind);
                if (chains.size() > 0) {
                    out.print(String.join(" ", chains));
                }
                out.println();
                // Uncomment the following if you want to play with the program interactively.
                 out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void add(String key)
    {
        int hashCode = hashFunc(key);
        elems.get(hashCode).add(0, key);
    }

    public void del(String key)
    {
        int hashCode = hashFunc(key);
        LinkedList<String> chains = elems.get(hashCode);
        ListIterator<String> iterator = chains.listIterator();
        int i = 0;
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.compareTo(key) == 0) {
                chains.remove(i);
                break;
            }
            i++;
        }
    }

    public boolean find(String key)
    {
        int hashCode = hashFunc(key);
        LinkedList<String> chains = elems.get(hashCode);
        for (String c : chains) {
            if (c.compareTo(key) == 0) {
                return true;
            }
        }
        return false;
    }

    public LinkedList<String> check(int index)
    {
        return elems.get(index);
    }

    public void processQueries() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        elems = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            elems.add(new LinkedList<>());
        }
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

