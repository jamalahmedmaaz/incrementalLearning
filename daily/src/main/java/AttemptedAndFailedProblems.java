import java.util.*;

public class AttemptedAndFailedProblems {

    /**
     * Example 1:
     * <p>
     * Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
     * Output: [null,0,9,4,2,null,5]
     * Explanation:
     * ExamRoom(10) -> null
     * seat() -> 0, no one is in the room, then the student sits at seat number 0.
     * seat() -> 9, the student sits at the last seat number 9.
     * seat() -> 4, the student sits at the last seat number 4.
     * seat() -> 2, the student sits at the last seat number 2.
     * leave(4) -> null
     * seat() -> 5, the student sits at the last seat number 5.
     **/
    class ExamRoom_855 {
        int N;
        TreeSet<Integer> students;

        public ExamRoom_855(int N) {
            this.N = N;
            students = new TreeSet();
        }

        public int seat() {
            //Let's determine student, the position of the next
            //student to sit down.
            int student = 0;
            if (students.size() > 0) {
                //Tenatively, dist is the distance to the closest student,
                //which is achieved by sitting in the position 'student'.
                //We start by considering the left-most seat.
                int dist = students.first();
                Integer prev = null;
                for (Integer s : students) {
                    if (prev != null) {
                        //For each pair of adjacent students in positions (prev, s),
                        //d is the distance to the closest student;
                        //achieved at position prev + d.
                        int d = (s - prev) / 2;
                        if (d > dist) {
                            dist = d;
                            student = prev + d;
                        }
                    }
                    prev = s;
                }
                //Considering the right-most seat.
                if (N - 1 - students.last() > dist)
                    student = N - 1;
            }
            //Add the student to our sorted TreeSet of positions.
            students.add(student);
            return student;
        }

        public void leave(int p) {
            students.remove(p);
        }
    }

    class InsertintoaSortedCircularLinkedList_708 {
        public Node insert(Node head, int insertVal) {
            if (head == null) {
                Node newNode = new Node(insertVal, null);
                newNode.next = newNode;
                return newNode;
            }

            Node prev = head;
            Node curr = head.next;
            boolean toInsert = false;

            do {
                if (prev.val <= insertVal && insertVal <= curr.val) {
                    // Case 1).
                    toInsert = true;
                } else if (prev.val > curr.val) {
                    // Case 2).
                    if (insertVal >= prev.val || insertVal <= curr.val)
                        toInsert = true;
                }

                if (toInsert) {
                    prev.next = new Node(insertVal, curr);
                    return head;
                }

                prev = curr;
                curr = curr.next;
            } while (prev != head);

            // Case 3).
            prev.next = new Node(insertVal, curr);
            return head;
        }

        class Node {

            public Node next;
            public int val;

            public Node(int val, Node next) {
                this.val = val;
                this.next = next;
            }
        }
    }

    /**
     * Given many words, words[i] has weight i.
     * <p>
     * Design a class WordFilter that supports one function,
     * WordFilter.f(String prefix, String suffix). It will return the word with
     * given prefix and suffix with maximum weight. If no word exists, return -1.
     * <p>
     * Examples:
     * <p>
     * Input:
     * WordFilter(["apple"])
     * WordFilter.f("a", "e") // returns 0
     * WordFilter.f("b", "") // returns -1
     */
    class PrefixandSuffixSearch_745 {
        TrieNode trie;

        public PrefixandSuffixSearch_745(String[] words) {
            trie = new TrieNode();
            for (int weight = 0; weight < words.length; ++weight) {
                String word = words[weight] + "{";
                for (int i = 0; i < word.length(); ++i) {
                    TrieNode cur = trie;
                    cur.weight = weight;
                    for (int j = i; j < 2 * word.length() - 1; ++j) {
                        int k = word.charAt(j % word.length()) - 'a';
                        if (cur.children[k] == null)
                            cur.children[k] = new TrieNode();
                        cur = cur.children[k];
                        cur.weight = weight;
                    }
                }
            }
        }

        public int f(String prefix, String suffix) {
            TrieNode cur = trie;
            for (char letter : (suffix + '{' + prefix).toCharArray()) {
                if (cur.children[letter - 'a'] == null) return -1;
                cur = cur.children[letter - 'a'];
            }
            return cur.weight;
        }
    }

    class TrieNode {
        TrieNode[] children;
        int weight;

        public TrieNode() {
            children = new TrieNode[27];
            weight = 0;
        }
    }

    class StickerstoSpellWord_691 {
        Map<String, Integer> DP;
        int[][] mp;

