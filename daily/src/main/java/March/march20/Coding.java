package March.march20;

import java.util.*;

public class Coding {

    class ReachableNodes {
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


    class ShortestPathAllKeys_wrong_idea_failed {
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        public int shortestPathAllKeys(String[] grid) {
            int row = grid.length;
            int col = grid[0].length();
            Map<Integer, Integer> weights = new HashMap();
            PriorityQueue<Element> pq = new PriorityQueue<>((a, b) -> {
                if (a.keys.size() != b.keys.size()) {
                    return b.keys.size() - a.keys.size();
                }
                return weights.get(a.x * col + a.y) - weights.get(b.x * col + b.y);
            });

            int keys = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    char c = grid[i].charAt(j);
                    int ci = c;
                    if (c != '#') {
                        weights.put(i * col + j, 10000);
                    }
                    if (c == '@') {
                        weights.put(i * col + j, 0);
                        pq.add(new Element(i, j, 0, new HashSet<>()));
                    }
                    if ((c >= 'a' && c <= 'z')) {
                        keys++;
                    }
                }
            }
            // System.out.println(weights);
            while (!pq.isEmpty()) {
                Element e = pq.poll();
                int x = e.x;
                int y = e.y;
                int count = e.count;
                int cw = weights.get(x * col + y);
                Set<Character> setOfKeys = e.keys;
                if (setOfKeys.size() == keys) {
                    return count;
                }
                for (int[] dir : directions) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];

                    if (nx >= 0 && ny >= 0 && nx < row && ny < col
                            && grid[nx].charAt(ny) != '#'
                            && weights.get(nx * col + ny) > cw + 1) {

                        char c = grid[nx].charAt(ny);
                        int index = nx * col + ny;
                        // System.out.println(index);
                        Element next = new Element(nx, ny, count + 1, new HashSet(setOfKeys));
                        if (isLockAndDoWeHaveKey(c, setOfKeys)) {
                            pq.add(next);
                            weights.put(index, cw + 1);
                        } else if ((c >= 'a' && c <= 'z')) {
                            next.keys.add(Character.toUpperCase(c));
                            pq.add(next);
                            weights.put(index, cw + 1);
                        } else if (c == '.') {
                            pq.add(next);
                            weights.put(index, cw + 1);
                        }
                        if (next.keys.size() == keys) {
                            return next.count;
                        }
                    }
                }
            }


            return -1;
        }

        public boolean isLockAndDoWeHaveKey(char c, Set<Character> setOfKeys) {
            return c >= 'A' && c <= 'B' && setOfKeys.contains(c);
        }

        class Element {
            int x;
            int y;
            Set<Character> keys = new HashSet();
            int count;

            public Element(int x, int y, int count) {
                this.x = x;
                this.y = y;
                this.count = count;
            }

            public Element(int x, int y, int count, Set<Character> set) {
                this.x = x;
                this.y = y;
                this.count = count;
                this.keys = set;
            }
        }

    }

    class ShortestPathAllKeys {
        public int shortestPathAllKeys(String[] grid) {
            int x = -1, y = -1, row = grid.length, col = grid[0].length(), max = -1;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    char c = grid[i].charAt(j);
                    if (c == '@') {
                        x = i;
                        y = j;
                    }
                    if (c >= 'a' && c <= 'f') {
                        max = Math.max(c - 'a' + 1, max);
                    }
                }
            }
            Element start = new Element(0, x, y);
            Queue<Element> q = new LinkedList<>();
            Set<String> visited = new HashSet<>();
            visited.add(0 + " " + x + " " + y);
            q.offer(start);
            int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int step = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    Element cur = q.poll();
                    if (cur.keys == (1 << max) - 1) {
                        return step;
                    }
                    for (int[] dir : dirs) {
                        int nx = cur.x + dir[0];
                        int ny = cur.y + dir[1];
                        int keys = cur.keys;
                        if (nx >= 0 && nx < row && ny >= 0 && ny < col) {
                            char c = grid[nx].charAt(ny);
                            if (c == '#') {
                                continue;
                            }
                            if (c >= 'a' && c <= 'f') {
                                keys |= 1 << (c - 'a');
                            }
                            if (c >= 'A' && c <= 'F' && ((keys >> (c - 'A')) & 1) == 0) {
                                continue;
                            }
                            if (!visited.contains(keys + " " + nx + " " + ny)) {
                                visited.add(keys + " " + nx + " " + ny);
                                q.offer(new Element(keys, nx, ny));
                            }
                        }
                    }
                }
                step++;
            }
            return -1;
        }

        class Element {
            int keys, x, y;

            Element(int keys, int x, int y) {
                this.keys = keys;
                this.x = x;
                this.y = y;
            }
        }
    }

    class KthSmallestPrimeFraction {
        public int[] kthSmallestPrimeFraction(int[] A, int K) {
            PriorityQueue<Element> pq =
                    new PriorityQueue<Element>(Comparator.comparingDouble((Element a) -> a.fraction()).reversed());

            for (int i = 0; i < A.length; i++) {
                for (int j = i; j < A.length; j++) {
                    pq.add(new Element(A[i], A[j]));
                    if (pq.size() > K) {
                        pq.poll();
                    }
                }
            }
            return new int[]{pq.peek().x, pq.peek().y};
        }

        class Element {
            int x;
            int y;

            public Element(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public double fraction() {
                return x / y;
            }
        }
    }
}
