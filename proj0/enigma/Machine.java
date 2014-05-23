package enigma;

/** Class that represents a complete enigma machine.
 *  @author Derek Zheng
 */
class Machine {
    /** This is the instance variable MACHINEROTORS,
     *  a five-element array of rotor objects.
     */
    private Rotor[] machineRotors = new Rotor[5];

    /** This is the constructor of Machine class.
     *  It takes no arguments and initializes
     *  the MACHINEROTORS array. Also, all current positions
     *  of the elements of the array default to "A".*/
    public Machine() {
        machineRotors[0] = new Rotor();
        machineRotors[1] = new Rotor();
        machineRotors[2] = new Rotor();
        machineRotors[3] = new Rotor();
        machineRotors[4] = new Rotor();
        for (int i = 0; i < 5; i++) {
            machineRotors[i].setCurrent("A");
        }
    }

    /** Set my rotors to (from left to right) ROTORS.  Initially, the rotor
     *  settings are all 'A'. */
    void replaceRotors(Rotor[] rotors) {
        for (int i = 0; i < 5; i++) {
            machineRotors[i].setType(rotors[i].getType());
        }
    }

    /** Set my rotors according to SETTING, which must be a string of four
     *  upper-case letters. The first letter refers to the leftmost
     *  rotor setting.  */
    void setRotors(String setting) {
        for (int i = 1; i < 5; i++) {
            machineRotors[i].setCurrent(setting.substring(i - 1, i));
        }
    }

    /** Set up the rotor notches according to the rotor types in ROTORS. */
    void setNotches(Rotor[] rotors) {
        for (int i = 2; i < 5; i++) {
            for (int c = 0; c < 8; c++) {
                if (rotors[i].getType().
                    equals(PermutationData.ROTOR_SPECS[c][0])) {
                    machineRotors[i].
                        setNotch(PermutationData.ROTOR_SPECS[c][3]);
                }
            }
        }
    }

    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        String[] input = new String[msg.length()];
        for (int i = 0; i < input.length; i++) {
            if (msg.charAt(i) != ' ') {
                input[i] = Character.toString(msg.charAt(i));
            }
        }
        String result = "";
        for (String s : input) {
            machineRotors[2].setCount(0);
            machineRotors[3].setCount(0);
            machineRotors[4].setCount(0);
            for (int i = 4; i >= 2; i--) {
                if (machineRotors[i].atNotch()) {
                    if (i == 4) {
                        if (machineRotors[3].atNotch()) {
                            machineRotors[2].advance();
                        }
                        machineRotors[3].advance();
                        machineRotors[4].advance();
                        break;
                    }
                    if (i == 2) {
                        break;
                    }
                    machineRotors[i - 1].advance();
                    int c = i;
                    while (c != 4) {
                        machineRotors[c].advance();
                        c++;
                    }
                }
            }
            machineRotors[4].advance();
            int strNum = 0;
            strNum = Rotor.toIndex(s.charAt(0));
            machineRotors[4].advance();
            for (int i = 4; i >= 0; i--) {
                strNum = machineRotors[i].convertForward(strNum);
            }
            for (int i = 1; i <= 4; i++) {
                strNum = machineRotors[i].convertBackward(strNum);
            }
            char res = Rotor.toLetter(strNum);
            result = result + Character.toString(res);
        }
        return result;
    }
}
