package March.march15;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Coding {

    public static void main(String[] args) {
        Game_Name_24_Problem_Number_679_NotWorking g = new Game_Name_24_Problem_Number_679_NotWorking();
        System.out.println(g.judgePoint24(new int[]{1, 5, 9, 1}));
    }

    static class Game_Name_24_Problem_Number_679_NotWorking {
        int[] A;
        boolean[] used;

        public boolean judgePoint24(int[] nums) {
            this.A = nums;
            used = new boolean[A.length];
            return rec(0, 0, "");
        }

        public boolean rec(int total, int prev, String s) {

            if (total == 24 && used()) {
                return true;
            }
            boolean result = false;

            for (int i = 0; i < A.length; i++) {
                if (used[i]) continue;
                used[i] = true;
                if (rec(total + A[i], A[i], s + "+" + A[i])) {
                    result = true;
                }
                if (rec(total - A[i], -A[i], s + "-" + A[i])) {
                    result = true;
                }
                if (rec(total + ((total - prev) * A[i]), ((total - prev) * A[i]), s + "*" + A[i])) {
                    result = true;
                }
                if (rec(total + ((total - prev) / A[i]), ((total - prev) / A[i]), s + "/" + A[i])) {
                    result = true;
                }
                used[i] = false;
            }
            return result;
        }

        public boolean used() {
            for (boolean b : used) {
                if (!b) return false;
            }
            return true;
        }
    }

    class Solution {
        public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
            Employee[] e = new Employee[wage.length];
            for (int i = 0; i < wage.length; i++) {
                e[i] = new Employee(quality[i], wage[i]);
            }
            int sum = 0;
            Arrays.sort(e);
            double max = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (Employee emp : e) {
                pq.add(-emp.quality);
                sum = sum + emp.quality;
                if (pq.size() > K) {
                    sum = sum + pq.poll();
                }
                if (pq.size() == K) {
                    max = Math.max(max, sum * emp.ratio());
                }
            }

            return max;
        }

        class Employee implements Comparable<Employee> {
            int quality;
            int wage;

            public Employee(int q, int w) {
                quality = q;
                wage = w;
            }

            public double ratio() {
                return wage / quality;
            }

            public int compareTo(Employee other) {
                return Double.compare(ratio(), other.ratio());
            }
        }

    }
}
