package February.feb22;

import java.util.LinkedList;
import java.util.Queue;
public class Coding {
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
    }

}
