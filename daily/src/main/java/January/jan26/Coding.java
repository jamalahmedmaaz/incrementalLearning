package January.jan26;

import java.util.*;
public class Coding {

    /**
     * What have i learned
     * <p>
     * 1. Using BFS to solve the problem.
     * 2. using boolean variable found to stop the tree execution.
     * 3. Removing a character from a given string during execution
     * String t = s.substring(0, i) + s.substring(i + 1);
     */
    class RemoveParanthesis {
        public List<String> removeInvalidParentheses(String s) {
            List<String> res = new ArrayList<>();
            // sanity check
            if (s == null) {
                return res;
            }

            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            // initialize
            queue.add(s);
            visited.add(s);
            while (!queue.isEmpty()) {
                s = queue.poll();
                if (isValid(s)) {
                    // found an answer, add to the result
                    res.add(s);
                    continue;
                }
                // generate all possible states
                for (int i = 0; i < s.length(); i++) {
                    // we only try to remove left or right paren
                    if (s.charAt(i) != '(' && s.charAt(i) != ')') {
                        continue;
                    }
                    String t = s.substring(0, i) + s.substring(i + 1);
                    if (!visited.contains(t)) {
                        // for each state, if it's not visited, add it to the queue
                        queue.add(t);
                        visited.add(t);
                    }
                }
            }
            return res;
        }

        // helper function checks if string s contains valid parantheses
        boolean isValid(String s) {
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    count++;
                }
                if (c == ')' && count-- == 0) {
                    return false;
                }
            }
            return count == 0;
        }
    }

}