package March.march02;

import java.util.ArrayList;
import java.util.List;
public class Coding {
    public class NumberOfDecodingWays_II {
        int M = 1000000007;
        Integer[] dp;
        String s;

        public int numDecodings(String s) {
            dp = new Integer[s.length()];
            this.s = s;
            return rec(s, s.length() - 1);
        }

        public int rec(String s, int i) {
            if (i < 0) {
                return 1;
            }
            if (dp[i] != null) {
                return dp[i];
            }
            if (s.charAt(i) == '*') {
                long result = 9 * rec(s, i - 1);
                if (checkCharacter(i, '1')) {
                    result = (result + 9 * rec(s, i - 2)) % M;
                } else if (checkCharacter(i, '2')) {
                    result = (result + 6 * rec(s, i - 2)) % M;
                } else if (checkCharacter(i, '*')) {
                    result = (result + 15 * rec(s, i - 2)) % M;
                }
                dp[i] = (int) result;
                return dp[i];
            }
            long result = s.charAt(i) != '0' ? rec(s, i - 1) : 0;
            if (checkCharacter(i, '1')) {
                result = (result + rec(s, i - 2)) % M;
            } else if (i > 0 && s.charAt(i - 1) == '2' && s.charAt(i) <= '6') {
                result = (result + rec(s, i - 2)) % M;
            } else if (checkCharacter(i, '*')) {
                result = (result + (s.charAt(i) <= '6' ? 2 : 1) * rec(s, i - 2)) % M;
            }
            dp[i] = (int) result;
            return dp[i];
        }

        private boolean checkCharacter(int i, char c) {
            return i > 0 && s.charAt(i - 1) == c;
        }

        public int numDecodings_BETTER(String s) {
            long first = 1, second = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
            for (int i = 1; i < s.length(); i++) {
                long temp = second;
                if (s.charAt(i) == '*') {
                    second = 9 * second;
                    if (s.charAt(i - 1) == '1') {
                        second = (second + 9 * first) % M;
                    } else if (s.charAt(i - 1) == '2') {
                        second = (second + 6 * first) % M;
                    } else if (s.charAt(i - 1) == '*') {
                        second = (second + 15 * first) % M;
                    }
                } else {
                    second = s.charAt(i) != '0' ? second : 0;
                    if (s.charAt(i - 1) == '1') {
                        second = (second + first) % M;
                    } else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6') {
                        second = (second + first) % M;
                    } else if (s.charAt(i - 1) == '*') {
                        second = (second + (s.charAt(i) <= '6' ? 2 : 1) * first) % M;
                    }
                }
                first = temp;
            }
            return (int) second;
        }
    }

    class Solution {
        List<Pair> dp = new ArrayList();

        public int findNumberOfLIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            for (int i = 0; i < nums.length; i++) {
                dp.add(new Pair(-1, 1));
            }
            int LIS = 0, ans = 0;
            rec(nums.length - 1, nums);
            for (Pair pair : dp) {
                LIS = Math.max(LIS, pair.first);
            }
            for (Pair p : dp) {
                if (p.first == LIS) {
                    ans += p.second;
                }
            }
            return ans;
        }

        public int rec(int i, int[] nums) {
            if (dp.get(i).first != -1) {
                return dp.get(i).first;
            }
            int ans = 1;
            for (int j = 0; j < i; j++) {
                int val = rec(j, nums);
                if (nums[i] > nums[j] && ans <= val + 1) {
                    if (ans == val + 1) {
                        dp.get(i).second += dp.get(j).second;
                    } else {
                        ans = val + 1;
                        dp.get(i).second = dp.get(j).second;
                    }
                }
            }
            return dp.get(i).first = ans;
        }

        class Pair {
            int first;
            int second;

            public Pair(int first, int second) {
                this.first = first;
                this.second = second;
            }
        }
    }

}
