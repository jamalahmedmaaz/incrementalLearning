package March.march09;

import java.util.ArrayList;
import java.util.List;
public class Coding {

    public static void main(String[] args) {
        System.out.println(12 & 1);
    }

    class GenerateParenthesis_22 {
        List<String> result = new ArrayList();
        int n = 0;

        public List<String> generateParenthesis(int n) {
            this.n = n;
            rec(0, 0, "");
            return result;
        }

        public void rec(int open, int close, String generated) {
            if (generated.length() == n * 2) {
                result.add(generated);
                return;
            }
            if (open < n) {
                rec(open + 1, close, generated + "(");
            }
            if (close < open) {
                rec(open, close + 1, generated + ")");
            }
        }
    }

    class Solution {
        public int[] maxDepthAfterSplit(String seq) {
            List<Integer> result = new ArrayList();
            int idx = 0;
            for (char c : seq.toCharArray()) {
                if (c == '(') {
                    result.add(++idx % 2);
                }
                if (c == ')') {
                    result.add(idx-- % 2);
                }
            }
            // System.out.println(Arrays.toString(result.toArray()));
            int[] r = new int[result.size()];
            for (int i = 0; i < r.length; i++) {
                r[i] = result.get(i);
            }
            return r;
        }
    }
}
