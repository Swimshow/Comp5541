public class RecordSort{

	//This classes uses HeapSort to sort Record arrays based on field specification
	
	
	private static void workDown(Record[] theArray, int initial, int end, int field){

		int root = initial;

		while(((root * 2) + 1) <= end) //keep going condition
		{      
			int child = (root * 2) + 1; //location 
			//conditional check

			if((child + 1) <= end && (theArray[child].getData()[field].compareTo(theArray[child + 1].getData()[field]) < 0)) //smaller than
				child = child + 1; // point right

			if(theArray[root].getData()[field].compareTo(theArray[child].getData()[field]) < 0){     //smaller than
				Record ghostTwo = theArray[root];
				theArray[root] = theArray[child];
				theArray[child] = ghostTwo;
				root = child; //continue with child 
			}else

				return;
		}
	}

	
	
	private static void heapify(Record[] theArray, int l, int field){

		int start = (l - 2) / 2; //INDEX STARTING POINT 

		while(start >= 0){

			workDown(theArray, start, l - 1, field); // working order
			start = start-1;
		}


	}

	
	
	protected static void heapSort(Record[] theArray, int field){

		int length = theArray.length; //loaded size of the array

		heapify(theArray, length, field); // max heap it.

		int end = length-1;

		while(end > 0){

			Record ghost = theArray[end]; // swap

			theArray[end]= theArray[0]; 

			theArray[0]=ghost; 

			workDown(theArray, 0, end - 1, field); // max heap

			end = end-1; // decrement heap 
		}
	}
}

