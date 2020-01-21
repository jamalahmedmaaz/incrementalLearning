package jan22;

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
}