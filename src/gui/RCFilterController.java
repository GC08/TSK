package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import electric_circuit.HighPassRCFilter;
import electric_circuit.LowPassRCFilter;
import electric_circuit.RCFilter;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RCFilterController implements Initializable {
	private Boolean run = true;
	private RCFilter filter = new LowPassRCFilter(1,1,1,1);
	private ArrayList sourceVoltage = new ArrayList<Double>();
	private ArrayList forwardVoltage = new ArrayList<Double>();
	private ArrayList gain = new ArrayList<Double>();
	@FXML
	private LineChart<Number, Number> lcGain;
	@FXML
	private LineChart<Number, Number> lcUF;
	@FXML
	private LineChart<Number, Number> lcUSource;
	@FXML
	private Button pauseBtn;
	@FXML
	private NumberTextField rTF;
	@FXML
	private NumberTextField cTF;
	@FXML
	private NumberTextField uInTF;
	@FXML
	private NumberTextField freqTF;
	@FXML
	private ToggleGroup sourceType;
	@FXML
	private Label limitFreq;
	@FXML
	private ImageView imageView;

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
	private void selectLowPassAction(ActionEvent event) throws InterruptedException {
		filter = new LowPassRCFilter(freqTF.getValue(), uInTF.getValue(), rTF.getValue(), cTF.getValue());
		limitFreq.setText(""+filter.getLimitFreq());
		Image image = new Image("file:src/gui/images/RC_low_pass.png");
        imageView.setImage(image);
	}
	
	@FXML
	private void selectHighPassAction(ActionEvent event) throws InterruptedException {
		filter = new HighPassRCFilter(freqTF.getValue(), uInTF.getValue(), rTF.getValue(), cTF.getValue());
		limitFreq.setText(""+filter.getLimitFreq());
		Image image = new Image("file:src/gui/images/RC_high_pass.png");
		imageView.setImage(image);
	}

	private void updateInput() {
		filter.setFreq(freqTF.getValue());
		filter.setUin(uInTF.getValue());
		filter.setR(rTF.getValue());
		filter.setC(cTF.getValue());
		limitFreq.setText(""+filter.getLimitFreq());
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeGraphs();
		freqTF.setText("1");
		uInTF.setText("1");
		rTF.setText("1");
		cTF.setText("1");
		limitFreq.setText(""+filter.getLimitFreq());

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
		initializeGraph(lcGain, gain, "Wzmocnienie", "Wzmocnienie");
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
			double Uin = uInTF.getValue();
			sourceVoltage.remove(0);
			sourceVoltage.add(Uin);
			updateGraph(lcUSource, sourceVoltage, "Voltage");
			gain.remove(0);
			gain.add(filter.getVoltageGain());
			updateGraph(lcGain, gain, "Current");
			forwardVoltage.remove(0);
			forwardVoltage.add(filter.Uout());
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