        public int minStickers(String[] stickers, String target) {
            int m = stickers.length;
            mp = new int[m][26];
            DP = new HashMap<>();
            for (int i = 0; i < m; i++)
                for (char c : stickers[i].toCharArray()) mp[i][c - 'a']++;
            DP.put("", 0);
            return rec(target);
        }

        private int rec(String target) {
            if (DP.containsKey(target)) return DP.get(target);
            int ans = Integer.MAX_VALUE, n = mp.length;
            int[] tar = new int[26];
            for (char c : target.toCharArray()) tar[c - 'a']++;
            // try every sticker
            for (int i = 0; i < n; i++) {
                // optimization
                if (mp[i][target.charAt(0) - 'a'] == 0) continue;
                StringBuilder sb = new StringBuilder();
                // apply a sticker on every character a-z
                for (int j = 0; j < 26; j++) {
                    if (tar[j] > 0)
                        for (int k = 0; k < Math.max(0, tar[j] - mp[i][j]); k++)
                            sb.append((char) ('a' + j));
                }
                String s = sb.toString();
                int tmp = rec(s);
                if (tmp != -1) ans = Math.min(ans, 1 + tmp);
            }
            DP.put(target, ans == Integer.MAX_VALUE ? -1 : ans);
            return DP.get(target);
        }
    }

