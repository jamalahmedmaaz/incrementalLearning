package January.jan28;

import java.util.*;
public class Coding {

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(new Edge(4, 5, 0.35), new Edge(4, 7, 0.37), new Edge(5, 7, 0.28), new Edge(0, 7, 0.16), new Edge(1, 5, 0.32), new Edge(0, 4, 0.38), new Edge(2, 3, 0.17), new Edge(1, 7, 0.19), new Edge(0, 2, 0.26), new Edge(1, 2, 0.36), new Edge(1, 3, 0.29), new Edge(2, 7, 0.34), new Edge(6, 2, 0.40), new Edge(3, 6, 0.52), new Edge(6, 0, 0.58), new Edge(6, 4, 0.93));
        Kruskal_Sorted_Union_MST kruskal = new Kruskal_Sorted_Union_MST();
        System.out.println(kruskal.mstCost(edges, 8));

        List<Edge> edges1 = Arrays.asList(new Edge(4, 5, 0.35), new Edge(4, 7, 0.37), new Edge(5, 7, 0.28), new Edge(0, 7, 0.16), new Edge(1, 5, 0.32), new Edge(0, 4, 0.38), new Edge(2, 3, 0.17), new Edge(1, 7, 0.19), new Edge(0, 2, 0.26), new Edge(1, 2, 0.36), new Edge(1, 3, 0.29), new Edge(2, 7, 0.34), new Edge(6, 2, 0.40), new Edge(3, 6, 0.52), new Edge(6, 0, 0.58), new Edge(6, 4, 0.93));
        Kruskal_Sorted_Union_MST kruskal1 = new Kruskal_Sorted_Union_MST();
        System.out.println(kruskal1.getMST(edges1, 8));

