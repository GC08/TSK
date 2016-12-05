package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import electric_circuit.DiodeGe;
import electric_circuit.DiodePN;
import electric_circuit.DiodeSi;
import electric_circuit.Transistor;
import electric_circuit.VoltageSource;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

public class TransistorsController implements Initializable {
        
	public Transistor transistor = new Transistor(initUb,initUc,325);
	@FXML
	public Label Ub;
        @FXML
	public Label Ub2;
        @FXML
	public Slider UbSlider;
        @FXML
	public Label Uc;
        @FXML
	public Label Uc2;
        @FXML
	public Slider UcSlider;
        @FXML
	public Label Ue;
	@FXML
	public Label Ib;
	@FXML
	public Label Ic;
        @FXML
	public Label Ie;
        @FXML
        public ImageView imageTransistor;

        private DecimalFormat formatter = new DecimalFormat("####.##");
        private static final double initUb = 0.5;
        private static final double initUc = 2.00;
        
	@FXML
	public void selectA(ActionEvent event) throws InterruptedException {
		transistor.setBeta(115);
		updateInput();
	}
	
	@FXML
	public void selectB(ActionEvent event) throws InterruptedException {
		transistor.setBeta(325);
		updateInput();
	}
	
	@FXML
	public void selectC(ActionEvent event) throws InterruptedException {
		transistor.setBeta(610);
		updateInput();
	}
	
	@FXML
	public void selectPNPAction(ActionEvent event) throws InterruptedException {
            Image img = new Image("file:src/gui/img/PNP_Transistor_Without_V.png");
            imageTransistor.setImage(img);
            updateInput();
	}
	
	@FXML
	public void selectNPNAction(ActionEvent event) throws InterruptedException {
            Image img = new Image("file:src/gui/img/NPN_Transistor_Without_V.png");
            imageTransistor.setImage(img);
            updateInput();
	}
	

	public void updateInput() {
		transistor.setUbase(Double.parseDouble(Ub.getText().replace(",", ".")));
                transistor.setUcol(Double.parseDouble(Uc.getText().replace(",", ".")));
                this.Ic.setText(formatter.format(transistor.Icol()) + " A");
                this.Ib.setText(formatter.format(transistor.Ibase()) + " A");
                this.Ie.setText(formatter.format(transistor.Iemi())+ " A");
                
	}
        
	@Override
	public void initialize(URL url, ResourceBundle rb) {
            Image img = new Image("file:src/gui/img/NPN_Transistor_Without_V.png");
            imageTransistor.setImage(img);

            this.UbSlider.setValue(initUb);
            this.Ub.textProperty().bindBidirectional(UbSlider.valueProperty(),NumberFormat.getNumberInstance());
            this.Ub2.textProperty().bindBidirectional(UbSlider.valueProperty(),NumberFormat.getNumberInstance());
            
            this.UcSlider.setValue(initUc);
            this.Uc.textProperty().bindBidirectional(UcSlider.valueProperty(),NumberFormat.getNumberInstance());
            this.Uc2.textProperty().bindBidirectional(UcSlider.valueProperty(),NumberFormat.getNumberInstance());
            
            this.Ic.setText(Double.toString(transistor.Icol()) + " A");
            this.Ib.setText(Double.toString(transistor.Ibase()) + " A");
            this.Ie.setText(formatter.format(transistor.Iemi())+ " A");
	}


	public void serieFromList(Series series, ArrayList list) {
		for (int i = 0; i < list.size(); i++) {
			series.getData().add(new XYChart.Data(i, list.get(i)));
		}
	}

}
