/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp5511_group02_assignment4_q6;

/**
 *
 * @author aiken
 */
public class BM {
    String ref_file;
    String inputPattern;
    int TStart;
    
    int[] last;
    int compare = 0;
    int index = 0;
    
    public BM(String ref_file, String inputPattern) {
        this.TStart = inputPattern.length()-1;
        //this.BStart = inputPattern.length()-1;
        this.ref_file = ref_file;
        this.inputPattern = inputPattern;
    }
    
    public void BM_lastOccurFunction(String pattern) {
        String inputPatternAnalysis = pattern;
        StringBuilder last_builder = new StringBuilder();
        last_builder.append(inputPatternAnalysis.charAt(0));        
        
        //last = new int[inputPatternAnalysis.length()];
        for(int i =1;i<inputPatternAnalysis.length();i++){
            for(int j=0;j<last_builder.length();j++){
                if(inputPatternAnalysis.charAt(i)!=last_builder.charAt(j)){
                    last_builder.append(inputPatternAnalysis.charAt(i));
                }
            }
        }
        //int histPatternSize=last_builder.length();
        last = new int[last_builder.length()];
        for(int i =0;i<inputPatternAnalysis.length();i++){
            for(int j=0;j<last_builder.length();j++){
                if(inputPatternAnalysis.charAt(i)==last_builder.charAt(j)){
                    last[j]=i;
                }
            }
        }
    }// end of last occuring function
     
    public int[] CallBM() {
         int[] BM_result = new int[2];
         int m = inputPattern.length();
         int n = ref_file.length();
         int i = m-1;
         int j = m-1;
         
         while(i<=(n-1)){
             if(ref_file.charAt(i)==inputPattern.charAt(j)){
                 compare++;
                 
                 if(j==0){
                     BM_result[0]=i;
                     BM_result[1]=compare;
                     return BM_result;  
                 }else{
                     i--;
                     j--;
                 }
             } else {
                 int lastValueIndex=0;
                 int lastValue;
                 for(int k =0;k<m;k++){
                     if (ref_file.charAt(i)==inputPattern.charAt(k)){
                        lastValueIndex = k; 
                     }
                 }
                 lastValue = last[lastValueIndex];
                 if (j<(lastValue+1)){
                    i =i+m-j;
                    j = m-1;
                 } else if (j>=(lastValue+1)){
                    i = i+m-(lastValue+1);
                    j = m-1;
                 }
                 
             }
         }
         BM_result[0]=-1;
         BM_result[1]=compare;
         return BM_result;
     } // end of callBM()
}
