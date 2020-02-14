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
        public CircularNode insert(CircularNode head, int insertVal) {
            if (head == null) {
                CircularNode cur = new CircularNode(insertVal);
                cur.next = cur;
                return cur;
            }
            CircularNode currentNode = head.next;
            CircularNode previousNode = head;
            boolean found = false;
            while (currentNode != head) {
                int currentVal = currentNode.val;
                int previousVal = previousNode.val;
                //All the conditions you will land into to find the current and previous node
                // IN-BETWEEN which you have to insert.
                if ((insertVal == previousVal) ||
                        (insertVal <= currentVal && insertVal > previousVal) ||
                        (currentVal < previousVal && insertVal > previousVal) ||
                        (currentVal < previousVal && insertVal < currentVal)) {
                    found = true;
                    CircularNode node = new CircularNode(insertVal);
                    previousNode.next = node;
                    node.next = currentNode;
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
            if (!found) {
                CircularNode node = new CircularNode(insertVal);
                previousNode.next = node;
                node.next = currentNode;
            }
            return head;
        }

        class CircularNode {
            public CircularNode next;
            public int val;

            public CircularNode(int x) {
                this.val = x;
            }
        }
    }

    class NextPermutationOfGivenArray {
        int[] A;

        public void nextPermutation(int[] nums) {
            this.A = nums;
            int k = -1;
            k = findTheNextDecreasingElement();

            /**
             * If it is the last order just sort and return.
             * because the next permutation will be reverse of what is present now
             */
            if (k == -1) {
                Arrays.sort(nums);
                return;
            }

            // Find the largest index l greater than k such that a[k] < a[l].
            int l = k + 1;
            l = findLargestElementGreaterThaneKthIndex(k, l);

            // Swap the value of a[k] with that of a[l].
            swapTheValues(k, l);

            // Reverse the sequence from a[k + 1] up to and including the final element a[n].
            int tmp;
            for (int i = 1; k + i < A.length - i; i++) {
                tmp = A[k + i];
                A[k + i] = A[A.length - i];
                A[A.length - i] = tmp;
            }
        }

        private void swapTheValues(int k, int l) {
            int tmp = A[k];
            A[k] = A[l];
            A[l] = tmp;
        }

        private int findLargestElementGreaterThaneKthIndex(int k, int l) {
            for (int i = k + 2; i < A.length; i++) {
                if (A[k] < A[i]) {
                    l = i;
                }
            }
            return l;
        }

        private int findTheNextDecreasingElement() {
            int k = -1;
            for (int i = 0; i < A.length - 1; i++) {
                if (A[i] < A[i + 1]) {
                    k = i;
                }
            }
            return k;
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

    public class FindFirstBadVersion extends VersionControl {
        public int firstBadVersion(int n) {
            int low = 1;
            int high = n;
            while (low < high) {
                int mid = low + ((high - low) / 2);
                boolean midResult = isBadVersion(mid);
                if (midResult) {
                    high = mid;
                } else {
                    //This is the key. Missing a mid + 1, Infinite loop it will go
                    low = mid + 1;
                }
            }
            return low;
        }

    }

    class VersionControl {
        public boolean isBadVersion(int mid) {
            return false;
        }
    }
}
