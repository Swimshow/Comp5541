
package comp5511_group02_assignment4_q6;

/**
 *
 * @author comp5511_group02
 */
public class KmpMatching {
    //private final String inputText;
    //private final String pattern;
    public int inputPatternSize;
    public int ref_fileSize;
    public int[] failureFunctionArray;
    public int comparisonNum = 0;
    
//    public KmpMatching (String inputText,String pattern){
//        this.inputText = inputText;
//        this.pattern = pattern;
//    }

    // computing the failure function
    public int[] failureFunction(String pattern){
        
        inputPatternSize = pattern.length();
        failureFunctionArray = new int[inputPatternSize];
        failureFunctionArray[0]=0;
        int i=1;
        int j=0;
        while (i<inputPatternSize){
            if (pattern.charAt(i)==pattern.charAt(j)){
                failureFunctionArray[i]=j+1;
                i++;
                j++;
            } else if (j>0){
                j=failureFunctionArray[j-1];
            } else {
                failureFunctionArray[i]=0;
                i++;
            }
        }    
        return failureFunctionArray;
    } // end of failureFunction()
    // KMP matching, if there is no match, return -1.
    public int[] matching(String inputText,String pattern){
        int[] kmpFailureFunction = failureFunction(pattern);
        int i=0;
        int j=0;
        int n=inputText.length();
        int m=pattern.length();
        int[] result = new int[2];
        while (i<n){
            if(inputText.charAt(i)==pattern.charAt(j)){
                comparisonNum++;
                if(j==(m-1)){
                    result[0] = i-j;
                    result[1] = comparisonNum;
                    return result;
                }else{
                    i++;
                    j++;
                }
            }else{
                comparisonNum++;
                if(j>0){
                    j=kmpFailureFunction[j-1];
                }else{
                    i++;
                }
            }
        } // end of while
        result[0] = -1;
        result[1] = comparisonNum;
        return result;
    } // end of Matching()
} // end of KmpMatching()
