package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class TimerPanel extends AnchorPane {
  
  private Button startBtn;
  private Button pauseBtn;
  private Button stopBtn;
  private DigitPanel minutesDecades;
  private DigitPanel minutesUnits;
  private DigitPanel secondsDecades;
  private DigitPanel secondsUnits;
  
  public TimerPanel() {
    startBtn = new Button("start");
    pauseBtn = new Button("pause");
    stopBtn = new Button("stop");
    minutesDecades = new DigitPanel();
    minutesUnits = new DigitPanel();
    secondsDecades = new DigitPanel();
    secondsUnits = new DigitPanel();

    
    initializePanel();
    
    setStyle("-fx-background-color: #0000ff;");
  }
  
  private void initializePanel() {
    setPrefWidth(300);
    setPrefHeight(150);
    
    startBtn.setPrefHeight(150/3);
    startBtn.setPrefWidth(150/3);
    startBtn.setLayoutX(300 - 50);
    startBtn.setLayoutY(0);
    startBtn.setStyle("-fx-background-color: #ffff00;"
              + "-fx-background-radius:0,0,0,0;"
              + "-fx-border-style:solid; -fx-border-width:1, 0,0,0");
    
    pauseBtn.setPrefHeight(150/3);
    pauseBtn.setPrefWidth(150/3);
    pauseBtn.setLayoutX(300 - 50);
    pauseBtn.setLayoutY(50);
    pauseBtn.setStyle("-fx-background-color: #ffa500;"
              + "-fx-background-radius:0,0,0,0;"
              + "-fx-border-style:solid; -fx-border-width:1px");

    stopBtn.setPrefHeight(150/3);
    stopBtn.setPrefWidth(150/3);
    stopBtn.setLayoutX(300 - 50);
    stopBtn.setLayoutY(100);
    stopBtn.setStyle("-fx-background-color: #38424b;"
             + "-fx-background-radius:0,0,0,0;"
             + "-fx-border-style:solid; -fx-border-width:1px");
    
    minutesDecades.setLayoutX(0);
    minutesDecades.setLayoutY(0);
    
    minutesUnits.setLayoutX(minutesDecades.getLayoutX() + minutesDecades.getPrefWidth());
    minutesUnits.setLayoutY(0);
    
    secondsDecades.setLayoutX(minutesUnits.getLayoutX() + minutesUnits.getPrefWidth());
    secondsDecades.setLayoutY(0);
    
    secondsUnits.setLayoutX(secondsDecades.getLayoutX() + secondsDecades.getPrefWidth());
    secondsUnits.setLayoutY(0);
    
    getChildren().add(minutesDecades);
    getChildren().add(minutesUnits);
    getChildren().add(secondsDecades);
    getChildren().add(secondsUnits);

    getChildren().add(startBtn);
    getChildren().add(pauseBtn);
    getChildren().add(stopBtn);
  }
  
  public void setStartBtnHandler(EventHandler<ActionEvent> handler) {
    startBtn.setOnAction(handler);
  }

  public void setPauseBtnHandler(EventHandler<ActionEvent> handler) {
    pauseBtn.setOnAction(handler);
  }
  
  public void setStopBtnHandler(EventHandler<ActionEvent> handler) {
    stopBtn.setOnAction(handler);
  }  
  
  public void testUpdate(int number) {
    minutesDecades.update(number);
  }
}
