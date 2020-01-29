package January.jan29;

import java.util.*;
public class Coding {
    public static void main(String[] args) {
        TreesforGolfEvent solution = new TreesforGolfEvent();
        List<List<Integer>> a = new ArrayList<>();
        a.add(Arrays.asList(1, 2, 3));
        a.add(Arrays.asList(5, 0, 6));
        a.add(Arrays.asList(9, 8, 7));
        System.out.println(solution.cutOffTree(a));
    }

    static class TreesforGolfEvent {
        List<List<Integer>> forest;
        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int r, c;

        public int cutOffTree(List<List<Integer>> forest) {
            this.forest = forest;
            r = forest.size();
            c = forest.get(0).size();
            TreeSet<Integer> ts = new TreeSet<>();
            for (List<Integer> l : forest) {
                ts.addAll(l);
            }
            int count = 0;
            Queue<int[]> q = new LinkedList();
            q.add(new int[]{0, 0, forest.get(0).get(0)});
            ts.remove(forest.get(0).get(0));
            ts.remove(0);
            // 0 = x, 1 = y, 2 = the value.
            while (!q.isEmpty()) {
                int[] current = q.poll();
                int x = current[0];
                int y = current[1];
                int value = current[2];
                boolean f = false;
                for (int[] d : directions) {
                    int nx = d[0] + x;
                    int ny = d[1] + y;
                    if (nx >= 0 && ny >= 0 && nx < r && ny < c && forest.get(nx).get(ny) == ts.higher(value)) {
                        q.add(new int[]{nx, ny, ts.higher(value)});
                        count++;
                        ts.remove(ts.higher(value));
                        f = true;
                        break;
                    }
                }
                if (!f) {
                    int[] t = countStepsFrom(current, ts.higher(current[2]));
                    if (t == null) {
                        if (ts.isEmpty()) {
                            return count;
                        }
                    } else {
                        q.add(t);
                        ts.remove(ts.higher(value));
                        count = count + t[2];
                    }
                }
            }
            return -1;
        }

        // 0 = x, 1 = y  and number of steps.
        private int[] countStepsFrom(int[] current, Integer higher) {
            if (higher == null) {
                return null;
            }

            boolean[][] visited = new boolean[r][c];
            Queue<int[]> q = new LinkedList();
            q.add(new int[]{current[0], current[1], 0});
            visited[current[0]][current[1]] = true;
            while (!q.isEmpty()) {
                if ((r * c) * 2 < q.size()) {
                    return null;
                }
                int[] cell = q.poll();
                int x = cell[0];
                int y = cell[1];
                int count = cell[2];
                if (higher == forest.get(x).get(y)) {
                    return new int[]{x, y, count};
                }

                for (int[] dir : directions) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx >= 0 && ny >= 0 && nx < r && ny < c && !visited[nx][ny] && forest.get(nx).get(ny) != 0) {
                        q.add(new int[]{nx, ny, count + 1});
                        visited[nx][ny] = true;
                    }
                }

            }
            return null;
        }
    }
}