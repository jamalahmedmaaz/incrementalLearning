package February.feb23;

import java.util.Arrays;
public class Coding {

    public class NextGreaterElement_III {
        public int nextGreaterElement(int n) {
            char[] ca = (n + "").toCharArray();
            
            int i;
            i = findFirstLowestNumberOnLeft(ca);

            if (i == 0) {
                return -1;
            }

            int smallest = getBiggerNumberOnRight(ca, i);

            swap(ca, i, smallest);

            Arrays.sort(ca, i, ca.length);

            long val = Long.parseLong(new String(ca));
            return (val <= Integer.MAX_VALUE) ? (int) val : -1;
        }

        private void swap(char[] number, int i, int smallest) {
            char temp = number[i - 1];
            number[i - 1] = number[smallest];
            number[smallest] = temp;
        }

        private int getBiggerNumberOnRight(char[] number, int i) {
            int j;
            int x = number[i - 1], smallest = i;
            for (j = i + 1; j < number.length; j++) {
                if (number[j] > x && number[j] <= number[smallest]) {
                    smallest = j;
                }
            }
            return smallest;
        }

        private int findFirstLowestNumberOnLeft(char[] number) {
            int i;
            for (i = number.length - 1; i > 0; i--) {
                if (number[i - 1] < number[i]) {
                    break;
                }
            }
            return i;
        }
    }
}
