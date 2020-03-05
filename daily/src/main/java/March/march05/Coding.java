package March.march05;

import java.util.HashMap;
import java.util.Map;
public class Coding {

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
}
