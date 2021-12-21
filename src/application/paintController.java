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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class paintController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;
    
    @FXML
    private Button ButtonOut;
    
    @FXML
    private Button ButtonSave;

    @FXML
    private ChoiceBox<String> ChoiceBoxType;

    @FXML
    private Label LabelResult;

    @FXML
    private Label LabelResult1;
    
    @FXML
    private Label LabelResult2;
    
    @FXML
    private Button ResultButton;
    
    @FXML
    private ImageView img;

    @FXML
    private TextField TextFieldExp;
    
    @FXML
    private TextField TextFieldCos;
    
    @FXML
    private TextField TextFieldE;

    @FXML
    private TextField TextFieldLenght;

    @FXML
    private TextField TextFieldQua;

    @FXML
    private TextField TextFieldQua1;
    
    @FXML
    private TextField TextFieldWidth;
    
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
    	
    	BackButton.setOnAction(event -> {
    		BackButton.getScene().getWindow().hide();
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Sample.fxml"));  
    	    Stage stage = new Stage();
    	    try {
				stage.setScene(new Scene((Parent) loader.load()));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	    stage.show();
    	});
    	
    	ChoiceBoxType.getItems().removeAll(ChoiceBoxType.getItems());
    	ChoiceBoxType.getItems().addAll("Штукатурка/шпатлевка", "Металл", "Дерево");//создание выпадающего списка
    	
    	TextFieldExp.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldLenght.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldQua.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldWidth.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldCos.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldQua1.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldE.setTextFormatter(new TextFormatter<String>(integerFilter));
    	
    	ButtonSave.setOnAction(event -> {
    		try {
    			File file = new File("file4.txt");
    			if (!file.exists())
    				file.createNewFile();
    			
    			PrintWriter pw = new PrintWriter(file);
    			pw.println(TextFieldWidth.getText());
    			pw.println(TextFieldLenght.getText());
    			pw.println(TextFieldE.getText());
    			pw.println(TextFieldExp.getText());
    			pw.println(TextFieldQua.getText());
    			pw.println(TextFieldQua1.getText());
    			pw.println(TextFieldCos.getText());
    			
    			pw.close();
    			
    		} catch (IOException e) {}
    	});
    	
    	ButtonOut.setOnAction(event -> {
    		try {
    			BufferedReader br = null;
    			br = new BufferedReader(new FileReader("file4.txt"));
    		
    			TextFieldWidth.setText(br.readLine());
    			TextFieldLenght.setText(br.readLine());
    			TextFieldE.setText(br.readLine());
    			TextFieldExp.setText(br.readLine());
    			TextFieldQua.setText(br.readLine());
    			TextFieldQua1.setText(br.readLine());
    			TextFieldCos.setText(br.readLine());
    			br.close();
    			
    		} catch (IOException e) {}
    	});
    	
    	ChoiceBoxType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		if ("Штукатурка/шпатлевка".equals(newValue)) {
    			TextFieldE.setText(String.valueOf("0"));//ввод числа в текстовое поле при нажатии
				try { //вывод изображение при нажатии
	    	        Image image;
					image = new Image(getClass().getResource("../assets/shtukaturka.jpg").toURI().toString());
					img.setImage(image);
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
    		}
    		else if ("Металл".equals(newValue)) {
    			TextFieldE.setText(String.valueOf("20"));
				try {
					Image image;
					image = new Image(getClass().getResource("../assets/metal.png").toURI().toString());
					img.setImage(image);
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
    		}
    		else {
    			TextFieldE.setText(String.valueOf("10"));
				try {
	    	        Image image;
					image = new Image(getClass().getResource("../assets/derevo.png").toURI().toString());
					img.setImage(image);
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
    		}
    	});
    	
    	ResultButton.setOnAction(event -> Calk());
    	
    }
    
    private void Calk() {
    	
    	try {
    	float a = Float.parseFloat(TextFieldWidth.getText());
    	float b = Float.parseFloat(TextFieldLenght.getText());
    	float c = Float.parseFloat(TextFieldExp.getText());
    	float d = Float.parseFloat(TextFieldQua.getText());
    	float e = Float.parseFloat(TextFieldE.getText());
    	float f = Float.parseFloat(TextFieldQua1.getText());
    	float g = Float.parseFloat(TextFieldCos.getText());
    	
    	float result = (a*b*c*d)*(1-(e/100));
    	float result1 = result/f;
    	float result2 = (float) (Math.ceil(result1)*g);
    	
    	LabelResult.setText(String.valueOf(result));
    	LabelResult1.setText(String.valueOf(Math.ceil(result1)));
    	LabelResult2.setText(String.valueOf(result2));}
    	catch (NumberFormatException e1) {};
    	
    }
}

