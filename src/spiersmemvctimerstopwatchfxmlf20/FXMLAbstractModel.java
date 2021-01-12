/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiersmemvctimerstopwatchfxmlf20;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author Meredith
 */
public abstract class FXMLAbstractModel {
    // the line below was referenced from AbstractModel.java MVC CPU class example
    protected PropertyChangeSupport propertyChangeSupport;

// the initializations below were referenced from StopwatchModel.java from MVC Stopwatch example    
    protected double tickTimeInSeconds;
    private double angleDeltaPerSeconds;
    
    protected double secondsElapsed;
    
    public Timeline timeline;
    public KeyFrame keyFrame;
    
    // the method below was referenced from AbstractModel.java MVC CPU class example
    public FXMLAbstractModel(){
        propertyChangeSupport = new PropertyChangeSupport(this);
        tickTimeInSeconds = 0.1;
    }
    
    // the setUpTimer method referenced from AbstractModel.java from MVC CPU class example
    public void setupMonitor() {
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
            updateMonitor(); 
        });
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    // updateTimer referenced from StopwatchModel.java MVC stopwatch class example
    public void updateMonitor(){
        secondsElapsed += tickTimeInSeconds;
 //       return secondsElapsed * angleDeltaPerSeconds;
    }
    
    public double getTime(){
        return secondsElapsed;
    }
    
    // the method below was referenced from my previous challenge submission
    public boolean isRunning(){
        if(timeline != null){
            if(timeline.getStatus() == Animation.Status.RUNNING){
               return true;
            }
        }
        return false;
    }
    
    public void start(){
        timeline.play();
    }
    
    public void stop(){
        timeline.pause();
    }
    
    
    public void reset(){
        timeline.stop();
        secondsElapsed = 0.0;
        
    }
    
    // the ChangeListener and fireProperty methods below were referenced from AbstractModel.java MVC CPU class example
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
}
