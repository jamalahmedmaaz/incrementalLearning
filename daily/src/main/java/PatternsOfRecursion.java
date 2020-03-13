
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class PatternsOfRecursion {

    class NoReturn_Recursion {
        private StringBuilder sb = new StringBuilder();
        private char[] digits;
        private List<String> result = new ArrayList<>();
        private Map<Character, List<Character>> map = new HashMap<>();

        public void letterCombinations(int index) {
            if (digits.length == sb.length()) {
                result.add(sb.toString());
            } else {
                for (int i = index; i < digits.length; i++) {
                    for (char c : map.get(digits[i])) {
                        sb.append(c);
                        letterCombinations(i + 1);
                        sb.setLength(sb.length() - 1);
                    }
                }
            }
        }
    }

    class SingleValueReturn_Recursion {

    }

    class ReturningTree_Recursion {
        StringBuilder sb = new StringBuilder();

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            // if(root == null) sb.toString();
            rec(root);
            sb.setLength(sb.length() - 1);
            return sb.toString();
        }

        public void rec(TreeNode root) {
            if (root == null) {
                sb.append("x").append(",");
                return;
            }
            sb.append(root.val).append(",");
            rec(root.left);
            rec(root.right);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] sa = data.split(",");
            Queue<String> q = new LinkedList();
            for (String s : sa) {
                q.add(s);
            }
            return rec(q);
        }

        public TreeNode rec(Queue<String> q) {
            String val = q.poll();
            if (Objects.equals(val, "x")) {
                return null;
            } else {
                TreeNode root = new TreeNode(Integer.valueOf(val));
                root.left = rec(q);
                root.right = rec(q);
                return root;
            }
        }

    }

    class PairValueReturn_Recursion {

        public TreeNode subtreeWithAllDeepest(TreeNode root) {
            return rec(root).getValue();
        }

        public Pair<Integer, TreeNode> rec(TreeNode root) {
            if (root == null) {
                return Pair.of(0, null);
            }

            Pair<Integer, TreeNode> left = rec(root.left);
            Pair<Integer, TreeNode> right = rec(root.right);

            int currentDepth = Math.max(left.getKey(), right.getKey()) + 1;
            TreeNode node;

            if (left.getKey() == right.getKey()) {
                node = root;
            } else if (left.getKey() > right.getKey()) {
                node = left.getValue();
            } else {
                node = right.getValue();
            }

            return Pair.of(currentDepth, node);
        }

    }

    class ListOfValueReturn_Recursion {
        public List<TreeNode> generateTrees(int n) {
            if (n != 0) {
                return rec(1, n);
            } else {
                return new ArrayList();
            }
        }

        public List<TreeNode> rec(int start, int end) {
            List<TreeNode> result = new ArrayList();
            if (start > end) {
                result.add(null);
                return result;
            }
            for (int i = start; i <= end; i++) {
                List<TreeNode> left = rec(start, i - 1);
                List<TreeNode> right = rec(i + 1, end);
                for (TreeNode l : left) {
                    for (TreeNode r : right) {
                        TreeNode cursor = new TreeNode(i);
                        cursor.left = l;
                        cursor.right = r;
                        result.add(cursor);
                    }
                }
            }
            return result;
        }
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        private int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}

