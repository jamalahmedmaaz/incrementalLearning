package February.feb14;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
            int[] s1a = new int[26];
            for (char c : s1.toCharArray()) {
                s1a[c - 'a']++;
            }
            int s1Length = s1.length();
            int[] s2a = new int[26];
            for (int i = 0; i < s2.length(); i++) {
                int idx = s2.charAt(i) - 'a';
                s2a[idx]++;
                if (i >= s1Length) {
                    int previousIdx = s2.charAt(i - s1Length) - 'a';
                    s2a[previousIdx]--;
                }
                if (match(s1a, s2a)) {
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

    class InsertIntoCircularLinkedList {
        public CircularNode insert(CircularNode start, int x) {
            if (start == null) {
                CircularNode cur = new CircularNode(x);
                cur.next = cur;
                return cur;
            }
            CircularNode currentNode = start.next;
            CircularNode previousNode = start;
            boolean found = false;
            while (currentNode != start) {
                int currentVal = currentNode.val;
                int previousVal = previousNode.val;
                //All the conditions you will land into to find the current and previous node
                // IN-BETWEEN which you have to insert.
                if ((x == previousVal) ||
                        (x <= currentVal && x > previousVal) ||
                        (currentVal < previousVal && x > previousVal) ||
                        (currentVal < previousVal && x < currentVal)) {
                    found = true;
                    CircularNode node = new CircularNode(x);
                    previousNode.next = node;
                    node.next = currentNode;
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
            if (!found) {
                CircularNode node = new CircularNode(x);
                previousNode.next = node;
                node.next = currentNode;
            }
            return start;
        }

        class CircularNode {
            public CircularNode next;
            public int val;

            public CircularNode(int x) {
                this.val = x;
            }
        }
    }

    public class NextPermutationOfAGivenArray {

        public void nextPermutation(int[] nums) {
            int i = nums.length - 2;
            while (i >= 0 && nums[i + 1] <= nums[i]) {
                i--;
            }
            if (i >= 0) {
                int j = nums.length - 1;
                while (j >= 0 && nums[j] <= nums[i]) {
                    j--;
                }
                swap(nums, i, j);
            }
            reverse(nums, i + 1);
        }

        private void reverse(int[] nums, int start) {
            int i = start, j = nums.length - 1;
            while (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    class ThressSumNonDuplicateResult {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList();
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                if (i != 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int tmp = nums[i] + nums[left] + nums[right];
                    if (tmp == 0) {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        right--;
                    } else if (tmp > 0) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
            return result;
        }
    }

}
