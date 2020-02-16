import java.util.*;

public class CompleteDyanmicProgramming {

    class LongestPalindromicSubString {
        public void longestPalindromicSubString(String s) {
            char[] ca = s.toCharArray();
            int n = ca.length;
            String result = "";
            boolean[][] DP = new boolean[n][n];
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i; j < n; j++) {
                    DP[i][j] = ca[i] == ca[j] && (j - i < 3 || DP[i + 1][j - 1]);
                    if (DP[i][j] && (j - i + 1 > result.length())) {
                        result = s.substring(i, j + 1);
                    }
                }
            }
        }
    }

    class LongestValidParentheses {
        /**
         * V[i] represents number of valid parentheses matches from S[j to i] (j<i)
         * <p>
         * V[i] = V[i-1] + 2 + previous matches V[i- (V[i-1] + 2) ] if S[i] = ')' and '(' count > 0
         *
         * @param s
         * @return
         */
        public int longestValidParentheses(String s) {
            char[] S = s.toCharArray();
            int[] DP = new int[S.length];
            int open = 0;
            int max = 0;
            for (int i = 0; i < S.length; i++) {
                if (S[i] == '(') {
                    open++;
                }
                if (S[i] == ')' && open > 0) {
                    // matches found
                    DP[i] = 2 + DP[i - 1];
                    // add matches from previous
                    if (i - DP[i] > 0) {
                        DP[i] += DP[i - DP[i]];
                    }
                    open--;
                }
                if (DP[i] > max) {
                    max = DP[i];
                }
            }
            return max;
        }
    }

    class UniquePaths {
        public int uniquePaths(int m, int n) {
            int[][] DP = new int[m][n];
            for (int i = 0; i < m; i++) {
                DP[i][0] = 1;
            }

            for (int i = 0; i < n; i++) {
                DP[0][i] = 1;
            }

            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    DP[i][j] = DP[i - 1][j] + DP[i][j - 1];
                }
            }
            return DP[m - 1][n - 1];
        }
    }

    public int uniquePathsWithObstacles(int[][] A) {
        if (A.length == 0) {
            return 0;
        }
        int r = A.length;
        int c = A[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (A[i][j] == 1) {
                    A[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < r; i++) {
            if (A[i][0] == -1) {
                break;
            }
            A[i][0] = 1;
        }
        for (int i = 0; i < c; i++) {
            if (A[0][i] == -1) {
                break;
            }
            A[0][i] = 1;
        }
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (A[i][j] == -1) {
                    continue;
                }
                A[i][j] += (A[i - 1][j] == -1 ? 0 : A[i - 1][j]) + (A[i][j - 1] == -1 ? 0 : A[i][j - 1]);
            }
        }
        // System.out.println(Arrays.deepToString(A));
        return A[r - 1][c - 1] == -1 ? 0 : A[r - 1][c - 1];
    }

    public int minPathSum(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        for (int i = 1; i < c; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < r; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                grid[i][j] = Math.min(grid[i - 1][j] + grid[i][j], grid[i][j - 1] + grid[i][j]);
            }
        }
        return grid[r - 1][c - 1];
    }

    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int one = 1;
        int two = 2;

        for (int i = 3; i <= n; i++) {
            int tmp = one + two;
            one = two;
            two = tmp;
        }
        return two;
    }

    String w1, w2;
    int[][] cache;

    public int minDistance(String word1, String word2) {
        w1 = word1;
        w2 = word2;
        cache = new int[w1.length() + 1][w2.length() + 1];
        return editDistance(w1.length(), w2.length());
    }

    public int editDistance(int i, int j) {
        if (i == 0) {
            return j;
        } else if (j == 0) {
            return i;
        }
        if (cache[i][j] != 0) {
            return cache[i][j];
        }
        int min = Integer.MAX_VALUE;
        if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
            min = editDistance(i - 1, j - 1);
        } else {
            int r1 = editDistance(i - 1, j);
            int r2 = editDistance(i, j - 1);
            int d = editDistance(i - 1, j - 1);
            min = 1 + Math.min(Math.min(r1, d), r2);
        }
        return cache[i][j] = min;
    }

    /**
     * Unique Binary Search Trees II
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n != 0) {
            return rec(1, n);
        } else {
            return new ArrayList();
        }
    }

    public List<TreeNode> rec(int start, int end) {
        List<TreeNode> result = new ArrayList();

        if (start > end) {
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> left = rec(start, i - 1);
            List<TreeNode> right = rec(i + 1, end);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode cursor = new TreeNode(i);
                    cursor.left = l;
                    cursor.right = r;
                    result.add(cursor);
                }
            }
        }
        return result;
    }

    class UniqueBinarySearchTrees {
        int[] cache;

        public int numTrees(int n) {
            cache = new int[n + 1];
            return rec(n);
        }

        private int rec(int n) {
            if (n <= 1) {
                return 1;
            }
            if (cache[n] > 0) {
                return cache[n];
            }

            int count = 0;
            for (int i = 1; i <= n; i++) {
                count = count + rec(i - 1) * rec(n - i);
            }

            return cache[n] = count;
        }
    }

    /**
     * Distinct Subsequences
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int c = s.length();
        int r = t.length();
        int[][] DP = new int[r + 1][c + 1];
        for (int i = 0; i <= c; i++) {
            DP[0][i] = 1;
        }
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (s.charAt(j - 1) == t.charAt(i - 1)) {
                    DP[i][j] = DP[i][j - 1] + DP[i - 1][j - 1];
                } else {
                    DP[i][j] = DP[i][j - 1];
                }

            }
        }
        return DP[r][c];
    }

    /**
     * 120. Triangle
     * <p>
     * Given a triangle, find the minimum path sum from top to bottom.
     * Each step you may move to adjacent numbers on the row below.
     * <p>
     * For example, given the following triangle
     * <p>
     * [
     * [2],
     * [3,4],
     * [6,5,7],
     * [4,1,8,3]
     * ]
     * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
     */
    List<List<Integer>> A;

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        for (int i = n - 2; i >= 0; i--) {
            List<Integer> current = triangle.get(i);
            List<Integer> prev = triangle.get(i + 1);
            for (int j = 0; j < current.size(); j++) {
                int cv = current.get(j);
                int previous = cv + prev.get(j);
                int next = cv + prev.get(j + 1);
                current.set(j, Math.min(previous, next));
            }
        }
        // System.out.println(Arrays.deepToString(triangle.toArray()));
        return triangle.get(0).get(0);
    }

    class BestTimetoBuyandSellStock {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int max = 0;

            int min = prices[0];

            for (int i = 1; i < prices.length; i++) {
                max = Math.max(max, prices[i] - min);
                min = Math.min(min, prices[i]);
            }

            return max;
        }
    }

    /**
     * 139. Word Break
     */
    class wordBreak {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<Integer> set = new HashSet(wordDict);

            //The idea is to memorize the operations we are doing.
            //Lets take a DP array of 1 greater size than the string.
            boolean[] dp = new boolean[s.length() + 1];

            //Lets assume the first character exists.
            dp[0] = true;

            //As we have already used first location start from 1st.
            for (int i = 1; i <= s.length(); i++) {

                //The magic of this loop is it creates a subset. see Below
                // l
                // le
                // e
                // lee
                // ee
                // e
                // leet
                // leetc
                // eetc
                // etc
                // tc
                // c
                // leetco
                // eetco
                // etco
                // tco
                // co
                // o
                // leetcod
                // eetcod
                // etcod
                // tcod
                // cod
                // od
                // d
                // leetcode
                // eetcode
                // etcode
                // tcode
                // code
                for (int j = 0; j < i; j++) {

                    //Now, to fill in the entry \text{dp}[i]dp[i], we check if the \text{dp}[j]dp[j]
                    // contains \text{true}true, i.e. if the substring s1's1 fulfills the required criteria.
                    // If so, we further check if s2's2 is present in the dictionary. If both the strings fulfill the
                    // criteria, we make \text{dp}[i]dp[i] as \text{true}true, otherwise as \text{false}false

                    String sub = s.substring(j, i);

                    //Check first character exists.
                    //and also check if the substring exists
                    //in the set.
                    if (dp[j] && set.contains(sub)) {
                        //Use this information and memorize the
                        // location of string to be true.
                        dp[i] = true;
                        //Dont go anymore or else it will be false
                        break;
                    }
                }
            }

            //  Example of what has happend in the dp array.
            //  [true, false, false, false, true, false, false, false, true]
            // System.out.println(Arrays.toString(dp));

            return dp[s.length()];
        }
    }

    class MaxProduct {
        /**
         * Maximum Product Subarray
         *
         * @param nums
         * @return
         */
        public int maxProduct(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int result = nums[0];
            int l = 0;
            int r = 0;
            for (int i = 0; i < nums.length; i++) {
                l = (l == 0 ? 1 : l) * nums[i];
                r = (r == 0 ? 1 : r) * nums[nums.length - i - 1];
                result = Math.max(result, Math.max(l, r));
            }
            return result;
        }
    }

    class BestTimetoBuyandSellStockIV {
        public int maxProfit(int k, int[] prices) {
            int len = prices.length;
            if (k >= len / 2) {
                return quickSolve(prices);
            }

            int[][] t = new int[k + 1][len];
            for (int i = 1; i <= k; i++) {
                int tmpMax = -prices[0];
                for (int j = 1; j < len; j++) {
                    t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                    tmpMax = Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
                }
            }
            return t[k][len - 1];
        }

        private int quickSolve(int[] prices) {
            int len = prices.length, profit = 0;
            for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }
    }

    class HouseRobber {
        public int rob(int[] nums) {

            if (nums == null || nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }

            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            int max = dp[1];
            for (int i = 2; i < dp.length; i++) {
                dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }

    class HouseRobberII {
        public int rob(int[] nums) {

            if (nums.length == 0) {
                return 0;
            }

            if (nums.length == 1) {
                return nums[0];
            }

            if (nums.length == 2) {
                if (nums[0] > nums[1]) {
                    return nums[0];
                } else {
                    return nums[1];
                }
            }

            int uncon = 0;
            int con = 0;

            for (int i = 1; i <= nums.length - 1; i++) {
                int tmp = uncon;
                uncon = Math.max(uncon, con);
                con = nums[i - 1] + tmp;
            }

            int uncon1 = 0;
            int con1 = 0;
            for (int i = 2; i <= nums.length; i++) {
                int tmp = uncon1;
                uncon1 = Math.max(uncon1, con1);
                con1 = nums[i - 1] + tmp;
            }

            return Math.max(Math.max(uncon, con), Math.max(uncon1, con1));
        }
    }

    /**
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
     * <p>
     * Example:
     * <p>
     * Input:
     * <p>
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     * <p>
     * Output: 4
     */
    class MaximalSquare {
        public int maximalSquare(char[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return 0;
            }
            int r = matrix.length;
            int c = matrix[0].length;
            int[][] dp = new int[r + 1][c + 1];
            int max = 0;
            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp[i].length; j++) {
                    if (matrix[i - 1][j - 1] == '1') {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }
            return max * max;
        }
    }

    class PaintHouse {
        public int minCost(int[][] costs) {
            if (costs == null || costs.length == 0) {
                return 0;
            }
            for (int i = 1; i < costs.length; i++) {
                costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
                costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
                costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
            }
            int size = costs.length - 1;
            return Math.min(costs[size][0], Math.min(costs[size][1], costs[size][2]));
        }
    }

    class PaintFence {
        public int numWays(int n, int k) {
            return rec(n, k);
        }

        public int rec(int n, int k) {
            if (n == 0) {
                return 1;
            }
            if (n < 0) {
                return 0;
            }
            return rec(n - 1, k - 1) + rec(n - 2, k - 1);
        }
    }

    class PerfectSquares {
        int count = 0;

        public int numSquares(int n) {
            if (n <= 0) {
                return 0;
            }
            int[] dp = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                int size = i;
                int min = Integer.MAX_VALUE;
                for (int j = 1; j * j <= size; j++) {
                    min = Math.min(min, dp[size - j * j] + 1);
                }
                dp[i] = min;
            }
            // System.out.println(Arrays.toString(dp.toArray()));
            return dp[n];
        }
    }

    /**
     * Input: [10,9,2,5,3,7,101,18]
     * Output: 4
     * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
     */
    class LongestIncreasingSubsequence {
        int[] A;
        int[] cache;

        public int lengthOfLIS(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            this.A = nums;
            cache = new int[A.length];
            int result = 0;
            for (int i = 0; i < A.length; i++) {
                result = Math.max(result, rec(i));
            }
            return result;
        }

        public int rec(int index) {
            int max = 1;
            if (cache[index] != 0) {
                return cache[index];
            }
            for (int i = index; i < A.length; i++) {
                if (A[i] > A[index]) {
                    max = Math.max(max, 1 + rec(i));
                }
            }
            return cache[index] = max;
        }
    }

    class BestTimetoBuyandSellStockwithCooldown {
        public int maxProfit(int[] prices) {

            //I HAVE TO UNDERSTAND THE PROBLEM BETTER I AM NOT PERFECT.

            int sell = 0;
            int buy = Integer.MIN_VALUE;
            int sell_previous = 0;
            int buy_previous = 0;

            for (int i = 0; i < prices.length; i++) {

                //Track
                buy_previous = buy;
                //Process
                buy = Math.max(sell_previous - prices[i], buy_previous);

                //Track
                sell_previous = sell;

                //Process
                sell = Math.max(buy_previous + prices[i], sell_previous);

            }

            return sell;
        }
    }

    /**
     * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
     * You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
     * Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
     * <p>
     * Find the maximum coins you can collect by bursting the balloons wisely.
     * <p>
     * Note:
     * <p>
     * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
     * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
     * Example:
     * <p>
     * Input: [3,1,5,8]
     * Output: 167
     * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
     * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
     */
    class BurstBalloons {
        int[] A;
        int[][] cache;

        public int maxCoins(int[] nums) {
            A = new int[nums.length + 2];
            for (int i = 1; i < A.length - 1; i++) {
                A[i] = nums[i - 1];
            }
            A[0] = 1;
            A[A.length - 1] = 1;
            cache = new int[A.length][A.length];
            return rec(0, A.length - 1);
        }

        public int rec(int i, int j) {
            if (i + 1 == j) {
                return 0;
            }
            if (cache[i][j] != 0) {
                return cache[i][j];
            }
            int sum = 0;
            for (int k = i + 1; k < j; k++) {
                //MERGE INTERVAL FORMULA.
                int cal = A[i] * A[k] * A[j];
                sum = Math.max(sum, rec(i, k) + cal + rec(k, j));
            }
            return cache[i][j] = sum;
        }
    }

    class CoinChange {
        public int coinChange(int[] coins, int amount) {
            if (amount == 0) {
                return 0;
            }
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, Integer.MAX_VALUE - 1);
            for (int i = 0; i < coins.length; i++) {
                if (coins[i] < dp.length) {
                    dp[coins[i]] = 1;
                }
            }
            for (int i = 1; i <= amount; ++i) {
                for (int j = 0; j < coins.length; ++j) {
                    if (coins[j] <= i) {
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    }
                }
            }
            // System.out.println(Arrays.toString(dp));
            return dp[amount] == Integer.MAX_VALUE - 1 ? -1 : dp[amount];
        }
    }

    class CountingBits {
        public int[] countBits(int num) {
            int[] dp = new int[num + 1];
            for (int i = 1; i <= num; i++) {
                dp[i] = dp[i / 2] + i % 2;
            }
            return dp;
        }
    }

    class LargestDivisibleSubset {

        Map<Integer, List<Integer>> map = new HashMap();
        int[] A;

        public List<Integer> largestDivisibleSubset(int[] nums) {
            List<Integer> result = new ArrayList();
            A = nums;
            Arrays.sort(A);
            for (int i = 0; i < A.length; i++) {
                List<Integer> list = rec(i);
                if (list.size() > result.size()) {
                    result = list;
                }
            }

            return result;
        }

        public List<Integer> rec(int idx) {
            if (map.containsKey(idx)) {
                return map.get(idx);
            }
            List<Integer> tmp = new ArrayList();
            for (int i = idx + 1; i < A.length; i++) {
                if (A[i] % A[idx] == 0) {
                    List<Integer> forward = rec(i);
                    if (forward.size() > tmp.size()) {
                        tmp = forward;
                    }
                }
            }
            tmp = new ArrayList(tmp);
            tmp.add(0, A[idx]);
            map.put(idx, tmp);
            return tmp;
        }
    }

    class WiggleSubsequence {
        int[] A;
        int[][] cache;

        public int wiggleMaxLength(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            A = nums;
            int result = 0;
            cache = new int[A.length][4];
            // 1 = true and 2 = false
            return 1 + Math.max(rec(0, 1), rec(0, 2));
        }

        public int rec(int index, int previous) {
            int count = 0;
            if (cache[index][previous] != 0) {
                return cache[index][previous];
            }
            for (int i = index + 1; i < A.length; i++) {
                if (previous == 1 && A[index] > A[i] || previous == 2 && A[index] < A[i]) {
                    count = Math.max(count, rec(i, previous == 1 ? 2 : 1) + 1);
                }
            }
            return cache[index][previous] = count;
        }
    }

    class CombinationSumIV {
        int[] A;
        int[] cache;

        public int combinationSum4(int[] nums, int target) {
            A = nums;
            cache = new int[target + 1];
            Arrays.fill(cache, -1);
            cache[0] = 1;
            return rec(target);
        }

        public int rec(int target) {
            if (target == 0) {
                return 1;
            }
            if (cache[target] != -1) {
                return cache[target];
            }
            int count = 0;
            for (int ele : A) {
                if (target >= ele) {
                    count = count + rec(target - ele);
                }
            }
            return cache[target] = count;
        }
    }

    class IsSubsequence {
        public boolean isSubsequence(String s, String t) {
            int ns = s.length();
            int nt = t.length();
            int[][] dp = new int[ns + 1][nt + 1];
            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp[i].length; j++) {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[ns][nt] == ns;
        }
    }

    class LongestArithSeqLength {

        /**
         * Time Limit exceeded.
         */
        Map<Integer, Map<Integer, Integer>> dp = new HashMap();
        int[] A;

        public int longestArithSeqLength_BEST(int[] A) {
            if (A == null || A.length == 0) {
                return 0;
            }
            int n = A.length, result = 0;
            int[][] dp = new int[n][n];
            int[] index = new int[20001];

            for (int first = 0; first < n; first++) {
                for (int second = first + 1; second < n; second++) {
                    int previousValue = A[first] - (A[second] - A[first]);
                    if (previousValue < 0 || index[previousValue] == 0) {
                        continue;
                    }
                    int idx = index[previousValue] - 1;
                    dp[first][second] = dp[idx][first] + 1;
                    result = Math.max(result, dp[first][second]);
                }
                int firstIndex = A[first];
                index[firstIndex] = first + 1;
            }
            return result + 2;
        }

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

        public int longestArithSeqLength_Recursive(int[] A) {
            this.A = A;
            int max = 2;
            for (int i = 0; i < A.length; i++) {
                for (int j = i + 1; j < A.length; j++) {
                    max = Math.max(max, 2 + rec(i, A[j] - A[i]));
                }
            }
            // System.out.println(dp);
            return max;
        }

        private int rec(int idx, int diff) {
            if (idx < 0) {
                return 0;
            }
            if (dp.containsKey(idx)
                    && dp.get(idx).containsKey(diff)
                    && dp.get(idx).get(diff) != 0) {
                return dp.get(idx).get(diff);
            }
            int max = 0;
            for (int i = idx - 1; i >= 0; i--) {
                if (A[idx] - A[i] == diff) {
                    max = Math.max(max, 1 + rec(i, diff));
                }
            }
            if (!dp.containsKey(idx)) {
                dp.put(idx, new HashMap<>());
            }
            dp.get(idx).put(diff, 0);
            return max;
        }

    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}