package February.feb13;

import java.util.HashMap;
public class Coding {

    /**
     * Questions:
     * <p>
     * 1. Can you do this in recursion.
     * 2. Is this problem connected to Longest Increasing sub-sequence.
     */
    class LongestArithSeqLength {
        public int longestArithSeqLength(int[] A) {
            if (A.length <= 1) {
                return A.length;
            }
            int longest = 0;
            HashMap<Integer, Integer>[] dp = new HashMap[A.length];
            for (int i = 0; i < A.length; i++) {
                dp[i] = new HashMap<>();
            }
            for (int i = 1; i < A.length; i++) {
                int firstValue = A[i];
                for (int j = 0; j < i; j++) {
                    int secondValue = A[j];
                    int diff = firstValue - secondValue;
                    int len = 2;
                    if (dp[j].containsKey(diff)) {
                        len = dp[j].get(diff) + 1;
                    }
                    int curr = dp[j].getOrDefault(diff, 0);
                    dp[i].put(diff, Math.max(curr, len));
                    longest = Math.max(longest, dp[i].get(diff));
                }
            }
            return longest;
        }
    }
}
