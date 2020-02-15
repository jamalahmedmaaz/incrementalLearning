package February.feb16;

import java.util.HashMap;
public class Coding {
    class CountContigiousSubArrayWhoseSumIsEqualK {
        public int subarraySum(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int count = 0, sum = 0;
            HashMap<Integer, Integer> map = new HashMap();
            map.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                sum = sum + nums[i];
                if (map.containsKey(sum - k)) {
                    count = count + map.get(sum - k);
                }
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return count;
        }
    }

    class IsPalindromeWithOneDeletion {
        public boolean validPalindrome(String s) {
            int left = 0;
            int right = s.length() - 1;

            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
                } else {
                    left++;
                    right--;
                }
            }
            return true;
        }

        public boolean isPalindrome(String s, int left, int right) {
            while (left < right) {
                if (s.charAt(left++) != s.charAt(right--)) {
                    return false;
                }
            }
            return true;
        }
    }
}
