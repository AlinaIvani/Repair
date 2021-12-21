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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.stage.Stage;

public class glueController { //�������� �����

    @FXML //������������� ������
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButtonGL;

    @FXML
    private Label LabelResult2GL;

    @FXML
    private Label LabelResultGL;
    
    @FXML
    private Label LabelResult3GL;

    @FXML
    private Button ResultButtonGL;
    
    @FXML
    private Button ButtonOut;
    
    @FXML
    private Button ButtonSave;

    @FXML
    private TextField TextFieldExpGL;
    
    @FXML
    private TextField TextFieldCosGL;

    @FXML
    private TextField TextFieldLenghtGL;

    @FXML
    private TextField TextFieldThickGL;

    @FXML
    private TextField TextFieldWeightGL;

    @FXML
    private TextField TextFieldWidthGL;
    
    @FXML
    UnaryOperator<Change> integerFilter = change -> { //������ �����
        String input = change.getText();
        if (input.matches("[0-9.]*") || change.isDeleted()) { 
            return change;
        }
        return null;
    };

    @FXML
    void initialize() { //������� �� ������� �����
    	BackButtonGL.setOnAction(event -> {
    		BackButtonGL.getScene().getWindow().hide();
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Sample.fxml"));  
    	    Stage stage = new Stage();
    	    try {
				stage.setScene(new Scene((Parent) loader.load()));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	    stage.show();
    	});
    	
    	TextFieldExpGL.setTextFormatter(new TextFormatter<String>(integerFilter)); //����������� �������
    	TextFieldLenghtGL.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldThickGL.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldWeightGL.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldWidthGL.setTextFormatter(new TextFormatter<String>(integerFilter));
    	TextFieldCosGL.setTextFormatter(new TextFormatter<String>(integerFilter));
    	
    	ButtonSave.setOnAction(event -> {//���������� � ����
    		try {
    			File file = new File("file2.txt");
    			if (!file.exists())
    				file.createNewFile();
    			
    			PrintWriter pw = new PrintWriter(file);
    			pw.println(TextFieldWidthGL.getText());
    			pw.println(TextFieldLenghtGL.getText());
    			pw.println(TextFieldThickGL.getText());
    			pw.println(TextFieldExpGL.getText());
    			pw.println(TextFieldWeightGL.getText());
    			pw.println(TextFieldCosGL.getText());
    			
    			pw.close();
    			
    		} catch (IOException e) {}
    	});
    	
    	ButtonOut.setOnAction(event -> {//����� �� �����
    		try {
    			BufferedReader br = null;
    			br = new BufferedReader(new FileReader("file2.txt"));
    		
    			TextFieldWidthGL.setText(br.readLine());
    			TextFieldLenghtGL.setText(br.readLine());
    			TextFieldThickGL.setText(br.readLine());
    			TextFieldExpGL.setText(br.readLine());
    			TextFieldWeightGL.setText(br.readLine());
    			TextFieldCosGL.setText(br.readLine());
    			
    			br.close();
    			
    		} catch (IOException e) {}
    	});

    	ResultButtonGL.setOnAction(event -> Calk());
    	 
    }
    
    private void Calk() {
    	 
    	try {
    	float a = Float.parseFloat(TextFieldWidthGL.getText()); //���������� ����������
    	float b = Float.parseFloat(TextFieldLenghtGL.getText());
    	float c = Float.parseFloat(TextFieldThickGL.getText());
    	float d = Float.parseFloat(TextFieldExpGL.getText());
    	float e = Float.parseFloat(TextFieldWeightGL.getText());
    	float f = Float.parseFloat(TextFieldCosGL.getText());
    	
    	float result1 = a*b*c*d; //���������� ��������
    	float result2 = result1/e;
    	float result3 = (float) (Math.ceil(result2)*f);
    	
    	LabelResultGL.setText(String.valueOf(result1)); //�����
    	LabelResult2GL.setText(String.valueOf(Math.ceil(result2)));
    	LabelResult3GL.setText(String.valueOf(result3));}
    	catch (NumberFormatException e1) {};
    	
    }

}
