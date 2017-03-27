
public class Record{


	private String[] data = null;		//array of Strings --> all the data in the line


	public Record(String[] input){
		setData(input);
	}


	public String[] getData() {
		return data;
	}


	public void setData(String[] data) {
		this.data = data;
	}


	//toString method to help display data
	public String toString(){   
		String output = "";
		for(String s: this.data){
			output = output + s + "\t";
		}
		
		return output;
	}



}
