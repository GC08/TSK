package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import electric_circuit.DiodePN;
import electric_circuit.DiodeSi;
import electric_circuit.VoltageSource;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
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

	private VoltageSource source = new VoltageSource(1, VoltageSource.Type.SINUS, 0, 0.25);
	private DiodePN diode = new DiodeSi();
	private ArrayList sourceVoltage = new ArrayList<Double>();

	@FXML
	private void handleButtonAction(ActionEvent event) {
		label.setText(poleTekstowe.getText());
		kolko.setFill(Color.GREEN);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		for (int i = 0; i < 100; i++) {
			sourceVoltage.add(0);
		}
		lc.getXAxis().setAutoRanging(true);
		lc.getYAxis().setAutoRanging(true);

		lc.setCreateSymbols(false);
		lc.setTitle("Sourrce Voltage");
		XYChart.Series series = new XYChart.Series();
		series.setName("Voltage");
		serieFromList(series, sourceVoltage);
		XYChart.Series series2 = new XYChart.Series();
		series2.getData().add(new XYChart.Data(0, 3));

		lc.getData().add(0, series);
		lc.getData().add(series2);
		
		Task task = new Task<Void>() {
			@Override
			public Void call() throws Exception {
				double time = 0;
				while (true) {
					time += 0.100;
					final double finalTime = time;
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							sourceVoltage.remove(0);
							sourceVoltage.add(source.U(finalTime));
							XYChart.Series series = new XYChart.Series();
							series.setName("Voltage");
							serieFromList(series, sourceVoltage);
							lc.getData().set(0, series);
						}
					});
					Thread.sleep(100);
				}
			}
		};
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
	}

	private void serieFromList(Series series, ArrayList list) {
		for (int i = 0; i < list.size(); i++) {
			series.getData().add(new XYChart.Data(i, list.get(i)));
		}
	}
//
//	protected void UpdateGraphs(double time) {
//		sourceVoltage.remove(0);
//		sourceVoltage.add(0);
//		XYChart.Series series = new XYChart.Series();
//		series.setName("Voltage");
//		serieFromList(series, sourceVoltage);
//
//		// this.lc.getData().retainAll();
//		this.lc.getData().add(series);
//		// lc.getData().get(0).getData().add(10,new XYChart.Data(12, 25));
//		// XYChart.Data xd= new XYChart.Data(12, 25);
//		// xd.getNode().(xd.getXValue() - 1);
//	}

}
