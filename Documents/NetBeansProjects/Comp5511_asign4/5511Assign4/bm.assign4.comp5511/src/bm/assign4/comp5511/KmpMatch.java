
package bm.assign4.comp5511;

import static bm.assign4.comp5511.BM.arraysize;
import static bm.assign4.comp5511.BM.patternSize;

/**
 *
 * @author aiken
 */
public class KmpMatch {
    // input text and pattern from page 7 of pattern matching course slide
    //String[] ref_file =       {"a","b","a","c","a","a","b","a","c","c","a","b","a","c","a","b","a","a","b","b"};
    //String[] inputPattern_kmp   = {"a","b","a","c","a","b"};
    private String[] inputText;
    private String[] pattern;
    int inputPatternSize = patternSize;
    public int[] failureFunctionArray = new int[inputPatternSize];
    public int comparisonNum = 0;
    
    public KmpMatch (String[] inputText,String[] pattern){
        this.inputText = inputText;
        this.pattern = pattern;
    }
    // computing the failure function
    public int[] failureFunction(){
        failureFunctionArray[0]=0;
        int i=1;
        int j=0;
        while (i<inputPatternSize){
            if (pattern[i].equals(pattern[j])){
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
    }
    // KMP matching, if there is no match, return -1.
    public int[] matching(){
        int[] kmpFailureFunction = failureFunction();
        int i=0;
        int j=0;
        int n=arraysize;
        int m=patternSize;
        int[] result = new int[2];
        while (i<n){
            if(inputText[i].equals(pattern[j])){
                comparisonNum++;
                if(j==(m-1)){
                    result[0] = i;
                    result[1] = comparisonNum;
                    return result;
                }else{
                    comparisonNum++;
                    i++;
                    j++;
                }
            }else{
                if(j>0){
                    j=kmpFailureFunction[j-1];
                }else{
                    i++;
                }
            }
        }
        result[0] = -1;
        result[1] = comparisonNum;
        return result;
    }
}
