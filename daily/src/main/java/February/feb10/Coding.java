package February.feb10;

import java.util.*;
public class Coding {

    /**
     * There is a new alien language which uses the latin alphabet. However,
     * the order among letters are unknown to you. You receive a list of
     * non-empty words from the dictionary, where words are sorted
     * lexicographically by the rules of this new language. Derive
     * the order of letters in this language.
     * <p>
     * Example 1:
     * <p>
     * Input:
     * [
     * "wrt",
     * "wrf",
     * "er",
     * "ett",
     * "rftt"
     * ]
     * <p>
     * Output: "wertf"
     * Example 2:
     * <p>
     * Input:
     * [
     * "z",
     * "x"
     * ]
     * <p>
     * Output: "zx"
     * Example 3:
     * <p>
     * Input:
     * [
     * "z",
     * "x",
     * "z"
     * ]
     * <p>
     * Output: ""
     * <p>
     * Explanation: The order is invalid, so return "".
     * Note:
     * <p>
     * You may assume all letters are in lowercase.
     * You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
     * If the order is invalid, return an empty string.
     * There may be multiple valid order of letters, return any one of them is fine.
     * <p>
     * <p>
     * <p>
     * What i have learned:
     * <p>
     * 1. Creating a graph from string, using the order specified by the problem.
     * 2. Creating degree map, using this graph.
     * 3. Applying topological sort(kadence algo).
     */
    /**
     * Questions:
     * <p>
     * 1. While building the graph why do we only take pair of sequence words?
     * 2. While building the graph why do we "break" after finding the first two
     * not equals characters in words?
     * 3. The last comparision of result length and indegree size, why do we need
     * to do?
     */
    static class AlienDictionaryOrder {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> degree = new HashMap<>();
        StringBuilder result = new StringBuilder();
        private String[] words;

        public static void main(String[] args) {
            AlienDictionaryOrder alienDictionaryOrder = new AlienDictionaryOrder();
            System.out.println(alienDictionaryOrder.alienOrder(new String[]{"wrt",
                    "wrf",
                    "er",
                    "ett",
                    "rftt"}));
        }

        public String alienOrder(String[] words) {
            this.words = words;
            if (words == null || words.length == 0) {
                return result.toString();
            }
            createDegree();
            buildGraph();
            System.out.println(degree);
            System.out.println(graph);
            topologicalSort();

            /**
             * Most important part of the algo.
             *
             * if the result doesnt have all the elements of size degree (meaning all characters)
             * then the result is incorrect. (this is true for every topological sort implementation).
             */
            if (result.length() != degree.size()) {
                return "";
            }
            return result.toString();
        }

        /**
         * Topological sort Algo:
         * <p>
         * 1. Add all the nodes (char) which have a degree of 0 (meaning no parents).
         * 2. Do a BFS.
         * 3. Update the degree of all the children of current node (char)
         * 4. Add children of the current node, if they have a degree of 0.
         */
        private void topologicalSort() {
            Queue<Character> q = new LinkedList<>();
            for (char c : degree.keySet()) {
                if (degree.get(c) == 0) {
                    q.add(c);
                }
            }
            while (!q.isEmpty()) {
                char c = q.poll();
                result.append(c);
                if (graph.containsKey(c)) {
                    for (char children : graph.get(c)) {
                        degree.put(children, degree.get(children) - 1);
                        if (degree.get(children) == 0) {
                            q.add(children);
                        }
                    }
                }
            }
        }

        private void createDegree() {
            for (String s : words) {
                for (char c : s.toCharArray()) {
                    degree.put(c, 0);
                }
            }
        }

        private void buildGraph() {
            for (int i = 0; i < words.length - 1; i++) {
                String current = words[i];
                String next = words[i + 1];

                int length = Math.min(current.length(), next.length());

                for (int j = 0; j < length; j++) {
                    char a1 = current.charAt(j);
                    char b1 = next.charAt(j);

                    if (a1 != b1) {
                        Set<Character> set = new HashSet<>();
                        if (graph.containsKey(a1)) {
                            set = graph.get(a1);
                        }
                        if (!set.contains(b1)) {
                            set.add(b1);
                            graph.put(a1, set);
                            degree.put(b1, degree.get(b1) + 1);
                        }
                        break;
                    }
                }
            }
        }
    }
}
