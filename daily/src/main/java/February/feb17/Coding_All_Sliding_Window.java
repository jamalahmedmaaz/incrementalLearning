package February.feb17;

import java.util.Deque;
import java.util.LinkedList;
public class Coding_All_Sliding_Window {

    class ShortestSubarrayWithSumAtLeastK {
        public int shortestSubarray(int[] A, int K) {
            long[] ps = new long[A.length + 1];
            int min = A.length + 1;
            for (int i = 0; i < A.length; i++) {
                ps[i + 1] = ps[i] + A[i];
            }
            Deque<Integer> dq = new LinkedList();
            for (int i = 0; i < A.length + 1; i++) {
                while (!dq.isEmpty() && ps[i] - ps[dq.peekFirst()] >= K) {
                    min = Math.min(min, i - dq.pollFirst());
                }
                while (!dq.isEmpty() && ps[i] <= ps[dq.peekLast()]) {
                    dq.pollLast();
                }
                dq.addLast(i);
            }
            return min <= A.length ? min : -1;
        }

        public int shortestSubarray_BEST_NO_DEQUE(int[] A, int K) {
            if (A == null || A.length == 0) {
                return -1;
            }
            int ptr = 0, sum = 0, len = Integer.MAX_VALUE;
            int[] rightSum = new int[A.length];
            for (int i = A.length - 1; i >= 0; i--) {
                if (i == A.length - 1) {
                    rightSum[i] = A[i];
                } else {
                    rightSum[i] = Math.min(rightSum[i + 1] + A[i], A[i]);
                }
            }
            for (int i = 0; i < A.length; i++) {
                if (sum + A[i] < 0) {
                    sum = A[i];
                    ptr = i;
                } else {
                    sum += A[i];
                }
                if (sum >= K) {
                    len = Math.min(len, i - ptr + 1);
                }
                while (ptr <= i && sum - rightSum[ptr] >= K) {
                    sum -= A[ptr];
                    ptr++;
                }
                if (sum >= K) {
                    len = Math.min(len, i - ptr + 1);
                }
            }
            if (len == Integer.MAX_VALUE) {
                return -1;
            }
            return len;
        }
    }

    class CountNumberOfNiceSubarrays {

    }

    class ReplacetheSubstringforBalancedString {

    }

    class MaxConsecutiveOnesIII {

    }

    class BinarySubarraysWithSum {

    }

    class SubarrayswithKDifferentIntegers {

    }

    class FruitIntoBaskets {

    }

    boolean isValid(int[][] queen, int row, int col, int n) {
        //check if the column had a queen before.
        for (int i = 0; i != row; ++i) {
            if (queen[i][col] == 'Q') {
                return false;
            }
        }
        //check if the 45° diagonal had a queen before.
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; --i, --j) {
            if (queen[i][j] == 'Q') {
                return false;
            }
        }
        //check if the 135° diagonal had a queen before.
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; --i, ++j) {
            if (queen[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    class MinimumSizeSubarraySum {

    }
}
