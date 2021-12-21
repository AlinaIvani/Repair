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

public class tileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButtonPI;
    
    @FXML
    private Button ButtonOut;
    
    @FXML
    private Button ButtonSave;

    @FXML
    private Label LabelResult1PI;

    @FXML
    private Label LabelResultPI;
    
    @FXML
    private Label LabelResult2PI;   

    @FXML
    private Button ResultButtonPI;

    @FXML
    private TextField TextFieldLenght1PI;

    @FXML
    private TextField TextFieldLenghtPI;
    
    @FXML
    private TextField TextFieldCosPI;

    @FXML
    private TextField TextFieldQuaPI;

    @FXML
    private TextField TextFieldWidth1PI;

    @FXML
    private TextField TextFieldWidthPI;
    
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
    	BackButtonPI.setOnAction(event -> {
    		BackButtonPI.getScene().getWindow().hide();
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Sample.fxml"));  
    	    Stage stage = new Stage();
    	    try {
				stage.setScene(new Scene((Parent) loader.load()));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	    stage.show();
    	});
    	
    	TextFieldLenght1PI.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldLenghtPI.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldQuaPI.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldWidth1PI.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldWidthPI.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldCosPI.setTextFormatter(new TextFormatter<String>(integerFilter));
    	
    	ButtonSave.setOnAction(event -> {
    		try {
    			File file = new File("file7.txt");
    			if (!file.exists())
    				file.createNewFile();
    			
    			PrintWriter pw = new PrintWriter(file);
    			pw.println(TextFieldWidthPI.getText());
    			pw.println(TextFieldLenghtPI.getText());
    			pw.println(TextFieldWidth1PI.getText());
    			pw.println(TextFieldLenght1PI.getText());
    			pw.println(TextFieldQuaPI.getText());
    			pw.println(TextFieldCosPI.getText());
    			
    			pw.close();
    			
    		} catch (IOException e) {}
    	});
    	
    	ButtonOut.setOnAction(event -> {
    		try {
    			BufferedReader br = null;
    			br = new BufferedReader(new FileReader("file7.txt"));
    		
    			TextFieldWidthPI.setText(br.readLine());
    			TextFieldLenghtPI.setText(br.readLine());
    			TextFieldWidth1PI.setText(br.readLine());
    			TextFieldLenght1PI.setText(br.readLine());
    			TextFieldQuaPI.setText(br.readLine());
    			TextFieldCosPI.setText(br.readLine());
    			
    			br.close();
    			
    		} catch (IOException e) {}
    	});


    	ResultButtonPI.setOnAction(event -> Calk()); 

    }
    
    private void Calk() {
    	
    	try {
    	float a = Float.parseFloat(TextFieldWidthPI.getText());
    	float b = Float.parseFloat(TextFieldLenghtPI.getText());
    	float c = Float.parseFloat(TextFieldWidth1PI.getText());
    	float d = Float.parseFloat(TextFieldLenght1PI.getText());
    	float e = Float.parseFloat(TextFieldQuaPI.getText());
    	float f = Float.parseFloat(TextFieldCosPI.getText());
    	
    	float res1 = a*b;
    	float res2 = (c*d)/10000;
    	float result1 = res1/res2;
    	float result2 = result1/e;
    	float result3 = (float) (Math.ceil(result2)*f);
    	
    	LabelResultPI.setText(String.valueOf(Math.ceil(result1)));
    	LabelResult1PI.setText(String.valueOf(Math.ceil(result2)));
    	LabelResult2PI.setText(String.valueOf(result3));}
    	catch (NumberFormatException e1) {};
    	
    }

}

