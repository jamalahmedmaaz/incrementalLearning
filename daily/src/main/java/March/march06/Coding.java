package March.march06;

public class Coding {

    class singleNonDuplicate_BinarySearchOnEvenIndexes {
        public int singleNonDuplicate(int[] A) {
            int n = A.length;
            if (n % 2 == 0) {
                return -1;
            }
            int low = 0;
            int high = A.length - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (mid % 2 == 1) {
                    mid--;
                }

                if (A[mid] == A[mid + 1]) {
                    low = mid + 2;
                } else {
                    high = mid;
                }
            }
            return A[low];
        }
    }
}


