package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.CountdownTimer;
import model.TaskManager;
import potatoto.ScreenManager;
import view.MainPanel;

public class MainController implements ManagedScreen {

  private ScreenManager manager;
  private MainPanel panel;
  
  private CountdownTimer timer;
  private TaskManager taskManager;
  
  public MainController(CountdownTimer timer, TaskManager taskManager) {
    this.timer = timer;
    this.taskManager = taskManager;
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
