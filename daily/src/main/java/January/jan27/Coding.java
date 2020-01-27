package January.jan27;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Coding {

    /**
     * What have i learned
     * <p>
     * 1. memorizing a list.
     * 2. doing backtracking recursion properly.
     * 3. using a sublist of returned list and creating the result list.
     */
    class WordBreakII {
        List<String> wordDict;
        Map<String, List<String>> map = new HashMap();

        public List<String> wordBreak(String s, List<String> wordDict) {
            this.wordDict = wordDict;
            return rec(s);
        }

        public List<String> rec(String current) {
            if (map.containsKey(current)) {
                return map.get(current);
            }
            List<String> result = new ArrayList();
            if (current.length() == 0) {
                //Very important returning empty, its the base case
                result.add("");
                return result;
            }
            for (String word : wordDict) {
                if (current.startsWith(word)) {
                    String calString = current.substring(word.length());
                    List<String> sub = rec(calString);
                    for (String substring : sub) {
                        String sentence = word + (substring.isEmpty() ? "" : " ") + substring;
                        result.add(sentence);
                    }
                }
            }
            map.put(current, result);
            return result;
        }
    }
}
