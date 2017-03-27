
public class RecordSearch {


	
	
	
	/**
	 * This method finds the first position of a given element in a sorted Record array
	 * @param r Record array
	 * @param field 
	 * @param s String which is searched for
	 * @return -1 if not found, index if found
	 */
	public static int findStartIndex(Record[] r, int field, String s){


		int guess = binaryRecSearch(r, field, s);    	//find location of element using binary search


		if(guess == -1){return -1;}                 	//if element was not found --> return -1

		while(true){

			if(s.equals(r[guess].getData()[field])){
				if(guess == 0){return 0;}             	//case where record is found at position 0
				guess--;}                          
			else{break;}
		}

		return guess+1;      							//return first position
	}





	/**
	 * 
	 * @param r Record array input
	 * @param field, field specification
	 * @param s, String element which specifies extraction
	 * @return output, record array of extracted records
	 */
	public static Record[] extractRecords(Record[] r, int field, String s){

		int index = findStartIndex(r, field, s);	//find first index of matching Record
		if(index == -1){return null;}            	//if it was not found
		
		int count = 0;								//initialize record count to zero
		int index2 = index;							//copy index for later use
		
		while(true){

			if (index >= r.length){break;}  					//Search is out of bounds  --> break
			if(!(r[index].getData()[field].equals(s))){break;} 	//mismatch is found --> break
			else{index ++; count ++;}							//increment index and count
		}
                   

		if(count == 0){return null;}							//no matches found to extract

		Record[] output = new Record[count]; 					//initialize output array

		for(int i = 0; i < count; i++){

			output[i] = r[index2];								//load output array
			index2++;
		}

		return output;  										//return array of matching records 
	}





	/**
	 * 
	 * @param r Record array
	 * @param field specification for search
	 * @param s String being searched
	 * @return -1 if not found, if found --> 1st find of string
	 */
	public static int binaryRecSearch(Record[] r, int field, String s){


	

		RecordSort.heapSort(r, field);           //sorting based on specified field

		int guess = r.length/2;              //middle of array
		int min = 0;						 //start of array
		int max = r.length-1;                //end of array

		while(true){


			if (min > max){return -1;}	//condition where match is not found  --> return -1

			if(s.equals(r[guess].getData()[field])){return guess;}  								//--> return first match index
			else if(s.compareTo(r[guess].getData()[field])>0){min = guess+1; guess = (min+max)/2;}  //binary search
			else if(s.compareTo(r[guess].getData()[field])<0){max = guess-1; guess = guess/2;}

		}
	}


}