        List<Edge> edges2 = Arrays.asList(new Edge(4, 5, 0.35), new Edge(4, 7, 0.37), new Edge(5, 7, 0.28), new Edge(0, 7, 0.16), new Edge(1, 5, 0.32), new Edge(0, 4, 0.38), new Edge(2, 3, 0.17), new Edge(1, 7, 0.19), new Edge(0, 2, 0.26), new Edge(1, 2, 0.36), new Edge(1, 3, 0.29), new Edge(2, 7, 0.34), new Edge(6, 2, 0.40), new Edge(3, 6, 0.52), new Edge(6, 0, 0.58), new Edge(6, 4, 0.93));
        RelaxBellmanFord relaxBellmanFord = new RelaxBellmanFord();
        relaxBellmanFord.getSourceToAllNodesShortestPath(edges2, 8);
    }

    /**
     * What did i learn?
     * <p>
     * 1. Applying bellmanford.
     * 2. It is clear, when there is no source node give, that means it a Minimum spanning tree problem.
     * 3. bellman ford is used to find the shortest path from source to all vertices (source node should be provided).
     */
    static class RelaxBellmanFord {
        int MAX = 9999;

        public Map<Integer, Integer> getSourceToAllNodesShortestPath(List<Edge> edges, int noVertices) {
            double[] distances = new double[noVertices];
            Arrays.fill(distances, MAX);
            int sourceNode = 0;
            distances[sourceNode] = 0;
            Map<Integer, Integer> predecessor = new HashMap<>();
            for (int i = 0; i < noVertices - 1; i++) {
                relax(edges, distances, predecessor);
            }
            for (int i = 0; i < distances.length; i++) {
                System.out.println("The cost to reach node " + i + " is " + distances[i]);
            }
            return predecessor;
        }

        private void relax(List<Edge> edges, double[] distances, Map<Integer, Integer> predecessor) {
            for (Edge edge : edges) {
                if (distances[edge.from] > distances[edge.to] + edge.weight) {
                    distances[edge.from] = distances[edge.to] + edge.weight;
                    predecessor.put(edge.to, edge.from);
                }
            }
        }
    }


    /**
     * What did i learn i learned using Kruskal (sorted, Union, MST).
     * <p>
     * 1. To create a MST.
     * 2. Also to get the min cost of MST.
     */
    static class Kruskal_Sorted_Union_MST {
        List<Edge> MST = new ArrayList<>();

        public List<Edge> getMST(List<Edge> edges, int noVertices) {
            mstCost(edges, noVertices);
            return MST;
        }

        public double mstCost(List<Edge> edges, int noOfVertices) {
            double cost = 0.0;
            Collections.sort(edges, Comparator.comparingDouble(Edge::getWeight));
            UnionFind uf = new UnionFind(noOfVertices);
            for (Edge edge : edges) {
                if (uf.union(edge.to, edge.from)) {
                    cost = cost + edge.weight;
                    MST.add(edge);
                }
            }
            return cost;
        }



        private class UnionFind {
            int[] uf;

            public UnionFind(int noOfVertices) {
                uf = new int[noOfVertices];
                for (int i = 0; i < noOfVertices; i++) {
                    uf[i] = i;
                }
            }

            public int find(int idx) {
                if (uf[idx] != idx) {
                    uf[idx] = find(uf[idx]);
                }
                return uf[idx];
            }

            public boolean union(int i, int j) {
                int pi = find(i);
                int pj = find(j);
                if (pi != pj) {
                    uf[pj] = pi;
                    return true;
                } else {
                    return false;
                }
            }

        }

    }

    class WordDistance_I {
        public int shortestDistance(String[] words, String word1, String word2) {

            if (words == null || words.length == 0) {
                return -1;
            }

            //Take two pointers, which will keep track of the location of word.
            // It is possible the word is duplicate and available at multiple places.
            // So whenever we have reached the index of both the words,
            // Find the minimum distance between there indexes (using abs)

            int p1 = -1;
            int p2 = -1;
            int min = words.length;

            for (int i = 0; i < words.length; i++) {

                if (words[i].equals(word1)) {
                    p1 = i;
                }

                if (words[i].equals(word2)) {
                    p2 = i;
                }

                if (p1 != -1 && p2 != -1) {
                    min = Math.min(min, Math.abs(p1 - p2));
                }

            }
            return (p1 == -1 || p2 == -1) ? -1 : min;
        }
    }

    /**
     * What have i learned.
     * <p>
     * 1. first i tried this using map init.. O(n2). TLE.
     * I learned you can use index and use them to get to
     * distance with simple formula like 2 sum (sorted idea).
     */
    class WordDistance_II {
        Map<String, List<Integer>> map = new HashMap();

        public WordDistance_II(String[] words) {
            for (int i = 0; i < words.length; i++) {
                if (!map.containsKey(words[i])) {
                    map.put(words[i], new ArrayList());
                }
                map.get(words[i]).add(i);
            }
        }

        public int shortest(String word1, String word2) {
            int dis = Integer.MAX_VALUE;
            List<Integer> w1 = map.get(word1);
            List<Integer> w2 = map.get(word2);
            for (int i = 0, j = 0; i < w1.size() && j < w2.size(); ) {
                dis = Math.min(dis, Math.abs(w1.get(i) - w2.get(j)));
                if (w1.get(i) > w2.get(j)) {
                    j++;
                } else {
                    i++;
                }
            }
            return dis;
        }
    }

    class WordDistance_III {
        Map<String, List<Integer>> map = new HashMap();

        public int shortestWordDistance(String[] words, String word1, String word2) {
            for (int i = 0; i < words.length; i++) {
                if (!map.containsKey(words[i])) {
                    map.put(words[i], new ArrayList());
                }
                map.get(words[i]).add(i);
            }
            return shortest(word1, word2);
        }

        public int shortest(String word1, String word2) {
            int dis = Integer.MAX_VALUE;
            List<Integer> w1 = map.get(word1);
            List<Integer> w2 = map.get(word2);

            if (Objects.equals(word1, word2)) {
                if (w1.size() == 1) {
                    return Integer.MAX_VALUE;
                } else {
                    for (int i = 1; i < w1.size(); i++) {
                        dis = Math.min(dis, w1.get(i) - w1.get(i - 1));
                    }
                    return dis;
                }
            }

            for (int i = 0, j = 0; i < w1.size() && j < w2.size(); ) {
                dis = Math.min(dis, Math.abs(w1.get(i) - w2.get(j)));
                if (w1.get(i) > w2.get(j)) {
                    j++;
                } else {
                    i++;
                }
            }
            return dis;
        }

        public int BETTER_SOLUTION_shortestWordDistance(String[] words, String word1, String word2) {
            int idx1 = -1;
            int idx2 = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < words.length; i++) {
                if (word1.equals(words[i])) {
                    idx1 = i;
                    if (idx2 != -1) {
                        min = Math.min(min, Math.abs(idx2 - idx1));
                    }
                }
                if (word2.equals(words[i])) {
                    idx2 = i;
                    if (idx1 != -1 && idx1 != idx2) {
                        min = Math.min(min, Math.abs(idx2 - idx1));
                    }
                }
            }
            return min;
        }
    }

    static class Edge {
        int from;
        int to;
        double weight;

        public Edge(int from, int to, double cost) {
            this.to = to;
            this.from = from;
            this.weight = cost;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge(" + "from=" + from + ", to=" + to + ", weight=" + weight + '}';
        }
    }
}
