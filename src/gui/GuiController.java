package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GuiController implements Initializable {

	public void diodeButtonAction(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Diody");
		Pane myPane = FXMLLoader.load(getClass().getResource("diode.fxml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();
	}
	
	public void rcButtonAction(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Filtry RC");
		Pane myPane = FXMLLoader.load(getClass().getResource("rcFilters.fxml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();
	}
	
	public void rlButtonAction(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Filtry RL");
		Pane myPane = FXMLLoader.load(getClass().getResource("rlFilters.fxml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();
	}
	
	public void rlcButtonAction(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Układ RLC");
		Pane myPane = FXMLLoader.load(getClass().getResource("rlc.fxml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();
	}
        
        public void transistorButtonAction(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Tranzystory");
		Pane myPane = FXMLLoader.load(getClass().getResource("transistors.fxml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();
	}
        
        public void amplifilerInvertingButtonAction(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Wzmacniacz odwracający");
		Pane myPane = FXMLLoader.load(getClass().getResource("amplifilerInverting.fxml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();
	}
        public void amplifilerNonInvertingButtonAction(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Wzmacniacz nieodwracający");
		Pane myPane = FXMLLoader.load(getClass().getResource("amplifilerNoInverting.fxml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
}
