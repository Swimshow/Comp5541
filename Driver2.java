
public class Driver2 {

	public static void main(String[] args) {
		
		DataSet.setData(RawDataReader.loadRawRecords("MOCK_TXT.csv"));

		DataSet.setFields(RawDataReader.readFields("MOCK_TXT.csv"));

		int[] f = {3,4}; //row + column selections

		PivotTree tree = new PivotTree(f, DataSet.getDataSet());

		int[] r = {3};  //row selections

		int[] c = {4};   //column selections 

		LabelTree row = new LabelTree(r, DataSet.getDataSet());
		
		LabelTree col = new LabelTree(c, DataSet.getDataSet());

		String[][] test = TablePrinter.printToArray(tree, row, col, 1, -1);  //return a 2D array of table

		
		

		/*for(int i = 0; i < 30; i++){				//print array to console (test)

			for(int x = 0; x < 30; x++){
				System.out.print(test[i][x] + "\t");
			}

			System.out.println();

		}*/
		
		HTMLFileGenerator gen = new HTMLFileGenerator("newTable");
		gen.generateHTML(test, "newTable",1,1);


	}

}
