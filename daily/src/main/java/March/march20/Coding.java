package March.march20;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Coding {

    class Solution {
        Map<Integer, Map<Integer, Integer>> map = new HashMap();

        public int reachableNodes(int[][] edges, int M, int N) {
            createGraph(edges);
            //Weight acts as visited.
            Map<Integer, Integer> movesAsWeight = new HashMap();
            PriorityQueue<Element> pq = new PriorityQueue<>((a, b) -> b.movesLeft - a.movesLeft);
            pq.add(new Element(0, M));
            while (!pq.isEmpty()) {
                Element current = pq.poll();
                int movesLeft = current.movesLeft;
                int currentNode = current.node;
                if (!movesAsWeight.containsKey(currentNode)) {
                    movesAsWeight.put(currentNode, movesLeft);
                    if (map.containsKey(currentNode)) {
                        Map<Integer, Integer> edge = map.get(currentNode);
                        for (Map.Entry<Integer, Integer> entry : edge.entrySet()) {
                            int nextNode = entry.getKey();
                            int nextMoves = movesLeft - entry.getValue() - 1;
                            if (!movesAsWeight.containsKey(nextNode) && nextMoves >= 0) {
                                pq.add(new Element(nextNode, nextMoves));
                            }
                        }
                    }
                }

            }
            int result = movesAsWeight.size();
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int cost = edge[2];
                int a = movesAsWeight.getOrDefault(u, 0);
                int b = movesAsWeight.getOrDefault(v, 0);
                result = result + Math.min(a + b, cost);
            }
            return result;
        }

        public void createGraph(int[][] edges) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int cost = edge[2];
                if (!map.containsKey(u)) {
                    map.put(u, new HashMap());
                }
                map.get(u).put(v, cost);
                if (!map.containsKey(v)) {
                    map.put(v, new HashMap());
                }
                map.get(v).put(u, cost);
            }
        }

        class Element {
            int node;
            int movesLeft;

            public Element(int node, int movesLeft) {
                this.node = node;
                this.movesLeft = movesLeft;
            }
        }
    }
}
