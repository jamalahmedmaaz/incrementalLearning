package February.feb1;

import java.util.ArrayList;
import java.util.List;
public class Coding {

    /**
     * 763. Partition Labels
     * A string S of lowercase letters is given. We want to partition this string into as many
     * parts as possible so that each letter appears in at most one part, and return a list of
     * integers representing the size of these parts.
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
            int[] ca = new int[26];
            for (int i = 0; i < S.length(); i++) {
                int index = S.charAt(i) - 'a';
                ca[index] = i;
            }
            List<Integer> result = new ArrayList();
            int start = 0, end = 0;
            for (int i = 0; i < S.length(); i++) {
                int index = S.charAt(i) - 'a';
                int la = ca[index];
                end = Math.max(end, la);
                if (end == i) {
                    result.add(end - start + 1);
                    start = end + 1;
                }
            }
            return result;
        }
    }
}
