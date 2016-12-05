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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DiodeController implements Initializable {

	@FXML
	public LineChart<Number, Number> lcUSource;
	public Boolean run = true;
	public VoltageSource source = new VoltageSource(0.44, VoltageSource.Type.TRIANGULAR, 0, 0.25);
	public DiodePN diode = new DiodeGe();
	public ArrayList<Double> sourceVoltage = new ArrayList<Double>();
	public ArrayList<Double> forwardVoltage = new ArrayList<Double>();
	public ArrayList<Double> forwardCurrent = new ArrayList<Double>();
	@FXML
	public LineChart<Number, Number> lcIF;
	@FXML
	public LineChart<Number, Number> lcUF;
	@FXML
	public Button pauseBtn;
	@FXML
	public TextField offsetTF;
	@FXML
	public TextField uInTF;
	@FXML
	public TextField freqTF;
	@FXML
	public ToggleGroup sourceType;
        @FXML
        public ImageView imageDiode;

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
	public void selectTriangulateAction(ActionEvent event) throws InterruptedException {
		source = new VoltageSource(0.44, VoltageSource.Type.TRIANGULAR, 0, 0.25);
		updateInput();
	}
	
	@FXML
	public void selectSinusAction(ActionEvent event) throws InterruptedException {
		source = new VoltageSource(0.44, VoltageSource.Type.SINUS, 0, 0.25);
		updateInput();
	}
	
	@FXML
	public void selectDirectAction(ActionEvent event) throws InterruptedException {
		source = new VoltageSource(0.44, VoltageSource.Type.DIRECT);
		updateInput();
	}
	
	@FXML
	public void selectGeAction(ActionEvent event) throws InterruptedException {
		diode = new DiodeGe();   
	}
	
	@FXML
	public void selectSiAction(ActionEvent event) throws InterruptedException {
		diode = new DiodeSi();
	}
	

	public void updateInput() {
		source.setFrequency(Double.parseDouble(freqTF.getText()));
		source.setOffset(Double.parseDouble(offsetTF.getText()));
		source.setU(Double.parseDouble(uInTF.getText()));
	}
        
	@Override
	public void initialize(URL url, ResourceBundle rb) {
            Image img = new Image("file:src/gui/img/Diode.png");
            imageDiode.setImage(img);
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

	public void initializeGraphs() {
		initializeGraph(lcIF, forwardCurrent, "Forward Current", "Current");
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

	public void updateGraph(LineChart<Number, Number> lc, ArrayList sourceArray, String label) {
            
            XYChart.Series series = new XYChart.Series();
            series.setName(label);
            serieFromList(series, sourceArray);
            lc.getData().set(0, series);
	}

}
