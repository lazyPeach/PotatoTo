package view;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TaskPanel extends AnchorPane {

  private Label taskNameLbl;
  
  public TaskPanel(int i) {
    taskNameLbl = new Label();
    taskNameLbl.setText("test label " + String.valueOf(i));
    
    initializePanel();
  }
  
  private void initializePanel() {
    setPrefHeight(20);
    setPrefWidth(250);
    
    getChildren().add(taskNameLbl);
  }
  
  
  
}
