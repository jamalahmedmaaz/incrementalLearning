package Jan21;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
public class Coding {

    class PopulateNextPointer {

        public void connect(NextPointerNode root) {
            while (root != null && root.left != null) {
                NextPointerNode cur = root;
                while (cur != null) {
                    cur.left.next = cur.right;
                    cur.right.next = cur.next == null ? null : cur.next.left;
                    cur = cur.next;
                }
                root = root.left;
            }
        }

        private class NextPointerNode {
            public NextPointerNode left;
            public NextPointerNode right;
            public NextPointerNode next;
        }
    }

    /**
     * THIS PROBLEM FOR ME IS A DISASTER I HAVE DONE CRAPPY THINGS POSSIBLE. IT DOESNT FEEL GOOD NEED TO DO THIS AGAIN.
     */
    class ConstructBinaryTreeFromString {

        /**
         * Definition for a binary tree node.
         * public class ConstructTreeNode {
         * int val;
         * ConstructTreeNode left;
         * ConstructTreeNode right;
         * ConstructTreeNode(int x) { val = x; }
         * }
         */
        class Solution {
            Stack<ConstructTreeNode> stack = new Stack();
            Set<ConstructTreeNode> set = new HashSet();

            public ConstructTreeNode str2tree(String s) {
                if (s == null || Objects.equals(s, "")) {
                    return null;
                }
                if (!s.contains("(")) {
                    return new ConstructTreeNode(Integer.valueOf(s));
                }

                char[] sa = s.toCharArray();
                boolean negative = false;
                int i = 0;
                if (sa[0] == '-') {
                    i = 1;
                    negative = true;
                }
                // System.out.println(Character.getNumericValue(sa[0]));
                char num = 'a';

                int number = 0;
                for (; i < sa.length; i++) {
                    char c = sa[i];
                    switch (c) {
                        case ')':
                            if (!stack.isEmpty()) {
                                applyClosingBracket(num);
                            }
                            num = ')';
                            break;
                        case '(':
                            num = '(';
                            break;
                        default:
                            if (c == '-') {
                                negative = true;
                                number = 0;
                            } else {
                                num = '1';
                                int cal = Character.getNumericValue(c);
                                number = number * 10 + cal;
                                if (!Character.isDigit(sa[i + 1])) {
                                    ConstructTreeNode ele = new ConstructTreeNode(number * (negative ? -1 : 1));
                                    stack.add(ele);
                                    number = 0;
                                    negative = false;
                                }
                            }
                    }
                }

                for (ConstructTreeNode t : set) {
                    System.out.println(t.val);
                    if (t.left != null && t.left.val == Integer.MAX_VALUE) {
                        t.left = null;
                    }

                    if (t.right != null && t.right.val == Integer.MAX_VALUE) {
                        t.right = null;
                    }
                }

                return stack.pop();
            }

            private void applyClosingBracket(char num) {
                if (num == '(') {
                    if (stack.peek().left == null) {
                        stack.peek().left = new ConstructTreeNode(Integer.MAX_VALUE);
                        set.add(stack.peek());
                    } else if (stack.peek().right == null) {
                        stack.peek().right = new ConstructTreeNode(Integer.MAX_VALUE);
                        set.add(stack.peek());
                    }
                } else {
                    ConstructTreeNode last = stack.pop();
                    if (stack.peek().left == null) {
                        stack.peek().left = last;
                    } else if (stack.peek().right == null) {
                        stack.peek().right = last;
                    }
                }
                // System.out.println(stack.peek().val);
            }

            private class ConstructTreeNode {
                private final int val;
                public ConstructTreeNode left;
                public ConstructTreeNode right;

                public ConstructTreeNode(Integer val) {
                    this.val = val;
                }
            }
        }
    }

}
