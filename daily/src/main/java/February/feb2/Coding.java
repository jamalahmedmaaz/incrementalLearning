package February.feb2;

import java.util.*;
public class Coding {

    /**
     * NOT Working need to fix it.
     */
    class NumberOfPlayList {
        Map<String, Integer> map = new HashMap();
        List<Integer> tmp = new ArrayList();
        int K, N;
        int[] A;

        public int numMusicPlaylists(int N, int L, int K) {
            A = new int[N];
            for (int i = 1; i <= N; i++) {
                A[i - 1] = i;
            }
            System.out.println(Arrays.toString(A));
            this.K = K;
            this.N = N;
            return rec(0, 0, L);
        }

        public int rec(int idx, int coinsUsed, int L) {
            String id = idx + "_" + coinsUsed + "_" + L;
            if (map.containsKey(id)) {
                System.out.println("0-00-");
                // return map.get(id);
            }
            if (L == 0 && coinsUsed >= N) {
                return 1;
            }
            if (L == 0 && (coinsUsed < N)) {
                System.out.println(" wrong ");
                return -1;
            }
            int cal = 0;
            for (int ele : A) {
                if (validateIfEleIsUsedUnderK(ele, idx)) {
                    int r = rec(idx + 1, coinsUsed + 1, L - 1);
                    if (r != -1) {
                        cal = cal + r;
                    }
                    tmp.remove(tmp.size() - 1);
                }
            }
            map.put(id, cal);
            return map.get(id);
        }

        public boolean validateIfEleIsUsedUnderK(int ele, int idx) {
            for (int i = idx - K; i >= 0 && i < tmp.size(); i++) {
                if (tmp.get(i) == ele) {
                    return false;
                }
            }
            tmp.add(ele);
            return true;
        }
    }
}
