package February.feb9;

import java.util.*;
public class Coding {

    class MinRemoveToMakeValid {
        public String minRemoveToMakeValid(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }
            Stack<Integer> stack = new Stack();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                switch (c) {
                    case '(':
                        stack.push(i);
                        break;
                    case ')':
                        if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                            stack.pop();
                        } else {
                            stack.push(i);
                        }
                        break;
                }
            }
            // if you use delete logic Takes O(N) times and total time will O(N^2)
            // sb.delete(stack.peek(),stack.pop()+1);
            Set<Integer> set = new HashSet();
            while (!stack.isEmpty()) {
                set.add(stack.pop());
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (!set.contains(i)) {
                    sb.append(s.charAt(i));
                }
            }
            return sb.toString();
        }
    }

    class KClosestPointstoOrigin {

        class EuclideanDistanceSolution {
            public int[][] kClosest(int[][] points, int K) {
                if (points == null || points.length == 0) {
                    return null;
                }
                PriorityQueue<DataEle> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> -a.ed));
                int x0 = 0;
                int y0 = 0;
                for (int idx = 0; idx < points.length; idx++) {
                    int[] p = points[idx];
                    int x1 = p[0];
                    int y1 = p[1];
                    int x = (x0 - x1) * (x0 - x1);
                    int y = (y0 - y1) * (y0 - y1);
                    double euclideanDistance = Math.sqrt(x + y);
                    pq.add(new DataEle(euclideanDistance, idx));
                    if (pq.size() > K) {
                        pq.poll();
                    }
                }
                int[][] result = new int[pq.size()][2];
                int i = 0;
                while (!pq.isEmpty()) {
                    DataEle de = pq.poll();
                    int idx = de.idx;
                    result[i++] = points[idx];
                }
                return result;
            }

            class DataEle {
                //Euclidean Distance
                double ed;
                int idx;

                public DataEle(double ed, int idx) {
                    this.ed = ed;
                    this.idx = idx;
                }
            }

        }

        class BetterSolution {
            public int[][] kClosest(int[][] points, int K) {
                int len = points.length, l = 0, r = len - 1;
                while (l <= r) {
                    int mid = helper(points, l, r);
                    if (mid == K) {
                        break;
                    }
                    if (mid < K) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                return Arrays.copyOfRange(points, 0, K);
            }

            private int helper(int[][] A, int l, int r) {
                int[] pivot = A[l];
                while (l < r) {
                    while (l < r && compare(A[r], pivot) >= 0) {
                        r--;
                    }
                    A[l] = A[r];
                    while (l < r && compare(A[l], pivot) <= 0) {
                        l++;
                    }
                    A[r] = A[l];
                }
                A[l] = pivot;
                return l;
            }

            private int compare(int[] p1, int[] p2) {
                return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
            }
        }
    }

    class IntervalIntersection {

        public int[][] intervalIntersection(int[][] A, int[][] B) {
            if (A == null || B == null) {
                return new int[0][0];
            }
            if (A.length == 0 || B.length == 0) {
                return new int[0][0];
            }
            PriorityQueue<DataEle> apq = new PriorityQueue<>((a, b) -> a.start - b.start);
            PriorityQueue<DataEle> bpq = new PriorityQueue<>((a, b) -> a.start - b.start);

            for (int[] ele : A) {
                apq.add(new DataEle(ele[0], ele[1]));
            }
            for (int[] ele : B) {
                bpq.add(new DataEle(ele[0], ele[1]));
            }
            List<DataEle> result = new ArrayList();
            while (!apq.isEmpty() && !bpq.isEmpty()) {
                DataEle a = apq.peek();
                DataEle b = bpq.peek();

                int start = Math.max(a.start, b.start);
                int end = Math.min(a.end, b.end);

                if (start <= end) {
                    result.add(new DataEle(start, end));
                }

                if (a.end < b.end) {
                    apq.poll();
                } else {
                    bpq.poll();
                }
            }

            int[][] r = new int[result.size()][2];
            int count = 0;
            for (DataEle de : result) {
                r[count][0] = de.start;
                r[count][1] = de.end;
                count++;
            }
            return r;
        }

        class DataEle {
            int start;
            int end;

            public DataEle(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        class BetterSolution {
            public int[][] intervalIntersection(int[][] A, int[][] B) {
                List<int[]> result = new ArrayList();
                int i = 0, j = 0;

                while (i < A.length && j < B.length) {
                    // Let's check if A[i] intersects B[j].
                    // lo - the startpoint of the intersection
                    // hi - the endpoint of the intersection
                    int lo = Math.max(A[i][0], B[j][0]);
                    int hi = Math.min(A[i][0], B[j][0]);
                    if (lo <= hi) {
                        result.add(new int[]{lo, hi});
                    }

                    // Remove the interval with the smallest endpoint
                    if (A[i][1] < B[j][1]) {
                        //This is like removing elements from PriorityQueue
                        i++;
                    } else {
                        j++;
                    }
                }
                /**
                 * LEARNING:
                 *
                 * Didn't knew it can done this way. NICE.
                 */
                return result.toArray(new int[0][]);
            }
        }
    }

    class MergeKSortedLists {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
            for (ListNode n : lists) {
                if (n != null) {
                    pq.add(n);
                }
            }
            ListNode root = new ListNode(0);
            ListNode tmp = root;
            while (!pq.isEmpty()) {
                tmp.next = pq.poll();
                tmp = tmp.next;
                if (tmp.next != null) {
                    pq.add(tmp.next);
                }
            }
            return root.next;
        }

        private class ListNode {
            public ListNode next;
            public int val;

            public ListNode(int val) {
                this.val = val;
            }
        }
    }

    class IntegerToEnglishWords {

        public final int THOUSAND = 1000;
        public final int HUNDRED = 100;
        public final int BILLION = 1000000000;
        public final int MILLION = 1000000;
        String[] MAP = new String[]{
                "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
                "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
                "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty", "Thirty",
                "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
        };

        public String numberToWords(int num) {
            if (num == 0) {
                return "Zero";
            }
            return REC(num);
        }

        String REC(int num) {
            StringBuffer sb = new StringBuffer();
            if (num >= BILLION) {
                sb.append(REC(num / BILLION))
                        .append(" Billion ")
                        .append(REC(num % BILLION));
            } else if (num >= MILLION) {
                sb.append(REC(num / MILLION))
                        .append(" Million ")
                        .append(REC(num % MILLION));
            } else if (num >= THOUSAND) {
                sb.append(REC(num / THOUSAND))
                        .append(" Thousand ")
                        .append(REC(num % THOUSAND));
            } else if (num >= HUNDRED) {
                sb.append(REC(num / HUNDRED))
                        .append(" Hundred ")
                        .append(REC(num % HUNDRED));
            } else if (num >= 20) {
                sb.append(MAP[(num - 20) / 10 + 20])
                        .append(" ")
                        .append(REC(num % 10));
            } else {
                sb.append(MAP[num]);
            }
            return sb.toString().trim();
        }
    }

    class MinWindowSubString {
        public String minWindow(String s, String t) {
            int[] ca = new int[128];
            for (int c : t.toCharArray()) {
                ca[c]++;
            }
            int start = 0;
            int end = 0;
            int head = 0;
            int counter = t.length();
            int idx = Integer.MAX_VALUE;
            while (end < s.length()) {
                int tmp = s.charAt(end++);
                if (ca[tmp]-- > 0) {
                    counter--;
                }
                while (counter == 0) {
                    if (end - start < idx) {
                        idx = end - start;
                        head = start;
                    }
                    int next = s.charAt(start++);
                    if (ca[next]++ == 0) {
                        counter++;
                    }
                }
            }
            // System.out.println(head + " "+idx);
            return idx == Integer.MAX_VALUE ? "" : s.substring(head, head + idx);
        }
    }

    class RangeSumQuery2D_Immutable {
        int[][] prefixsum;

        public RangeSumQuery2D_Immutable(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return;
            }
            int r = matrix.length;
            int c = matrix[0].length;
            prefixsum = new int[r + 1][c + 1];

            for (int i = 1; i < prefixsum.length; i++) {
                for (int j = 1; j < prefixsum[i].length; j++) {
                    prefixsum[i][j] = prefixsum[i - 1][j] //Top
                            + prefixsum[i][j - 1] //left
                            - prefixsum[i - 1][j - 1] // sub diagonal its already added.
                            + matrix[i - 1][j - 1]; //Current value;
                }
            }
            // System.out.println(Arrays.deepToString(prefixsum));
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (prefixsum == null || prefixsum.length == 0) {
                return 0;
            }
            //Increment everything because you have used a +1 more size prefixsum
            row1++;
            col1++;
            row2++;
            col2++;
            return prefixsum[row2][col2] // Take the end of rectangle or square.
                    - prefixsum[row1 - 1][col2] // remove the top area
                    - prefixsum[row2][col1 - 1] // remove the left area
                    + prefixsum[row1 - 1][col1 - 1]; // add back diagonal
            // because you delete top and left, so it got deleted two times
        }
    }

    /**
     * This is a very good problem
     * <p>
     * "1234"
     * <p>
     * if you want to create 1 + 2 + 3 * 4 =>
     * <p>
     * <p>
     * You actually need to do => (1 + 2 + 3) - 3 + (3 * 4)
     */
    class Solution {
        List<String> result = new ArrayList();
        String num;
        int target;

        public List<String> addOperators(String num, int target) {
            this.num = num;
            this.target = target;
            rec("", 0, 0, 0);
            return result;
        }

        public void rec(String exp, int idx, long tmpTarget, long nextMult) {
            if (idx == num.length()) {
                if (tmpTarget == target) {
                    result.add(exp);
                }
            } else {
                for (int i = idx; i < num.length(); i++) {
                    if (i != idx && num.charAt(idx) == '0') {
                        break;
                    }
                    long curNo = Long.parseLong(num.substring(idx, i + 1));
                    if (idx == 0) {
                        rec(exp + curNo, i + 1, curNo, curNo);
                    } else {
                        rec(exp + "+" + curNo, i + 1, tmpTarget + curNo, curNo);
                        rec(exp + "-" + curNo, i + 1, tmpTarget - curNo, -curNo);
                        rec(exp + "*" + curNo, i + 1, tmpTarget - nextMult + (curNo * nextMult), curNo * nextMult);
                    }
                }
            }
        }
    }
}
