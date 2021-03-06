package February.feb5;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Coding {
    Set<String> set = new HashSet<>();
    String s;
    boolean result;
    String sresult = "";

    public static void main(String[] args) {
        Coding coding = new Coding();
//        String input = "bbwfowdeauwderbddpwzrfowybhpvfoyvfdrsgjiytfxxawkctyfvrywxqwwoculuoiqzmsbaonhtswpmachjaademrwznqbkrravioseyibmqeuuayrnxzyptpuwlblkpvhgkufnjhprgsecqzpgfdjdgagjgiifjiftyiimgegotdylcxhdakzwgicnbzefvmdbhbbgbvxbdueewyzrpvxfcbigaprdudvbxreavzgwpcxldwcfnqrbbfvcmeiyafbhtixegibfnugfytiqczwqclfsksameergvxljtxeranlnozzhpdexkfwysuzeavvzqoxogxsixiczwrwrefqnefkumlzdzknqwizvqzyginiakjxllvpttdrhorinzhkxirfkryymvqezvdifjbndxdlflzsbigypltvuyocbudqidyxfknoslylcwwvidlrfjntfkgmzpvkkzscspslrnypbgziknzawqpfvmarzjwdwbezcudhmedfcmdwutehzeayufgmkbnuxaozypkakonotapbzeambrileusrfzhijejuggvtakwsnxuzubdojfgkzwrvsetjvmwqobtagebxgicsgrtgzmrzjnzitxknocptmayabfwrupscpwmclknwqlhkyejhyfxuiunasfbiuttrfotckztxozawqgqwswvwfdnozbmocmdmlyupaoayxnzwrvapputncymzpefiozqimezggqvwlhtpdaseputojdrjxfueemvzdjhhwhfvsauvhpkhldwvwuvonpginysnltfgqawamilcpxdreyjwnmlxcbdurpeasxnabftirkappyrbwsuccrkrzsvlwrwyivctvdmrmdrrxipbqusmicdbqasklcadkianuctcxkewctdrdllodyrpskipsybwrldbsvpjuxmgdbxwhuweizihgiulzrsjsdesdodhmqzwtayfpdtbhnjyjvsilfspghnwytnhoqpcaaawsvxvuotfjkqismsjvevloccfzyubzbucdorgasyhnmemaetpgjruhrbvzdqdjycgybrfxlviqjosqamighivronqyguaunuoxyxnlvysuitxeibyhndoarjbcxxvovleuygweqbsmqtsgvvnwcyooikmeqjjeypfcomywiuyxuwcvlpnypqmaqeuckjgkmhofvbjqubrybeovxtyvgxoodyfjkiicqxfrwhqhnrgfuxtcxyswwluiwpmfdoqsuijjauophmzyyydleuaipsnfpswjfgmaqdigiuzyxtbsgxabbrxlcprzamzwzljbyqnnfhfitnmmruidqcuudwtqstloatznninzmezliprpkzxgoahevghjpwbodqmgcywwanykmijimsdbohmhrgxvkuevuqrlxhgzasmcycwzijwxklmiyfcvyycmfrilqowhsqpqcyexjuhpmcveyipnljcbroiuzizwdclcsbqxzeg";
        String input = "abbaabba";
        BigInteger bi = coding.hash(input);

        coding.rec(0, coding.s.length() - 1, bi);
        System.out.println(coding.sresult);
        System.out.println(bi.toString());
        BigInteger deleted = coding.removeValueFromRollingHash(bi, coding.s.length() - 2);
        System.out.println(deleted.toString());
    }

    public BigInteger hash(String s) {
        this.s = s;
        BigInteger bigInteger = new BigInteger(String.valueOf(0));
        for (int i = 0; i < s.length(); i++) {
            long l = s.charAt(i);
            bigInteger = bigInteger.add(BigInteger.valueOf((long) (l * Math.pow(7, i))));
        }
        return bigInteger;
    }

    public BigInteger removeValueFromRollingHash(BigInteger rollHash, int idx) {
        char currentCharacter = s.charAt(idx);
        return rollHash.subtract(BigInteger.valueOf((long) (currentCharacter * Math.pow(7, idx))));
    }

    public void rec(int start, int end, BigInteger hash) {
        if (start + 1 == end || Math.abs(start - end) == 1 || start == s.length() || end < 0) {
            return;
        }

        if (set.contains(hash.toString())) {
            if (sresult.length() < s.substring(start, end).length()) {
                sresult = s.substring(start, end + 1);
            }
        }
        set.add(hash.toString());
        rec(start + 1, end, removeValueFromRollingHash(hash, start));
        rec(start, end - 1, removeValueFromRollingHash(hash, end));
    }
}
