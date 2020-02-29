package February.feb28;

import java.util.*;
public class Coding {
    class LowestCommonAccesstorOfDeepestLeaves {
        TreeNode result;
        int count;

        public TreeNode lcaDeepestLeaves(TreeNode root) {
            rec(root, 0);
            return result;
        }

        public int rec(TreeNode root, int height) {
            count = Math.max(count, height);
            if (root == null) {
                return height;
            }
            int left = rec(root.left, height + 1);
            int right = rec(root.right, height + 1);
            // System.out.println(left + " "+ right + " "+ count + "  root: "+ root.val);
            if (left == count && right == count) {
                result = root;
            }
            return Math.max(left, right);
        }

    }

    class TreeNode {

        public TreeNode left;
        public TreeNode right;
    }

    class StringTransformsIntoAnotherString {
        public boolean canConvert(String str1, String str2) {
            if (str1.equals(str2)) {
                return true;
            }
            if (str1.length() != str2.length()) {
                return false;
            }
            Map<Character, Character> map = new HashMap();

            for (int i = 0; i < str1.length(); i++) {
                char c1 = str1.charAt(i);
                char c2 = str2.charAt(i);
                if (map.getOrDefault(c1, c2) != c2) {
                    return false;
                }
                map.put(c1, c2);
            }
            Set<Character> set = new HashSet(map.values());
            return set.size() < 26;
        }
    }

    class MaximalSquare {
        public int maximalSquare(char[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return 0;
            }
            int r = matrix.length;
            int c = matrix[0].length;
            int[][][] dp = new int[r + 1][c + 1][3]; //TOP LEFT Hight/Width
            int size = 0;
            // Top = 0 , left = 1, dia = 2
            for (int i = 1; i <= r; i++) {
                for (int j = 1; j <= c; j++) {
                    if (matrix[i - 1][j - 1] == '1') {
                        dp[i][j][0] = dp[i - 1][j][0] + 1;
                        dp[i][j][1] = dp[i][j - 1][1] + 1;

                        int top = dp[i][j][0];
                        int left = dp[i][j][1];

                        /**
                         * Diagnol should be set by top and left.
                         */
                        int diagMin = Math.min(left, top);
                        dp[i][j][2] = Math.min(diagMin, dp[i - 1][j - 1][2] + 1);
                        int diag = dp[i][j][2];

                        int min = Math.min(left, Math.min(top, diag));

                        //Min between top, left and diag is going to be the area parameter.
                        // min*min = will be the answer.
                        size = Math.max(size, min * min);
                    }
                }
            }
            return size;
        }
    }

    public static void main(String[] args) {
        System.out.println("jamal".substring(2));
    }

    class TaskScheduler {
        public int leastInterval(char[] tasks, int n) {
            int[] map = new int[26];
            for (char c : tasks) {
                map[c - 'A']++;
            }
            Arrays.sort(map);
            int time = 0;
            while (map[25] > 0) {
                int i = 0;
                while (i <= n) {
                    if (map[25] == 0) {
                        break;
                    }
                    if (i < 26 && map[25 - i] > 0) {
                        map[25 - i]--;
                    }
                    time++;
                    i++;
                }
                Arrays.sort(map);
            }
            return time;
        }
    }

    public class Solution {
        public boolean isMatch(String s, String p) {
            if (p.length() == 0) {
                return s.length() == 0;
            }
            if (starCondition(p)) {  // second char is '*'
                if (isMatch(s, p.substring(2))) {
                    return true;
                }
                if (dotConditionOrDefault(s, p)) {
                    return isMatch(s.substring(1), p);
                }
                return false;
            } else {                                     // second char is not '*'
                if (dotConditionOrDefault(s, p)) {
                    return isMatch(s.substring(1), p.substring(1));
                }
                return false;
            }
        }

        private boolean starCondition(String p) {
            return p.length() > 1 && p.charAt(1) == '*';
        }

        private boolean dotConditionOrDefault(String s, String p) {
            return s.length() > 0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0));
        }
    }
}

