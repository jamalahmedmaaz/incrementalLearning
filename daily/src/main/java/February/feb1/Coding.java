package February.feb1;

import java.util.ArrayList;
import java.util.List;
public class Coding {

    class PartitionLabels {
        public List<Integer> partitionLabels(String S) {
            int[] ca = new int[26];
            for (int i = 0; i < S.length(); i++) {
                int index = S.charAt(i) - 'a';
                ca[index] = i;
            }
            List<Integer> result = new ArrayList();
            int start = 0, end = 0;
            for (int i = 0; i < S.length(); i++) {
                int index = S.charAt(i) - 'a';
                int la = ca[index];
                end = Math.max(end, la);
                if (end == i) {
                    result.add(end - start + 1);
                    start = end + 1;
                }
            }
            return result;
        }
    }
}
