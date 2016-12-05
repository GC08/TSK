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
	public Boolean run = true;
	public RCFilter filter = new LowPassRCFilter(1,1,1,1);
	public ArrayList<Double> sourceVoltage = new ArrayList<Double>();
	public ArrayList<Double> forwardVoltage = new ArrayList<Double>();
	public ArrayList<Double> gain = new ArrayList<Double>();
	@FXML
	public LineChart<Number, Number> lcGain;
	@FXML
	public LineChart<Number, Number> lcUF;
	@FXML
	public LineChart<Number, Number> lcUSource;
	@FXML
	public Button pauseBtn;
	@FXML
	public TextField rTF;
	@FXML
	public TextField cTF;
	@FXML
	public TextField uInTF;
	@FXML
	public TextField freqTF;
	@FXML
	public ToggleGroup sourceType;
	@FXML
	public Label limitFreq;
        @FXML
        public ImageView imageRC;

	@FXML
	public void pauseAction(ActionEvent event) throws InterruptedException {
		if (run) {
			pauseBtn.setText("Wznów");
			run = false;
		} else {
			pauseBtn.setText("Pauza");
			run = true;
		}
	}

	@FXML
	public void updateAction(ActionEvent event) throws InterruptedException {
		updateInput();
	}

	@FXML
	public void selectLowPassAction(ActionEvent event) throws InterruptedException {
            Image img = new Image("file:src/gui/img/RC_low_pass.png");
            imageRC.setImage(img);
            filter = new LowPassRCFilter(
                        Double.parseDouble(freqTF.getText()), 
                        Double.parseDouble(uInTF.getText()), 
                        Double.parseDouble(rTF.getText()), 
                        Double.parseDouble(cTF.getText()));
            limitFreq.setText(""+filter.getLimitFreq());
	}
	
	@FXML
	public void selectHighPassAction(ActionEvent event) throws InterruptedException {
            Image img = new Image("file:src/gui/img/RC_high_pass.png");
            imageRC.setImage(img);
            filter = new HighPassRCFilter(
                    Double.parseDouble(freqTF.getText()), 
                    Double.parseDouble(uInTF.getText()), 
                    Double.parseDouble(rTF.getText()), 
                    Double.parseDouble(cTF.getText()));
            limitFreq.setText(""+filter.getLimitFreq());
	}

	public void updateInput() {
		filter.setFreq(Double.parseDouble(freqTF.getText()));
		filter.setUin(Double.parseDouble(uInTF.getText()));
		filter.setR(Double.parseDouble(rTF.getText()));
		filter.setC(Double.parseDouble(cTF.getText()));
		limitFreq.setText(""+filter.getLimitFreq());
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Image img = new Image("file:src/gui/img/RC_low_pass.png");
                imageRC.setImage(img);
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

	public void initializeGraphs() {
		initializeGraph(lcGain, gain, "Wzmocnienie", "Wzmocnienie");
		initializeGraph(lcUF, forwardVoltage, "Forward Voltage", "Voltage");
		initializeGraph(lcUSource, sourceVoltage, "Sourrce Voltage", "Voltage");
	}

	public void initializeGraph(LineChart<Number, Number> lc, ArrayList sourceList, String title, String label) {
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

	public void serieFromList(Series series, ArrayList list) {
		for (int i = 0; i < list.size(); i++) {
			series.getData().add(new XYChart.Data(i, list.get(i)));
		}
	}

	public void updateGraphs(double time) throws InterruptedException {
		if (run) {
			double Uin = Double.parseDouble(uInTF.getText());
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

	public void updateGraph(LineChart<Number, Number> lc, ArrayList sourceArray, String label) {
		XYChart.Series series = new XYChart.Series();
		series.setName(label);
		serieFromList(series, sourceArray);
		lc.getData().set(0, series);
	}

}
