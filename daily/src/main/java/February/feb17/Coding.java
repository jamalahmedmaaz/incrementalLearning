package February.feb17;

import java.util.*;
public class Coding {

    class All_N_Queen_Placements {
        char[][] ca;
        int n;
        List<List<String>> result = new ArrayList();

        public List<List<String>> solveNQueens(int n) {
            this.n = n;
            ca = new char[n][n];
            for (char[] c : ca) {
                Arrays.fill(c, '.');
            }
            rec(0);
            return result;
        }

        public void rec(int colIdx) {
            if (colIdx == ca.length) {
                result.add(build());
                return;
            } else {
                for (int i = 0; i < n; i++) {
                    if (validate(i, colIdx)) {
                        ca[i][colIdx] = 'Q';
                        rec(colIdx + 1);
                        ca[i][colIdx] = '.';
                    }
                }
            }
        }

        private boolean validate(int x, int y) {
            for (int i = 0; i < ca.length; i++) {
                for (int j = 0; j < y; j++) {
                    if (ca[i][j] == 'Q' &&
                            (x + j == y + i || x + y == i + j || x == i)) {
                        return false;
                    }
                }
            }
            return true;
        }

        public List<String> build() {
            List<String> tmp = new ArrayList();
            for (char[] c : ca) {
                tmp.add(String.valueOf(c));
            }
            return tmp;
        }
    }

    class BestMeetingPoint {
        public int minTotalDistance_TLE(int[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            List<int[]> ones = new ArrayList();
            List<int[]> zeros = new ArrayList();
            int r = grid.length;
            int c = grid[0].length;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (grid[i][j] == 1) {
                        ones.add(new int[]{i, j});
                    } else {
                        zeros.add(new int[]{i, j});
                    }
                }
            }
            zeros.addAll(ones);
            Map<Integer, Integer> map = new HashMap();
            for (int[] zero : zeros) {
                for (int[] node : ones) {
                    int x1 = zero[0];
                    int y1 = zero[1];
                    int x2 = node[0];
                    int y2 = node[1];
                    int z = x1 * c + y1;
                    int md = Math.abs(x2 - x1) + Math.abs(y2 - y1);
                    map.put(z, map.getOrDefault(z, 0) + md);
                }
            }
            int min = Integer.MAX_VALUE;
            for (int key : map.keySet()) {
                min = Math.min(min, map.get(key));
            }
            // System.out.println(min);
            return min == Integer.MAX_VALUE ? 1 : min;
        }

        public int minTotalDistance(int[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            List<Integer> rows = new ArrayList<>();
            List<Integer> columns = new ArrayList<>();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        rows.add(i);
                        columns.add(j);
                    }
                }
            }
            int result = 0;
            /**
             * Median Calculation
             */
            Collections.sort(rows);
            Collections.sort(columns);

            //Find mid of rows
            int rowPivot = rows.get(rows.size() / 2);
            //Find mid of col
            int columnPivot = columns.get(columns.size() / 2);

            for (int row : rows) {
                result = result + Math.abs(row - rowPivot);
            }
            for (int column : columns) {
                result = result + Math.abs(column - columnPivot);
            }

            return result;
        }
    }
}

