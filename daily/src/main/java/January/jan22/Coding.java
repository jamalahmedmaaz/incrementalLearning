package January.jan22;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
/**
 * The type Coding.
 */
public class Coding {

    /**
     * The type Palindrome partitioning.
     */
    class PalindromePartitioning {

        /**
         * The Tmp.
         */
        List<String> tmp = new ArrayList<String>();
        /**
         * The Result.
         */
        List<List<String>> result = new ArrayList<List<String>>();

        /**
         * Partition list.
         *
         * @param s the s
         * @return the list
         */
        public List<List<String>> partition(String s) {
            if (s == null || s.length() == 0) {
                return result;
            }
            rec(s, 0);
            return result;
        }

        /**
         * Rec.
         *
         * @param s   the s
         * @param idx the idx
         */
        public void rec(String s, int idx) {
            if (idx == s.length()) {
                result.add(new ArrayList<String>(tmp));
            } else {
                for (int i = idx; i < s.length(); i++) {
                    if (isPalindrome(s, idx, i)) {
                        String ts = s.substring(idx, i + 1);
                        tmp.add(ts);
                        rec(s, i + 1);
                        tmp.remove(tmp.size() - 1);
                    }
                }

            }
        }

        /**
         * Is palindrome boolean.
         *
         * @param s     the s
         * @param left  the left
         * @param right the right
         * @return the boolean
         */
        public boolean isPalindrome(String s, int left, int right) {
            while (left < right) {
                if (s.charAt(left) == s.charAt(right)) {
                    left++;
                    right--;
                } else {
                    return false;
                }
            }
            return true;
        }

    }

    /**
     * The type Longest palindrome.
     */
    class LongestPalindrome {
        /**
         * Longest palindrome int.
         *
         * @param s the s
         * @return the int
         */
        public int longestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int[] ca = new int[128];
            int result = 0;
            for (char c : s.toCharArray()) {
                ca[c]++;
            }
            for (int i = 0; i < ca.length; i++) {
                if (ca[i] > 1) {
                    if (ca[i] % 2 != 0) {
                        int diff = ca[i] - 1;
                        ca[i] = 1;
                        result = result + diff;
                    } else {
                        result = result + ca[i];
                        ca[i] = 0;
                    }
                }
            }
            for (int i = 0; i < ca.length; i++) {
                if (ca[i] == 1) {
                    return result + 1;
                }
            }
            return result;
        }

        /**
         * Longest palindrome better solution int.
         *
         * @param s the s
         * @return the int
         */
        public int longestPalindrome_better_solution(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            HashSet<Character> hs = new HashSet<Character>();
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                if (hs.contains(s.charAt(i))) {
                    hs.remove(s.charAt(i));
                    count++;
                } else {
                    hs.add(s.charAt(i));
                }
            }
            if (!hs.isEmpty()) {
                return count * 2 + 1;
            }
            return count * 2;
        }
    }

    /**
     * The type Count palindrome google.
     */
    class CountPalindrome_Google {
        /**
         * The Count.
         */
        int count = 0;

        /**
         * Count substrings int.
         *
         * @param s the s
         * @return the int
         */
        public int countSubstrings(String s) {
            if (s == null || s.length() == 0) {
                return count;
            }
            for (int i = 0; i < s.length(); i++) {
                extendPalindrome(s, i, i);
                extendPalindrome(s, i, i + 1);
            }
            return count;
        }

        /**
         * Extend palindrome.
         *
         * @param s     the s
         * @param left  the left
         * @param right the right
         */
        public void extendPalindrome(String s, int left, int right) {
            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) == s.charAt(right)) {
                    count++;
                    left--;
                    right++;
                } else {
                    break;
                }
            }
        }
    }

    class LongestPalindromeSubseq {
        String s;
        int[][] cache;
        int count = 0;

        public int longestPalindromeSubseq(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            this.s = s;
            cache = new int[s.length()][s.length()];
            int r = rec(0, s.length() - 1);
            // System.out.println(count);
            return r;
        }

        public int rec(int first, int last) {
            if (cache[first][last] != 0) {
                // count++;
                return cache[first][last];
            }
            if (first > last) {
                return 0;
            }
            if (first == last) {
                return 1;
            }

            if (s.charAt(first) == s.charAt(last)) {
                cache[first][last] = 2 + rec(first + 1, last - 1);
            } else {
                int r1 = rec(first + 1, last);
                int r2 = rec(first, last - 1);
                int max = Math.max(r1, r2);
                cache[first][last] = max;
            }
            return cache[first][last];
        }
    }

    /**
     * very similar to longest subsequence palindrome
     */
    class PalindromeRemoval_Hard {
        int[][] dp;

        public int minimumMoves(int[] A) {
            int n = A.length;
            dp = new int[n][n];
            return dfs(0, n - 1, A);
        }

        int dfs(int i, int j, int[] A) {
            if (i > j) {
                return 0;
            }
            if (dp[i][j] > 0) {
                return dp[i][j];
            }
            int res = dfs(i, j - 1, A) + 1;
            if (j > 0 && A[j] == A[j - 1]) {
                res = Math.min(res, dfs(i, j - 2, A) + 1);
            }
            for (int k = i; k < j - 1; ++k) {
                if (A[k] == A[j]) {
                    res = Math.min(res, dfs(i, k - 1, A) + dfs(k + 1, j - 1, A));
                }
            }
            dp[i][j] = res;
            return res;
        }
    }

    class LongestPalindromeSubString {
        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }
            String result = "";
            int n = s.length();
            boolean[][] dp = new boolean[n][n];
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i; j < n; j++) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                    if (dp[i][j] && result.length() < j - i + 1) {
                        result = s.substring(i, j + 1);
                    }
                }
            }
            return result;
        }
    }
}