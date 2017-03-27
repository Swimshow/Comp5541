public class TablePrinter {

	public static String[][] printToArray(PivotTree pt, LabelTree row, LabelTree col, int sum, int ave){

		if(row != null && col != null){

			String[][] cols = col.getLabels();
			String[][] rows = row.getLabels();
			String[][] output = new String[rows[0].length + cols.length][cols[0].length + rows.length];

			appendRowCol(output, rows, cols);

			appendData(output, rows, cols, pt, sum, ave);

			return output;
		}
		else if(row != null && col == null){

			String[][] rows = row.getLabels();
			String[][] output = new String[rows[0].length][rows.length + 1];

			appendRowsOnlyNdata(output, rows, pt, sum, ave);
			return output;
		}
		else if(row == null && col != null){

			String[][] cols = col.getLabels();
			String[][] output = new String[cols.length+1][cols[0].length];

			appendColsOnlyNData(output, cols, pt, sum, ave);
			return output;
		}

		return null;

	}

	public static void appendRowCol(String[][] arr, String[][] rows, String[][] cols){

		int rpos , cpos = 0;

		for(String[] x : rows){

			rpos = cols.length;

			for(String s: x){

				arr[rpos][cpos] = s;

				rpos++;
			}	

			cpos++;		
		}



		rpos = 0;

		for(String[] x : cols){
			cpos = rows.length;

			for(String s: x){

				arr[rpos][cpos] = s;

				cpos++;
			}	

			rpos++;		
		}


	}

	public static void appendData(String[][] arr, String[][] rows, String[][] cols, PivotTree pt, int sum, int ave){

		String[] search = new String[cols.length + rows.length];

		int outRow = cols.length;
		int outCol = rows.length;
		int inColLim = cols[0].length;
		int inRowLim = rows[0].length;
		int inCol = 0;
		int inRow = 0;
		int outColLim = arr[0].length;


		while(true){

			for(int x=0; x<rows.length; x++){
				search[x] = rows[x][inRow];
			}

			int z =  rows.length;

			for(int x=0; z<search.length; x++){
				search[z] = cols[x][inCol];
				z++;
			}

			inCol++;

			if(inCol == inColLim){
				inCol = 0;
				inRow++;
			}

			if(inRow==inRowLim){
				break;
			}


			Record[] result = pt.searchTree(search);


			if(result == null){

				arr[outRow][outCol] = "-";

			}else{

				if(sum != -1){

					double outsum = 0.0;

					for(Record r : result){
						outsum += Double.parseDouble(r.getData()[sum]);
					}

					arr[outRow][outCol] =  "" + outsum;

				}else if(ave != -1){

					double outave = 0.0;

					for(Record r : result){
						outave += Double.parseDouble(r.getData()[ave]);
					}

					outave = outave/result.length;

					arr[outRow][outCol] =  "" + outave;

				}else{

					arr[outRow][outCol] = ""+result.length;
				}

			}

			outCol++;

			if(outCol==outColLim){
				outCol = rows.length;
				outRow++;
			}

		}
	}

	public static void appendRowsOnlyNdata(String[][] arr, String[][] rows, PivotTree pt, int sum, int ave){

		int inRow = 0;
		int inRowLim = rows[0].length;
		String[] search = new String[rows.length];

		while(true){


			for(int x = 0; x < rows.length; x++){

				arr[inRow][x] = rows[x][inRow];
				search[x] = rows[x][inRow];

			}


			Record[] result = pt.searchTree(search);


			if(result == null){arr[inRow][rows.length] = "";}

			if(sum != -1){

				double outsum = 0.0;

				for(Record r : result){
					outsum += Double.parseDouble(r.getData()[sum]);
				}

				arr[inRow][rows.length] =  "" + outsum;


			}else if(ave != -1 ){

				double outave = 0.0;

				for(Record r : result){
					outave += Double.parseDouble(r.getData()[ave]);
				}

				outave = outave/result.length;

				arr[inRow][rows.length] =  "" + outave;



			}
			else{
				arr[inRow][rows.length] = "" + result.length;
			}

			inRow++;

			if(inRow == inRowLim){
				break;
			}


		}



	}

	public static void appendColsOnlyNData(String[][] arr, String[][] cols, PivotTree pt, int sum, int ave){
		
		int inCol = 0;
		int inColLim = cols[0].length;
		String[] search = new String[cols.length];

		while(true){


			for(int x = 0; x < cols.length; x++){

				arr[x][inCol] = cols[x][inCol];
				search[x] = cols[x][inCol];

			}


			Record[] result = pt.searchTree(search);


			if(result == null){arr[cols.length][inCol] = "";}

			if(sum != -1){

				double outsum = 0.0;

				for(Record r : result){
					outsum += Double.parseDouble(r.getData()[sum]);
				}

				arr[cols.length][inCol] =  "" + outsum;


			}else if(ave != -1 ){

				double outave = 0.0;

				for(Record r : result){
					outave += Double.parseDouble(r.getData()[ave]);
				}

				outave = outave/result.length;

				arr[cols.length][inCol] =  "" + outave;



			}
			else{
				arr[cols.length][inCol] = "" + result.length;
			}

			inCol++;

			if(inCol == inColLim){
				break;
			}


		}

	}

}