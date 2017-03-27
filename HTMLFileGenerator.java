import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Helps the HTML service by 
 * 
 * @author Christopher Birkbeck
 *
 */
public class HTMLFileGenerator {
	
	
	private HTMLGenerator htmlGenerator;
	private PrintWriter html;

	public HTMLFileGenerator(String title) {
		htmlGenerator = new HTMLGenerator(title);
	}
	
	/**
	 * Generates the actual file from the datasource and the file name.
	 * 
	 * @param data
	 * @param fileName
	 */
	public void generateHTML(String[][] data, String fileName, int rows, int columns) {
		Queue<String> elements = new LinkedList<String>();
		
		elements = htmlGenerator.generateHTML(data, rows, columns);
		
		try {
			html = new PrintWriter(new FileOutputStream(fileName + ".html"));
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find HTML file. Exiting.");
			System.exit(0);
		}
		
		//System.out.println("Starting write.");
		
		while (!elements.isEmpty()) {
			String element = elements.remove();
			html.println(element);
		}
		
		html.flush();
		
		//System.out.println("Ending write.");
		
	}

	

}
