package March.march18;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Coding {
    class SlidingWindowMaximum_239 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0) return new int[0];
            Deque<Integer> dq = new LinkedList();
            List<Integer> list = new ArrayList();
            for (int i = 0; i < nums.length; i++) {
                //Remove Elements out of range (from top)
                while (!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
                    dq.pollFirst();
                }
                //Remove element which are smaller than current element (from bottom)
                while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                    dq.pollLast();
                }
                dq.addLast(i);
                if (i >= k - 1)
                    list.add(nums[dq.peekFirst()]);
            }
            int[] r = new int[list.size()];
            for (int i = 0; i < r.length; i++) {
                r[i] = list.get(i);
            }
            return r;
        }
    }
}
