// HEY AIKEN, SO I HAVE MADE THIS REPO 
// FOR JUST YOU AND ME. SEE IF YOU CAN CLONE 
// ONLY THIS FILE INTO YOUR WORKSPACE TO EDIT IT
// AND PULL REQUEST SO THAT I CAN SEE YOUR CHANGES.
// JUST ADD SOME COMMENTS BELOW THIS :)


// Aiken co-works with Daniel on swing GUI project and Add some basic layout and functions!!
// 1. put "MOCK_TXTv1.csv" file in project root folder (contains src folder)
// 2. put "Swing.java" in the src folder
// 3. run "Swing.java" file
// 4. Data source (jRadioButton1,jRadioButton2) needs to implement // you mean database connection ? 
// 5. Parsing user input text field (jTextField1) needs to implement
// 6. The function of generating html file doesn't work properly (sometimes work, sometimes not).

// 4. TODO with @ Ernesto tomorrow!!
// 5. FIXED I used getter fields locally for JtextField1.
// 6. FIXED I used black magic, I dont want to talk about it. It works everytime now!

// Updated Swing.java file @ 2017.Apr.03 for iteration 3
// 1. Please run "pivotLogin.java" first, then click "Login" or "OK" all the way down
// 2. User name and password function need to be implemented
// 3. Register function needs to be implemented along with user name and password function
// 4. Data source function needs a bit more revising along with database tables
// 5. Save table schema function needs to be implemented.
// 6. Shared data and user privilege need to be implemented. 

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.*;

/**
 * @author Comp5541_group C, 2017,Apr,1st
 */
public class Swing extends javax.swing.JFrame {

    public static String[][] testStringData ;
    public static String[] tableColTitle;
    public static String[] fileType = {"html", "csv", "html and csv"};
    public static String[] aggregation = {"Sum", "Count", "Average"};
    public static String rowDisplay;
    public static String colDisplay;
    public String outputName = "";
    public static String dataSource = "";
    public static String inputName = "";
    //varibles for field selections
    static int[] fieldSelection;
    static int[] rowSelection;
    static int[] colSelection;
    static int fileTypeSelection;
    static int sumSelection;
    static int avgSelection;
    List<Integer> rowArray = new ArrayList<Integer>();
    List<Integer> colArray = new ArrayList<Integer>();

