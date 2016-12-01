package Comp5511_group02_Assign4_Q6;

import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author comp5511_group02
 */

public class PatternMatchingApps {

    // Example of reference text and pattern from slide 7 of 9-Pattern-matching.pdf
    public static String[] ref_text_exp = {"a", "b", "a", "c", "a", "a", "b", "a", "d", "c", "a", "b", "a", "c", "a", "b", "a", "a", "b", "b"};
    public static String[] ref_pattern_exp = {"a", "b", "a", "c", "a", "b"};

    public static void main(String[] args) throws IOException {

        // Start PatternMatchingApps
        // Transforming input string array into a single string
        StringBuilder text_builder = new StringBuilder();
        for (String text : ref_text_exp) {
            text_builder.append(text);
        }
        StringBuilder pattern_builder = new StringBuilder();
        for (String pattern : ref_pattern_exp) {
            pattern_builder.append(pattern);
        }
        System.out.println("Testing the example of reference text and pattern from slide 7 of 9-Pattern-matching.pdf");
        System.out.println("The example text is: "+text_builder.toString());
        System.out.println("The example pattern is: "+pattern_builder.toString());
        System.out.println("");
        // Call Brute-Force matching algorithm
        int i = BF_matching.search(text_builder.toString(), pattern_builder.toString());
        if (i == -1) {
            System.out.println("Your pattern is not found");
            System.out.println("");
        } else {
            System.out.println("The position of first appearance in the text is =  " + i);
            System.out.println("");
        }// end of Brute-Force matching algorithm
        
        // Call BM matching algorithm
        BM_matching call_BM = new BM_matching(text_builder.toString(), pattern_builder.toString());
        call_BM.BMPatternAnalyst(pattern_builder.toString());
        call_BM.CallBM();
        
        // Call KMP matching algorithm
        KmpMatching call_kmp = new KmpMatching();
        System.out.println("");
        System.out.println("Using KMP Pattern Matching:");
        System.out.println("The failure fuction for KMP matching is: " + Arrays.toString(call_kmp.failureFunction(pattern_builder.toString())));
        int[] kmpIndex = call_kmp.matching(text_builder.toString(), pattern_builder.toString());
        if (kmpIndex[0] == -1) {
            System.out.println("There is no matched pattern in the text!");
        } else {
            System.out.println("There is a matched pattern at the index: '" + kmpIndex[0] + "' in the text.");
            System.out.println("The total number of comparison is: " + kmpIndex[1]);
        } // end of KMP
        
        ImportFile_UI fileUI = new ImportFile_UI();
        fileUI.UI();
    }// end of "main"

}
