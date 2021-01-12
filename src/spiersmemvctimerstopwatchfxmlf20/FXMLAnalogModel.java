/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiersmemvctimerstopwatchfxmlf20;


/**
 *
 * @author Meredith
 */
// the methods below were referenced from StopwatchModel.java class example
public class FXMLAnalogModel extends FXMLAbstractModel{
    // referenced from AnalogModel.java from MVC CPU class example
    private double angle= 0.0;
    // the 3 doubles below were referenced from StopwatchModel.java from MVC stopwatch example
    protected double secondsElapsed = 0.0;
    protected double tickTimeInSeconds = 0.01;
    protected double angleDeltaPerSeconds = 6.0; 
    
    public FXMLAnalogModel(){

    }
    
    // the methods updateTimer and updateAnalogClock were referenced from AnalogModel.java from MVC CPU class example
    @Override
    public void updateMonitor(){
        super.updateMonitor();
        updateAnalogClock(secondsElapsed);
    }
    
    public void updateAnalogClock(double secondsElapsed){
        double oldAngle = angle; 
        angle = calculateRotation(secondsElapsed); 
        
        firePropertyChange("Analog", oldAngle, angle);
    }
    // calculateRotation was referenced from StopwatchModel.java MVC stopwatch class example    
    public double calculateRotation(double secondsElapsed){
        secondsElapsed += tickTimeInSeconds;
        return secondsElapsed * angleDeltaPerSeconds;
    }

}
