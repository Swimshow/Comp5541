
package dataretrieval01;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 *
 * @author aiken
 */
public class DataRetrievalTable {
    public static void main(String[] args) {
      //String html = "http://www.proteinatlas.org/ENSG00000154975-CA10/tissue/kidney";
      String html = "http://www.proteinatlas.org/ENSG00000154975-CA10/cell";
      try {
         Document doc = Jsoup.connect(html).get();
         Elements tableElements = doc.select("table");

         //Elements tableHeaderEles = tableElements.select("thead tr th");
         /*System.out.println("headers");
         for (int i = 0; i < tableHeaderEles.size(); i++) {
            System.out.println(tableHeaderEles.get(i).text());
         }
         System.out.println();*/

         Elements tableRowElements = tableElements.select(":not(thead) tr");

         for (int i = 0; i < tableRowElements.size(); i++) {
            Element row = tableRowElements.get(i);
            System.out.println("row");
            Elements rowItems = row.select("td");
            for (int j = 0; j < rowItems.size(); j++) {
               System.out.println(rowItems.get(j).text());
            }
            System.out.println();
         }

      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
