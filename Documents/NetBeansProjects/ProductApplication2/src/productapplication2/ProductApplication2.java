/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productapplication2;

import bean.Product;
import java.util.Scanner;

/**
 *
 * @author c_hung
 */
public class ProductApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Product monitor = new Product("monitor",123,"#123","kg",6,null);
        
        double pricePlusTax = monitor.calculatePriceTaxIncluded();
        
        System.out.println(monitor);
        System.out.println("Price plus tax is: "+pricePlusTax);
        //Scanner sc = new Scanner(System.in);
        //monitor.price =12.0;
        //monitor.price=monitor.price/2;
        //System.out.println(monitor.price);
        //monitor.setSerial ("1234");
        //monitor.setWeight (40);
        
                
    }
    
}
