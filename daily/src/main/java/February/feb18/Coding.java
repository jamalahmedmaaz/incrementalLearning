package February.feb18;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Coding {

    class Solution {
        List<Integer> result = new ArrayList();

        public List<Integer> findAnagrams(String s, String p) {
            if (p == null || p.length() == 0) {
                return result;
            }
            Map<Character, Integer> pmap = new HashMap();
            int plen = p.length();
            for (char c : p.toCharArray()) {
                pmap.put(c, pmap.getOrDefault(c, 0) + 1);
            }
            int count = 0;
            Map<Character, Integer> tmp = new HashMap();
            for (int i = 0; i < s.length(); i++) {
                char current = s.charAt(i);
                count++;
                tmp.put(current, tmp.getOrDefault(current, 0) + 1);
                if (count == plen) {
                    count--;
                    int prev = i - plen + 1;
                    if (tmp.equals(pmap)) {
                        result.add(prev);
                    }
                    // System.out.println( k + " "+ i);
                    char c = s.charAt(prev);
                    tmp.put(c, tmp.getOrDefault(c, 0) - 1);
                    if (tmp.get(c) <= 0) {
                        tmp.remove(c);
                    }
                }
            }
            return result;
        }
    }
}
