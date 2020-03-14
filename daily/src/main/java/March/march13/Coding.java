package March.march13;

public class Coding {

    static class Solution {
        Boolean dp[][];
        String s, p;

        public boolean isMatch(String s, String p) {
            dp = new Boolean[s.length() + 1][p.length() + 1];
            this.s = s;
            this.p = p;
            return rec(s, p, 0, 0);
        }

        public boolean rec(String k, String l, int si, int pi) {
            System.out.println(si +" "+pi);
            if (pi == p.length()) {
                System.out.println(si + " " + pi + " -- " + s + " -- " + p);
                return si == s.length();
            }
            if (dp[si][pi] != null) return dp[si][pi];
            boolean result = false;
            if (dotOrDefault(si, pi) && rec(s, p, si + 1, pi + 1)) {
                result = true;
            } else if (starCondition(pi) && rec(s, p, si, pi + 2)) {
                result = true;
            } else if (dotOrDefault(si, pi) && starCondition(pi) && rec(s, p, si + 1, pi)) {
                result = true;
            }
            return dp[si][pi] = result;
        }

        public boolean dotOrDefault(int si, int pi) {
            return (pi < p.length() && p.charAt(pi) == '.') || (si < s.length() && pi < p.length() && p.charAt(si) == s.charAt(pi));
        }

        public boolean starCondition(int pi) {
            return pi + 1 < p.length() && p.charAt(pi + 1) == '*';
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.isMatch("aa", "a"));
        System.out.println(solution.isMatch("aa", "a*"));
    }
}
