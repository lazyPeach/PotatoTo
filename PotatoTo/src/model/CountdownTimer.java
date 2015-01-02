package model;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CountdownTimer {

  private enum State {
    STOP, RUN, PAUSE
  };

  private State timerState = State.STOP;
  private Timer timer;
  private int timeLimit;
  private int time;
  private IntegerProperty seconds = new SimpleIntegerProperty();
  private IntegerProperty minutes = new SimpleIntegerProperty();

  // In case a timer was previously started cancel it, reset the timer and start counting down.
  public void start() {
    if (timer != null) timer.cancel();
    
    timerState = State.RUN;
    timer = new Timer();
    time = timeLimit;
    timer.scheduleAtFixedRate(new TimerTask() {

      public void run() {
        // this is needed, otherwise an exception will be thrown when updating the GUI since JavaFX 
        // requires GUI update only from FX thread and this timer thread is a different one.
        Platform.runLater(new Runnable() {
          public void run() {
            time--;
            setSeconds(time % 60);
            setMinutes(time / 60);

            if (time < 0) {
              timer.cancel();
            }
          }
        });

      }
    }, 0, 1000);
  }

  // Cancels any started timer and resets the time counter.
  public void stop() {
    if (timer != null) timer.cancel();
    
    timerState = State.STOP;
    time = timeLimit;
  }


  public void pauseResume() {
    if (timerState == State.RUN) {
      timer.cancel();
      timerState = State.PAUSE;
    } else {
      resume();
      timerState = State.RUN;
    }
  }

  public void resume() {
    if (timer != null) timer.cancel();
    
    timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {

      public void run() {
        // this is needed, otherwise an exception will be thrown when updating the GUI since JavaFX 
        // requires GUI update only from FX thread and this timer thread is a different one.
        Platform.runLater(new Runnable() {
          public void run() {
            time--;
            setSeconds(time % 60);
            setMinutes(time / 60);

            if (time < 0) {
              timer.cancel();
            }
          }
        });

      }
    }, 0, 1000);
  }

  public void setTimeLimit(int timeLimit) {
    this.timeLimit = timeLimit;
  }
  
  public final double getSeconds() {
    return seconds.get();
  }

  public final void setSeconds(int value) {
    seconds.set(value);
  }

  public IntegerProperty secondsProperty() {
    return seconds;
  }
  
  public final double getMinutes() {
    return minutes.get();
  }

  public final void setMinutes(int value) {
    minutes.set(value);
  }

  public IntegerProperty minutesProperty() {
    return minutes;
  }
}
