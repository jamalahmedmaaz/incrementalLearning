package March.march12;

import java.util.*;
public class Coding {

    static class VerifyPreorder_DOESNT_WORK {
        public boolean verifyPreorder(int[] A) {
            if (A == null || A.length == 0) {
                return true;
            }
            Map<Integer, Integer> data = new HashMap();
            data.put(0, A[0]);
            int counter = 1;
            for (int i = 1; i < A.length; i++) {
                if (A[i] > A[i - 1]) {
                    counter--;
                } else {
                    counter++;
                }
                int prev = data.get(Math.abs(counter));
                if (prev > A[i]) {
                    return false;
                }
                data.put(counter, A[i]);
            }
            return true;
        }
    }

    class BinaryTreeUpSideDown {
        public TreeNode upsideDownBinaryTree(TreeNode root) {
            if (root == null || root.left == null) {
                return root;
            }
            TreeNode newRoot = upsideDownBinaryTree(root.left);
            root.left.left = root.right;   // node 2 left children
            root.left.right = root;         // node 2 right children
            root.left = null;
            root.right = null;
            return newRoot;
        }

        class TreeNode {
            public TreeNode left;
            public TreeNode right;
        }
    }

    class ProductOfNumbers {
        TreeSet<Integer> zeroIndex;
        List<Integer> prefixProd;

        public ProductOfNumbers() {
            zeroIndex = new TreeSet();
            prefixProd = new ArrayList();
        }

        public void add(int num) {
            if (prefixProd.size() == 0) {
                prefixProd.add(num);
            } else {
                int previousNumber = prefixProd.get(prefixProd.size() - 1);
                prefixProd.add((previousNumber == 0 ? 1 : previousNumber) * num);
            }
            if (num == 0) {
                zeroIndex.add(prefixProd.size() - 1);
            }
        }

        public int getProduct(int k) {
            if (!containsZerosInRange(k) || zeroIndex.isEmpty()) {
                int lastIndex = prefixProd.size() - 1;
                int lastValue = prefixProd.get(lastIndex);

                int boundElement = 0;
                if (lastIndex - k >= 0) {
                    boundElement = prefixProd.get(lastIndex - k);
                }
                if (boundElement == 0) {
                    return lastValue;
                } else {
                    return lastValue / boundElement;
                }
            } else {
                return 0;
            }
        }

        public boolean containsZerosInRange(int k) {
            int lastIndex = prefixProd.size() - 1;
            int initialBoundIndex = lastIndex - k + 1;
            return zeroIndex.subSet(initialBoundIndex, lastIndex).size() > 0;
        }

    }

    class ProductOfNumbers_IMPROVED {
        ArrayList<Integer> prefixProduct;

        public ProductOfNumbers_IMPROVED() {
            add(0);
        }

        public void add(int a) {
            if (a > 0) {
                prefixProduct.add(prefixProduct.get(prefixProduct.size() - 1) * a);
            } else {
                prefixProduct = new ArrayList();
                prefixProduct.add(1);
            }
        }

        public int getProduct(int k) {
            int size = prefixProduct.size();
            return k < size ? prefixProduct.get(size - 1) / prefixProduct.get(size - k - 1) : 0;
        }
    }

}
