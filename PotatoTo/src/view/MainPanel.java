package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.CountdownTimer;

public class MainPanel extends AnchorPane {

  private TimerPanel timerPanel;
  private TaskListPanel taskListPanel;
  
  private CountdownTimer timer;
  
  public MainPanel(CountdownTimer timer) {
    this.timer = timer;
    timerPanel = new TimerPanel(timer);
    taskListPanel = new TaskListPanel();
    
    initializePanel();

    setStyle("-fx-background-color: #ffff00");
  }
  
  private void initializePanel() {
    setPrefWidth(300);
    setPrefHeight(500);
  
    taskListPanel.setLayoutX(0);
    taskListPanel.setLayoutY(timerPanel.getPrefHeight());
    
    getChildren().add(timerPanel);
    getChildren().add(taskListPanel);
    
    Button testBtn = new Button("test me test me");
    getChildren().add(testBtn);
    testBtn.setVisible(false);
    testBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent t) {
        System.out.println("clicked on test");
      }
    });
    
    testBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent t) {
        System.out.println("mouse enterred the test area beep beep");
      }
    });
    
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
