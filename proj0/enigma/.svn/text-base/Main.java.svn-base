package enigma;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/** Enigma simulator.
 *  @author Derek Zheng
 */
public final class Main {
    /** Process a sequence of encryptions and decryptions, as
     *  specified in the input from the standard input.  Print the
     *  results on the standard output. Exits normally if there are
     *  no errors in the input; otherwise with code 1. */
    public static void main(String[] unused) {
        Machine M;
        BufferedReader input =
            new BufferedReader(new InputStreamReader(System.in));

        buildRotors();

        M = null;

        try {
            while (true) {
                String line = input.readLine();
                if (line == null) {
                    break;
                }
                if (isConfigurationLine(line)) {
                    M = new Machine();
                    configure(M, line);
                } else {
                    printMessageLine(M.convert(standardize(line)));
                }
            }
        } catch (IOException excp) {
            System.err.printf("Input error: %s%n", excp.getMessage());
            System.exit(1);
        } catch (NullPointerException e) {
            System.err.printf("Input error: %s%n", e.getMessage());
            System.exit(1);
        }
    }

    /** Return true iff LINE is an Enigma configuration line. */
    public static boolean isConfigurationLine(String line) {
        if (line.startsWith("*")) {
            String[] rotors = line.split(" ");
            if (isNonMovingRotor(rotors[1])) {
                if (isReflector(rotors[2])) {
                    if (isRotor(rotors[3])) {
                        if (isRotor(rotors[4]) && (rotors[3] != rotors[4])) {
                            if (isRotor(rotors[5]) && rotors[5] != rotors[4]
                                        && rotors[5] != rotors[3]) {
                                if (isSetting(rotors[6])) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /** Return true iff ROTOR is a non-moving rotor, B or C. */
    private static boolean isNonMovingRotor(String rotor) {
        if (rotor.startsWith("B") || rotor.startsWith("C")) {
            return true;
        }
        return false;
    }

    /** Return true iff ROTOR is a reflector rotor, Beta or Gamma. */
    private static boolean isReflector(String rotor) {
        if (rotor.startsWith("BETA") || rotor.startsWith("GAMMA")) {
            return true;
        }
        return false;
    }

    /** Return true iff ROTOR is a moving rotor, from rotors I-VIII. */
    private static boolean isRotor(String rotor) {
        String valids = "I II III IV V VI VII VIII";
        if (valids.contains(rotor)) {
            return true;
        }
        return false;
    }

    /** Return true if the SETTINGS is a string of 4 letters from
     *  A-Z. */
    private static boolean isSetting(String settings) {
        if (settings.length() != 4) {
            return false;
        } else {
            String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            for (int i = 0; i < 4; i++) {
                if (!alph.contains(settings.charAt(i) + "")) {
                    return false;
                }
            }
            return true;
        }
    }

    /** Configure M according to the specification given on CONFIG,
     *  which must have the format specified in the assignment. */
    private static void configure(Machine M, String config) {
        String[] types = config.split(" ");
        M.setRotors(types[types.length - 1]);
        Rotor rotor1 = new Reflector();
        Rotor rotor2 = new FixedRotor();
        Rotor rotor3 = new Rotor();
        Rotor rotor4 = new Rotor();
        Rotor rotor5 = new Rotor();
        rotor1.setType(types[1]);
        rotor2.setType(types[2]);
        rotor3.setType(types[3]);
        rotor4.setType(types[4]);
        rotor5.setType(types[5]);
        Rotor[] rotors = {rotor1, rotor2, rotor3, rotor4, rotor5};
        M.replaceRotors(rotors);
        M.setNotches(rotors);
    }
    /** Return the result of converting LINE to all upper case,
     *  removing all blanks and tabs.  It is an error if LINE contains
     *  characters other than letters and blanks. */
    private static String standardize(String line) {
        String newline = line.replaceAll(" ", "");
        return newline.toUpperCase();
    }

    /** Print MSG in groups of five (except that the last group may
     *  have fewer letters). */
    private static void printMessageLine(String msg) {
        int counter = 1;
        int counter2 = 0;
        while (counter2 < msg.length()) {
            if (counter % 6 != 0) {
                System.out.print(msg.charAt(counter2));
                counter++;
                counter2++;
            } else {
                System.out.print(' ');
                counter = 1;
            }
        }
        System.out.println();
    }

    /** Create all the necessary rotors. */
    private static void buildRotors() {
        return;
    }

}
