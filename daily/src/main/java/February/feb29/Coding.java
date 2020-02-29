package February.feb29;

import java.util.*;
public class Coding {
    static class Solution {
        PriorityQueue<Element> min = new PriorityQueue<>((edge1, edge2) -> {
            if (edge1.ele > edge2.ele) {
                return -1;
            }
            return 0;
        });
        PriorityQueue<Element> max = new PriorityQueue<>((edge1, edge2) -> {
            if (edge1.ele < edge2.ele) {
                return -1;
            }
            return 0;
        });

        public double[] medianSlidingWindow(int[] nums, int k) {
            List<Double> list = new ArrayList();
            for (int i = 0; i < nums.length; i++) {
                max.add(new Element(nums[i], i));
                min.add(max.poll());
                if (max.size() < min.size()) {
                    max.add(min.poll());
                }
                if (i + 1 >= k) {
                    if (max.size() > min.size()) {
                        list.add(max.peek().ele * 1.0);
                    } else {
                        double d = (max.peek().ele * 1.0 + min.peek().ele * 1.0) / 2;
                        list.add(d);
                    }
                    remove(i - k + 1);

                }
            }
            double[] r = new double[list.size()];
            for (int i = 0; i < list.size(); i++) {
                r[i] = list.get(i);
            }
            return r;
        }

        public void remove(int idx) {
            min.remove(new Element(0, idx));
            max.remove(new Element(0, idx));
            List<Element> elements = new ArrayList<>();
            elements.addAll(max);
            elements.addAll(min);
            max.clear();
            min.clear();
            for (Element element : elements) {
                max.add(element);
                min.add(max.poll());
                if (max.size() < min.size()) {
                    max.add(min.poll());
                }
            }
        }

        class Element {
            int ele;
            int idx;

            public Element(int ele, int idx) {
                this.ele = ele;
                this.idx = idx;
            }

            public boolean equals(Object o) {
                return ((Element) o).idx == idx;
            }

            public int hashCode() {
                return idx;
            }

        }
    }

    class RegularExpressionMatching {
        int[][] dp;
        String s;
        String p;

        public boolean isMatch(String s, String p) {
            dp = new int[s.length() + 1][p.length() + 1];
            for (int[] d : dp) {
                Arrays.fill(d, -1);
            }
            this.s = s;
            this.p = p;
            boolean x = rec(0, 0);
            // System.out.println(count + " "+Arrays.deepToString(dp) );
            return x;
        }

        public boolean rec(int si, int pi) {
            if (p.length() == 0) {
                return s.length() == 0;
            }
            if (dp[si][pi] != -1) {
                return dp[si][pi] == 1;
            }
            int result = 0;
            if (dotOrDefault(si, pi) && rec(si + 1, pi + 1)) {
                result = 1;
            } else if (starCondition(pi) && rec(si, pi + 2)) {
                result = 1;
            } else if (starCondition(pi) && dotOrDefault(si, pi) && rec(si + 1, pi)) {
                result = 1;
            }
            dp[si][pi] = result;
            return dp[si][pi] == 1;
        }

        private boolean starCondition(int pi) {
            return p.length() > pi + 1 && p.charAt(pi + 1) == '*';
        }

        private boolean dotOrDefault(int si, int pi) {
            return (p.length() > pi && p.charAt(pi) == '.') || (p.length() > pi && s.length() > si && s.charAt(0) == p.charAt(0));
        }
    }

    class IsValidPalindrome_III {
        Map<String, Boolean> map = new HashMap();
        String s;

        public boolean isValidPalindrome(String s, int k) {
            int n = s.length();
            this.s = s;
            return rec(0, n - 1, k);
        }

        public boolean rec(int i, int j, int k) {
            if (k < 0) {
                return false;
            }
            if (i >= j) {
                return true;
            }
            String key = String.valueOf(i * 1001 * 1001 + j * 1001 + k);
            if (map.containsKey(key)) {
                return map.get(key);
            }
            boolean result;
            if (s.charAt(i) == s.charAt(j)) {
                result = rec(i + 1, j - 1, k);
            } else {
                result = rec(i + 1, j, k - 1) || rec(i, j - 1, k - 1);
            }
            map.put(key, result);
            return result;
        }

    }
}
