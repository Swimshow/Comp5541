/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aikendateformatapps;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author aiken
 */
public class AikenDateFormatApps extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(grid, 300, 275);

        Text scenetitle = new Text("Date Format Exchange");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Date:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Button DCButton = new Button("Change Date Format");
        HBox hbox = new HBox(10);
        hbox.getChildren().add(DCButton);
        grid.add(hbox, 1, 4);
        final Text taxMessage = new Text();
        grid.add(taxMessage, 1, 6);
        
        DCButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // Declare ds as user input of date
                String ds = userTextField.getText();
                // old date format as "Date Format" (dformat) 
                SimpleDateFormat dformat = new SimpleDateFormat("MM/d/yy h:mm aa", Locale.ENGLISH);
                // new date format as "Changed Date Format" (cdformat) 
                SimpleDateFormat cdformat = new SimpleDateFormat("EEE MMM d kk:mm:ss yyyy", Locale.ENGLISH);
                // Declare a Gregorian date format as default when user input is null 
                GregorianCalendar calender =new GregorianCalendar();
                Date date = new Date (calender.getTimeInMillis());
                // Parse input string to date
                try {
                    date = dformat.parse(ds);
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }
               // show the new date format 
               taxMessage.setText(date.toString());
            }
        });

        primaryStage.setTitle("Aiken's Date Format Change");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
