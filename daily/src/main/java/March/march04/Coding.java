package March.march04;

import java.util.*;
public class Coding {

    public static void main(String[] args) {
        Solution solution = new Solution();

        solution.palindromePairs(new String[]{"bat", "tab", "cat"});
    }

    static class Solution {
        Trie trie = new Trie();
        List<List<Integer>> result = new ArrayList();

        public List<List<Integer>> palindromePairs(String[] words) {
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                trie.addWord(word, i);
            }
            for (int i = 0; i < words.length; i++) {
                String current = new StringBuilder(words[i]).reverse().toString();
                TrieNode trieNode = trie.searchWordTrieNode(current);
                System.out.println(current + " " + trieNode);
                Map<Integer, String> map = new HashMap();
                genreateString(map, trieNode, "");
//                System.out.println(map + " " + trieNode.childrens);
                findPalindromeAndAddToResult(map, i);
            }
            return result;
        }

        public void findPalindromeAndAddToResult(Map<Integer, String> map, int idx) {
            for (int i : map.keySet()) {
                if (i != idx) {
                    if (isPalindrome(map.get(i))) {
                        result.add(Arrays.asList(i, idx));
                    }
                }
            }
        }

        public boolean isPalindrome(String s) {
            int left = 0;
            int right = s.length() - 1;
            while (left < right) {
                if (s.charAt(left) == s.charAt(right)) {
                    left++;
                    right--;
                } else {
                    return false;
                }
            }
            return true;
        }

        public void genreateString(Map<Integer, String> map, TrieNode subRoot, String tmp) {
            if (subRoot == null) {
                return;
            }
            if (subRoot.isEndWord) {
                map.put(subRoot.index, tmp);
                return;
            }
            for (char c : subRoot.childrens.keySet()) {
                genreateString(map, subRoot.childrens.get(c), tmp + c);
            }
        }
    }

    static class TrieNode {
        Map<Character, TrieNode> childrens = new HashMap();
        boolean isEndWord;
        int index;

    }

    static class Trie {
        TrieNode root = new TrieNode();

        public void addWord(String word, int idx) {
            TrieNode tmp = root;
            for (char c : word.toCharArray()) {
                TrieNode next = tmp.childrens.get(c);
                if (next == null) {
                    tmp.childrens.put(c, new TrieNode());
                    next = tmp.childrens.get(c);
                }
                tmp = next;
            }
            tmp.isEndWord = true;
            tmp.index = idx;
        }

        public TrieNode searchWordTrieNode(String word) {
            TrieNode tmpRoot = root;
            for (char c : word.toCharArray()) {
                TrieNode next = tmpRoot.childrens.get(c);
                if (next == null) {
                    return tmpRoot;
                }
                tmpRoot = next;
            }
            return tmpRoot;
        }
    }

    class ValidMountainArray {
        public boolean validMountainArray(int[] A) {
            if (A == null || A.length <= 2) {
                return false;
            }
            int i = 1;
            while (i < A.length) {
                if (A[i] > A[i - 1]) {
                } else {
                    break;
                }
                i++;
            }
            if (i == A.length || i == 1) {
                return false;
            }
            while (i < A.length) {
                if (A[i] < A[i - 1]) {

                } else {
                    return false;
                }
                i++;
            }
            return true;
        }
    }

    class CanPartitionKSubsets {
        int[] nums;
        boolean[] visited;
        int sum;

        public boolean canPartitionKSubsets(int[] nums, int k) {
            this.nums = nums;
            this.visited = new boolean[nums.length];
            for (int num : nums) {
                sum += num;
            }
            if (k <= 0 || sum % k != 0) {
                return false;
            }
            sum = sum / k;
            return backTracking(0, k, 0, 0);
        }

        public boolean backTracking(int idx, int k, int runningSum, int counter) {
            if (k == 1) {
                return true;
            }
            if (runningSum == sum && counter > 0) {
                return backTracking(0, k - 1, 0, 0);
            }
            for (int i = idx; i < nums.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    if (backTracking(i + 1, k, runningSum + nums[i], counter++)) {
                        return true;
                    }
                    visited[i] = false;
                }
            }
            return false;
        }

    }

}