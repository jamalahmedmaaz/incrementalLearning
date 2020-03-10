package March.march10;

import java.util.HashMap;
import java.util.Map;
public class Coding {
    class ShortestPalindrome_214 {
        String s;

        public String shortestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }
            this.s = s;
            int idx = findPalindromePoint();
            if (idx != -1) {
                String tmp = s.substring(idx);
                return new StringBuilder(tmp).reverse().toString() + s;
            } else {
                return new StringBuilder(s).reverse().toString() + s;
            }
        }

        public int findPalindromePoint() {
            int mid = (s.length() - 1) / 2;
            while (mid >= 0) {
                int a = findValidPalindromeEndWhichIncludesTheBegining(mid, mid);
                int b = findValidPalindromeEndWhichIncludesTheBegining(mid, mid + 1);
                if (a == -1 && b == -1) {
                    mid--;
                } else {
                    return Math.max(a, b);
                }
            }
            return -1;
        }

        public int findValidPalindromeEndWhichIncludesTheBegining(int left, int right) {
            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) != s.charAt(right)) {
                    return -1;
                }
                left--;
                right++;
            }
            return left == -1 ? right : -1;
        }
    }

    class WordFilter {
        Trie trie = new Trie();

        public WordFilter(String[] words) {
            for (int i = 0; i < words.length; i++) {
                String w = words[i];
                for (int j = 0; j < w.length(); j++) {
                    String suffix = w.substring(j);
                    trie.addWord(suffix + "#" + w, i);
                }
                trie.addWord("EMPTY" + "#" + w, i);
            }
            trie.addWord("EMPTY" + "#" + "EMPTY", 0);
        }

        public int f(String prefix, String suffix) {
            if (suffix.length() == 0 && prefix.length() == 0) {
                return 0;
            }
            String x = (suffix.length() == 0 ? "EMPTY" : suffix) + "#" + (prefix.length() == 0 ? "EMPTY" : prefix);
            TrieNode trieNode = trie.search(x);
            return trieNode != null ? trieNode.index : -1;
        }
    }

    class Trie {
        TrieNode root = new TrieNode();

        public void addWord(String word, int idx) {
            TrieNode tmp = root;
            for (char c : word.toCharArray()) {
                TrieNode next = tmp.childrens.get(c);
                if (next == null) {
                    tmp.childrens.put(c, new TrieNode());
                    next = tmp.childrens.get(c);
                }
                tmp = next;
                tmp.index = idx;
            }
            tmp.isEndWord = true;
            tmp.index = idx;
        }

        public TrieNode search(String word) {
            return search(root, 0, word);
        }

        public TrieNode search(TrieNode root, int idx, String word) {
            if (idx == word.length()) {
                return root;
            }
            char c = word.charAt(idx);
            TrieNode node = root.childrens.get(c);
            if (node == null) {
                return null;
            }
            return search(node, idx + 1, word);
        }

    }

    class TrieNode {
        Map<Character, TrieNode> childrens = new HashMap();
        boolean isEndWord;
        int index;

    }

}
