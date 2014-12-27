package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;

public class MainPanel extends AnchorPane {

  private TimerPanel timerPanel;
  
  public MainPanel() {
    timerPanel = new TimerPanel();
    
    setPrefWidth(300);
    setPrefHeight(500);
    setStyle("-fx-background-color: #ffff00");
    
    getChildren().add(timerPanel);
    
//    timerPanel.setLayoutX(0);
//    timerPanel.setLayoutY(30);
  }
  
  public void setTimerStartBtnHandler(EventHandler<ActionEvent> handler) {
    timerPanel.setStartBtnHandler(handler);
  }

  public void setTimerPauseBtnHandler(EventHandler<ActionEvent> handler) {
    timerPanel.setPauseBtnHandler(handler);
  }
  
  public void setTimerStopBtnHandler(EventHandler<ActionEvent> handler) {
    timerPanel.setStopBtnHandler(handler);
  }  
  
}
