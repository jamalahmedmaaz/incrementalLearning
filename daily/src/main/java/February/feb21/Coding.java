package February.feb21;

import java.util.HashMap;
import java.util.Map;
public class Coding {

    /**
     * Example 1:
     * <p>
     * Input: "12"
     * Output: 2
     * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
     * <p>
     * <p>
     * Example 2:
     * <p>
     * Input: "226"
     * Output: 3
     * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
     */
    class NumberOfWaysAStringCanBeDecodedAsAlphabet {
        Map<Integer, Integer> map = new HashMap();
        String s;

        public int numDecodings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            this.s = s;
            return rec(0);
        }

        public int rec(int idx) {
            if (idx == s.length()) {
                return 1;
            } else {
                if (map.containsKey(idx)) {
                    return map.get(idx);
                }
                int count = 0;
                //Can we break only one character.
                if (checkOne(idx)) {
                    count = count + rec(idx + 1);
                }
                //Can we break two characters.
                if (checkTwo(idx + 1)) {
                    count = count + rec(idx + 2);
                }
                map.put(idx, count);
                return count;
            }
        }

        public boolean checkOne(int idx) {
            return s.charAt(idx) != '0';
        }

        public boolean checkTwo(int idx) {
            if (idx < s.length()) {
                //Check if previous character is 1 then we dont need to check anything.
                // Because '1' can paired with 0 - 9 (and we have letter from 10 to 19)
                if (s.charAt(idx - 1) == '1') {
                    return true;
                } else if (s.charAt(idx - 1) == '2') {
                    //Check if previous character is 2 and then the current character should be
                    // between 0 to 6 because we have alphabets between till only 26
                    return s.charAt(idx) >= '0' && s.charAt(idx) <= '6';
                }
            }
            return false;
        }
    }

    /**
     * Given a list of non-negative numbers and a target integer k, write a
     * function to check if the array has a continuous subarray of size at
     * least 2 that sums up to a multiple of k, that is, sums up to n*k where
     * n is also an integer.
     *
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: [23, 2, 4, 6, 7],  k=6
     * Output: True
     * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
     * <p>
     * <p>
     * Example 2:
     * <p>
     * Input: [23, 2, 6, 4, 7],  k=6
     * Output: True
     * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
     */
    class CheckSubArraySumWhichIsAMultipleOf_K {
        public boolean checkSubarraySum(int[] nums, int k) {
            int sum = 0;
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (k != 0) {
                    sum = sum % k;
                }
                if (map.containsKey(sum)) {
                    if (i - map.get(sum) > 1) {
                        return true;
                    }
                } else {
                    map.put(sum, i);
                }
            }
            return false;
        }
    }
}
