package view;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import entities.Task;
import javafx.scene.layout.HBox;

public class TaskPanel extends HBox {

  private Label taskNameLbl;
  private Label finishedSessionsLbl;
  private Label scheduledSessionsLbl;
  private Task task;

  public TaskPanel() {
    this(null);
  }

  public TaskPanel(Task task) {
    taskNameLbl = new Label();
    finishedSessionsLbl = new Label();
    scheduledSessionsLbl = new Label();
    this.task = task;

    initializePanel();
    initializeStyle();
  }

  private void initializeStyle() {
    setId("simpleTaskPanel");
    String idName = "simpleTaskPanel_" + task.getPriority();
    taskNameLbl.setId(idName);
    finishedSessionsLbl.setId(idName);
    scheduledSessionsLbl.setId(idName);
  }

  private void initializePanel() {
    setPrefHeight(20);

    if (task == null) {
      taskNameLbl.setText("Task name");
      finishedSessionsLbl.setText("D");
      scheduledSessionsLbl.setText("E");
    } else {
      taskNameLbl.setText(task.getTaskName());
      finishedSessionsLbl.setText(String.valueOf(task.getFinishedSessions()));
      scheduledSessionsLbl.setText(String.valueOf(task.getScheduledSessions()));
    }

    taskNameLbl.setPrefWidth(200);
    finishedSessionsLbl.setPrefWidth(20);
    scheduledSessionsLbl.setPrefWidth(20);

    getChildren().add(taskNameLbl);
    getChildren().add(finishedSessionsLbl);
    getChildren().add(scheduledSessionsLbl);
  }
  
  public Task getTask() {
    return task;
  }

}
