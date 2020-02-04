package February.feb4;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Coding {
    /**
     * 763. Partition Labels
     * Share
     * A string S of lowercase letters is given.
     * We want to partition this string into as many parts as possible
     * so that each letter appears in at most one part, and return a list
     * of integers representing the size of these parts.
     * <p>
     * Example 1:
     * Input: S = "ababcbacadefegdehijhklij"
     * Output: [9,7,8]
     * Explanation:
     * The partition is "ababcbaca", "defegde", "hijhklij".
     * This is a partition so that each letter appears in at most one part.
     * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
     * Note:
     * <p>
     * S will have length in range [1, 500].
     * S will consist of lowercase letters ('a' to 'z') only.
     */
    class PartitionLabels {
        public List<Integer> partitionLabels(String S) {
            int[] idxs = new int[26];
            for (int i = 0; i < S.length(); i++) {
                int c = S.charAt(i) - 'a';
                // System.out.println(c);
                idxs[c] = i;
            }
            Set<Character> visited = new HashSet();
            List<Integer> result = new ArrayList();
            List<Pair<Integer, Integer>> list = new ArrayList();
            for (int i = 0; i < S.length(); i++) {
                int c = S.charAt(i) - 'a';
                int startIndex = i;
                int endIndex = idxs[c];
                if (visited.contains(S.charAt(i))) {
                    continue;
                }
                if (list.isEmpty()) {
                    list.add(new Pair(startIndex, endIndex));
                } else {
                    if (i > 0) {
                        char pc = S.charAt(i - 1);
                        Pair<Integer, Integer> pair = list.get(list.size() - 1);
                        int pstart = pair.getKey();
                        int pend = pair.getValue();
                        if (pend < i) {
                            Pair<Integer, Integer> newp = new Pair(startIndex, endIndex);
                            list.add(newp);
                        } else if (startIndex < pstart || pend < endIndex) {
                            Pair<Integer, Integer> newp = new Pair(Math.min(pstart, startIndex), endIndex);
                            list.remove(list.size() - 1);
                            list.add(newp);
                        }
                    }
                }
                visited.add(S.charAt(i));
            }

            // System.out.println(Arrays.toString(idxs));
            // System.out.println(Arrays.toString(list.toArray()));
            for (Pair<Integer, Integer> entry : list) {
                result.add(entry.getValue() - entry.getKey() + 1);
            }

            // Collections.sort(result);
            return result;
        }

        /**
         * BETTER APPROACH
         */
        public List<Integer> partitionLabels_BETTER_APPROACH(String S) {
            if (S == null || S.length() == 0) {
                return null;
            }
            List<Integer> result = new ArrayList<>();
            int[] lastSeen = new int[26];

            for (int i = 0; i < S.length(); i++) {
                lastSeen[S.charAt(i) - 'a'] = i;
            }
            // record the end index of the current sub string
            int endIdx = 0;
            int beginIdx = 0;
            for (int i = 0; i < S.length(); i++) {
                endIdx = Math.max(endIdx, lastSeen[S.charAt(i) - 'a']);
                if (endIdx == i) {
                    result.add(endIdx - beginIdx + 1);
                    beginIdx = endIdx + 1;
                }
            }
            return result;
        }
    }

    /**
     * 788. Rotated Digits
     * X is a findIfGoodNumber number if after rotating each digit individually by 180 degrees,
     * we get a valid number that is different from X.  Each digit must be rotated -
     * we cannot choose to leave it alone.
     * <p>
     * A number is valid if each digit remains a digit after rotation. 0, 1, and
     * 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other,
     * and the rest of the numbers do not rotate to any other number and become invalid.
     * <p>
     * Now given a positive number N, how many numbers X from 1 to N are findIfGoodNumber?
     * <p>
     * Example:
     * Input: 10
     * Output: 4
     * Explanation:
     * There are four findIfGoodNumber numbers in the range [1, 10] : 2, 5, 6, 9.
     * Note that 1 and 10 are not findIfGoodNumber numbers, since they remain unchanged after rotating.
     * Note:
     * <p>
     * N  will be in range [1, 10000].
     */

    class RotatedDigits {
        public int rotatedDigits(int N) {
            int count = 0;
            for (int i = 1; i <= N; i++) {
                if (findIfGoodNumber(i, false)) {
                    count++;
                }
            }
            return count;
        }

        public boolean findIfGoodNumber(int N, boolean flag) {
            if (N == 0) {
                return flag;
            }
            int lastDigit = N % 10;
            if (lastDigit == 3 || lastDigit == 4 || lastDigit == 7) {
                return false;
            }
            if (lastDigit == 0 || lastDigit == 1 || lastDigit == 8) {
                return findIfGoodNumber(N / 10, flag);
            }
            return findIfGoodNumber(N / 10, true);
        }

        /**
         * Approach #2: Dynamic Programming On Digits [Accepted]
         * Intuition
         * <p>
         * Say we are writing a good number digit by digit. The necessary and sufficient conditions
         * are: we need to write using only digits from 0125689, write a number less than or equal
         * to N, and write at least one digit from 2569.
         * <p>
         * We can use dynamic programming to solve this efficiently. Our state will be how many
         * digits i we have written, whether we have previously written a jth digit lower than
         * the jth digit of N, and whether we have previously written a digit from 2569. We will
         * represent this state by three variables: i, equality_flag, involution_flag.
         * <p>
         * dp(i, equality_flag, involution_flag) will represent the number of ways to write the
         * suffix of N corresponding to these above conditions. The answer we want is dp(0, True, False).
         * <p>
         * Algorithm
         * <p>
         * If equality_flag is true, the ith digit (0 indexed) will be at most the ith digit of N.
         * For each digit d, we determine if we can write d based on the flags that are currently set.
         * <p>
         * In the below implementations, we showcase both top-down and bottom-up approaches.
         * The four lines in the top-down approach (Python) from for d in
         * xrange(... to before memo[...] = ans clearly illustrates the recursive relationship
         * between states in our dynamic programming.
         */
        public int rotatedDigits_DP(int N) {
            char[] A = String.valueOf(N).toCharArray();
            int K = A.length;

            int[][][] memo = new int[K + 1][2][2];
            memo[K][0][1] = memo[K][1][1] = 1;
            for (int i = K - 1; i >= 0; --i) {
                for (int eqf = 0; eqf <= 1; ++eqf) {
                    for (int invf = 0; invf <= 1; ++invf) {
                        // We will compute ans = memo[i][eqf][invf],
                        // the number of good numbers with respect to N = A[i:].
                        // If eqf is true, we must stay below N, otherwise
                        // we can use any digits.
                        // Invf becomes true when we write a 2569, and it
                        // must be true by the end of our writing as all
                        // good numbers have a digit in 2569.
                        int ans = 0;
                        for (char d = '0'; d <= (eqf == 1 ? A[i] : '9'); ++d) {
                            if (d == '3' || d == '4' || d == '7') {
                                continue;
                            }
                            boolean invo = (d == '2' || d == '5' || d == '6' || d == '9');
                            ans += memo[i + 1][d == A[i] ? eqf : 0][invo ? 1 : invf];
                        }
                        memo[i][eqf][invf] = ans;
                    }
                }
            }

            return memo[0][1][0];
        }

    }

    /**
     * 1184. Distance Between Bus Stops
     * A bus has n stops numbered from 0 to n - 1 that form a circle.
     * We know the distance between all pairs of neighboring stops where
     * distance[i] is the distance between the stops number i and (i + 1) % n.
     * <p>
     * The bus goes along both directions i.e. clockwise and counterclockwise.
     * <p>
     * Return the shortest distance between the given start and destination stops.
     */
    class DistanceBetweenBusStops {
        int[] A;
        int n;
        boolean[] visited;

        public int distanceBetweenBusStops(int[] distance, int start, int destination) {
            if (distance == null || distance.length == 0) {
                return 0;
            }
            n = distance.length;
            A = distance;
            visited = new boolean[distance.length + 1];
            int x = dfs(start, destination);
            visited = new boolean[distance.length + 1];
            int y = dfs(destination, start);
            return Math.min(x, y);
        }

        public int dfs(int start, int destination) {
            if (start >= A.length || visited[start] || start == destination) {
                return 0;
            }
            visited[start] = true;
            return A[start] + dfs((start + 1) % n, destination);
        }
    }

    /**
     * 686. Repeated String Match
     * <p>
     * Given two strings A and B, find the minimum number of times A has to be
     * repeated such that B is a substring of it. If no such solution, return -1.
     * <p>
     * For example, with A = "abcd" and B = "cdabcdab".
     * <p>
     * Return 3, because by repeating A three times (“abcdabcdabcd”), B is a
     * substring of it; and B is not a substring of A repeated two times ("abcdabcd").
     * <p>
     * Note:
     * The length of A and B will be between 1 and 10000.
     */
    class RepeatedStringMatch {
        public int repeatedStringMatch(String A, String B) {
            Set<Character> set = new HashSet<>();
            for (char c : A.toCharArray()) {
                set.add(c);
            }
            for (char c : B.toCharArray()) {
                if (!set.contains(c)) {
                    return -1;
                }
            }
            int count = 0;
            StringBuilder sb = new StringBuilder();
            while (sb.length() < B.length()) {
                sb.append(A);
                count++;
            }
            if (sb.toString().contains(B)) {
                return count;
            }
            if (sb.append(A).toString().contains(B)) {
                return ++count;
            }
            return -1;
        }
    }
}