    /**
     * Creates new form Swing
     */
    public Swing() {
        initComponents();
        inputName = pivotDataSource.inputFileName;
        // if there is no input file, default input file here:
        if (inputName == null) {
            inputName = "MOCK_TXT.csv";
        }
        //method which reads raw data and organizes it into array of records
        DataSet.setData(RawDataReader.loadRawRecords(inputName));
        //method which reads first line of .csv and sets fields
        DataSet.setFields(RawDataReader.readFields(inputName));
        //Create string array for displaying fields of data in the Swing comboBox
        tableColTitle = RawDataReader.readFields(inputName);
        //passing initial data into this UI
        fetchData();
        
    }
    //passing initial data into this UI
    public void fetchData() {
        // denote data source by dataSource UI
        jLabel3.setText(dataSource);
        // create row and column tabs
        jTabbedPane1.addTab("Rows", jScrollPane3);
        jTabbedPane1.addTab("Columns", jScrollPane4);
        // dynamically generate check boxes in the row/column tabs
        JCheckBox cb_row[] = new JCheckBox[tableColTitle.length];
        JCheckBox cb_col[] = new JCheckBox[tableColTitle.length];
        // create check boxes for row fields tabs
        for (int i = 0; i < tableColTitle.length; i++) {
            cb_row[i] = new JCheckBox(tableColTitle[i]);
            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_row[i])
            );
        }
        // create check boxes for column fields tabs
        for (int i = 0; i < tableColTitle.length; i++) {
            cb_col[i] = new JCheckBox(tableColTitle[i]);
            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_col[i])
            );
        }
        // jCheckBox event for row and column fields displaying
        for (int i = 0; i < tableColTitle.length; i++) {
            cb_row[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rowArray = new ArrayList<Integer>();
                    for (int i = 0; i < tableColTitle.length; i++) {
                        if (cb_row[i].isSelected()) {
                            rowArray.add(i);
                        }
                    }
                    rowSelection = new int[rowArray.size()];
                    for (int j = 0; j < rowArray.size(); j++) {
                        rowSelection[j] = rowArray.get(j).intValue();
                    }
                }
            });

            cb_col[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    colArray = new ArrayList<Integer>();
                    for (int i = 0; i < tableColTitle.length; i++) {
                        if (cb_col[i].isSelected()) {
                            colArray.add(i);
                        }
                    }
                    colSelection = new int[colArray.size()];
                    for (int j = 0; j < colArray.size(); j++) {
                        colSelection[j] = colArray.get(j).intValue();
                    }
                }
            });
        }
        // Initialize aggregation function
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(aggregation));
        // Initialize output file type selection function
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(fileType));
        // Set output file type selection function
        jComboBox3.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                fileTypeSelection = jComboBox3.getSelectedIndex();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pivot Table Viewer");
        setLocation(new java.awt.Point(0, 0));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setDragEnabled(true);
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Selected rows:");

        jLabel8.setText("Selected columns:");

        jLabel9.setText("row fields");

        jLabel10.setText("column fields");

        jLabel13.setText("Table Schema Name:");

        jTextField2.setText("Please type a name for the schema you want to save");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel8)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("view Table");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("file export");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("choose output file type:");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("press button to preview:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Type name for output:");

        jTextField1.setText("type here");

        jLabel12.setText("Save table schema:");

        jButton5.setText("Save");

        jButton4.setText("Load");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Load table schema:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton4)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(126, 126, 126))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton4, jButton5, jComboBox3, jTextField1});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel4, jLabel5});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setPreferredSize(new java.awt.Dimension(160, 70));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.setPreferredSize(new java.awt.Dimension(83, 23));

        jLabel6.setText("Aggregation:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setPreferredSize(new java.awt.Dimension(160, 70));

        jLabel7.setText("Data Source:");

        jButton3.setText("Change data source");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(49, 49, 49))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTabbedPane1.setToolTipText("select row fields");

        jScrollPane3.setViewportView(jPanel1);

        jTabbedPane1.addTab("Rows", jScrollPane3);

        jScrollPane4.setViewportView(jPanel3);

        jTabbedPane1.addTab("Columns", jScrollPane4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        
    // Set aggregation function from user slecltion 
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if (jComboBox4.getSelectedIndex() == 0) {
            sumSelection = 1;
            avgSelection = -1;
        } else if (jComboBox4.getSelectedIndex() == 1) {
            sumSelection = -1;
            avgSelection = -1;
        } else if (jComboBox4.getSelectedIndex() == 1) {
            sumSelection = -1;
            avgSelection = 1;
        }
        // Set "file export" button 
        if (fileTypeSelection == 0) {
            PivotTree tree = new PivotTree(fieldSelection, DataSet.getDataSet());
            LabelTree row = new LabelTree(rowSelection, DataSet.getDataSet());
            LabelTree col = new LabelTree(colSelection, DataSet.getDataSet());
            String[][] outputArray = TablePrinter.printToArray(tree, row, col, sumSelection, avgSelection);
            HTMLFileGenerator gen = new HTMLFileGenerator(jTextField1.getText());
            gen.generateHTML(outputArray, jTextField1.getText(), rowSelection.length, colSelection.length);
             // add .html
            String pass = jTextField1.getText()+".html";
            try {
		Open.openSesame(pass);
	    } catch (IOException e1) {
	    // TODO Auto-generated catch block
		e1.printStackTrace();
	    }
        } else if (fileTypeSelection == 1) {
            PivotTree tree = new PivotTree(fieldSelection, DataSet.getDataSet());
            LabelTree row = new LabelTree(rowSelection, DataSet.getDataSet());
            LabelTree col = new LabelTree(colSelection, DataSet.getDataSet());
            String[][] outputArray = TablePrinter.printToArray(tree, row, col, sumSelection, avgSelection);
            TablePrinter.printCsvDoc(outputArray, jTextField1.getText());
            String pass = jTextField1.getText()+".csv";
            try {
		Open.openSesame(pass);
            } catch (IOException e1) {
            // TODO Auto-generated catch block
		e1.printStackTrace();
            }
        } else if (fileTypeSelection == 2) {
            PivotTree tree = new PivotTree(fieldSelection, DataSet.getDataSet());
            LabelTree row = new LabelTree(rowSelection, DataSet.getDataSet());
            LabelTree col = new LabelTree(colSelection, DataSet.getDataSet());
            String[][] outputArray = TablePrinter.printToArray(tree, row, col, sumSelection, avgSelection);
            HTMLFileGenerator gen = new HTMLFileGenerator(jTextField1.getText());
            gen.generateHTML(outputArray, jTextField1.getText(), rowSelection.length, colSelection.length);
            TablePrinter.printCsvDoc(outputArray, jTextField1.getText());
            String pass1 = jTextField1.getText()+".html";
            String pass2 = jTextField1.getText()+".csv";
            try {
		Open.openSesame(pass1);
		Open.openSesame(pass2);
            } catch (IOException e1) {
            // TODO Auto-generated catch block
		e1.printStackTrace();
            }         
        }
    }                                        
    // Set "Change data source" button
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        JFrame dataSourceFrame = new pivotDataSource();
        dataSourceFrame.pack();
        dataSourceFrame.setLocationRelativeTo(null);
        dataSourceFrame.setVisible(true);
        //new pivotDataSource().setVisible(true);
        dispose();
    }                                        
    // Set "view table" button
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Check row selection along with jCheckBox
        List<Integer> fieldArray = new ArrayList<Integer>();
        StringBuilder rowBuilder = new StringBuilder();
        for (int i = 0; i < rowSelection.length; i++) {
            fieldArray.add(rowSelection[i]);
            rowBuilder.append(tableColTitle[rowSelection[i]]);
            rowBuilder.append("  ");
        }
        // Display row selection on the upper part of table
        jLabel9.setText(rowBuilder.toString());
        // Check column selection along with jCheckBox
        StringBuilder colBuilder = new StringBuilder();
        for (int i = 0; i < colSelection.length; i++) {
            fieldArray.add(colSelection[i]);
            colBuilder.append(tableColTitle[colSelection[i]]);
            colBuilder.append("  ");
        }
        // Display row selection on the upper part of table
        jLabel10.setText(colBuilder.toString());
        // Set "fieldSelection" for generating 2D string
        fieldSelection = new int[fieldArray.size()];
        for (int j = 0; j < fieldArray.size(); j++) {
            fieldSelection[j] = fieldArray.get(j);
        }
        // Set aggregation function from user slecltion 
        if (jComboBox4.getSelectedIndex() == 0) {
            sumSelection = 1;
            avgSelection = -1;
        } else if (jComboBox4.getSelectedIndex() == 1) {
            sumSelection = -1;
            avgSelection = -1;
        } else if (jComboBox4.getSelectedIndex() == 2) {
            sumSelection = -1;
            avgSelection = 1;
        }
        PivotTree tree = new PivotTree(fieldSelection, DataSet.getDataSet());
        LabelTree row = new LabelTree(rowSelection, DataSet.getDataSet());
        LabelTree col = new LabelTree(colSelection, DataSet.getDataSet());
        // Generate 2D string for output
        testStringData = TablePrinter.printToArray(tree, row, col, sumSelection, avgSelection);
        String[] tableColTitle3 = new String[testStringData.length];
        jTable1.setModel(new javax.swing.table.DefaultTableModel(testStringData, tableColTitle3));
    }                                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//=======================main() UI part============================================================
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//=======================main() UI part============================================================
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Swing.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Swing.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Swing.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Swing.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame pivotFrame = new Swing();
                pivotFrame.pack();
                pivotFrame.setLocationRelativeTo(null);
                pivotFrame.setVisible(true);
                //new Swing().setVisible(true);
            }
        });
//=====================main() UI part end============================================================

    }

//==========================Swing UI ==============================================================
    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration                   
}

                     
class Open {
	
	static String filePath = "WEBSITES_COURSES.txt";
	
	
    public static void openSesame(String filePath) throws IOException {
        //text file, should be opening in default text editor
    	
        File file = new File(filePath);
        
        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
        
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
        
      //done.
    }// END OF OPENSESAME 
    
    

}// END OF OPEN CLASS
