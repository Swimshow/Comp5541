import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class RawDataReader {

	public static String[] readFields(String sourceFile){

		Scanner inputStream = null;

		try{
			inputStream = new Scanner(new FileInputStream(sourceFile)); 
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file");
			System.exit(0);
		}

		String firstLine = inputStream.nextLine();

		firstLine = firstLine.trim();
		String[] fields =  firstLine.split(",");

		inputStream.close();

		return fields;
	}

	public static int countRawRecord(String sourceFile){

		Scanner inputStream = null;

		try{
			inputStream = new Scanner(new FileInputStream(sourceFile)); 
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file");
			System.exit(0);
		}

		inputStream.nextLine();

		int i = 0;

		while(inputStream.hasNext()){          //line counting mechanism ==> how big is array?
			inputStream.nextLine();
			i++;	
		}

		inputStream.close();

		return i;
	}

	public static Record[] loadRawRecords(String sourceFile){

		Record[] database = new Record[countRawRecord(sourceFile)];

		Scanner inputStream = null;

		try{
			inputStream = new Scanner(new FileInputStream(sourceFile)); 
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file");
			System.exit(0);
		}

		inputStream.nextLine();

		String line = null;

		int i = 0;
		while(inputStream.hasNext()){

			line = inputStream.nextLine();
			line = line.trim();

			String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);    //How does this syntax work?

			database[i] = new Record(data);

			i++;
		}

		inputStream.close();

		return database;
	}

}






