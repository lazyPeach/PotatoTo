package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.CountdownTimer;

public class TimerPreferences extends AnchorPane {

  private CountdownTimer timer;
  private Label titleLbl;
  private Label minutesLbl;
  private Label secondsLbl;
  private Slider minutesSlider;
  private Slider secondsSlider;
  private Button okButton;
  private Button cancelButton;

  public TimerPreferences(CountdownTimer timer) {
    this.timer = timer;

    titleLbl = new Label();
    minutesLbl = new Label();
    secondsLbl = new Label();
    minutesSlider = new Slider();
    secondsSlider = new Slider();
    okButton = new Button();
    cancelButton = new Button();

    initializePanel();
    initializeStyle();
    setButtonHandlers();
  }

  private void initializeStyle() {
    setId("timerPreferences");
    titleLbl.getStyleClass().add("timerPreferencesLabel");
    minutesLbl.getStyleClass().add("timerPreferencesLabel");
    secondsLbl.getStyleClass().add("timerPreferencesLabel");
    minutesSlider.getStyleClass().add("timerPreferencesSlider");
    secondsSlider.getStyleClass().add("timerPreferencesSlider");
    okButton.getStyleClass().add("timerPreferencesBtn");
    cancelButton.getStyleClass().add("timerPreferencesBtn");

  }

  private void initializePanel() {
    setPrefHeight(150);
    setPrefWidth(200);

    titleLbl.setText("Timer preferences");
    titleLbl.setLayoutX(55);

    minutesLbl.setText("minutes");
    minutesLbl.setLayoutX(10);
    minutesLbl.setLayoutY(40);

    secondsLbl.setText("seconds");
    secondsLbl.setLayoutX(10);
    secondsLbl.setLayoutY(80);

    minutesSlider.setLayoutX(60);
    minutesSlider.setLayoutY(40);
    minutesSlider.setMin(1);
    minutesSlider.setMax(59);
    minutesSlider.setShowTickLabels(true);
    minutesSlider.setShowTickMarks(true);
    minutesSlider.setValue(timer.getMinutes());
    
    secondsSlider.setLayoutX(60);
    secondsSlider.setLayoutY(80);
    secondsSlider.setMin(0);
    secondsSlider.setMax(59);
    secondsSlider.setShowTickLabels(true);
    secondsSlider.setShowTickMarks(true);
    secondsSlider.setValue(timer.getSeconds());

    okButton.setText("ok");
    okButton.setLayoutX(50);
    okButton.setLayoutY(120);
    okButton.setPrefHeight(35);
    okButton.setPrefWidth(35);

    cancelButton.setText("X ");
    cancelButton.setLayoutX(115);
    cancelButton.setLayoutY(120);
    cancelButton.setPrefHeight(35);
    cancelButton.setPrefWidth(35);

    getChildren().add(titleLbl);
    getChildren().add(minutesLbl);
    getChildren().add(secondsLbl);
    getChildren().add(minutesSlider);
    getChildren().add(secondsSlider);
    getChildren().add(okButton);
    getChildren().add(cancelButton);
  }

  private void setButtonHandlers() {
    okButton.setOnMouseClicked((MouseEvent t) -> {
      timer.setMinutes((int) minutesSlider.getValue());
      timer.setSeconds((int) secondsSlider.getValue());
      setVisible(false);
    });

    cancelButton.setOnMouseClicked((MouseEvent t) -> {
      setVisible(false);
    });
  }

}
