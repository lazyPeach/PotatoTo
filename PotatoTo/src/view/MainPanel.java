package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.CountdownTimer;
import model.TaskManager;

public class MainPanel extends AnchorPane {

  private TimerPanel timerPanel;
  private TaskListPanel taskListPanel;
  
  private CountdownTimer timer;
  private TaskManager taskManager;
  
  public MainPanel(CountdownTimer timer, TaskManager taskManager) {
    this.timer = timer;
    this.taskManager = taskManager;
    timerPanel = new TimerPanel(timer);
    taskListPanel = new TaskListPanel(taskManager);
    
    initializePanel();
    
    this.setId("mainPanel");
  }
  
  private void initializePanel() {
    setPrefWidth(300);
    setPrefHeight(500);
  
    taskListPanel.setLayoutX(0);
    taskListPanel.setLayoutY(timerPanel.getPrefHeight());
    
    getChildren().add(timerPanel);
    getChildren().add(taskListPanel);    
  }
  
  public void startTimer() {
    timerPanel.startTimer();
  }
  
  public void pauseResumeTimer() {
    timerPanel.pauseResumeTimer();
  }
  
  public void stopTimer() {
    timerPanel.stopTimer();
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
