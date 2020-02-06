package February.feb5;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Coding {
    Set<String> set = new HashSet<>();
    String s;

    public static void main(String[] args) {
        Coding coding = new Coding();
        BigInteger bi = coding.hash("bbwfowdeauwderbddpwzrfowybhpvfoyvfdrsgjiytfxxawkctyfvrywxqwwoculuoiqzmsbaonhtswpmachjaademrwznqbkrravioseyibmqeuuayrnxzyptpuwlblkpvhgkufnjhprgsecqzpgfdjdgagjgiifjiftyiimgegotdylcxhdakzwgicnbzefvmdbhbbgbvxbdueewyzrpvxfcbigaprdudvbxreavzgwpcxldwcfnqrbbfvcmeiyafbhtixegibfnugfytiqczwqclfsksameergvxljtxeranlnozzhpdexkfwysuzeavvzqoxogxsixiczwrwrefqnefkumlzdzknqwizvqzyginiakjxllvpttdrhorinzhkxirfkryymvqezvdifjbndxdlflzsbigypltvuyocbudqidyxfknoslylcwwvidlrfjntfkgmzpvkkzscspslrnypbgziknzawqpfvmarzjwdwbezcudhmedfcmdwutehzeayufgmkbnuxaozypkakonotapbzeambrileusrfzhijejuggvtakwsnxuzubdojfgkzwrvsetjvmwqobtagebxgicsgrtgzmrzjnzitxknocptmayabfwrupscpwmclknwqlhkyejhyfxuiunasfbiuttrfotckztxozawqgqwswvwfdnozbmocmdmlyupaoayxnzwrvapputncymzpefiozqimezggqvwlhtpdaseputojdrjxfueemvzdjhhwhfvsauvhpkhldwvwuvonpginysnltfgqawamilcpxdreyjwnmlxcbdurpeasxnabftirkappyrbwsuccrkrzsvlwrwyivctvdmrmdrrxipbqusmicdbqasklcadkianuctcxkewctdrdllodyrpskipsybwrldbsvpjuxmgdbxwhuweizihgiulzrsjsdesdodhmqzwtayfpdtbhnjyjvsilfspghnwytnhoqpcaaawsvxvuotfjkqismsjvevloccfzyubzbucdorgasyhnmemaetpgjruhrbvzdqdjycgybrfxlviqjosqamighivronqyguaunuoxyxnlvysuitxeibyhndoarjbcxxvovleuygweqbsmqtsgvvnwcyooikmeqjjeypfcomywiuyxuwcvlpnypqmaqeuckjgkmhofvbjqubrybeovxtyvgxoodyfjkiicqxfrwhqhnrgfuxtcxyswwluiwpmfdoqsuijjauophmzyyydleuaipsnfpswjfgmaqdigiuzyxtbsgxabbrxlcprzamzwzljbyqnnfhfitnmmruidqcuudwtqstloatznninzmezliprpkzxgoahevghjpwbodqmgcywwanykmijimsdbohmhrgxvkuevuqrlxhgzasmcycwzijwxklmiyfcvyycmfrilqowhsqpqcyexjuhpmcveyipnljcbroiuzizwdclcsbqxzeg");
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
}
