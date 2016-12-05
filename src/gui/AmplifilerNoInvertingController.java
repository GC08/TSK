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

public class AmplifilerNoInvertingController implements Initializable {
        
	@FXML
	public Label Uin;
        @FXML
	public Label Uin2;
        @FXML
	public Slider UinSlider;
        @FXML
	public Label R1;
        @FXML
	public Label R11;
        @FXML
	public Slider R1Slider;
        @FXML
	public Label R2;
        @FXML
	public Label R22;
        @FXML
	public Slider R2Slider;
        @FXML
	public Label Uout;

        private DecimalFormat formatter = new DecimalFormat("####.##");
        private static final double initUin = 5.00;
        private static final double initR1 = 15.00;
        private static final double initR2 = 15.00;
        
	public void updateInput() {
		this.Uout.setText(formatter.format(amplification()) + " V");
                
	}
        
	@Override
	public void initialize(URL url, ResourceBundle rb) {

            this.UinSlider.setValue(initUin);
            this.Uin.textProperty().bindBidirectional(UinSlider.valueProperty(),NumberFormat.getNumberInstance());
            this.Uin2.textProperty().bindBidirectional(UinSlider.valueProperty(),NumberFormat.getNumberInstance());
            
            this.R1Slider.setValue(initR1);
            this.R1.textProperty().bindBidirectional(R1Slider.valueProperty(),NumberFormat.getNumberInstance());
            this.R11.textProperty().bindBidirectional(R1Slider.valueProperty(),NumberFormat.getNumberInstance());
            
            this.R2Slider.setValue(initR2);
            this.R2.textProperty().bindBidirectional(R2Slider.valueProperty(),NumberFormat.getNumberInstance());
            this.R22.textProperty().bindBidirectional(R2Slider.valueProperty(),NumberFormat.getNumberInstance());
            
            
            this.Uout.setText(formatter.format(amplification()) + " V");
	}

        public double amplification()
        {
            double tmpR1 = Double.parseDouble(this.R1.getText().replace(",", "."));
            double tmpR2 = Double.parseDouble(this.R2.getText().replace(",", "."));
            double tmpUin = Double.parseDouble(this.Uin.getText().replace(",", "."));
            double result = (tmpR2/tmpR1+1) * tmpUin;
            if(result >15)
                return 15;
            return result;
        }

	public void serieFromList(Series series, ArrayList list) {
		for (int i = 0; i < list.size(); i++) {
			series.getData().add(new XYChart.Data(i, list.get(i)));
		}
	}

}
