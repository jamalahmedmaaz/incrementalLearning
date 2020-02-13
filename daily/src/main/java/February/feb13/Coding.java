package February.feb13;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
public class Coding {

    /**
     * Questions:
     * <p>
     * 1. Can you do this in recursion.
     * 2. Is this problem connected to Longest Increasing sub-sequence.
     */
    class LongestArithSeqLength {
        public int longestArithSeqLength(int[] A) {
            if (A.length <= 1) {
                return A.length;
            }
            int res = 2;
            int n = A.length;
            HashMap<Integer, Integer>[] dp = new HashMap[n];
            for (int i = 0; i < n; i++) {
                dp[i] = new HashMap<>();
            }
            for (int first = 0; first < A.length; first++) {
                int firstValue = A[first];
                for (int second = first + 1; second < A.length; second++) {
                    int secondValue = A[second];
                    int diff = secondValue - firstValue;
                    //Append the count of first value to second value (based on diff)
                    dp[second].put(diff, dp[first].getOrDefault(diff, 1) + 1);
                    res = Math.max(res, dp[second].get(diff));
                }
            }
            return res;
        }
    }

    class IslandPerimeter {
        public int islandPerimeter(int[][] grid) {

            int islands = 0;
            int neighbours = 0;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        islands++;

                        if (i + 1 < grid.length && grid[i + 1][j] == 1) {
                            neighbours++;
                        }

                        if (j + 1 < grid[i].length && grid[i][j + 1] == 1) {
                            neighbours++;
                        }
                    }
                }
            }

            return islands * 4 - 2 * neighbours;
        }
    }

    class MinDepth {
        int result = Integer.MAX_VALUE;

        public int minDepth(MinDepthTreeNode root) {
            if (root == null) {
                return 0;
            }
            rec(root, 1);
            return result;
        }

        public void rec(MinDepthTreeNode root, int height) {
            if (root == null) {
                return;
            }

            if (root.left == null && root.right == null) {
                result = Math.min(result, height);
                return;
            }

            rec(root.left, height + 1);
            rec(root.right, height + 1);
        }

        class MinDepthTreeNode {

            public MinDepthTreeNode left;
            public MinDepthTreeNode right;
        }
    }

    class PickRandomDuplicateNumberIndex {
        Map<Integer, Queue<Integer>> map = new HashMap();

        public PickRandomDuplicateNumberIndex(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                if (!map.containsKey(nums[i])) {
                    map.put(nums[i], new LinkedList());
                }
                map.get(nums[i]).add(i);
            }
        }

        public int pick(int target) {
            if (map.containsKey(target)) {
                int ele = map.get(target).poll();
                map.get(target).add(ele);
                return ele;
            }
            return -1;
        }
    }
}
