package March.march08;

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
}
