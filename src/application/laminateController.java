package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class laminateController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private CheckBox CheckBox1;

    @FXML
    private CheckBox CheckBox2;

    @FXML
    private Button BackButtonLA;
    
    @FXML
    private Button ButtonOut;
    
    @FXML
    private Button ButtonSave;
    
    @FXML
    private ImageView img;

    @FXML
    private Label LabelResult1LA;
    
    @FXML
    private Label LabelResult2LA;

    @FXML
    private Label LabelResultLA;

    @FXML
    private Label LabelResultPU;

    @FXML
    private Button ResultButtonLA;
    
    @FXML
    private Button ResultButtonLA1;
    
    @FXML
    private Button ResultButtonLA11;

    @FXML
    private TextField TextFieldExpPU;
    
    @FXML
    private TextField TextFieldCosPL;

    @FXML
    private TextField TextFieldIndent;

    @FXML
    private TextField TextFieldLenght1LA;

    @FXML
    private TextField TextFieldLenghtLA;

    @FXML
    private TextField TextFieldLenghtPU;

    @FXML
    private TextField TextFieldQuaPL;

    @FXML
    private TextField TextFieldThickPU;

    @FXML
    private TextField TextFieldWeightPU;

    @FXML
    private TextField TextFieldWidthL1A;

    @FXML
    private TextField TextFieldWidthLA;

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
    	
    	BackButtonLA.setOnAction(event -> {
    		BackButtonLA.getScene().getWindow().hide();
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
    	TextFieldIndent.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldLenght1LA.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldLenghtLA.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldLenghtPU.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldQuaPL.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldThickPU.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldWeightPU.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldWidthL1A.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldWidthLA.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldWidthPU.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldCosPL.setTextFormatter(new TextFormatter<String>(integerFilter));
    	
    	ButtonSave.setOnAction(event -> {
    		try {
    			File file = new File("file1.txt");
    			if (!file.exists())
    				file.createNewFile();
    			
    			PrintWriter pw = new PrintWriter(file);
    			pw.println(TextFieldWidthLA.getText());
    			pw.println(TextFieldLenghtLA.getText());
    			pw.println(TextFieldIndent.getText());
    			pw.println(TextFieldWidthL1A.getText());
    			pw.println(TextFieldLenght1LA.getText());
    			pw.println(TextFieldQuaPL.getText());
    			pw.println(TextFieldCosPL.getText());
    			
    			pw.close();
    			
    		} catch (IOException e) {}
    	});
    	
    	ButtonOut.setOnAction(event -> {
    		try {
    			BufferedReader br = null;
    			br = new BufferedReader(new FileReader("file1.txt"));
    		
    			TextFieldWidthLA.setText(br.readLine());
    			TextFieldLenghtLA.setText(br.readLine());
    			TextFieldIndent.setText(br.readLine());
    			TextFieldWidthL1A.setText(br.readLine());
    			TextFieldLenght1LA.setText(br.readLine());
    			TextFieldQuaPL.setText(br.readLine());
    			TextFieldCosPL.setText(br.readLine());
    			
    			br.close();
    			
    		} catch (IOException e) {}
    	});
    	
    	ResultButtonLA.setOnAction(event -> Calk());
   
    }

private void Calk() {
	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	
    	try {
    	float a = Float.parseFloat(TextFieldWidthLA.getText());
    	float b = Float.parseFloat(TextFieldLenghtLA.getText());
    	float c = Float.parseFloat(TextFieldIndent.getText());
    	float d = Float.parseFloat(TextFieldWidthL1A.getText());
    	float e = Float.parseFloat(TextFieldLenght1LA.getText());
    	float f = Float.parseFloat(TextFieldQuaPL.getText());
    	float g = Float.parseFloat(TextFieldCosPL.getText());
    	float result1, result2, result3;
    	
    	if (CheckBox1.isSelected() & CheckBox2.isSelected()) {//обработка исключительных ситуаций
    		alert.setTitle("Ошибка");
    		alert.setHeaderText(null);
    		alert.setContentText("Нельзя выбрать два способа!");
    		
    		alert.showAndWait();
    	}
    	else if (CheckBox1.isSelected()) { //расчет если галочка в первом квадрате
    		result1 = ((a*b)-((c*c)/1000000))/((d*e)/1000000);
    		result2 = result1/f;
    		result3 = (float) (Math.ceil(result2)*g);
    		LabelResultLA.setText(String.valueOf(Math.ceil(result1)));
        	LabelResult1LA.setText(String.valueOf(Math.ceil(result2)));
        	LabelResult2LA.setText(String.valueOf(result3));
			try { //вывод изображения при выполнении расчетов
    	        Image image;
				image = new Image(getClass().getResource("../assets/shemy_ukladki1.jpg").toURI().toString());
				img.setImage(image);
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
    	}
    	else if (CheckBox2.isSelected()) { //расчет если галочка во втором квадрате
    		result1 = (float) ((((a*b)-((c*c)/1000000))/((d*e)/1000000))*1.1);
    		result2 = result1/f;
    		result3 = (float) (Math.ceil(result2)*g);
    		LabelResultLA.setText(String.valueOf(Math.ceil(result1)));
        	LabelResult1LA.setText(String.valueOf(Math.ceil(result2)));
        	LabelResult2LA.setText(String.valueOf(result3));
			try {
    	        Image image;
				image = new Image(getClass().getResource("../assets/shemy_ukladki2.jpg").toURI().toString());
				img.setImage(image);
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
    	}
    	else {//сообщение об ошибке
    		alert.setTitle("Ошибка");
    		alert.setHeaderText(null);
    		alert.setContentText("Выберите способ укладки!");
    		
    		alert.showAndWait();
    	}}
    	catch (NumberFormatException e1) {};
    	
    }
}







