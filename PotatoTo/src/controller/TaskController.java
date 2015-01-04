package controller;

import model.TaskManager;
import potatoto.ScreenManager;
import view.DetailedTaskPanel;
import view.MainPanel;

public class TaskController implements ManagedScreen {

  private ScreenManager screenManager;
  private DetailedTaskPanel taskPanel;
  private TaskManager taskManager;

  public TaskController(DetailedTaskPanel taskPanel, TaskManager taskManager) {
    this.taskPanel = taskPanel;    
    this.taskManager = taskManager;
  }
  
  @Override
  public void setScreenManager(ScreenManager screenManager) {
    this.screenManager = screenManager;
  }

}
