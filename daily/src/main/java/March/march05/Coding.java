package March.march05;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class Coding {

    public static void main(String[] args) {
        StickerstoSpellWord_691 s = new StickerstoSpellWord_691();
        System.out.println(s.minStickers(new String[]{"control", "heart", "interest", "stream", "sentence", "soil",
                        "wonder", "them", "month", "slip", "table", "miss", "boat", "speak", "figure", "no", "perhaps",
                        "twenty", "throw", "rich", "capital", "save", "method", "store", "meant", "life", "oil", "string",
                        "song", "food", "am", "who", "fat", "if", "put", "path", "come", "grow", "box", "great",
                        "word", "object", "stead", "common", "fresh", "the", "operate", "where", "road", "mean"},
                "stoodcrease"));
    }

    static class StickerstoSpellWord_691 {
        int[] ca = new int[128];
        int[] empty = new int[128];
        String[] stickers;
        Map<String, Integer> map = new HashMap();

        public int minStickers(String[] stickers, String target) {
            this.stickers = stickers;
            for (int c : target.toCharArray()) {
                ca[c]++;
            }
            int result = rec(0, ca);
            return result == Integer.MAX_VALUE ? -1 : result;
        }

        public int rec(int idx, int[] tca) {
            if (Arrays.equals(empty, tca)) {
                return 0;
            }
            String key = idx + "#" + Arrays.toString(tca);

            if (map.containsKey(key)) {
                // System.out.println("yahooooooo ");
                return map.get(key);
            }

            int min = Integer.MAX_VALUE;

            for (int i = 0; i < stickers.length; i++) {
                int[] tmp = createArray(stickers[i], tca);
                if (tmp == null) {
                    continue;
                }
                int count = rec(i + 1, tmp);
                if (count == Integer.MAX_VALUE) {
                    continue;
                }
                min = Math.min(min, 1 + count);
            }
            map.put(key, min);
            return min;
        }

        public int[] createArray(String sticker, int[] tca) {
            int[] newTca = new int[128];
            System.arraycopy(tca, 0, newTca, 0, newTca.length);
            for (int c : sticker.toCharArray()) {
                if (newTca[c] > 0) {
                    newTca[c]--;
                }
            }
            return Arrays.equals(newTca, tca) ? null : newTca;
        }

    }

    class WordPatternII {
        Map<String, String> patternToString = new HashMap();
        Map<String, String> stringToPattern = new HashMap();

        public boolean wordPatternMatch(String pattern, String str) {
            if (pattern.equals(str)) {
                return true;
            }
            if (pattern.length() == 0 && str.length() > 0) {
                return false;
            }
            if (pattern.length() == 1 && str.length() > 0) {
                return true;
            }
            return rec(pattern, str);
        }

        public boolean rec(String pattern, String mainString) {
            if (pattern.length() == 1 && patternToString.containsKey(pattern)) {
                return patternToString.get(pattern).equals(mainString);
            } else if (pattern.length() == 1 && !patternToString.containsKey(pattern) && mainString.length() > 0) {
                return !stringToPattern.containsKey(mainString);
            }
            String patternPiece = pattern.substring(0, 1);
            if (patternToString.containsKey(patternPiece)) {
                String t = patternToString.get(patternPiece);
                if (!mainString.startsWith(t)) {
                    return false;
                } else {
                    return rec(pattern.substring(1), mainString.substring(t.length()));
                }
            }
            for (int i = 0; i < mainString.length(); i++) {
                String nextString = mainString.substring(0, i + 1);
                patternToString.put(patternPiece, nextString);
                stringToPattern.put(nextString, patternPiece);
                if (rec(pattern.substring(1), mainString.substring(i + 1))) {
                    return true;
                }
                patternToString.remove(patternPiece);
            }
            return false;
        }

    }

    class SortedListToBinarySearchTree {

        int size = 0;
        ListNode head;

        public TreeNode sortedListToBST(ListNode head) {
            for (ListNode tmp = head; tmp != null; tmp = tmp.next, size++) {
            }
            this.head = head;
            return rec(0, size - 1);
        }

        public TreeNode rec(int low, int high) {
            if (low > high) {
                return null;
            }
            int mid = (low + high) / 2;
            TreeNode left = rec(low, mid - 1);
            TreeNode root = new TreeNode(head.val);
            root.left = left;
            head = head.next;
            root.right = rec(mid + 1, high);
            return root;
        }

        class ListNode {

            public ListNode next;
            public int val;
        }

        class TreeNode {

            private final int val;
            public TreeNode left;
            public TreeNode right;

            public TreeNode(int val) {
                this.val = val;
            }
        }
    }

    /**
     * Given a list of non negative integers, arrange them such that they form
     * the largest number.
     * <p>
     * Example 1:
     * <p>
     * Input: [10,2]
     * Output: "210"
     * Example 2:
     * <p>
     * Input: [3,30,34,5,9]
     * Output: "9534330"
     * Note: The result may be very large, so you need to return a string
     * instead of an integer.
     */
    class LargestNumberFromIntegerArrayToString {
        public String largestNumber(int[] nums) {
            String[] strs = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                strs[i] = String.valueOf(nums[i]);
            }
            Arrays.sort(strs, (s1, s2) -> {
                String cal1 = s1 + s2;
                String cal2 = s2 + s1;
                return cal2.compareTo(cal1);
            });
            if (strs[0].equals("0")) {
                return "0";
            }

            StringBuilder sb = new StringBuilder();
            for (String s : strs) {
                sb.append(s);
            }
            return sb.toString();
        }
    }
}
