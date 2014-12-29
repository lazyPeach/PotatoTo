package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import potatoto.ScreenManager;
import view.MainPanel;

public class MainController implements ManagedScreen {

  private ScreenManager manager;
  private MainPanel panel;
  
  @Override
  public void setScreenManager(ScreenManager screenManager) {
    manager = screenManager;
  }
  
  public void setMainPanel(MainPanel panel) {
    this.panel = panel;
    
    initialize();
  }
  
  private void initialize() {
    panel.setTimerStartBtnHandler(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent t) {
        System.out.println("clicked on start");
      }
    });
    
    panel.setTimerPauseBtnHandler(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent t) {
        System.out.println("cliecked on pause");
      }
    });
    
    panel.setTimerStopBtnHandler(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent t) {
        System.out.println("clicked on stop");
      }
    });
    
  }
  
}
