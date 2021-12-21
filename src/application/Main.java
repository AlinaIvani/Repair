package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{//открытие главной формы при запуске
		
		Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		primaryStage.setTitle("Ремонт");
		primaryStage.setScene(new Scene(root,700,400));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
