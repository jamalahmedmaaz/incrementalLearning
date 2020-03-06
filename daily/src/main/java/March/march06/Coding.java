package March.march06;

import java.util.ArrayList;
import java.util.List;
public class Coding {

    class singleNonDuplicate_BinarySearchOnEvenIndexes {
        public int singleNonDuplicate(int[] A) {
            int n = A.length;
            if (n % 2 == 0) {
                return -1;
            }
            int low = 0;
            int high = A.length - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (mid % 2 == 1) {
                    mid--;
                }

                if (A[mid] == A[mid + 1]) {
                    low = mid + 2;
                } else {
                    high = mid;
                }
            }
            return A[low];
        }
    }

    /**
     * 916. Word Subsets
     * Medium
     * <p>
     * 262
     * <p>
     * 63
     * <p>
     * Add to List
     * <p>
     * Share
     * We are given two arrays A and B of words.  Each word is a string of
     * lowercase letters.
     * <p>
     * Now, say that word b is a subset of word a if every letter in b occurs in a,
     * including multiplicity.  For example, "wrr" is a subset of "warrior",
     * but is not a subset of "world".
     * <p>
     * Now say a word a from A is universal if for every b in B, b is a subset of a.
     * <p>
     * Return a list of all universal words in A.  You can return the words in any order.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
     * Output: ["facebook","google","leetcode"]
     * Example 2:
     * <p>
     * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
     * Output: ["apple","google","leetcode"]
     * Example 3:
     * <p>
     * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
     * Output: ["facebook","google"]
     * Example 4:
     * <p>
     * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
     * Output: ["google","leetcode"]
     * Example 5:
     * <p>
     * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
     * Output: ["facebook","leetcode"]
     */
    class WordSubsets_916 {
        public List<String> wordSubsets(String[] A, String[] B) {
            int[] btotal = new int[26];
            for (String b : B) {
                int[] bcount = convert(b);
                for (int i = 0; i < 26; i++) {
                    btotal[i] = Math.max(btotal[i], bcount[i]);
                }
            }
            List<String> result = new ArrayList();
            for (String a : A) {
                int[] aa = convert(a);
                boolean add = true;
                for (int i = 0; i < 26; i++) {
                    if (aa[i] < btotal[i]) {
                        add = false;
                        break;
                    }
                }
                if (add) {
                    result.add(a);
                }
            }
            return result;
        }

        public int[] convert(String x) {
            int[] a = new int[26];

            for (char c : x.toCharArray()) {
                int i = c - 'a';
                a[i]++;
            }
            return a;
        }
    }
}


