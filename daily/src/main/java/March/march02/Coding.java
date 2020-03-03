package March.march02;

public class Coding {
    public class NumberOfDecodingWays_II {
        int M = 1000000007;
        Integer[] dp;
        String s;

        public int numDecodings(String s) {
            dp = new Integer[s.length()];
            this.s = s;
            return rec(s, s.length() - 1);
        }

        public int rec(String s, int i) {
            if (i < 0) {
                return 1;
            }
            if (dp[i] != null) {
                return dp[i];
            }
            if (s.charAt(i) == '*') {
                long result = 9 * rec(s, i - 1);
                if (checkCharacter(i, '1')) {
                    result = (result + 9 * rec(s, i - 2)) % M;
                } else if (checkCharacter(i, '2')) {
                    result = (result + 6 * rec(s, i - 2)) % M;
                } else if (checkCharacter(i, '*')) {
                    result = (result + 15 * rec(s, i - 2)) % M;
                }
                dp[i] = (int) result;
                return dp[i];
            }
            long result = s.charAt(i) != '0' ? rec(s, i - 1) : 0;
            if (checkCharacter(i, '1')) {
                result = (result + rec(s, i - 2)) % M;
            } else if (i > 0 && s.charAt(i - 1) == '2' && s.charAt(i) <= '6') {
                result = (result + rec(s, i - 2)) % M;
            } else if (checkCharacter(i, '*')) {
                result = (result + (s.charAt(i) <= '6' ? 2 : 1) * rec(s, i - 2)) % M;
            }
            dp[i] = (int) result;
            return dp[i];
        }

        private boolean checkCharacter(int i, char c) {
            return i > 0 && s.charAt(i - 1) == c;
        }

        public int numDecodings_BETTER(String s) {
            long first = 1, second = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
            for (int i = 1; i < s.length(); i++) {
                long temp = second;
                if (s.charAt(i) == '*') {
                    second = 9 * second;
                    if (s.charAt(i - 1) == '1') {
                        second = (second + 9 * first) % M;
                    } else if (s.charAt(i - 1) == '2') {
                        second = (second + 6 * first) % M;
                    } else if (s.charAt(i - 1) == '*') {
                        second = (second + 15 * first) % M;
                    }
                } else {
                    second = s.charAt(i) != '0' ? second : 0;
                    if (s.charAt(i - 1) == '1') {
                        second = (second + first) % M;
                    } else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6') {
                        second = (second + first) % M;
                    } else if (s.charAt(i - 1) == '*') {
                        second = (second + (s.charAt(i) <= '6' ? 2 : 1) * first) % M;
                    }
                }
                first = temp;
            }
            return (int) second;
        }
    }
}
