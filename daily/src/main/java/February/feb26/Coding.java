package February.feb26;

import java.util.HashMap;
import java.util.Map;
public class Coding {

    public static void main(String[] args) {
        System.out.println(~1);
    }

    class MostStonesRemovedWithSameRowOrColumn {
        Map<Integer, Integer> map = new HashMap();
        int islands = 0;

        public int removeStones(int[][] stones) {
            for (int i = 0; i < stones.length; i++) {
                //union(stones[i][0], ~stones[i][1]);
                int x = stones[i][0];
                int y = -1 - stones[i][1];
                union(x, y);
            }
            return stones.length - islands;
        }

        public void union(int x, int y) {
            int i = find(x);
            int j = find(y);

            if (i != j) {
                map.put(i, j);
                islands--;
            }
        }

        public int find(int x) {
            if (!map.containsKey(x)) {
                map.put(x, x);
                islands++;
            }

            if (x != map.get(x)) {
                map.put(x, find(map.get(x)));
            }
            return map.get(x);
        }
    }

}
