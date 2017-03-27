public class DataSet {

	private static Record[] dataSet = null;  //array which holds all of the raw records (each record being one line in raw .csv file)
	private  static String[] fields = null;   //array which holds all of the field names ("Country", "Name", "Amount" etc....)
	

	public static void setData(Record[] data){   	//setter method 
		dataSet = data;
	}


	public static void setFields(String[] f){		//setter method
		fields = f;
	}


	public static Record[] getDataSet() {
		return dataSet;
	}


	public static String[] getFields() {
		return fields;
	}

	
}
