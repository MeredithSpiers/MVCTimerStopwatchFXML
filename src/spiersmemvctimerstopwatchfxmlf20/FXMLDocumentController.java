/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiersmemvctimerstopwatchfxmlf20;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Meredith
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ImageView clockHand;
    @FXML
    private Button startStopButton;
    @FXML
    private Button recordResetButton;
    @FXML
    private Label digitalLabel;
    @FXML
    private Label lapLabel;
    @FXML
    private Label timerLabel;
     // the chart initialization was referenced from my FXML CPU submission
    protected XYChart.Series<String, Number> series;
    @FXML
    private LineChart<String, Number> recordedLineChart;

    FXMLAnalogModel analogModel;
    FXMLDigitalModel digitalModel;
    
    // the initialization below was referenced from the Controller.java MVC CPU class example
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        analogModel = new FXMLAnalogModel();
        digitalModel = new FXMLDigitalModel();
        
        analogModel.setupMonitor();
        digitalModel.setupMonitor();
        
        clockHand.setRotate(0);
    }
    
    // the propertyChange was referenced from https://www.programcreek.com/java-api-examples/?api=java.beans.PropertyChangeEvent
    public void propertyChange(PropertyChangeEvent evt){
        if(evt.getPropertyName().equals("Analog")){
            double handAngle = (double) evt.getNewValue();
            clockHand.setRotate(handAngle);
        } else if (evt.getPropertyName().equals("Digital")){
            digitalLabel.setText(evt.getNewValue().toString());
        } else if (evt.getPropertyName().equals("Timer")){
            timerLabel.setText(evt.getNewValue().toString());
        } else {
            lapLabel.setText(evt.getNewValue().toString());
        }
    }
    
    // the line chart code below was referenced from my previous MVC CPU challenge submission
    public void record(){
        series = new XYChart.Series();
        series.getData().add(new XYChart.Data(Integer.toString(digitalModel.lapCount), digitalModel.secondsElapsed));
        recordedLineChart.getData().add(series);
    }
    
    // the method below was referenced from my previous challenge submission and the Controller.java file from the MVC CPU class example
    @FXML
    public void switchStartStopButtons(ActionEvent event){
        if ( !(analogModel.isRunning()) && !(digitalModel.isRunning()) ) {
            startStopButton.setText("Stop");
            // button color change referenced from http://tutorials.jenkov.com/javafx/button.html
            startStopButton.setStyle("-fx-background-color: #ff0000");
            recordResetButton.setText("Record");
            // the start actions below referenced from StopwatchController.java class example
            analogModel.start();
            digitalModel.start();
                
        } else {
            startStopButton.setText("Start");
            // button color change referenced from http://tutorials.jenkov.com/javafx/button.html
            startStopButton.setStyle("-fx-background-color: #00ff00");
            recordResetButton.setText("Reset");
            // the stop actions below referenced from StopwatchController.java class example
            analogModel.stop();
            digitalModel.stop();
        }
    }
    
    // the method below was referenced from my previous challenge submission and the Controller.java file from the MVC CPU class example
    @FXML
    public void switchRecordResetButtons(ActionEvent event){
        if ( !(analogModel.isRunning()) && !(digitalModel.isRunning()) ) {
            recordResetButton.setText("Record");
            startStopButton.setText("Start");
            // button color change referenced from http://tutorials.jenkov.com/javafx/button.html
            startStopButton.setStyle("-fx-background-color: #00ff00");

            // the line below was referenced from https://stackoverflow.com/questions/41956339/javafx-linechart-clean-data-from-chart
            recordedLineChart.getData().clear();
            clockHand.setRotate(0);
            digitalLabel.setText("--:--:--.--");
            lapLabel.setText("Lap -:  --:--:--.--");
            timerLabel.setText("Timer --:--");
            digitalModel.lapCount = 0;
            
            analogModel.reset();
            digitalModel.reset();                
        } else {
            recordResetButton.setText("Record");
            startStopButton.setText("Stop");
            // button color change referenced from http://tutorials.jenkov.com/javafx/button.html
            startStopButton.setStyle("-fx-background-color: #ff0000");
                
            record();
        }
    }    
}
