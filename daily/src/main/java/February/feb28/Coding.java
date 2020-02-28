package February.feb28;

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
}
