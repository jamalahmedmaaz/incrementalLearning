package February.feb21;

import java.util.*;
public class Coding {

    /**
     * Example 1:
     * <p>
     * Input: "12"
     * Output: 2
     * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
     * <p>
     * <p>
     * Example 2:
     * <p>
     * Input: "226"
     * Output: 3
     * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
     */
    class NumberOfWaysAStringCanBeDecodedAsAlphabet {
        Map<Integer, Integer> map = new HashMap();
        String s;

        public int numDecodings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            this.s = s;
            return rec(0);
        }

        public int rec(int idx) {
            if (idx == s.length()) {
                return 1;
            } else {
                if (map.containsKey(idx)) {
                    return map.get(idx);
                }
                int count = 0;
                //Can we break only one character.
                if (checkOne(idx)) {
                    count = count + rec(idx + 1);
                }
                //Can we break two characters.
                if (checkTwo(idx + 1)) {
                    count = count + rec(idx + 2);
                }
                map.put(idx, count);
                return count;
            }
        }

        public boolean checkOne(int idx) {
            return s.charAt(idx) != '0';
        }

        public boolean checkTwo(int idx) {
            if (idx < s.length()) {
                //Check if previous character is 1 then we dont need to check anything.
                // Because '1' can paired with 0 - 9 (and we have letter from 10 to 19)
                if (s.charAt(idx - 1) == '1') {
                    return true;
                } else if (s.charAt(idx - 1) == '2') {
                    //Check if previous character is 2 and then the current character should be
                    // between 0 to 6 because we have alphabets between till only 26
                    return s.charAt(idx) >= '0' && s.charAt(idx) <= '6';
                }
            }
            return false;
        }
    }

    /**
     * Given a list of non-negative numbers and a target integer k, write a
     * function to check if the array has a continuous subarray of size at
     * least 2 that sums up to a multiple of k, that is, sums up to n*k where
     * n is also an integer.
     *
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: [23, 2, 4, 6, 7],  k=6
     * Output: True
     * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
     * <p>
     * <p>
     * Example 2:
     * <p>
     * Input: [23, 2, 6, 4, 7],  k=6
     * Output: True
     * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
     */
    class CheckSubArraySumWhichIsAMultipleOf_K {
        public boolean checkSubarraySum(int[] nums, int k) {
            int sum = 0;
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (k != 0) {
                    sum = sum % k;
                }
                if (map.containsKey(sum)) {
                    if (i - map.get(sum) > 1) {
                        return true;
                    }
                } else {
                    map.put(sum, i);
                }
            }
            return false;
        }
    }

    class findSmallestRegion_Lowest_Common_Acestor {
        public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
            Map<String, String> parents = new HashMap();
            for (List<String> region : regions) {
                String parent = region.get(0);
                for (int i = 1; i < region.size(); i++) {
                    parents.put(region.get(i), parent);
                }
            }
            Set<String> region1set = new HashSet();
            while (region1 != null) {
                region1set.add(region1);
                region1 = parents.getOrDefault(region1, null);
            }
            String result = null;
            while (region2 != null) {
                if (region1set.contains(region2)) {
                    result = region2;
                    break;
                }
                region2 = parents.getOrDefault(region2, null);

            }
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.findStrobogrammatic(2).toArray()));
    }

    static class Solution {
        private static final char[][] direction = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
        List<String> res = new ArrayList<>();

        public List<String> findStrobogrammatic(int n) {
            if (n < 1) {
                return res;
            }
            rec(0, n - 1, new char[n]);
            return res;
        }

        private void rec(int left, int right, char[] chars) {
            if (left > right) {
                String str = new String(chars);
                res.add(str);
                return;
            }
            if (left == right) {
                chars[left] = '0';
                rec(left + 1, right - 1, chars);
                chars[left] = '1';
                rec(left + 1, right - 1, chars);
                chars[left] = '8';
                rec(left + 1, right - 1, chars);
                return;
            }
            for (int i = 0; i < 5; i++) {
                if (i == 0 && left == 0) {
                    continue;
                }
                chars[left] = direction[i][0];
                chars[right] = direction[i][1];
                rec(left + 1, right - 1, chars);
            }
        }
    }

    class ReverseKGroup {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode curr = head;
            int count = 0;
            while (curr != null && count != k) { // find the k+1 node
                curr = curr.next;
                count++;
            }
            if (count == k) { // if k+1 node is found
                curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
                // head - head-pointer to direct part,
                // curr - head-pointer to reversed part;
                while (count-- > 0) { // reverse current k-group:
                    ListNode tmp = head.next; // tmp - next head in direct part
                    head.next = curr; // preappending "direct" head to the reversed list
                    curr = head; // move head of reversed part to a new node
                    head = tmp; // move "direct" head to the next node in direct part
                }
                head = curr;
            }
            return head;
        }

        class ListNode {
            public ListNode next;
        }
    }

    class AccountsMerge_UnionFind {

        List<List<String>> accounts;
        int[] uf;

        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            this.accounts = accounts;
            uf = new int[accounts.size()];
            for (int i = 0; i < uf.length; i++) {
                uf[i] = i;
            }
            List<List<String>> result = new ArrayList();
            for (int i = 0; i < accounts.size(); i++) {
                for (int j = i + 1; j < accounts.size(); j++) {
                    if (areEmailAddressCommon(accounts, i, j)) {
                        union(i, j);
                    }
                }
            }
            Map<Integer, Set<Integer>> map = new HashMap();
            for (int i = 0; i < uf.length; i++) {
                int parent = uf[i];
                int child = i;
                if (!map.containsKey(parent)) {
                    map.put(parent, new HashSet());
                }
                map.get(parent).add(child);
            }

            for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
                Set<String> val = new TreeSet();
                for (int i : entry.getValue()) {
                    val.addAll(accounts.get(i));
                }
                result.add(new ArrayList(val));
            }

//            System.out.println(map);
            return result;
        }

        private boolean areEmailAddressCommon(List<List<String>> accounts, int i, int j) {
            List<String> x1 = accounts.get(i).subList(1, accounts.get(i).size());
            List<String> x2 = accounts.get(j).subList(1, accounts.get(j).size());
            return !Collections.disjoint(x1, x2);
        }

        public boolean union(int i, int j) {
            int pi = find(i);
            int pj = find(j);
            if (pi != pj) {
                uf[pj] = pi;
                return true;
            }
            return false;
        }

        public int find(int idx) {
            if (uf[idx] != idx) {
                uf[idx] = find(uf[idx]);
            }
            return uf[idx];
        }

    }

}
