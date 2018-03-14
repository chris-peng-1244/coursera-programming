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

    private static int[] precomputeHash(Data data)
    {
        int textLength = data.text.length();
        int patternLength = data.pattern.length();
        int[] hashes = new int[textLength-patternLength+1];
        String s = data.text.substring(data.text.length() - data.pattern.length(), data.text.length());
        hashes[textLength-patternLength]  = polyHash(s);
        int y = 1;
        for (int i = 0; i < patternLength; i++) {
            y = (y * multiplier) % prime;
        }
        for (int i = textLength - patternLength - 1; i >= 0; i--) {
            hashes[i] = (multiplier * hashes[i+1] + data.text.charAt(i) - y * data.text.charAt(i + patternLength)) % prime;
        }
        return hashes;
    }

    private static int polyHash(String s)
    {
        int hashCode = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            hashCode = (hashCode * multiplier + s.charAt(i)) % prime;
        }
        return hashCode;
    }

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int[] hashes = precomputeHash(input);
        int m = s.length(), n = t.length();
        int pHash = polyHash(input.pattern);
        List<Integer> occurrences = new ArrayList<Integer>();
        for (int i = 0; i + m <= n; ++i) {
            int tHash = hashes[i];
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

