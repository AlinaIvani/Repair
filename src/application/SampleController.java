package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button glueButton;

    @FXML
    private Button laminateButton;

    @FXML
    private Button linoleumButton;

    @FXML
    private Button paintButton;

    @FXML
    private Button plasterButton;

    @FXML
    private Button puttyButton;

    @FXML
    private Button tileButton;

    @FXML
    private Button wallButton;

    @FXML
    void initialize() {
    	
    	glueButton.setOnAction(event -> { //переходы на другие формы
    		glueButton.getScene().getWindow().hide();
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/glue.fxml"));  
    	    Stage stage = new Stage();
    	    try {
				stage.setScene(new Scene((Parent) loader.load()));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	    stage.show();
    	});
    	    	
    	laminateButton.setOnAction(event -> {
    		laminateButton.getScene().getWindow().hide();
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/laminate.fxml"));  
    	    Stage stage = new Stage();
    	    try {
				stage.setScene(new Scene((Parent) loader.load()));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	    stage.show();
    	});
    	
    	linoleumButton.setOnAction(event -> {
    		linoleumButton.getScene().getWindow().hide();
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/linoleum.fxml"));  
    	    Stage stage = new Stage();
    	    try {
				stage.setScene(new Scene((Parent) loader.load()));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	    stage.show();
    	});
	
    	paintButton.setOnAction(event -> {
    		paintButton.getScene().getWindow().hide();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/paint.fxml"));  
    		Stage stage = new Stage();
    		try {
    			stage.setScene(new Scene((Parent) loader.load()));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		stage.show();
    	});
	
    	plasterButton.setOnAction(event -> {
    		plasterButton.getScene().getWindow().hide();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/plaster.fxml"));  
    		Stage stage = new Stage();
    		try {
    			stage.setScene(new Scene((Parent) loader.load()));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		stage.show();
    	});
	
    	puttyButton.setOnAction(event -> {
    		puttyButton.getScene().getWindow().hide();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/putty.fxml"));  
    		Stage stage = new Stage();
    		try {
    			stage.setScene(new Scene((Parent) loader.load()));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		stage.show();
    	});

    	tileButton.setOnAction(event -> {
    		tileButton.getScene().getWindow().hide();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/tile.fxml"));  
    		Stage stage = new Stage();
    		try {
    			stage.setScene(new Scene((Parent) loader.load()));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		stage.show();
    	});
    	
    	wallButton.setOnAction(event -> {
    		wallButton.getScene().getWindow().hide();
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/wall.fxml"));  
    	    Stage stage = new Stage();
    	    try {
				stage.setScene(new Scene((Parent) loader.load()));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	    stage.show();
    	});

    }

}
