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
            int res = 2;
            int n = A.length;
            HashMap<Integer, Integer>[] dp = new HashMap[n];
            for (int i = 0; i < n; i++) {
                dp[i] = new HashMap<>();
            }
            for (int first = 0; first < A.length; first++) {
                int firstValue = A[first];
                for (int second = first + 1; second < A.length; second++) {
                    int secondValue = A[second];
                    int diff = secondValue - firstValue;
                    //increase the count of secondvalue index
                    //With that difference
                    dp[second].put(diff, dp[first].getOrDefault(diff, 1) + 1);
                    res = Math.max(res, dp[second].get(diff));
                }
            }
            return res;
        }
    }
}
