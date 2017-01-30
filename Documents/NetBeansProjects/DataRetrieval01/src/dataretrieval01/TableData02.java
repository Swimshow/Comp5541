package dataretrieval01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author aiken
 */
public class TableData02 {

    public static void main(String[] args) {
        int arrayListSize = 0;
        // "ensembelID_geneArray" is an ArrayList data structure for storing records of the input text file
            List<String> ensembelID_geneArray = new ArrayList<>();
        try {
            File inputTextFile = new File("AdCytoSK_PCP_Genes.txt");
            // A connection stream connects to the text file
            FileReader fileReader = new FileReader(inputTextFile);
            // A file pointer always points to the text file
            BufferedReader filePointer = new BufferedReader(fileReader);
            // "textWords" is a String data structure for storing words of the input text file
            String ensembelID_gene = filePointer.readLine();            
            while ((ensembelID_gene = filePointer.readLine()) != null) {
                ensembelID_geneArray.add(ensembelID_gene);
            }
            // Specify how many rows are there in the text file records
            arrayListSize = ensembelID_geneArray.size();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Start to web info retrieval
        ArrayList<String> retrievedData = new ArrayList<>();
        
        Document doc;
        for (int i = 0; i < arrayListSize; i++) {
            StringBuilder outPutString = new StringBuilder();
            try {
                outPutString.append(ensembelID_geneArray.get(i));
                outPutString.append(" ");
                doc = Jsoup.connect("http://www.proteinatlas.org/"+ensembelID_geneArray.get(i)+"/tissue/kidney").get();
                for (Element table : doc.select("table[class=noborder nowrap]")) {
                    for (Element row : table.select("tr")) {
                        Elements tds = row.select("td");
                        outPutString.append(tds.get(0).text());
                        outPutString.append(" ");
                        outPutString.append(tds.get(1).text());
                        outPutString.append(" ");
                        System.out.println(tds.get(0).text() + " " + tds.get(1).text() + " ");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // For cell expression info
            /*Document doc1;
            try {
                doc1 = Jsoup.connect("http://www.proteinatlas.org/"+ensembelID_geneArray.get(i)+"/cell").get();
                for (Element table : doc1.select("table[class=light border no_styled_a legend_table wide]")) {
                    //for (Element row : table.select("tr")) {
                    Elements row = table.select("tr");
                    Elements tds = row.select("td");
                    outPutString.append(tds.get(1).text());
                    outPutString.append(" ");
                    System.out.println(tds.get(1).text() + " ");
                    //}
                }
                for (Element table : doc1.select("table[class=border dark round margin_right ]")) {
                    //for (Element row : table.select("tr")) {
                    Elements row = table.select("tr");
                    Elements tds = row.select("td");
                    System.out.println("tds.size: "+ tds.size());
                    if (!tds.isEmpty() && tds.size()>2){
                        
                        outPutString.append(tds.get(2).text());
                        outPutString.append(" ");
                        System.out.println(tds.get(2).text());
                    }
                }               
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            retrievedData.add(outPutString.toString());
        }

        File outputDataFile = new File("podocyteAdCytoSK_PCP_GenesHPA.txt");
        int newArrarListsize = retrievedData.size();
        try {
            BufferedWriter writeBuffer = new BufferedWriter(new FileWriter(outputDataFile));
            //need to add header
            //writeBuffer.write("first_Name; last_Name; companyName; address;city;province; postal; phone; email;");
            writeBuffer.newLine();
            for (int j = 0; j < newArrarListsize; j++) {
                writeBuffer.write(retrievedData.get(j));
                writeBuffer.newLine();
            }
            writeBuffer.close();
            //sortCatalog();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
