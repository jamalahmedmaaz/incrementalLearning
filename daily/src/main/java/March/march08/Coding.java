package March.march08;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class Coding {
    class ConstructMaximumBinaryTree_654 {
        int[] A;
        boolean[] visited;

        public TreeNode constructMaximumBinaryTree(int[] nums) {
            A = nums;
            visited = new boolean[A.length];
            return rec(0, A.length - 1);
        }

        public TreeNode rec(int left, int right) {
            if (left > right) {
                return null;
            }
            int idx = findMax(left, right);
            TreeNode root = new TreeNode(A[idx]);
            visited[idx] = true;
            root.left = rec(left, idx - 1);
            root.right = rec(idx + 1, right);
            return root;
        }

        public int findMax(int left, int right) {
            int idx = -1;
            int max = Integer.MIN_VALUE;
            for (int i = left; i <= right; i++) {
                if (max < A[i]) {
                    idx = i;
                    max = A[i];
                }
            }
            return idx;
        }
    }

    class InsertIntoMaxTree_998 {
        public TreeNode insertIntoMaxTree(TreeNode root, int val) {
            return rec(root, val);
        }

        public TreeNode rec(TreeNode root, int val) {
            if (root != null && root.val > val) {
                root.right = rec(root.right, val);
                return root;
            }
            TreeNode newNode = new TreeNode(val);
            newNode.left = root;
            return newNode;
        }

    }

    class TreeNode {
        public TreeNode left;
        public TreeNode right;
        private int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    class GetAllElements {
        List<Integer> result = new ArrayList();
        Stack<TreeNode> s1 = new Stack();
        Stack<TreeNode> s2 = new Stack();

        public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
            populate(root1, s1);
            populate(root2, s2);
            while (!s1.isEmpty() && !s2.isEmpty()) {
                TreeNode r1 = s1.peek();
                TreeNode r2 = s2.peek();
                if (r1.val > r2.val) {
                    result.add(r2.val);
                    s2.pop();
                    populate(r2.right, s2);
                } else {
                    result.add(r1.val);
                    s1.pop();
                    populate(r1.right, s1);
                }

            }
            Stack<TreeNode> s = s1.isEmpty() ? s2 : s1;
            while (!s.isEmpty()) {
                TreeNode r = s.pop();
                result.add(r.val);
                populate(r.right, s2);
            }
            return result;
        }

        public void populate(TreeNode root, Stack<TreeNode> s) {
            if (root == null) {
                return;
            }
            s.add(root);
            populate(root.left, s);
        }
    }

    class maxProduct {
        long result = 0, totalSum = 0, subTotal;

        public int maxProduct(TreeNode root) {
            totalSum = rec(root);
            rec(root);
            return (int) (result % (int) (1e9 + 7));
        }

        private long rec(TreeNode root) {
            if (root == null) {
                return 0;
            }
            subTotal = root.val + rec(root.left) + rec(root.right);
            result = Math.max(result, subTotal * (totalSum - subTotal));
            return subTotal;
        }
    }
}
