
package Comp5511_group02_Assign4_Q6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author comp5511_group02
 */
public class BF_matching {
    //******Reading & converting a text file (*.txt) to a string 
	public static String ReadText(String FileAddress)
	{
		String text = "";		
		File file = new File(FileAddress) ;		
		BufferedReader reader;		
		try {		
			reader = new BufferedReader(new FileReader(file));		
			String line ;		
			do 		
			{			
				line = reader.readLine();			
				text += line ;		
			}while (line !=null) ;		
			text = text.toLowerCase();				
			reader.close();								
		} catch (Exception e) {			
			System.out.println("Your file format or path is not correct.");
			System.exit(1);
		}				
		return text;	
	}//end of method "ReadText"
	
//**receiving Text and Pattern and, returning the result of pattern searching in that text file.  
	public static int search(String Text , String Pattern )
	{
		int NoC = 0 ;    //Number of comparison  
		int TL = Text.length();                
		int PL = Pattern.length();
		int j;
		for (int i = 0 ; i<TL-PL ; i++)
		{
			j = 0 ;
			NoC++ ;
			while (j < PL && Text.charAt(i+j) == Pattern.charAt(j) ) 
			{
				j++ ;
				NoC++ ;
				if (j == PL )
				{
                                    System.out.println("Using BF Pattern Matching:");
                                    System.out.println("Number of comparison = " + NoC);
				    return i ;
				}
			}
		}
		System.out.println("Number of comparison = " + NoC);
		return -1 ;
	}//end of method "search"
}
