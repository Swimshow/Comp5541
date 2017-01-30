package comp5511_group02_assignment4_q6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Comp5511_group02
 */
public class ImportFile_UI {

    public void UI() throws FileNotFoundException, IOException {

        System.out.println("");
        System.out.println("For testing pattern matching algorithm, here is an article. (e.g. DonaldTrumpAbortionPoliciesWouldKillWomen.txt)");
        Scanner Type = new Scanner(System.in);
        System.out.println("Please type first letter of your desired function -> ");
        System.out.println("'show' for displaying the text file, 'sp' for searching by pattern,");
        System.out.println(" 'e' for exiting the program (please type in the line below):");
        String Function = Type.nextLine();

        switch (Function) {

            case "show":
                File showInputText = new File("DonaldTrumpAbortionPoliciesWouldKillWomen.txt");
                // A connection stream connects to the text file
                FileReader showFileReader = new FileReader(showInputText);
                // A file pointer always points to the text file
                BufferedReader showFilePointer = new BufferedReader(showFileReader);
                // "textWords" is a String data structure for storing words of the input text file
                String showTextWords = showFilePointer.readLine();
                System.out.println("");
                System.out.println("Your text is : \n" + showTextWords.replaceAll("[.$]", System.getProperty("line.separator")));
                UI();
            case "sp":
                File inputTextFile = new File("DonaldTrumpAbortionPoliciesWouldKillWomen.txt");
                // A connection stream connects to the text file
                FileReader fileReader = new FileReader(inputTextFile);
                // A file pointer always points to the text file
                BufferedReader filePointer = new BufferedReader(fileReader);
                // "textWords" is a String data structure for storing words of the input text file
                String textWords = filePointer.readLine();
                System.out.println("Please input a pattern to find : \n");
                Scanner input = new Scanner(System.in);
                String pattern = input.nextLine();
                // Call Brute-Force matching algorithm
                int i = BF_matching.search(textWords, pattern);
                if (i == -1) {
                    System.out.println("Your pattern is not found");
                    System.out.println("");
                } else {
                    System.out.println("The position of first appearance in the text is =  " + i);
                    System.out.println("");
                }// end of Brute-Force matching algorithm

                // Call BM matching algorithm
//                BM_matching call_BM = new BM_matching(textWords, pattern);
//                call_BM.BMPatternAnalyst(pattern);
//                call_BM.CallBM();
                BM call_BM = new BM(textWords, pattern);
                call_BM.BM_lastOccurFunction(pattern);
                int[] bmResult = call_BM.CallBM();
                if (bmResult[0] == -1) {
                    System.out.println("Your pattern is not found");
                    System.out.println("");
                } else {
                    System.out.println("The position of first appearance in the text is =  " + bmResult[0]);
                    System.out.println("The total number of comparison is: " + bmResult[1]);
                    System.out.println("");
                }

                // Call KMP matching algorithm
                KmpMatching call_kmp = new KmpMatching();
                System.out.println("");
                System.out.println("Using KMP Pattern Matching:");
                System.out.println("The failure fuction for KMP matching is: " + Arrays.toString(call_kmp.failureFunction(pattern)));
                int[] kmpIndex = call_kmp.matching(textWords, pattern);
                if (kmpIndex[0] == -1) {
                    System.out.println("There is no matched pattern in the text!");
                } else {
                    System.out.println("There is a matched pattern at the index: '" + kmpIndex[0] + "' in the text.");
                    System.out.println("The total number of comparison is: " + kmpIndex[1]);
                } // end of KMP
                UI();
            case "e":
                System.out.println("This program stops running.");
                System.exit(0);
            default:
                System.out.println("Please enter a valid input instruction.");
                UI();
        } // end of switch
    } // end of UI()
} // end of ImportFile_UI()
