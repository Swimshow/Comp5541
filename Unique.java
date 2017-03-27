import java.util.Stack;

public class Unique {


	
	
	
	/**
	 * This method returns all the unique elements (of a given field) in an array of records
	 * 
	 * @param field, specifies which field the elements will come from
	 * @param r, input array
	 * @return output, a String array containing all the unique elements
	 */
	public static String[] uniqueElements(int field, Record[] r){
		
		

		Stack<String> unique = new Stack<String>();  		//stack which will hold the elements
	            		

		Record[] copy = r;  								//input array is copied 

		RecordSort.heapSort(r, field); 							//array is then sorted according to field argument


		for(int i = 0; i < copy.length; i++){

		
			if(i == 0){unique.push(copy[i].getData()[field]);} 						//first element is always unique --> push into stack 
			else if(copy[i].getData()[field].equals(copy[i-1].getData()[field])); 	//ignore if duplicate element is found
			else{unique.push(copy[i].getData()[field]);           					//if not duplicate --> push into stack
			
			}
		}

		
		String[] output = new String[unique.size()];    	//initialization of output string array

		for(int i = output.length-1; i>= 0; i--){   		//stack is loaded into array in reverse order

			output[i] = unique.pop();  
		}

		
		return output;                              		//unique elements are returned

	}

}
