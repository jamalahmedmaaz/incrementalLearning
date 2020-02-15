package February.feb15;

import java.util.HashMap;
public class Coding {

    /**
     * Couldnt do this problem too many edge cases associated (and it is math).
     * I am not ready for it.
     * <p>
     * Took help, here is the description.
     * <p>
     * The important thing is to consider all edge cases while thinking
     * this problem through, including: negative integer, possible overflow, etc.
     * <p>
     * Use HashMap to store a remainder and its associated index while doing
     * the division so that whenever a same remainder comes up, we know there
     * is a repeating fractional part.
     * <p>
     * Please comment if you see something wrong or can be improved. Cheers!
     */
    public class Solution {
        public String fractionToDecimal(int numerator, int denominator) {
            if (numerator == 0) {
                return "0";
            }
            StringBuilder res = new StringBuilder();
            // "+" or "-"
            res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
            long num = Math.abs((long) numerator);
            long den = Math.abs((long) denominator);

            // integral part
            res.append(num / den);
            num %= den;
            if (num == 0) {
                return res.toString();
            }

            // fractional part
            res.append(".");
            HashMap<Long, Integer> map = new HashMap<>();
            map.put(num, res.length());
            while (num != 0) {
                num *= 10;
                res.append(num / den);
                num %= den;
                if (map.containsKey(num)) {
                    int index = map.get(num);
                    res.insert(index, "(");
                    res.append(")");
                    break;
                } else {
                    map.put(num, res.length());
                }
            }
            return res.toString();
        }
    }
}
