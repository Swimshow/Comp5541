
package comp5511_group02_assignment4_q6;

/**
 *
 * @author comp5511_group02
 */
public class BM_matching {

    String ref_file;
    String inputPattern;
    int TStart;
    int BStart;
    int[] last = new int[26];
    public static int compare = 0;
    int index = 0;

    public BM_matching(String ref_file, String inputPattern) {
        this.TStart = inputPattern.length()-1;
        this.BStart = inputPattern.length()-1;
        this.ref_file = ref_file;
        this.inputPattern = inputPattern;
    }
    
    public void BMPatternAnalyst(String pattern) {
       
        String inputPatternAnalysis = pattern;
        // first we have to define last[]
        for (int x = 1; x <= inputPatternAnalysis.length(); x++) {
            int c = x;
            for (int y = 1; y < x; y++) {
                if (inputPatternAnalysis.charAt(inputPatternAnalysis.length() - y) != inputPatternAnalysis.charAt(inputPatternAnalysis.length() - x)) {
                    c--;
                }
            }
            if (c == 1) {
                switch (inputPatternAnalysis.charAt(inputPatternAnalysis.length() - x)) {
                    case 'a':
                        last[0] = inputPatternAnalysis.length() - x;
                        ;
                        break;
                    case 'b':
                        last[1] = inputPatternAnalysis.length() - x;
                        break;
                    case 'c':
                        last[2] = inputPatternAnalysis.length() - x;
                        break;
                    case 'd':
                        last[3] = inputPatternAnalysis.length() - x;
                        break;
                    case 'e':
                        last[4] = inputPatternAnalysis.length() - x;
                        break;
                    case 'f':
                        last[5] = inputPatternAnalysis.length() - x;
                        break;
                    case 'g':
                        last[6] = inputPatternAnalysis.length() - x;
                        break;
                    case 'h':
                        last[7] = inputPatternAnalysis.length() - x;
                        break;
                    case 'i':
                        last[8] = inputPatternAnalysis.length() - x;
                        break;
                    case 'j':
                        last[9] = inputPatternAnalysis.length() - x;
                        break;
                    case 'k':
                        last[10] = inputPatternAnalysis.length() - x;
                        break;
                    case 'l':
                        last[11] = inputPatternAnalysis.length() - x;
                        break;
                    case 'm':
                        last[12] = inputPatternAnalysis.length() - x;
                        break;
                    case 'n':
                        last[13] = inputPatternAnalysis.length() - x;
                        break;
                    case 'o':
                        last[14] = inputPatternAnalysis.length() - x;
                        break;
                    case 'p':
                        last[15] = inputPatternAnalysis.length() - x;
                        break;
                    case 'q':
                        last[16] = inputPatternAnalysis.length() - x;
                        break;
                    case 'r':
                        last[17] = inputPatternAnalysis.length() - x;
                        break;
                    case 's':
                        last[18] = inputPatternAnalysis.length() - x;
                        break;
                    case 't':
                        last[19] = inputPatternAnalysis.length() - x;
                        break;
                    case 'u':
                        last[20] = inputPatternAnalysis.length() - x;
                        break;
                    case 'v':
                        last[21] = inputPatternAnalysis.length() - x;
                        break;
                    case 'w':
                        last[22] = inputPatternAnalysis.length() - x;
                        break;
                    case 'x':
                        last[23] = inputPatternAnalysis.length() - x;
                        break;
                    case 'y':
                        last[24] = inputPatternAnalysis.length() - x;
                        break;
                    case 'z':
                        last[25] = inputPatternAnalysis.length() - x;
                        break;
                }
            }
        }
    }

    public void CallBM() {
        //Match
        if (ref_file.charAt(TStart + index)==inputPattern.charAt(BStart)) {
            compare++; 
            if (BStart == 0)//Last match
            {
                System.out.println("Using BM Pattern:");
                System.out.println("Index is found at: " + index + "    ");
                System.out.println("Number of comparisions are: " + compare + "    ");
                return;
            }
            TStart--;
            BStart--;
            CallBM();
        } else {
            int i = 0;
            compare++; 
            switch (ref_file.charAt(BStart + index)) {
                case 'a':
                    i = last[0];
                    break;
                case 'b':
                    i = last[1];
                    break;
                case 'c':
                    i = last[2];
                    break;
                case 'd':
                    i = last[3];
                    break;
                case 'e':
                    i = last[4];
                    break;
                case 'f':
                    i = last[5];
                    break;
                case 'g':
                    i = last[6];
                    break;
                case 'h':
                    i = last[7];
                    break;
                case 'i':
                    i = last[8];
                    break;
                case 'j':
                    i = last[9];
                    break;
                case 'k':
                    i = last[10];
                    break;
                case 'l':
                    i = last[11];
                    break;
                case 'm':
                    i = last[12];
                    break;
                case 'n':
                    i = last[13];
                    break;
                case 'o':
                    i = last[14];
                    break;
                case 'p':
                    i = last[15];
                    break;
                case 'q':
                    i = last[16];
                    break;
                case 'r':
                    i = last[17];
                    break;
                case 's':
                    i = last[18];
                    break;
                case 't':
                    i = last[19];
                    break;
                case 'u':
                    i = last[20];
                    break;
                case 'v':
                    i = last[21];
                    break;
                case 'w':
                    i = last[22];
                    break;
                case 'x':
                    i = last[23];
                    break;
                case 'y':
                    i = last[24];
                    break;
                case 'z':
                    i = last[25];
                    break;
            }

            if (i == 0) {
                index = index + BStart + 1;
            } else {
                index = index + inputPattern.length() - 1 - i;
            }

            if ((index + BStart + 2) >= ref_file.length()) {
                System.out.println("No match!");
                System.out.println("Number of comparisions are: " + compare + "    ");
                //return;
            } else {
                TStart = inputPattern.length() - 1;
                BStart = inputPattern.length() - 1;
                CallBM();
            }
        }
    }

}