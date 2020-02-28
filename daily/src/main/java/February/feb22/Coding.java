package February.feb22;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class Coding {
    enum Color {
        NEUTRAL,
        RED,
        BLUE
    }

    class IsGraphBipartite {
        int[] color;

        public boolean isBipartite(int[][] graph) {
            if (graph == null || graph.length == 0) {
                return true;
            }
            int[] visited = new int[graph.length];

            for (int i = 0; i < graph.length; i++) {
                if (graph[i].length != 0 && visited[i] == 0) {
                    Queue<Integer> q = new LinkedList();
                    q.add(i);
                    visited[i] = 1;
                    while (!q.isEmpty()) {
                        int parent = q.poll();
                        for (int ele : graph[parent]) {
                            if (visited[ele] != 1) {
                                visited[ele] = visited[parent] ^ 2;
                                q.add(ele);
                            } else {
                                if (visited[ele] == visited[parent]) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }

        public boolean isBipartite_dfs_solution(int[][] graph) {
            color = new int[graph.length];
            for (int i = 0; i < graph.length; i++) {
                if (color[i] == 0) {
                    color[i] = 1;
                    if (!dfs(graph, i)) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean dfs(int[][] graph, int parent) {
            for (int neigh : graph[parent]) {
                if (color[neigh] == 0) {
                    color[neigh] = color[parent] ^ 2;
                } else if (color[neigh] == color[parent]) {
                    return false;
                } else {
                    continue;
                }
                if (!dfs(graph, neigh)) {
                    return false;
                }
            }
            return true;
        }

        class IsBipartite_READABLE {
            Color[] color;
            int[][] graph;

            public boolean isBipartite(int[][] graph) {
                this.graph = graph;
                this.color = new Color[graph.length];
                Arrays.fill(color, Color.NEUTRAL);
                for (int i = 0; i < graph.length; i++) {
                    if (color[i] == Color.NEUTRAL) {
                        color[i] = Color.RED;
                        if (!dfs(i)) {
                            return false;
                        }
                    }
                }
                return true;
            }

            public boolean dfs(int parent) {
                for (int neigh : graph[parent]) {
                    if (color[neigh] == Color.NEUTRAL) {
                        color[neigh] = color[parent] ==
                                Color.RED ? Color.BLUE : Color.RED;
                        if (!dfs(neigh)) {
                            return false;
                        }
                    } else if (color[neigh] == color[parent]) {
                        return false;
                    }
                }
                return true;
            }

        }
    }
    public class LongestValidParentheses_Stack {

        /**
         * How does it work?
         * <p>
         * Idea is simple: Use stack to maintain two things,
         * <p>
         * 1. Open parenthesis index. whenever you see it.
         * 2. Keep a boundary index.
         * 3. Pop when you see a closing parenthesis and if stack not empty check
         * for the max length = loopIndex - stack.peek(), if stack is empty, then
         * add the loopIndex into stack.
         * <p>
         * <p>
         * What did we learn?
         * <p>
         * 1. We know for problems were you dont know what to do with an element
         * current '(' because it depends on future element ')' ,we use stack.
         * 2. But we have used stack in a very different way here, we used stack
         * to maintain indexes.
         * 3. PLUS - CORE IDEA, using a boundary index in stack. (like we added
         * -1 and every time we see a closing index we use a previous boundary
         * index to calculate max (boundary index can be anything based on input
         * starting from -1, 0, 1, 2, 3.....N.
         *
         * @param s
         * @return
         */

        public int longestValidParentheses(String s) {
            if (s == null || s.length() == 0 || s.length() == 1) {
                return 0;
            }
            int result = 0;
            Stack<Integer> stack = new Stack();
            stack.push(-1);
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                switch (c) {
                    case '(':
                        stack.push(i);
                        break;
                    case ')':
                        stack.pop();
                        if (stack.isEmpty()) {
                            stack.push(i);
                        } else {
                            result = Math.max(result, i - stack.peek());
                        }
                        break;
                }
            }

            return result;
        }
    }

}
