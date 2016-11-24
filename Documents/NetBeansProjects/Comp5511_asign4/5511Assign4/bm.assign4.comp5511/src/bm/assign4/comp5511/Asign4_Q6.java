/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bm.assign4.comp5511;

import static bm.assign4.comp5511.BM.inputPattern;
import static bm.assign4.comp5511.BM.ref_file;
import java.util.Arrays;

/**
 *
 * Comp5511_group02
 */
public class Asign4_Q6 {
    
    public static void main(String[] args) {
    BM call = new BM();
    call.Call();
    BM print = new BM();
    print.Print();
    // Call KMP matching algorithm
    KmpMatch call_kmp = new KmpMatch(ref_file,inputPattern);
    System.out.println("The failure fuction for KMP matching is: "+Arrays.toString(call_kmp.failureFunction()));
    int[] kmpIndex = call_kmp.matching();
    if(kmpIndex[0]==-1){
        System.out.println("There is no matched pattern in the text!");
    }else{
        System.out.println("There is a matched pattern at the index: '"+kmpIndex[0]+"' in the text.");
        System.out.println("The total number of comparison is: "+kmpIndex[1]);
    }
    }    
}
