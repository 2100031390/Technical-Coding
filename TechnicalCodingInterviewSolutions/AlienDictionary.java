
/**
 * Problem 2: Alien Dictionary
 * 
 * Given a sorted list of words from an alien language, determine the character order used in that language.
 * 
 * Approach:
 * - Use topological sorting on the characters based on the order of words.
 * - Build a graph of character precedence.
 * - Detect cycles to ensure valid ordering.
 * - Return the character order as a string or empty string if invalid.
 * 
 * Includes test cases with sample input/output in main method.
 */

import java.util.*;

public class AlienDict {

    // Total lowercase letters
    private static final int N = 26;

    // Convert character to index (e.g., 'a' -> 0)
    private static int toIdx(char c) {
        return c - 'a';
    }

    // Convert index back to character (e.g., 0 -> 'a')
    private static char toChar(int i) {
        return (char) (i + 'a');
    }

    // Main function to find alien language order
    public static String order(String[] words) {

        // Create adjacency list for graph
        LinkedList<Integer>[] adj = new LinkedList[N];
        for (int i = 0; i < N; i++) adj[i] = new LinkedList<>();

        // To store how many edges come to each node
        int[] indeg = new int[N];

        // To check which characters are used in input
        boolean[] used = new boolean[N];

        // Mark all characters that appear in words
        for (String w : words)
            for (char c : w.toCharArray())
                used[toIdx(c)] = true;

        // Build graph by comparing adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String a = words[i], b = words[i + 1];
            int len = Math.min(a.length(), b.length());
            boolean diff = false;

            for (int j = 0; j < len; j++) {
                int u = toIdx(a.charAt(j)), v = toIdx(b.charAt(j));
                if (u != v) {
                    if (!adj[u].contains(v)) {
                        adj[u].add(v);       // Add edge u -> v
                        indeg[v]++;          // Increase in-degree of v
                    }
                    diff = true;
                    break;
                }
            }

            // If first word is longer but same as second, it's invalid
            if (!diff && a.length() > b.length()) return "";
        }

        // Do topological sort using BFS (Kahn's algorithm)
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++)
            if (used[i] && indeg[i] == 0)
                q.add(i); // Add all nodes with 0 in-degree

        StringBuilder ans = new StringBuilder();
        int seen = 0, total = 0;
        for (boolean b : used) if (b) total++;

        while (!q.isEmpty()) {
            int u = q.poll();
            ans.append(toChar(u));
            seen++;
            for (int v : adj[u]) {
                if (--indeg[v] == 0) q.add(v); // Add to queue when in-degree becomes 0
            }
        }

        // If all characters are seen, return answer
        return seen == total ? ans.toString() : "";
    }

    // Sample test cases
    public static void main(String[] args) {
        String[] w1 = {"wrt", "wrf", "er", "ett", "rftt"};
        String[] w2 = {"z", "x"};
        String[] w3 = {"z", "x", "z"};
        String[] w4 = {"abc", "ab"};

        System.out.println(order(w1)); // Output: wertf
        System.out.println(order(w2)); // Output: zx
        System.out.println(order(w3)); // Output: "" (invalid)
        System.out.println(order(w4)); // Output: "" (invalid)
    }
}
