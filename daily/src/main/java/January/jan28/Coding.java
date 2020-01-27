package January.jan28;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Coding {

    /**
     * What have i learned.
     * <p>
     * 1. first i tried this using map init.. O(n2). TLE.
     * I learned you can use index and use them to get to
     * distance with simple formula like 2 sum (sorted idea).
     */
    class WordDistance {
        Map<String, List<Integer>> map = new HashMap();

        public WordDistance(String[] words) {
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
}
