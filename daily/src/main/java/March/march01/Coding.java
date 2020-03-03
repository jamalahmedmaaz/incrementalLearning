package March.march01;

import java.util.HashSet;
import java.util.Set;
public class Coding {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minCost(new int[][]{{3, 4, 3}, {2, 2, 2}, {2, 1, 1}, {4, 3, 2}, {2, 1, 4}, {2, 4, 1}, {3, 3, 3}, {
                1, 4, 2}, {2, 2, 1}, {2, 1, 1}, {3, 3, 1}, {4, 1, 4}, {2, 1, 4}, {3, 2, 2}, {3, 3, 1}, {4, 4, 1}, {1, 2, 2}, {1, 1, 1}, {
                1, 3, 4}, {1, 2, 1}, {2, 2, 4}, {2, 1, 3}, {1, 2, 1}, {4, 3, 2}, {3, 3, 4}, {2, 2, 1}, {3, 4, 3}, {4, 2, 3}, {4, 4, 4}}));
    }

    static class Solution {
        int r;
        int c;
        int[][] grid;
        int[][] dp;
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; //R L B T
        Set<Integer> set = new HashSet();

        public int minCost(int[][] grid) {
            if (grid.length == 0) {
                return 0;
            }
            this.grid = grid;
            r = grid.length;
            c = grid[0].length;
            // dp = new int[r][c];
            return rec(0, 0);
        }

        public int rec(int x, int y) {
            if (x >= r || y >= c || x < 0 || y < 0 || set.contains(x * c + y)) {
                return Integer.MAX_VALUE / 2;
            }
            if (x == r - 1 && y == c - 1) {
                // System.out.println(" yes i have reached");
                return 0;
            }
            // if(dp[x][y] != 0){
            //     return dp[x][y];
            // }

            int dirIndex = grid[x][y] - 1;
            int min = Integer.MAX_VALUE / 2;
            set.add(x * c + y);
            for (int i = 0; i < directions.length; i++) {
                int[] d = directions[i];
                int nx = x + d[0];
                int ny = y + d[1];
                int temp;
                if (dirIndex == i) {
                    temp = rec(nx, ny);
                } else {
                    temp = 1 + rec(nx, ny);
                }
                min = Math.min(min, temp);
            }
            set.remove(x * c + y);
            // dp[x][y] = min;
            return min;
        }

    }
}
