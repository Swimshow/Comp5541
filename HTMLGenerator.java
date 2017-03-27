import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Creates a HTML file with a table from a given set of 2d array of Strings.
 * 
 * @author Christopher Birkbeck
 *
 */
public class HTMLGenerator {
	
	/* Variables */
	
	// Title shown in the window.
	private String pageTitle;
	// Title shown on the page.
	private String textTitle;
	// Language of the page.
	private String language = "EN";
	// Character encoding of the page.
	private String charset = "UTF-8";

	/**
	 * Defaults to having same page title.
	 */
	public HTMLGenerator() {
		pageTitle = "New Table";
		textTitle = "New Table";
	}
	
	/**
	 * Sets the title of the page.
	 * 
	 * @param title
	 */
	public HTMLGenerator(String title) {
		pageTitle = title;
		textTitle = title;
	}
	
	public HTMLGenerator(String newPageTitle, String newTextTitle) {
		pageTitle = newPageTitle;
		textTitle = newTextTitle;
	}
	
	/**
	 * @return the pageTitle
	 */
	public String getPageTitle() {
		return pageTitle;
	}

	/**
	 * @param pageTitle the pageTitle to set
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	/**
	 * @param textTitle the textTitle to set
	 */
	public void setTextTitle(String textTitle) {
		this.textTitle = textTitle;
	}

	/**
	 * @return the textTitle
	 */
	public String getTextTitle() {
		return textTitle;
	}

	/**
	 * Take a String a data and generates an entire string of web page.
	 * @param data
	 * @return
	 */
	public Queue<String> generateHTML(String[][] data, int rows, int columns) {
		Queue<String> elements = new LinkedList<String>();
		generateStart(elements);
		generateTable(elements, data, rows, columns);
		generateEnd(elements);
		return elements;
	}
	
	/**
	 * Make the first elements of the HTML page.
	 * 
	 * @param html
	 */
	private void generateStart(Queue<String> html) {
		html.add("<!DOCTYPE html>");
		html.add("<html lang=" + language + ">");
		html.add("\t<head>");
		html.add("\t\t<title>" + pageTitle + "</title>");
		html.add("\t\t<meta charset=" + charset + ">");
		
		// The following is an internal style sheet that cannot altered by the user.
		// This will be changed in future iterations.
		
		html.add("\t\t<style>");
		
		// Makes table heading green and the text white.
		html.add("\t\tth {");
		html.add("\t\t\tbackground-color: #4CAF50;");
		html.add("\t\t\tcolor: white;");
		html.add("\t\t}");
		
		// Adds a gray border at the bottom of the table rows.
		html.add("\t\ttd {");
		html.add("\t\t\tborder-bottom: 1px solid #ddd;");
		html.add("\t\t}");
		
		// Creates "zebra-striped" tables for each even row (not counting the header.
		html.add("\t\t\ttr:nth-child(even){background-color: #f2f2f2}");
		html.add("\t\t</style>");
		
		// End of internal style sheet.
		
		html.add("\t</head>");
		html.add("\t<body>");
		html.add("\t\t<center><h1>" + textTitle + "</h1></center>");
	}
	
	/**
	 * Take the base data and put into table with the proper HTML tags.
	 * 
	 * @param html
	 * @param source
	 */
	private void generateTable(Queue<String> html, String[][] source, int rows, int columns) {
		// Makes an internal horizontal scroll bar if the page overflows.
		html.add("\t\t\t<div style=\"overflow-x:auto;\">");
		html.add("\t\t\t<table>");
		for (int i = 0; i < source.length; i++) {
			html.add("\t\t\t\t<tr>");
			for (int j = 0; j < source[i].length; j++) {
				// Makes the first row the header with the HTML tags <th>... </th>.
				// Otherwise, uses the regular <tr>...</tr>.
				if (i < rows) {
					// Add nothing in case there is a null string.
					if (source[i][j] == null) {
						html.add("\t\t\t\t\t<th></th>");
					} else {
						html.add("\t\t\t\t\t<th>" + source[i][j] + "</th>");
					}
				} else {
					// Add nothing in case the column part of the second column.
					if (j < columns) {
						if (i > 0 && i < source.length - 1) {
							// If the label is a duplicate, do not add it.
							if (source[i - 1][j] != null && source[i - 1][j].equals(source[i][j])) {
								html.add("\t\t\t\t\t<td></td>");
							} else {
								html.add("\t\t\t\t\t<td>" + source[i][j] + "</td>");
							}
						}
					} else {
						html.add("\t\t\t\t\t<td>" + source[i][j] + "</td>");
					}
				}
			}
			html.add("\t\t\t\t</tr>");
		}
		html.add("\t\t\t</table>");
		html.add("</div>");
	}
	
	/**
	 * Generates the final elements of the HTML page.
	 * 
	 * @param html
	 */
	private void generateEnd(Queue<String> html) {
		html.add("\t\t<p>Table generated on " + LocalDate.now() + "</p>");
		html.add("\t</body>");
		html.add("</html>");
	}
	
}
