package March.march19;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Coding {
    /**
     * MOST IMPORTANT:
     * <p>
     * Ask all the possible questions related to the input.
     * 1. Can the graph have less number of elements than specified by N.
     * 2. Can there be cycles in the graph.
     * 3. are there negative values given to edge.
     * 4. are there negative cycles.
     * <p>
     * If making any assumptions clear those assumptions aswell.
     **/
    class Solution {
        Map<Integer, Map<Integer, Integer>> map = new HashMap();
        Map<Integer, Integer> weight = new HashMap();
        int MAX = 100000;

        public int networkDelayTime(int[][] times, int N, int K) {
            createMap(times);
            if (!map.containsKey(K)) return -1;
            weight.put(K, 0);
            PriorityQueue<Integer> pq =
                    new PriorityQueue((a, b) -> weight.get(a) - weight.get(b));
            pq.add(K);
            while (!pq.isEmpty()) {
                int current = pq.poll();
                int currentWeight = weight.get(current);
                if (map.containsKey(current)) {
                    Map<Integer, Integer> edges = map.get(current);
                    for (Map.Entry<Integer, Integer> entry : edges.entrySet()) {
                        if (weight.containsKey(entry.getKey())) {
                            int nextWeight = weight.get(entry.getKey());
                            if (nextWeight > currentWeight + entry.getValue()) {
                                weight.put(entry.getKey(), currentWeight + entry.getValue());
                                pq.add(entry.getKey());
                            }
                        }
                    }
                }
            }
            int result = 0;
            for (int value : weight.values()) result = Math.max(result, value);
            if (weight.size() != N) return -1;
            return result != MAX ? result : -1;
        }

        public void createMap(int[][] times) {
            for (int[] edge : times) {
                int u = edge[0];
                int v = edge[1];
                int cost = edge[2];
                if (!map.containsKey(u)) {
                    map.put(u, new HashMap());
                }
                weight.put(u, MAX);
                weight.put(v, MAX);
                map.get(u).put(v, cost);
            }
        }
    }
}