    /**
     * Input: [1,2,1,2,6,7,5,1], 2
     * Output: [0, 3, 5]
     * Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
     * We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
     */
    class MaximumSumof3NonOverlappingSubarrays_689 {
        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            int n = nums.length, maxsum = 0;
            int[] sum = new int[n + 1], posLeft = new int[n], posRight = new int[n], ans = new int[3];
            for (int i = 0; i < n; i++) sum[i + 1] = sum[i] + nums[i];
            // DP for starting index of the left max sum interval
            for (int i = k, tot = sum[k] - sum[0]; i < n; i++) {
                int cal = sum[i + 1] - sum[i + 1 - k];
                if (cal > tot) {
                    posLeft[i] = i + 1 - k;
                    tot = cal;
                } else
                    posLeft[i] = posLeft[i - 1];
            }
            // DP for starting index of the right max sum interval
            // caution: the condition is ">= tot" for right interval, and "> tot" for left interval
            posRight[n - k] = n - k;
            for (int i = n - k - 1, tot = sum[n] - sum[n - k]; i >= 0; i--) {
                int cal = sum[i + k] - sum[i];
                if (cal >= tot) {
                    posRight[i] = i;
                    tot = cal;
                } else
                    posRight[i] = posRight[i + 1];
            }
            // test all possible middle interval
            for (int i = k; i <= n - 2 * k; i++) {
                int l = posLeft[i - 1], r = posRight[i + k];
                int tot = (sum[i + k] - sum[i]) + (sum[l + k] - sum[l]) + (sum[r + k] - sum[r]);
                if (tot > maxsum) {
                    maxsum = tot;
                    ans[0] = l;
                    ans[1] = i;
                    ans[2] = r;
                }
            }
            return ans;
        }
    }

    /**
     * Input: 3, 2, 0, 0
     * Output: 0.0625
     * Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
     * From each of those positions, there are also two moves that will keep the knight on the board.
     * The total probability the knight stays on the board is 0.0625.
     */
    class KnightProbabilityInChessboard_688 {
        private int[][] dir = new int[][]{{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}};
        private double[][][] dp;

        public double knightProbability(int N, int K, int r, int c) {
            dp = new double[N][N][K + 1];
            return rec(N, K, r, c);
        }

        public double rec(int N, int K, int r, int c) {
            if (r < 0 || r > N - 1 || c < 0 || c > N - 1) return 0;
            if (K == 0) return 1;
            if (dp[r][c][K] != 0) return dp[r][c][K];
            double rate = 0;
            for (int i = 0; i < dir.length; i++) {
                // 0.125  = 1/8, 1 divided by 8
                rate = rate + 0.125 * rec(N, K - 1, r + dir[i][0], c + dir[i][1]);
            }
            dp[r][c][K] = rate;
            return rate;
        }
    }

    class Game_Name_24_679 {
        public boolean judgePoint24(int[] nums) {
            List<Double> list = new ArrayList<>();
            for (int i : nums) {
                list.add((double) i);
            }
            return dfs(list);
        }

        private boolean dfs(List<Double> list) {
            if (list.size() == 1) {
                return Math.abs(list.get(0) - 24.0) < 0.001;
            }

            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    for (double c : generatePossibleResults(list.get(i), list.get(j))) {
                        List<Double> nextRound = new ArrayList<>();
                        nextRound.add(c);
                        for (int k = 0; k < list.size(); k++) {
                            if (k == j || k == i) continue;
                            nextRound.add(list.get(k));
                        }
                        if (dfs(nextRound)) return true;
                    }
                }
            }
            return false;

        }

        private List<Double> generatePossibleResults(double a, double b) {
            List<Double> res = new ArrayList<>();
            res.add(a + b);
            res.add(a - b);
            res.add(b - a);
            res.add(a * b);
            res.add(a / b);
            res.add(b / a);
            return res;
        }
    }

    public class DecodeWaysII {

        int M = 1000000007;

        public int numDecodings(String s) {
            Integer[] dp = new Integer[s.length()];
            return ways(s, s.length() - 1, dp);
        }

        public int ways(String s, int i, Integer[] dp) {
            if (i < 0)
                return 1;
            if (dp[i] != null)
                return dp[i];
            if (s.charAt(i) == '*') {
                long result = 9 * ways(s, i - 1, dp);
                if (i > 0 && s.charAt(i - 1) == '1')
                    result = (result + 9 * ways(s, i - 2, dp)) % M;
                else if (i > 0 && s.charAt(i - 1) == '2')
                    result = (result + 6 * ways(s, i - 2, dp)) % M;
                else if (i > 0 && s.charAt(i - 1) == '*')
                    result = (result + 15 * ways(s, i - 2, dp)) % M;
                dp[i] = (int) result;
                return dp[i];
            }
            long result = s.charAt(i) != '0' ? ways(s, i - 1, dp) : 0;
            if (i > 0 && s.charAt(i - 1) == '1')
                result = (result + ways(s, i - 2, dp)) % M;
            else if (i > 0 && s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
                result = (result + ways(s, i - 2, dp)) % M;
            else if (i > 0 && s.charAt(i - 1) == '*')
                result = (result + (s.charAt(i) <= '6' ? 2 : 1) * ways(s, i - 2, dp)) % M;
            dp[i] = (int) result;
            return dp[i];
        }

        class Better_Solution {
            public int numDecodings(String s) {
                /* initial conditions */
                long[] dp = new long[s.length() + 1];
                dp[0] = 1;
                if (s.charAt(0) == '0') {
                    return 0;
                }
                dp[1] = (s.charAt(0) == '*') ? 9 : 1;

                /* bottom up method */
                for (int i = 2; i <= s.length(); i++) {
                    char first = s.charAt(i - 2);
                    char second = s.charAt(i - 1);

                    // For dp[i-1]
                    if (second == '*') {
                        dp[i] += 9 * dp[i - 1];
                    } else if (second > '0') {
                        dp[i] += dp[i - 1];
                    }

                    // For dp[i-2]
                    if (first == '*') {
                        if (second == '*') {
                            dp[i] += 15 * dp[i - 2];
                        } else if (second <= '6') {
                            dp[i] += 2 * dp[i - 2];
                        } else {
                            dp[i] += dp[i - 2];
                        }
                    } else if (first == '1' || first == '2') {
                        if (second == '*') {
                            if (first == '1') {
                                dp[i] += 9 * dp[i - 2];
                            } else { // first == '2'
                                dp[i] += 6 * dp[i - 2];
                            }
                        } else if (((first - '0') * 10 + (second - '0')) <= 26) {
                            dp[i] += dp[i - 2];
                        }
                    }

                    dp[i] %= 1000000007;
                }
                /* Return */
                return (int) dp[s.length()];
            }
        }
    }


    public class SlidingWindowMedian_480 {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(
                new Comparator<Integer>() {
                    public int compare(Integer i1, Integer i2) {
                        return i2.compareTo(i1);
                    }
                }
        );

        public double[] medianSlidingWindow(int[] nums, int k) {
            int n = nums.length - k + 1;
            if (n <= 0) return new double[0];
            double[] result = new double[n];

            for (int i = 0; i <= nums.length; i++) {
                if (i >= k) {
                    result[i - k] = getMedian();
                    remove(nums[i - k]);
                }
                if (i < nums.length) {
                    add(nums[i]);
                }
            }

            return result;
        }

        private void add(int num) {
            if (num < getMedian()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.poll());
            }
            if (minHeap.size() - maxHeap.size() > 1) {
                maxHeap.add(minHeap.poll());
            }
        }

        private void remove(int num) {
            if (num < getMedian()) {
                maxHeap.remove(num);
            } else {
                minHeap.remove(num);
            }
            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.poll());
            }
            if (minHeap.size() - maxHeap.size() > 1) {
                maxHeap.add(minHeap.poll());
            }
        }

        private double getMedian() {
            if (maxHeap.isEmpty() && minHeap.isEmpty()) return 0;

            if (maxHeap.size() == minHeap.size()) {
                return ((double) maxHeap.peek() + (double) minHeap.peek()) / 2.0;
            } else {
                return (double) minHeap.peek();
            }
        }
    }

}
