package March.march15;

public class Coding {

    class Game_Name_24_Problem_Number_679_NotWorking {
        int[] A;
        boolean[] used;

        public boolean judgePoint24(int[] nums) {
            this.A = nums;
            used = new boolean[A.length];
            return rec(0, 0);
        }

        public boolean rec(int total, int prev) {
            if (total == 24 && used()) {
                return true;
            }
            if (total > 24 * 10) {
                return false;
            }
            boolean result = false;

            for (int i = 0; i < A.length; i++) {
                used[i] = true;
                if (rec(total + A[i], A[i])) {
                    result = true;
                }
                used[i] = false;
                used[i] = true;
                if (rec(total - A[i], -A[i])) {
                    result = true;
                }
                used[i] = false;
                used[i] = true;
                if (rec(total + ((total - prev) * A[i]), ((total - prev) * A[i]))) {
                    result = true;
                }
                used[i] = false;
                if (A[i] != 0) {
                    used[i] = true;
                    if (rec(total + ((total - prev) / A[i]), ((total - prev) / A[i]))) {
                        result = true;
                    }
                    used[i] = false;
                }
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
}
