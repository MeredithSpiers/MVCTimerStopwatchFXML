/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiersmemvctimerstopwatchfxmlf20;

import java.text.DecimalFormat;
import java.util.Optional;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Meredith
 */
public class FXMLDigitalModel extends FXMLAbstractModel{ 
    private double secondsElapsedOld = 0.0;
    private int startLeft = 60;
    private double secondsLeft = 60.00;
    private int timeupTag = 0;
    protected int lapCount = 0;
    protected String newTimeStr = "--:--:--.--";
    private String newLapStr = "Lap -: --:--:--.--";
    private String labelStr = "Time: --:--";
    private int digitalCentiseconds;
    private int digitalSeconds;
    private int digitalMinutes;
    private int digitalHours;
      
    
    public FXMLDigitalModel(){     
        tickTimeInSeconds = 0.01;
        secondsElapsed = 0.0;
        setupDigitalUI();
    }
    // setupUI method was referenced from my TimerStopWatch submission
    private void setupDigitalUI(){
        // InputDialog referenced from https://www.geeksforgeeks.org/javafx-alert-with-examples/ and https://www.geeksforgeeks.org/javafx-textinputdialog/
        TextInputDialog inputDialog = new TextInputDialog("");
        inputDialog.setTitle("Timer Set-up");
        inputDialog.setHeaderText("Set-up the start time");
        inputDialog.setContentText("Please set-up the start time (integer):");
//        inputDialog.showAndWait();
        Optional <String> userInput = inputDialog.showAndWait();
        secondsLeft = Integer.valueOf(userInput.get());
    }
    
    @Override
    public void updateMonitor(){
        super.updateMonitor();
        updateDigitalClock(secondsElapsed);
        updateTimer(secondsLeft - secondsElapsed);
    }
    
    public void updateTimer(double secondsLeft){
        String oldLabelStr = labelStr;
        
        firePropertyChange("Timer", oldLabelStr, labelStr);
    }
    // the updateDigitalClock and updateLap methods were referenced from digitalModel.java of the MVC CPU class example
    public void updateDigitalClock(double secondsElapsed){
        String oldStr = newTimeStr;
        newTimeStr = getTimeStr(secondsElapsed);
        
        firePropertyChange("Digital", oldStr, newTimeStr);
    }
    
    public void updateLap(double secondsElapsed){
        String oldLapStr = newLapStr; 
        newLapStr = "Lap " + lapCount + ":  " + getTimeStr(secondsElapsed);
        secondsElapsedOld = secondsElapsed;
        
        firePropertyChange("Lap", oldLapStr, newLapStr);
    }
    
    public String getTimeStr(double secondsElapsed){
        // the code below was referenced from https://stackoverflow.com/questions/6118922/convert-seconds-value-to-hours-minutes-seconds/6118983
        digitalHours = (int) secondsElapsed / 3600;
        int newSecondsElapsed = (int) secondsElapsed - digitalHours * 3600;
        digitalMinutes = newSecondsElapsed / 60;
        newSecondsElapsed = newSecondsElapsed - digitalMinutes * 60;
        digitalSeconds = newSecondsElapsed;
        digitalCentiseconds = (newSecondsElapsed * 100) % 100;

        String hours = String.format("%02d", digitalHours);
        String minutes = String.format("%02d", digitalMinutes);
        String seconds = String.format("%02d", digitalSeconds);
        String centiseconds = String.format("%02d", digitalCentiseconds);
        
        return newTimeStr = (digitalHours + ":" + digitalMinutes + ":" + digitalSeconds + "." + digitalCentiseconds);
    }
}
