package March.march11;

import java.util.*;
public class Coding {

    public static void main(String[] args) {
        MinimumHeap minimumHeap = new MinimumHeap(10);
        Random random = new Random();
        PriorityQueue<Integer> pq = new PriorityQueue();
        for (int i = 9; i >= 0; i--) {
            int ele = random.nextInt(10);
            minimumHeap.add(i);
            pq.add(i);
            System.out.println(i);

        }
        System.out.println(Arrays.toString(minimumHeap.A));
        System.out.println(Arrays.toString(pq.toArray()));
    }

    static class MinimumHeap {

        int capacity;
        Integer[] A;
        private int currentIndex;

        public MinimumHeap(int size) {
            A = new Integer[size];
            this.capacity = size;
        }

        public Integer getMin() {
            if (currentIndex >= 0) {
                int ele = A[0];
                A[0] = 0;
                currentIndex--;
                pushDown(0);
                return ele;
            }
            return null;
        }

        private void pushDown(int idx) {
            int ele = A[idx];
            int leftIdx = getLeftChild(idx);
            int rightIdx = getRightChild(idx);

        }

        public int getLeftChild(int idx) {
            return 2 * idx;
        }

        public int getRightChild(int idx) {
            return 2 * idx;
        }

        public boolean add(int element) {
            if (currentIndex + 1 <= capacity) {
                A[currentIndex] = element;
                pullUp(currentIndex);
                currentIndex++;
                return true;
            }
            return false;
        }

        private void pullUp(int idx) {
            int parentIdx = getParent(idx);
            if (parentIdx != idx && A[parentIdx] > A[idx]) {
                int parentEle = A[parentIdx];
                A[parentIdx] = A[idx];
                A[idx] = parentEle;
                pullUp(parentIdx);
            }
        }

        public int getParent(int idx) {
            return idx / 2;
        }

    }

    public class Solution {
        Set<String> set = new HashSet<>();
        int[] ca = new int[128];
        String s;

        public List<String> generatePalindromes(String s) {
            this.s = s;
            char[] halfString = new char[s.length() / 2];
            if (!canPermutePalindrome()) {
                return new ArrayList<>();
            }
            char character = 0;
            int k = 0;
            for (int i = 0; i < ca.length; i++) {
                if (ca[i] % 2 == 1) {
                    character = (char) i;
                }
                for (int j = 0; j < ca[i] / 2; j++) {
                    halfString[k++] = (char) i;
                }
            }
            permute(halfString, 0, character);
            return new ArrayList<>(set);
        }

        public boolean canPermutePalindrome() {
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                ca[s.charAt(i)]++;
                if (ca[s.charAt(i)] % 2 == 0) {
                    count--;
                } else {
                    count++;
                }
            }
            return count <= 1;
        }

        public void swap(char[] s, int i, int j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }

        void permute(char[] haflString, int l, char ch) {
            if (l == haflString.length) {
                set.add(new String(haflString) + (ch == 0 ? "" : ch)
                        + new StringBuffer(new String(haflString)).reverse());
            } else {
                for (int i = l; i < haflString.length; i++) {
                    if (haflString[l] != haflString[i] || l == i) {
                        swap(haflString, l, i);
                        permute(haflString, l + 1, ch);
                        swap(haflString, l, i);
                    }
                }
            }
        }
    }

}
