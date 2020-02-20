package February.feb20;

import java.util.Stack;
public class Coding {

    /**
     * Should give next and hasnext in O(1) time.
     * <p>
     * Can only use log(n) = heigh of the tree space. NOT O(N)
     */
    class BSTIterator {

        Stack<TreeNode> stack = new Stack();

        public BSTIterator(TreeNode root) {
            pushAll(root);
        }

        public void pushAll(TreeNode root) {
            for (; root != null; root = root.left) {
                stack.push(root);
            }
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode current = stack.pop();
            pushAll(current.right);
            return current.val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
