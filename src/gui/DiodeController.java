package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import electric_circuit.DiodeGe;
import electric_circuit.DiodePN;
import electric_circuit.DiodeSi;
import electric_circuit.VoltageSource;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;

public class DiodeController implements Initializable {

	@FXML
	private LineChart<Number, Number> lcUSource;
	private Boolean run = true;
	private VoltageSource source = new VoltageSource(0.44, VoltageSource.Type.TRIANGULAR, 0, 0.25);
	private DiodePN diode = new DiodeGe();
	private ArrayList sourceVoltage = new ArrayList<Double>();
	private ArrayList forwardVoltage = new ArrayList<Double>();
	private ArrayList forwardCurrent = new ArrayList<Double>();
	@FXML
	private LineChart<Number, Number> lcIF;
	@FXML
	private LineChart<Number, Number> lcUF;
	@FXML
	private Button pauseBtn;
	@FXML
	private NumberTextField offsetTF;
	@FXML
	private NumberTextField uInTF;
	@FXML
	private NumberTextField freqTF;
	@FXML
	private ToggleGroup sourceType;

	@FXML
	private void pauseAction(ActionEvent event) throws InterruptedException {
		if (run) {
			pauseBtn.setText("Wznów");
			run = false;
		} else {
			pauseBtn.setText("Pauza");
			run = true;
		}
	}

	@FXML
	private void updateAction(ActionEvent event) throws InterruptedException {
		updateInput();
	}
	
	@FXML
	private void selectTriangulateAction(ActionEvent event) throws InterruptedException {
		source = new VoltageSource(0.44, VoltageSource.Type.TRIANGULAR, 0, 0.25);
		updateInput();
	}
	
	@FXML
	private void selectSinusAction(ActionEvent event) throws InterruptedException {
		source = new VoltageSource(0.44, VoltageSource.Type.SINUS, 0, 0.25);
		updateInput();
	}
	
	@FXML
	private void selectDirectAction(ActionEvent event) throws InterruptedException {
		source = new VoltageSource(0.44, VoltageSource.Type.DIRECT);
		updateInput();
	}
	
	@FXML
	private void selectGeAction(ActionEvent event) throws InterruptedException {
		diode = new DiodeGe();
	}
	
	@FXML
	private void selectSiAction(ActionEvent event) throws InterruptedException {
		diode = new DiodeSi();
	}
	

	private void updateInput() {
		source.setFrequency(freqTF.getValue());
		source.setOffset(offsetTF.getValue());
		source.setU(uInTF.getValue());
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeGraphs();
		uInTF.setText("0.44");
		offsetTF.setText("0");
		freqTF.setText("0.25");

		Task task = new Task<Void>() {
			@Override
			public Void call() throws Exception {
				double time = 0;
				while (true) {
					if (run) {
						time += 0.025;
					}

					final double finalTime = time;
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								updateGraphs(finalTime);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					});
					Thread.sleep(25);
				}
			}
		};
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
	}

	private void initializeGraphs() {
		initializeGraph(lcIF, forwardCurrent, "Forward Current", "Current");
		initializeGraph(lcUF, forwardVoltage, "Forward Voltage", "Voltage");
		initializeGraph(lcUSource, sourceVoltage, "Sourrce Voltage", "Voltage");
	}

	private void initializeGraph(LineChart<Number, Number> lc, ArrayList sourceList, String title, String label) {
		for (int i = 0; i < 200; i++) {
			sourceList.add(0);
		}

		lc.getXAxis().setAutoRanging(true);
		lc.getYAxis().setAutoRanging(true);
		lc.setCreateSymbols(false);
		lc.setTitle(title);
		XYChart.Series series = new XYChart.Series();
		series.setName(label);
		serieFromList(series, sourceList);
		lc.getData().add(0, series);

	}

	private void serieFromList(Series series, ArrayList list) {
		for (int i = 0; i < list.size(); i++) {
			series.getData().add(new XYChart.Data(i, list.get(i)));
		}
	}

	protected void updateGraphs(double time) throws InterruptedException {
		if (run) {
			double Uin = source.U(time);
			sourceVoltage.remove(0);
			sourceVoltage.add(Uin);
			updateGraph(lcUSource, sourceVoltage, "Voltage");
			forwardCurrent.remove(0);
			forwardCurrent.add(diode.IF(Uin));
			updateGraph(lcIF, forwardCurrent, "Current");
			forwardVoltage.remove(0);
			forwardVoltage.add(diode.UF(Uin));
			updateGraph(lcUF, forwardVoltage, "Voltage");
		}
	}

	private void updateGraph(LineChart<Number, Number> lc, ArrayList sourceArray, String label) {
		XYChart.Series series = new XYChart.Series();
		series.setName(label);
		serieFromList(series, sourceArray);
		lc.getData().set(0, series);
	}

}
