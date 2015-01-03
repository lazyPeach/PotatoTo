package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import model.CountdownTimer;

public class TimerPanel extends AnchorPane {

  private Button startBtn;
  private Button pauseBtn;
  private Button stopBtn;
  private DigitPanel minutesDecades;
  private DigitPanel minutesUnits;
  private DigitPanel secondsDecades;
  private DigitPanel secondsUnits;
  private CountdownTimer timer;

  public TimerPanel(CountdownTimer timer) {
    this.timer = timer;
    startBtn = new Button("start");
    pauseBtn = new Button("pause");
    stopBtn = new Button("stop");
    minutesDecades = new DigitPanel();
    minutesUnits = new DigitPanel();
    secondsDecades = new DigitPanel();
    secondsUnits = new DigitPanel();

    initializePanel();
    initializeStyle();
  }

  private void initializeStyle() {
    setId("timerStop");
    startBtn.getStyleClass().add("timerBtn");
    pauseBtn.getStyleClass().add("timerBtn");
    stopBtn.getStyleClass().add("timerBtn");

    startBtn.setId("timerStart");
    pauseBtn.setId("timerPause");
    stopBtn.setId("timerStop");
  }

  private void initializePanel() {
    setPrefWidth(300);
    setPrefHeight(150);

    startBtn.setPrefHeight(150 / 3);
    startBtn.setPrefWidth(150 / 3);
    startBtn.setLayoutX(300 - 50);
    startBtn.setLayoutY(0);

    pauseBtn.setPrefHeight(150 / 3);
    pauseBtn.setPrefWidth(150 / 3);
    pauseBtn.setLayoutX(300 - 50);
    pauseBtn.setLayoutY(50);

    stopBtn.setPrefHeight(150 / 3);
    stopBtn.setPrefWidth(150 / 3);
    stopBtn.setLayoutX(300 - 50);
    stopBtn.setLayoutY(100);

    minutesDecades.setLayoutX(0);
    minutesDecades.setLayoutY(0);

    minutesUnits.setLayoutX(minutesDecades.getLayoutX() + minutesDecades.getPrefWidth());
    minutesUnits.setLayoutY(0);

    DelimiterPanel delimiterPanel = new DelimiterPanel();
    delimiterPanel.setLayoutX(minutesUnits.getLayoutX() + minutesUnits.getPrefWidth());
    delimiterPanel.setLayoutY(0);

    secondsDecades.setLayoutX(delimiterPanel.getLayoutX() + delimiterPanel.getPrefWidth());
    secondsDecades.setLayoutY(0);

    secondsUnits.setLayoutX(secondsDecades.getLayoutX() + secondsDecades.getPrefWidth());
    secondsUnits.setLayoutY(0);

    getChildren().add(minutesDecades);
    getChildren().add(minutesUnits);
    getChildren().add(secondsDecades);
    getChildren().add(secondsUnits);
    getChildren().add(delimiterPanel);

    getChildren().add(startBtn);
    getChildren().add(pauseBtn);
    getChildren().add(stopBtn);

    addTimerListeners();
  }

  private void addTimerListeners() {
    timer.secondsProperty().addListener((ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> {
      secondsUnits.update(newValue.intValue() % 10);
      secondsDecades.update(newValue.intValue() / 10);
    });

    timer.minutesProperty().addListener((ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> {
      minutesUnits.update(newValue.intValue() % 10);
      minutesDecades.update(newValue.intValue() / 10);
    });
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

  public void startTimer() {
    timer.start();
    updateTimerPanelStyle(timer.getTimerState());
  }

  public void pauseResumeTimer() {
    timer.pauseResume();
    updateTimerPanelStyle(timer.getTimerState());
    if (timer.getTimerState() == CountdownTimer.State.PAUSE) pauseBtn.setText("resume");
    else pauseBtn.setText("pause");
  }

  public void stopTimer() {
    timer.stop();
    updateTimerPanelStyle(timer.getTimerState());
  }

  private void updateTimerPanelStyle(CountdownTimer.State newState) {

    switch (newState) {
      case STOP:
        setId("timerStop");
        break;
      case RUN:
        setId("timerStart");
        break;
      case PAUSE:
        setId("timerPause");
        break;
    }

  }

}

class DelimiterPanel extends AnchorPane {

  DelimiterPanel() {
    initializePanel();
  }

  private void initializePanel() {
    setPrefWidth(10);
    setPrefHeight(150);

    Polygon firstDelimiter = new Polygon(new double[]{2, 50, 8, 50, 8, 60, 2, 60});
    Polygon secondDelimiter = new Polygon(new double[]{2, 95, 8, 95, 8, 105, 2, 105});

    getChildren().add(firstDelimiter);
    getChildren().add(secondDelimiter);

    setId("digitPanel");
    firstDelimiter.getStyleClass().add("digit");
    secondDelimiter.getStyleClass().add("digit");

  }

}
