package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.stage.Stage;

public class wallController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButtonW;
    
    @FXML
    private Button ButtonOut;
    
    @FXML
    private Button ButtonSave;

    @FXML
    private Label LableResultW;
    
    @FXML
    private Label LableResult1W;

    @FXML
    private Button ResultButtonW;

    @FXML
    private TextField TextFieldHeight;
    
    @FXML
    private TextField TextFieldCos;

    @FXML
    private TextField TextFieldLenght1W;

    @FXML
    private TextField TextFieldLenghtW;

    @FXML
    private TextField TextFieldRepeat;

    @FXML
    private TextField TextFieldWidth1W;

    @FXML
    private TextField TextFieldWidthW;
    
    @FXML
    UnaryOperator<Change> integerFilter = change -> {
        String input = change.getText();
        if (input.matches("[0-9.]*") || change.isDeleted()) { 
            return change;
        }
        return null;
    };

    @FXML
    void initialize() {
    	BackButtonW.setOnAction(event -> {
    		BackButtonW.getScene().getWindow().hide();
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Sample.fxml"));  
    	    Stage stage = new Stage();
    	    try {
				stage.setScene(new Scene((Parent) loader.load()));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	    stage.show();
    	});
    	
    	TextFieldHeight.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldLenght1W.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldLenghtW.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldRepeat.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldWidth1W.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldWidthW.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldCos.setTextFormatter(new TextFormatter<String>(integerFilter));
    	
    	ButtonSave.setOnAction(event -> {
    		try {
    			File file = new File("file8.txt");
    			if (!file.exists())
    				file.createNewFile();
    			
    			PrintWriter pw = new PrintWriter(file);
    			pw.println(TextFieldWidthW.getText());
    			pw.println(TextFieldLenghtW.getText());
    			pw.println(TextFieldHeight.getText());
    			pw.println(TextFieldWidth1W.getText());
    			pw.println(TextFieldLenght1W.getText());
    			pw.println(TextFieldRepeat.getText());
    			pw.println(TextFieldCos.getText());
    			
    			pw.close();
    			
    		} catch (IOException e) {}
    	});
    	
    	ButtonOut.setOnAction(event -> {
    		try {
    			BufferedReader br = null;
    			br = new BufferedReader(new FileReader("file8.txt"));
    		
    			TextFieldWidthW.setText(br.readLine());
    			TextFieldLenghtW.setText(br.readLine());
    			TextFieldHeight.setText(br.readLine());
    			TextFieldWidth1W.setText(br.readLine());
    			TextFieldLenght1W.setText(br.readLine());
    			TextFieldRepeat.setText(br.readLine());
    			TextFieldCos.setText(br.readLine());
    			
    			br.close();
    			
    		} catch (IOException e) {}
    	});
    	
    	ResultButtonW.setOnAction(event -> Calk());
    	 
    }
    
    private void Calk() {
    
    	try {
    	float a = Float.parseFloat(TextFieldWidthW.getText());
    	float b = Float.parseFloat(TextFieldLenghtW.getText());
    	float c = Float.parseFloat(TextFieldHeight.getText());
    	float d = Float.parseFloat(TextFieldWidth1W.getText());
    	float e = Float.parseFloat(TextFieldLenght1W.getText());
    	float f = Float.parseFloat(TextFieldRepeat.getText());
    	float g = Float.parseFloat(TextFieldCos.getText());
    	
    	float res1 = (float) (c+(f/100)+0.1);
    	float res2 = ((a+b)*2)/(d/100);
    	float res3 = e/res1;
    	float result = (float) (Math.ceil(res2) / Math.floor(res3));
    	float result1 = (float) (Math.ceil(result)*g);
    	
    	LableResultW.setText(String.valueOf(Math.ceil(result))); 
    	LableResult1W.setText(String.valueOf(result1));}
    	catch (NumberFormatException e1) {};

}
}
