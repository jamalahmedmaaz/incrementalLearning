package January.Jan20;

public class Coding {

    /**
     * Finding if given edges of graph can make a valid tree.
     * <p>
     * <p>
     * So the first thought which comes into mind is Union Find.
     * <p>
     * Now it is tricky you can definitely identify cycles in an UN_DIRECTED GRAPH but
     * <p>
     * how do you identify if it is a valid tree.
     */

    class Solution {
        int[] uf;

        public boolean validTree(int n, int[][] edges) {
            uf = new int[n];
            for (int i = 0; i < n; i++) {
                uf[i] = i;
            }
            for (int[] edge : edges) {
                int x = edge[0];
                int y = edge[1];
                int px = find(x);
                int py = find(y);
                if (px == py) {
                    return false;
                } else {
                    uf[py] = px;
                }
            }
            /**
             * VERY IMPORTANT
             */
            //This is the trick for a graph to be valid tree, edges given should be equal to N-1.
            return edges.length == n - 1;
        }

        public int find(int x) {
            if (uf[x] != x) {
                uf[x] = find(uf[x]);
            }
            return uf[x];
        }
    }

}
