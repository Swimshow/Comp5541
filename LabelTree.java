public class LabelTree extends PivotTree {

	private String[][] labels = null;               
	private int row = -1;
	private int column = -1;



	public LabelTree(int[] f, Record[] r) {
		super(f, r);
		initializeArray();
		generateLabels(this.root);
		
	}

	
	public String[][] getLabels(){
		return labels;
	}

	
	
	public int treeWidth(Node n){

		int pass = 0;

		if(n.childs[0].childs == null){

			return n.childs.length;

		}
		else{

			for (Node x : n.childs){
				pass += treeWidth(x);
			}

		}

		return pass;
	}




	public void initializeArray(){

		labels = new String[this.fields.length][this.treeWidth(this.root)];

	}




	public void addToLabel(Node n){    //try to use this to create label array?

		column++;

		for(int x : this.fields){
			row++;

			this.labels[row][column] = n.data[0].getData()[x];

		}

		row = -1;

	}


	public void generateLabels(Node n){

		
		if(n.childs == null){

			addToLabel(n);

		}
		else{

			for (Node x : n.childs){
				generateLabels(x);
			}

		}

	}




}
