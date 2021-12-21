package application;

import java.io.BufferedReader; //����������� ���������
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.stage.Stage;

public class plasterController { //�������� �����

    @FXML //������������ ������
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButtonPL;
    
    @FXML
    private Button ButtonOut;
    
    @FXML
    private Button ButtonSave;

    @FXML
    private CheckBox ChecBoxPL;

    @FXML
    private ChoiceBox<String> ChoiceBoxTypePL;

    @FXML
    private Label LabelResult1PL;
    
    @FXML
    private Label LabelResult2PL;

    @FXML
    private Label LabelResultPL;

    @FXML
    private Button ResultButtonPL;

    @FXML
    private TextField TextFieldExpPL;

    @FXML
    private TextField TextFieldCosPL;

    @FXML
    private TextField TextFieldLenghtPL;

    @FXML
    private TextField TextFieldThickPL;

    @FXML
    private TextField TextFieldWeightPL;

    @FXML
    private TextField TextFieldWidthPL;
    
    @FXML
    UnaryOperator<Change> integerFilter = change -> { //����������� �����
        String input = change.getText();
        if (input.matches("[0-9.]*") || change.isDeleted()) { 
            return change;
        }
        return null;
    };
    
    @FXML
    void initialize() { //������� �� ������� �����
    	BackButtonPL.setOnAction(event -> {
    		BackButtonPL.getScene().getWindow().hide();
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Sample.fxml"));  
    	    Stage stage = new Stage();
    	    try {
				stage.setScene(new Scene((Parent) loader.load()));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	    stage.show();
    	});
    	
    	  TextFieldExpPL.setTextFormatter(new TextFormatter<String>(integerFilter)); //����������� ������� �����
    	  TextFieldLenghtPL.setTextFormatter(new TextFormatter<String>(integerFilter));
    	  TextFieldThickPL.setTextFormatter(new TextFormatter<String>(integerFilter));
    	  TextFieldWeightPL.setTextFormatter(new TextFormatter<String>(integerFilter));
    	  TextFieldWidthPL.setTextFormatter(new TextFormatter<String>(integerFilter));
    	  TextFieldCosPL.setTextFormatter(new TextFormatter<String>(integerFilter));
    	  
      	ButtonSave.setOnAction(event -> { //���������� ������ � ����
    		try {
    			File file = new File("file5.txt");
    			if (!file.exists())
    				file.createNewFile(); //�������� ����� � ������ ����������
    			
    			PrintWriter pw = new PrintWriter(file);
    			pw.println(TextFieldWidthPL.getText());
    			pw.println(TextFieldLenghtPL.getText());
    			pw.println(TextFieldExpPL.getText());
    			pw.println(TextFieldThickPL.getText());
    			pw.println(TextFieldWeightPL.getText());
    			pw.println(TextFieldCosPL.getText());
    			
    			pw.close();
    			
    		} catch (IOException e) {}
    	});
    	
    	ButtonOut.setOnAction(event -> { //����� ������ �� �����
    		try {
    			BufferedReader br = null;
    			br = new BufferedReader(new FileReader("file5.txt"));
    		
    			TextFieldWidthPL.setText(br.readLine());
    			TextFieldLenghtPL.setText(br.readLine());
    			TextFieldExpPL.setText(br.readLine());
    			TextFieldThickPL.setText(br.readLine());
    			TextFieldWeightPL.setText(br.readLine());
    			TextFieldCosPL.setText(br.readLine());
    			
    			br.close();
    			
    		} catch (IOException e) {}
    	});
    	  
    	  ChoiceBoxTypePL.getItems().removeAll(ChoiceBoxTypePL.getItems());
    	  ChoiceBoxTypePL.getItems().addAll("������������", "��������", "���������", "������"); //�������� � ���������� ����������� ������
    	  
    	  ChoiceBoxTypePL.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> { //����� � ��������� ���� � ����������� �� ������
      		if ("������������".equals(newValue)) {
      			TextFieldExpPL.setText(String.valueOf("3"));
      		}
      		else if ("��������".equals(newValue)) {
      			TextFieldExpPL.setText(String.valueOf("9"));
      		}
      		else if ("���������".equals(newValue)) {
      			TextFieldExpPL.setText(String.valueOf("17"));
      		}
      		else {
      			TextFieldExpPL.setText("");
      		}
      	});
      	
      	ResultButtonPL.setOnAction(event -> Calk()); //������ ������� ��� �������

    	}
    
    private void Calk() {
    	
    	try { //���������� ���������
    	float a = Float.parseFloat(TextFieldWidthPL.getText());
    	float b = Float.parseFloat(TextFieldLenghtPL.getText());
    	float c = Float.parseFloat(TextFieldExpPL.getText());
    	float d = Float.parseFloat(TextFieldThickPL.getText());
    	float e = Float.parseFloat(TextFieldWeightPL.getText());
    	float f = Float.parseFloat(TextFieldCosPL.getText());
    	float result1, result2, result3;
    	
    	if (ChecBoxPL.isSelected()) { //������ ���� ������� �����
    		result1 = (float) ((a*b*c*d)*0.8);
    		result2 = result1/e;
    		result3 = result2*f;
    		
    	}
    	else { //������ ���� ������� ���
    		result1 =  a*b*c*d;
    		result2 = result1/e;
    		result3 = (float) (Math.ceil(result2)*f);
    	}
    	
    	
    	LabelResultPL.setText(String.valueOf(result1)); //����� ����������
    	LabelResult1PL.setText(String.valueOf(Math.ceil(result2)));
    	LabelResult2PL.setText(String.valueOf(result3));}
    	catch (NumberFormatException e1) {};
    	
    }
}

