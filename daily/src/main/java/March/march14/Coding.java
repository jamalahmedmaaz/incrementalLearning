package March.march14;

import java.util.*;

public class Coding {

    class UniqueBinarySearchTreesII {
        public List<TreeNode> generateTrees(int n) {
            if (n <= 0) return new ArrayList();
            return rec(1, n);
        }

        public List<TreeNode> rec(int left, int right) {
            List<TreeNode> result = new ArrayList();
            if (left > right) {
                result.add(null);
                return result;
            }
            for (int i = left; i <= right; i++) {
                List<TreeNode> leftList = rec(left, i - 1);
                List<TreeNode> rightList = rec(i + 1, right);
                for (TreeNode l : leftList) {
                    for (TreeNode r : rightList) {
                        TreeNode cursor = new TreeNode(i);
                        cursor.left = l;
                        cursor.right = r;
                        result.add(cursor);
                    }
                }
            }
            return result;
        }

        class TreeNode {
            TreeNode left;
            TreeNode right;
            int val;

            public TreeNode(int val) {
                this.val = val;
            }
        }
    }

    class UniqueBinarySearchTreesI {
        int[] cache;

        public int numTrees(int n) {
            cache = new int[n + 1];
            return rec(n);
        }

        private int rec(int n) {
            if (n <= 1) return 1;
            if (cache[n] > 0) return cache[n];

            int count = 0;
            for (int i = 1; i <= n; i++)
                count = count + rec(i - 1) * rec(n - i);

            return cache[n] = count;
        }
    }
}
