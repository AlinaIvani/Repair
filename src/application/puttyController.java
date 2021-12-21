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

public class puttyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButtonPU;
    
    @FXML
    private Button ButtonOut;
    
    @FXML
    private Button ButtonSave;

    @FXML
    private Label LabelResult2PU;
    
    @FXML
    private Label LabelResult3PU;

    @FXML
    private Label LabelResultPU;

    @FXML
    private Button ResultButtonPU;

    @FXML
    private TextField TextFieldExpPU;
    
    @FXML
    private TextField TextFieldCosPU;

    @FXML
    private TextField TextFieldLenghtPU;

    @FXML
    private TextField TextFieldThickPU;

    @FXML
    private TextField TextFieldWeightPU;

    @FXML
    private TextField TextFieldWidthPU;
    
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
    	BackButtonPU.setOnAction(event -> {
    		BackButtonPU.getScene().getWindow().hide();
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Sample.fxml"));  
    	    Stage stage = new Stage();
    	    try {
				stage.setScene(new Scene((Parent) loader.load()));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	    stage.show();
    	});
    	
    	TextFieldExpPU.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldLenghtPU.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldThickPU.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldWeightPU.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldWidthPU.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldCosPU.setTextFormatter(new TextFormatter<String>(integerFilter));
    	
    	ButtonSave.setOnAction(event -> {
    		try {
    			File file = new File("file6.txt");
    			if (!file.exists())
    				file.createNewFile();
    			
    			PrintWriter pw = new PrintWriter(file);
    			pw.println(TextFieldWidthPU.getText());
    			pw.println(TextFieldLenghtPU.getText());
    			pw.println(TextFieldThickPU.getText());
    			pw.println(TextFieldExpPU.getText());
    			pw.println(TextFieldWeightPU.getText());
    			pw.println(TextFieldCosPU.getText());
    			
    			pw.close();
    			
    		} catch (IOException e) {}
    	});
    	
    	ButtonOut.setOnAction(event -> {
    		try {
    			BufferedReader br = null;
    			br = new BufferedReader(new FileReader("file6.txt"));
    		
    			TextFieldWidthPU.setText(br.readLine());
    			TextFieldLenghtPU.setText(br.readLine());
    			TextFieldThickPU.setText(br.readLine());
    			TextFieldExpPU.setText(br.readLine());
    			TextFieldWeightPU.setText(br.readLine());
    			TextFieldCosPU.setText(br.readLine());
    			
    			br.close();
    			
    		} catch (IOException e) {}
    	});
    	
    	ResultButtonPU.setOnAction(event -> Calk());
 

    }
    
    private void Calk() {
    	
    	try {
    	float a = Float.parseFloat(TextFieldLenghtPU.getText());
    	float b = Float.parseFloat(TextFieldWidthPU.getText());
    	float c = Float.parseFloat(TextFieldThickPU.getText());
    	float d = Float.parseFloat(TextFieldExpPU.getText());
    	float e = Float.parseFloat(TextFieldWeightPU.getText());
    	float f = Float.parseFloat(TextFieldCosPU.getText());
    	
    	float result1 = a*b*c*d;
    	float result2 = result1/e;
    	float result3 = (float) (Math.ceil(result2)*f);
    	
    	LabelResultPU.setText(String.valueOf(result1));
    	LabelResult2PU.setText(String.valueOf(Math.ceil(result2)));
    	LabelResult3PU.setText(String.valueOf(result3));}
    	catch (NumberFormatException e1) {};
    	
    }

}
