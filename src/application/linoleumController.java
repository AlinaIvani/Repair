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

public class linoleumController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButtonLI;
    
    @FXML
    private Button ButtonOut;
    
    @FXML
    private Button ButtonSave;

    @FXML
    private Label LableResultLI;
    
    @FXML
    private Label LableResult1LI;

    @FXML
    private Button ResultButtonLI;

    @FXML
    private TextField TextFieldLenght1LI;

    @FXML
    private TextField TextFieldLenghtLI;
    
    @FXML
    private TextField TextFieldLCosLI;

    @FXML
    private TextField TextFieldWidth1LI;

    @FXML
    private TextField TextFieldWidthLI;
    
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
    	BackButtonLI.setOnAction(event -> {
    		BackButtonLI.getScene().getWindow().hide();
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Sample.fxml"));  
    	    Stage stage = new Stage();
    	    try {
				stage.setScene(new Scene((Parent) loader.load()));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	    stage.show();
    	});
    	
    	TextFieldLenght1LI.setTextFormatter(new TextFormatter<String>(integerFilter)); 
    	TextFieldLenghtLI.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldWidth1LI.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldWidthLI.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldLCosLI.setTextFormatter(new TextFormatter<String>(integerFilter));
    	
    	ButtonSave.setOnAction(event -> { //функция сохранения
    		try {
    			File file = new File("file3.txt");
    			if (!file.exists())
    				file.createNewFile();
    			
    			PrintWriter pw = new PrintWriter(file);
    			pw.println(TextFieldWidthLI.getText());
    			pw.println(TextFieldLenghtLI.getText());
    			pw.println(TextFieldWidth1LI.getText());
    			pw.println(TextFieldLenght1LI.getText());
    			pw.println(TextFieldLCosLI.getText());
    			
    			pw.close();
    			
    		} catch (IOException e) {}
    	});
    	
    	ButtonOut.setOnAction(event -> { //функция вывода
    		try {
    			BufferedReader br = null;
    			br = new BufferedReader(new FileReader("file3.txt"));
    		
    			TextFieldWidthLI.setText(br.readLine());
    			TextFieldLenghtLI.setText(br.readLine());
    			TextFieldWidth1LI.setText(br.readLine());
    			TextFieldLenght1LI.setText(br.readLine());
    			TextFieldLCosLI.setText(br.readLine());
    			
    			br.close();
    			
    		} catch (IOException e) {}
    	});
    	
    	
    	ResultButtonLI.setOnAction(event -> Calk()); //запуск функции при нажатии на кнопку
    	
    }
    
    private void Calk() { 
    	
    	try {
    	float a = Float.parseFloat(TextFieldWidthLI.getText()); //присвоение переменных текстовым полям
    	float b = Float.parseFloat(TextFieldLenghtLI.getText());
    	float c = Float.parseFloat(TextFieldWidth1LI.getText());
    	float d = Float.parseFloat(TextFieldLenght1LI.getText());
    	float e = Float.parseFloat(TextFieldLCosLI.getText());
    	float result1 = (a*b)/(c*d); //работа с переменными
    	float result2 = c*d*e;
    	
    	LableResultLI.setText(String.valueOf(Math.ceil(result1))); //вывод результатов
    	LableResult1LI.setText(String.valueOf(result2));}
    	catch (NumberFormatException e1) {};
 	
    }

}
