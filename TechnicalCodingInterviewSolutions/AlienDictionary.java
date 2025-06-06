
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

public class AlienDictionary {

    /**
     * Determines the order of characters in an alien language given a sorted list
     * of words.
     * Uses topological sorting on the character precedence graph.
     * 
     * @param words Array of words sorted lexicographically in the alien language.
     * @return String representing the character order or empty string if invalid.
     */
    public static String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        // Initialize graph nodes for all unique characters
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }

        // Build edges based on the first differing character between adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int minLen = Math.min(w1.length(), w2.length());
            boolean foundOrder = false;
            for (int j = 0; j < minLen; j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    foundOrder = true;
                    break;
                }
            }
            // Invalid case: prefix longer word before shorter word
            if (!foundOrder && w1.length() > w2.length()) {
                return "";
            }
        }

        // Perform topological sort using Kahn's algorithm
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        StringBuilder order = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            order.append(c);
            for (char neighbor : graph.get(c)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If cycle detected (not all chars included), return empty string
        if (order.length() != inDegree.size()) {
            return "";
        }

        return order.toString();
    }

    // Sample test cases
    public static void main(String[] args) {
        String[] words1 = { "wrt", "wrf", "er", "ett", "rftt" };
        System.out.println("Order for words1: " + alienOrder(words1)); // Expected: "wertf"

        String[] words2 = { "z", "x" };
        System.out.println("Order for words2: " + alienOrder(words2)); // Expected: "zx"

        String[] words3 = { "z", "x", "z" };
        System.out.println("Order for words3: " + alienOrder(words3)); // Expected: "" (invalid)

        String[] words4 = { "abc", "ab" };
        System.out.println("Order for words4: " + alienOrder(words4)); // Expected: "" (invalid)
    }
}
