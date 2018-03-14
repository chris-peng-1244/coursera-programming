import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;
    private static final int multiplier = 31;
    private static final int prime = 1000000007;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static long[] precomputeHash(Data data)
    {
        int textLength = data.text.length();
        int patternLength = data.pattern.length();
        long[] hashes = new long[textLength-patternLength+1];
        String s = data.text.substring(data.text.length() - data.pattern.length(), data.text.length());
        hashes[textLength-patternLength]  = polyHash(s);
        long y = 1;
        for (int i = 0; i < patternLength; i++) {
            y = (y * multiplier) % prime;
        }
        for (int i = textLength - patternLength - 1; i >= 0; i--) {
            long z = multiplier * hashes[i+1] + data.text.charAt(i) - y * data.text.charAt(i + patternLength);
            hashes[i] = (z % prime + prime) % prime;
        }
        return hashes;
    }

    private static long polyHash(String s)
    {
        long hashCode = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            hashCode = (hashCode * multiplier + s.charAt(i)) % prime;
        }
        return hashCode;
    }

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        long[] hashes = precomputeHash(input);
        int m = s.length(), n = t.length();
        long pHash = polyHash(input.pattern);
        List<Integer> occurrences = new ArrayList<Integer>();
        for (int i = 0; i + m <= n; ++i) {
            long tHash = hashes[i];
            if (tHash != pHash) {
                continue;
            }
            if (t.substring(i, i + s.length()).compareTo(s) == 0) {
                occurrences.add(i);
            }
        }
        return occurrences;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
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

