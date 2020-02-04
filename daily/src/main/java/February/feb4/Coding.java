package February.feb4;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Coding {
    /**
     * 763. Partition Labels
     * Share
     * A string S of lowercase letters is given.
     * We want to partition this string into as many parts as possible
     * so that each letter appears in at most one part, and return a list
     * of integers representing the size of these parts.
     * <p>
     * Example 1:
     * Input: S = "ababcbacadefegdehijhklij"
     * Output: [9,7,8]
     * Explanation:
     * The partition is "ababcbaca", "defegde", "hijhklij".
     * This is a partition so that each letter appears in at most one part.
     * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
     * Note:
     * <p>
     * S will have length in range [1, 500].
     * S will consist of lowercase letters ('a' to 'z') only.
     */
    class PartitionLabels {
        public List<Integer> partitionLabels(String S) {
            int[] idxs = new int[26];
            for (int i = 0; i < S.length(); i++) {
                int c = S.charAt(i) - 'a';
                // System.out.println(c);
                idxs[c] = i;
            }
            Set<Character> visited = new HashSet();
            List<Integer> result = new ArrayList();
            List<Pair<Integer, Integer>> list = new ArrayList();
            for (int i = 0; i < S.length(); i++) {
                int c = S.charAt(i) - 'a';
                int startIndex = i;
                int endIndex = idxs[c];
                if (visited.contains(S.charAt(i))) {
                    continue;
                }
                if (list.isEmpty()) {
                    list.add(new Pair(startIndex, endIndex));
                } else {
                    if (i > 0) {
                        char pc = S.charAt(i - 1);
                        Pair<Integer, Integer> pair = list.get(list.size() - 1);
                        int pstart = pair.getKey();
                        int pend = pair.getValue();
                        if (pend < i) {
                            Pair<Integer, Integer> newp = new Pair(startIndex, endIndex);
                            list.add(newp);
                        } else if (startIndex < pstart || pend < endIndex) {
                            Pair<Integer, Integer> newp = new Pair(Math.min(pstart, startIndex), endIndex);
                            list.remove(list.size() - 1);
                            list.add(newp);
                        }
                    }
                }
                visited.add(S.charAt(i));
            }

            // System.out.println(Arrays.toString(idxs));
            // System.out.println(Arrays.toString(list.toArray()));
            for (Pair<Integer, Integer> entry : list) {
                result.add(entry.getValue() - entry.getKey() + 1);
            }

            // Collections.sort(result);
            return result;
        }

        /**
         * BETTER APPROACH
         */
        public List<Integer> partitionLabels_BETTER_APPROACH(String S) {
            if (S == null || S.length() == 0) {
                return null;
            }
            List<Integer> result = new ArrayList<>();
            int[] lastSeen = new int[26];

            for (int i = 0; i < S.length(); i++) {
                lastSeen[S.charAt(i) - 'a'] = i;
            }
            // record the end index of the current sub string
            int endIdx = 0;
            int beginIdx = 0;
            for (int i = 0; i < S.length(); i++) {
                endIdx = Math.max(endIdx, lastSeen[S.charAt(i) - 'a']);
                if (endIdx == i) {
                    result.add(endIdx - beginIdx + 1);
                    beginIdx = endIdx + 1;
                }
            }
            return result;
        }
    }
}
