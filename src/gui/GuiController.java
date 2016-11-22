package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GuiController implements Initializable {
	@FXML
	private AnchorPane guiContent;

	@FXML
	private void diodeButtonAction(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Diody");
		Pane myPane = FXMLLoader.load(getClass().getResource("diode.xml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void rcButtonAction(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Filtry RC");
		Pane myPane = FXMLLoader.load(getClass().getResource("rcFilters.xml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void rlButtonAction(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Filtry RL");
		Pane myPane = FXMLLoader.load(getClass().getResource("rlFilters.xml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void rlcButtonAction(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Układ RLC");
		Pane myPane = FXMLLoader.load(getClass().getResource("rlc.xml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}
}
