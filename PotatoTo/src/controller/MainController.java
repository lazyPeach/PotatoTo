package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.CountdownTimer;
import model.TaskManager;
import potatoto.ScreenManager;
import view.MainPanel;

public class MainController implements ManagedScreen {

  private ScreenManager manager;
  private MainPanel mainPanel;

  private CountdownTimer timer;
  private TaskManager taskManager;

  public MainController(MainPanel mainPanel, CountdownTimer timer, TaskManager taskManager) {
    this.mainPanel = mainPanel;
    this.timer = timer;
    this.taskManager = taskManager;

    setTimerHandlers();
  }

  @Override
  public void setScreenManager(ScreenManager screenManager) {
    manager = screenManager;
  }

  private void setTimerHandlers() {
    timer.setTimeLimit(30 * 60);

    mainPanel.setTimerStartBtnHandler((ActionEvent t) -> {
      mainPanel.startTimer();
    });

    mainPanel.setTimerPauseBtnHandler((ActionEvent t) -> {
      mainPanel.pauseResumeTimer();
    });

    mainPanel.setTimerStopBtnHandler((ActionEvent t) -> {
      mainPanel.stopTimer();
    });
  }
}
