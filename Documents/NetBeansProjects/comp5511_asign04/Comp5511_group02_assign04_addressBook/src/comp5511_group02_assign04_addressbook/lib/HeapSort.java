/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp5511_group02_assign04_addressbook.lib;


/**
 *
 * @author aiken
 */
public class HeapSort {
 
   private static long[] a;
   private static int n;
   private static int left;
   private static int right;
   private static int largest;

   // Constructor
    public HeapSort(long[] a) {
       this.a=a;
    }

    public static long[] getA() {
        return a;
    }
    
    public HeapSort(HeapSort heapSort){
        this.a = heapSort.getA();
    }
   
 
   public static void buildheap(long []a) {
      n = a.length-1;
      for(int i=n/2; i>=0; i--){
         maxheap(a,i);
      }
   }
 
   public static void maxheap(long[] a, int i) { 
      left = 2*i;
      right = 2*i+1;
 
      if(left <= n && a[left] > a[i]){
         largest=left;
      } else {
         largest=i;
      }
 
      if(right <= n && a[right] > a[largest]) {
         largest=right;
      }
      if(largest!=i) {
         exchange(i, largest);
         maxheap(a, largest);
      }
   }
 
   public static void exchange(int i, int j) {
        long t = a[i];
        a[i] = a[j];
        a[j] = t; 
   }
 
   public static void sortIsbn(long[] myarray) {
      a = myarray;
      buildheap(a);
      for(int i=n; i>0; i--) {
         exchange(0, i);
         n=n-1;
         maxheap(a, 0);
      }
   }
}
