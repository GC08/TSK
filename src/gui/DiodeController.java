package gui;

import java.net.URL;
import java.util.ResourceBundle;

import electric_circuit.DiodePN;
import electric_circuit.DiodeSi;
import electric_circuit.VoltageSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DiodeController implements Initializable {
	@FXML
	private TextField poleTekstowe;

	@FXML
	private AnchorPane guiContent;

	@FXML
	private Circle kolko;

	@FXML
	private Label label;

	@FXML 
	private LineChart<Number, Number> lc;
	
	private VoltageSource source = new VoltageSource(1, VoltageSource.Type.SINUS, 0, 1);
	private DiodePN diode = new DiodeSi();

	@FXML
	private void handleButtonAction(ActionEvent event) {
		label.setText(poleTekstowe.getText());
		kolko.setFill(Color.GREEN);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Thread one = new Thread() {
		    public void run() {
		        try {
		        	double time = 0;
		        	
		        	while(true){
		        		UpdateGraphs(time);
		        		time += 0.1;
			            Thread.sleep(100);
		        	}
		        } catch(InterruptedException v) {
		            System.out.println(v);
		        }
		    }  
		};

		one.start();
		
		// NumberAxis xAxis = new NumberAxis();
		// NumberAxis yAxis = new NumberAxis();
		// xAxis.setLabel("Number of Month");
		// // creating the chart
		// lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		//
		// lineChart.setTitle("Stock Monitoring, 2010");
		// // defining a series
		// XYChart.Series series = new XYChart.Series();
		// series.setName("My portfolio");
		// // populating the series with data
		// series.getData().add(new XYChart.Data(1, 23));
		// series.getData().add(new XYChart.Data(2, 14));
		// series.getData().add(new XYChart.Data(3, 15));
		// series.getData().add(new XYChart.Data(4, 24));
		// series.getData().add(new XYChart.Data(5, 34));
		// series.getData().add(new XYChart.Data(6, 36));
		// series.getData().add(new XYChart.Data(7, 22));
		// series.getData().add(new XYChart.Data(8, 45));
		// series.getData().add(new XYChart.Data(9, 43));
		// series.getData().add(new XYChart.Data(10, 17));
		// series.getData().add(new XYChart.Data(11, 29));
		// series.getData().add(new XYChart.Data(12, 25));
		// lineChart.getData().add(series);
	}

	protected void UpdateGraphs(double time) {
		
	}

}
