package February.feb14;

import javafx.util.Pair;
public class Coding {

    class SubtreeWithAllDeepest {
        public DeepTreeNode subtreeWithAllDeepest(DeepTreeNode root) {
            return deep(root).getValue();
        }

        public Pair<Integer, DeepTreeNode> deep(DeepTreeNode root) {
            if (root == null) {
                return new Pair(0, null);
            }
            Pair<Integer, DeepTreeNode> left = deep(root.left);
            Pair<Integer, DeepTreeNode> right = deep(root.right);

            int leftDepth = left.getKey();
            int rightDepth = right.getKey();

            int currentDepth = Math.max(leftDepth, rightDepth) + 1;

            DeepTreeNode deepTreeNode;
            if (leftDepth == rightDepth) {
                deepTreeNode = root;
            } else if (leftDepth > rightDepth) {
                deepTreeNode = left.getValue();
            } else {
                deepTreeNode = right.getValue();
            }
            return new Pair(currentDepth, deepTreeNode);
        }

        class DeepTreeNode {

            public DeepTreeNode left;
            public DeepTreeNode right;
        }
    }

    class PermutationInString {
        public boolean checkInclusion(String s1, String s2) {
            if (s2.length() < s1.length()) {
                return false;
            }
            int[] s1Map = new int[26];
            for (char c : s1.toCharArray()) {
                s1Map[c - 'a']++;
            }
            int s1Len = s1.length();
            char[] s2Chars = s2.toCharArray();
            int[] s2Map = new int[26];
            for (int i = 0; i < s2Chars.length; i++) {
                s2Map[s2Chars[i] - 'a']++;
                if (i >= s1Len) {
                    s2Map[s2Chars[i - s1Len] - 'a']--;

                }
                if (match(s1Map, s2Map)) {
                    return true;
                }
            }
            return false;
        }

        private boolean match(int[] s1Map, int[] s2Map) {
            for (int i = 0; i < s1Map.length; i++) {
                if (s1Map[i] != s2Map[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    class VerifyingAnAlienDictionaryOrder {
        int[] mapping = new int[26];

        public boolean isAlienSorted(String[] words, String order) {
            for (int i = 0; i < order.length(); i++) {
                mapping[order.charAt(i) - 'a'] = i;
            }
            for (int i = 1; i < words.length; i++) {
                if (bigger(words[i - 1], words[i])) {
                    return false;
                }
            }
            return true;
        }

        boolean bigger(String s1, String s2) {
            int n = s1.length(), m = s2.length();
            for (int i = 0; i < n && i < m; ++i) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    return mapping[s1.charAt(i) - 'a'] > mapping[s2.charAt(i) - 'a'];
                }
            }
            return n > m;
        }
    }
}
