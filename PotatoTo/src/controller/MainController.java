package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.CountdownTimer;
import potatoto.ScreenManager;
import view.MainPanel;

public class MainController implements ManagedScreen {

  private ScreenManager manager;
  private MainPanel panel;
  private CountdownTimer timer;

  
  public MainController(CountdownTimer timer) {
    this.timer = timer;
  }
  
  @Override
  public void setScreenManager(ScreenManager screenManager) {
    manager = screenManager;
  }
  
  public void setMainPanel(MainPanel panel) {
    this.panel = panel;
    
    initialize();
  }
  
  
  
  
  private void initialize() {    
    timer.setTimeLimit(30 * 60);
    
    panel.setTimerStartBtnHandler(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent t) {
        timer.start();
      }
    });
    
    panel.setTimerPauseBtnHandler(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent t) {
        timer.pauseResume();
      }
    });
    
    panel.setTimerStopBtnHandler(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent t) {
        timer.stop();
      }
    });
    
  }
  
}
