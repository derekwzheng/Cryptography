package enigma;

/** Class that represents a rotor in the enigma machine.
 *  @author Derek Zheng
 */
class Rotor {
    /** Rotor has three instance variables:
     *  TYPE, CURRENT, and NOTCH.*/
    private String type;
    /** Instance variable of current position: CURRENT.*/
    private String current;
    /** Instance variable of notch: NOTCH.*/
    private String notch;
    /** Instance variable of count: COUNT. It is used to limit
     *  every rotor can at most rotates once for
     *  every letter signal comes in.*/
    private int count;

    /** The constructor of Rotor class.
     *  The three variables default to empty strings.*/
    public Rotor() {
        type = "";
        current = "";
        notch = "";
        count = 0;
    }

    /** Get a rotor's type.Return TYPE.*/
    public String getType() {
        return type;
    }

    /** Set a rotor's type as TP.*/
    public void setType(String tp) {
        type = tp;
    }

    /** Get a rotor's current position. Return CURRENT.*/
    public String getCurrent() {
        return current;
    }

    /** Set a rotor's current as CURR.*/
    public void setCurrent(String curr) {
        current = curr;
    }

    /** Get a rotor's notch. Return NOTCH.*/
    public String getNotch() {
        return notch;
    }

    /** Set a rotor's notch as NCH.*/
    public void setNotch(String nch) {
        notch = nch;
    }

    /** Get a rotor's count. Return COUNT.*/
    public int getCount() {
        return count;
    }

    /** Set a rotor's count as CT.*/
    public void setCount(int ct) {
        count = ct;
    }

    /** Size of alphabet used for plaintext and ciphertext. */
    static final int ALPHABET_SIZE = 26;

    /** String of alphabets. */
    static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /** Total number of rotors = 12.*/
    static final int TOTAL_NUM = 12;

    /** Assuming that P is an integer in the range 0..25, returns the
     *  corresponding upper-case letter in the range A..Z.
     *  We add 26 to the number and divide it by 26 and take the remainder.*/
    static char toLetter(int p) {
        p = (p + ALPHABET_SIZE) % ALPHABET_SIZE;
        return ALPHABET.charAt(p);
    }

    /** Assuming that C is an upper-case letter in the range A-Z, return the
     *  corresponding index in the range 0..25. Inverse of toLetter. */
    static int toIndex(char c) {
        return ALPHABET.indexOf(Character.toString(c));
    }

    /** Returns true iff this rotor has a ratchet and can advance. */
    boolean advances() {
        return true;
    }

    /** Returns true iff this rotor has a left-to-right inverse. */
    boolean hasInverse() {
        return true;
    }

    /** Return my current rotational setting as an integer between 0
     *  and 25 (corresponding to letters 'A' to 'Z').  */
    int getSetting() {
        return _setting;
    }

    /** Set getSetting() to POSN.  */
    void set(int posn) {
        assert 0 <= posn && posn < ALPHABET_SIZE;
        _setting = posn;
    }

    /** Return the conversion of P (an integer in the range 0..25)
     *  according to my permutation. */
    int convertForward(int p) {
        char curr = current.charAt(0);
        int setting = toIndex(curr);
        int num = (p + setting + ALPHABET_SIZE) % ALPHABET_SIZE;
        for (int c = 0; c < TOTAL_NUM; c++) {
            if (type.equals(PermutationData.ROTOR_SPECS[c][0])) {
                char perm = PermutationData.ROTOR_SPECS[c][1].charAt(num);
                num = toIndex(perm);
            }
        }
        return (num - setting + ALPHABET_SIZE) % ALPHABET_SIZE;
    }

    /** Return the conversion of E (an integer in the range 0..25)
     *  according to the inverse of my permutation. */
    int convertBackward(int e) {
        char curr = current.charAt(0);
        int setting = toIndex(curr);
        int num = (e + setting + ALPHABET_SIZE) % ALPHABET_SIZE;
        for (int c = 0; c < TOTAL_NUM; c++) {
            if (type.equals(PermutationData.ROTOR_SPECS[c][0])) {
                char perm = PermutationData.ROTOR_SPECS[c][2].charAt(num);
                num = toIndex(perm);
            }
        }
        return (num - setting + ALPHABET_SIZE) % ALPHABET_SIZE;
    }

    /** Returns true iff I am positioned to allow the rotor to my left
     *  to advance. */
    boolean atNotch() {
        if (notch.length() == 1) {
            if (current.equals(notch)) {
                return true;
            }
            return false;
        } else {
            if (current.equals(notch.substring(0, 1))
                || current.equals(notch.substring(1))) {
                return true;
            }
            return false;
        }
    }

    /** Advance me one position. */
    void advance() {
        if (count == 0) {
            char ch = current.charAt(0);
            ch = toLetter(toIndex(ch) + 1);
            current = Character.toString(ch);
            count += 1;
        }
        return;
    }

    /** My current setting (index 0..25, with 0 indicating that 'A'
     *  is showing). */
    private int _setting;

}
