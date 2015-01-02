package view;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import entities.Task;

public class TaskPanel extends AnchorPane {

  private Label taskNameLbl;

  public TaskPanel(Task t) {
    taskNameLbl = new Label();
    taskNameLbl.setText("test label " + t.getTaskName());

    initializePanel();
  }

  private void initializePanel() {
    setPrefHeight(30);
    setPrefWidth(250);

    getChildren().add(taskNameLbl);
  }

}
