/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneexpressionanalysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author aiken
 */
public class GeneExpressionAnalysis extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Gene Expression level Analysis");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        //Label userName = new Label("User Name:");
        //grid.add(userName, 0, 1);
        TextField userTextField = new TextField();
        grid.add(userTextField, 0, 1);

        Button btn = new Button("Select Input File");

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 1);
        //hbBtn.getChildren().add(cancel);
        //grid.add(hbBtn, 1, 4);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File("."));
                fc.setFileFilter(new javax.swing.filechooser.FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return f.getName().toLowerCase().endsWith(".xls")
                                || f.getName().toLowerCase().endsWith(".xlsx")
                                || f.isDirectory();
                    }

                    @Override
                    public String getDescription() {
                        return "Excel files";
                    }
                });
                int r = fc.showOpenDialog(new JFrame());
                if (r == JFileChooser.APPROVE_OPTION) {
                    String name = fc.getSelectedFile().getName();
                    userTextField.setText(name);
                    //System.out.println(name);
                }
                //int returnValue = fc.showOpenDialog(aComponent);
                File file = fc.getSelectedFile();
                //String excelFilePath = "autophagy_test.xls";
                String excelFilePath = file.getName();
                FileInputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(new File(excelFilePath));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GeneExpressionAnalysis.class.getName()).log(Level.SEVERE, null, ex);
                }
                Workbook wb_xssf = null; //Declare XSSF WorkBook for .xlsx file
                Workbook wb_hssf = null; //Declare HSSF WorkBook for .xls file
                Sheet firstSheet = null; //sheet can be used as common for XSSF and HSSF WorkBook

                if (file.getName().toLowerCase().endsWith(".xls")) {
                    try {
                        wb_hssf = new HSSFWorkbook(inputStream);
                    } catch (IOException ex) {
                        Logger.getLogger(GeneExpressionAnalysis.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int sheetNum = wb_hssf.getNumberOfSheets();
                    firstSheet = wb_hssf.getSheetAt(0);
                    int rowNum_ref = firstSheet.getPhysicalNumberOfRows();
                    int colNum_ref = firstSheet.getRow(0).getLastCellNum(); // get the column number from a sheet
                    Iterator<Row> iterator = firstSheet.iterator();
                    ArrayList<String> geneList_ref = new ArrayList<>(); // gene name list
                    ArrayList<Double> geneValue_ref = new ArrayList<>(); // gene expression level value
                    while (iterator.hasNext()) {
                        Row nextRow = iterator.next();
                        Iterator<Cell> cellIterator = nextRow.cellIterator();
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();

                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    System.out.print(cell.getStringCellValue());
                                    geneList_ref.add(cell.getStringCellValue());
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    System.out.print(cell.getNumericCellValue());
                                    geneValue_ref.add(cell.getNumericCellValue());
                                    break;
                            }
                            System.out.print(" _ ");
                        }
                        System.out.println();
                    }
                    for (int i = 1; i < sheetNum; i++) {
                        Sheet currentSheet = wb_hssf.getSheetAt(i);
                        int rowNum_current = currentSheet.getPhysicalNumberOfRows();  // get the column number from the current sheet               
                        ArrayList<String> geneList_current = new ArrayList<>(); // creat gene name list for the current sheet               
                        Iterator<Row> iterator_current = currentSheet.iterator();
                        while (iterator_current.hasNext()) {
                            Row nextRow = iterator_current.next();
                            Iterator<Cell> cellIterator = nextRow.cellIterator();
                            while (cellIterator.hasNext()) {
                                Cell cell = cellIterator.next();
                                geneList_current.add(cell.getStringCellValue()); // assign gene name for the current gene list
                                break;
                            }
                        }
                        int rowIndex = 0;
                        for (int j = 0; j < rowNum_current; j++) {   // comparing referance value and inquery gene name
                            ArrayList<Double> geneValue_current = new ArrayList<>(); // gene expression level value list
                            Row row = currentSheet.createRow(rowIndex++);
                            for (int k = 0; k < rowNum_ref; k++) {
                                if (geneList_current.get(j).equals(geneList_ref.get(k))) {
                                    geneValue_current.add(geneValue_ref.get(k)); // assign gene expression value to the current sheet
                                    row.createCell(0).setCellValue(geneList_current.get(j));
                                    row.createCell(1).setCellValue(geneValue_ref.get(k));
                                }
                            }
                        }
                    }
                    //String newFileName = file.getName();
                    //newFileName = newFileName.replace(".xls", "");
                    //String filePath = ("C:\\Users\\aiken\\Documents\\NetBeansProjects\\GeneExpressionAnalysis\\" + newFileName + "_v1.xls");//"C:\\Users\\aiken\\Documents\\NetBeansProjects\\JavaExcelApp\\autophagy_test.xls";
                    Path newFilePath = file.toPath();
                    String fPath = newFilePath.toString();
                    fPath = fPath.replace(".xls", "");
                    String filePath = (fPath+"_v1.xls");
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(filePath);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(GeneExpressionAnalysis.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        wb_hssf.write(fos);
                    } catch (IOException ex) {
                        Logger.getLogger(GeneExpressionAnalysis.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        wb_hssf.close();
                    } catch (IOException ex) {
                        Logger.getLogger(GeneExpressionAnalysis.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(GeneExpressionAnalysis.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.exit(0);
                } else if (file.getName().toLowerCase().endsWith(".xlsx")) {
                    try {
                        //workbook = new XSSFWorkbook(inputStream);
                        wb_xssf = new XSSFWorkbook(inputStream);
                    } catch (IOException ex) {
                        Logger.getLogger(GeneExpressionAnalysis.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int sheetNum = wb_xssf.getNumberOfSheets();
                    firstSheet = wb_xssf.getSheetAt(0);
                    int rowNum_ref = firstSheet.getPhysicalNumberOfRows();
                    int colNum_ref = firstSheet.getRow(0).getLastCellNum(); // get the column number from a sheet
                    ArrayList<String> geneList_ref = new ArrayList<>(); // gene name list
                    ArrayList<Double> geneValue_ref = new ArrayList<>(); // gene expression level value
                    Iterator<Row> iterator = firstSheet.iterator();

                    while (iterator.hasNext()) {
                        Row nextRow = iterator.next();
                        Iterator<Cell> cellIterator = nextRow.cellIterator();
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();

                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    System.out.print(cell.getStringCellValue());
                                    geneList_ref.add(cell.getStringCellValue());
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    System.out.print(cell.getNumericCellValue());
                                    geneValue_ref.add(cell.getNumericCellValue());
                                    break;
                            }
                            System.out.print(" : ");
                        }
                        System.out.println();
                    }
                    for (int i = 1; i < sheetNum; i++) {
                        Sheet currentSheet = wb_xssf.getSheetAt(i);
                        int rowNum_current = currentSheet.getPhysicalNumberOfRows();  // get the column number from the current sheet               
                        ArrayList<String> geneList_current = new ArrayList<>(); // creat gene name list for the current sheet               
                        Iterator<Row> iterator_current = currentSheet.iterator();
                        while (iterator_current.hasNext()) {
                            Row nextRow = iterator_current.next();
                            Iterator<Cell> cellIterator = nextRow.cellIterator();
                            while (cellIterator.hasNext()) {
                                Cell cell = cellIterator.next();
                                geneList_current.add(cell.getStringCellValue()); // assign gene name for the current gene list
                                break;
                            }
                        }
                        int rowIndex = 0;
                        for (int j = 0; j < rowNum_current; j++) {   // comparing referance value and inquery gene name
                            ArrayList<Double> geneValue_current = new ArrayList<>(); // gene expression level value list
                            Row row = currentSheet.createRow(rowIndex++);
                            for (int k = 0; k < rowNum_ref; k++) {
                                if (geneList_current.get(j).equals(geneList_ref.get(k))) {
                                    geneValue_current.add(geneValue_ref.get(k)); // assign gene expression value to the current sheet
                                    row.createCell(0).setCellValue(geneList_current.get(j));
                                    row.createCell(1).setCellValue(geneValue_ref.get(k));
                                }
                            }
                        }

                    }
                    //String newFileName = file.getName();
                    //newFileName = newFileName.replace(".xlsx", "");
                    //String filePath = ("C:\\Users\\aiken\\Documents\\NetBeansProjects\\GeneExpressionAnalysis\\" + newFileName + "_v1.xlsx");//"C:\\Users\\aiken\\Documents\\NetBeansProjects\\JavaExcelApp\\autophagy_test.xlsx";
                    Path newFilePath = file.toPath();
                    String fPath = newFilePath.toString();
                    fPath = fPath.replace(".xlsx", "");
                    String filePath = (fPath+"_v1.xlsx");
                    //String filePath = (newFilePath.toString()+"_v1.xlsx");
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(filePath);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(GeneExpressionAnalysis.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        wb_xssf.write(fos);
                    } catch (IOException ex) {
                        Logger.getLogger(GeneExpressionAnalysis.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        wb_xssf.close();
                    } catch (IOException ex) {
                        Logger.getLogger(GeneExpressionAnalysis.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(GeneExpressionAnalysis.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.exit(0);
                }
            }
        });
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Nephrology Lab Welcome");
        primaryStage.show();
        /*if (primaryStage.isShowing()){
            System.exit(0);
        }*/
        System.out.println(primaryStage.isShowing());
       // JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }

}
