package view;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import entities.Task;
import java.util.List;
import model.TaskManager;

public class TaskListPanel extends AnchorPane {

  private Pane sizeManagerPanel;
  private Polygon triangleDecoration;
  private boolean shouldExtend = true;
  private List<Task> taskList;

  public TaskListPanel(List<Task> taskList) {
    this.taskList = taskList;
    sizeManagerPanel = new Pane();

    initializeSizeManagerPanel();

    initializePanel();

    setStyle("-fx-background-color: #00ff00;");
  }

  private void initializeSizeManagerPanel() {
    sizeManagerPanel.setPrefWidth(300);
    sizeManagerPanel.setPrefHeight(15);
    setStyle("-fx-background-color: rgba(0,0,0,0)");

    triangleDecoration = new Polygon(new double[]{150, 5, 153, 10, 147, 10});
    triangleDecoration.setFill(Color.BLACK);
    triangleDecoration.setStroke(Color.WHITE);

    sizeManagerPanel.getChildren().add(triangleDecoration);

    // This should be handled internally because it doesn't have anything to do with model so 
    // there is no reason to handle this event from controller
    sizeManagerPanel.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent t) {
        if (shouldExtend) triggerExtend();
        else triggerCompress();
        
        shouldExtend = !shouldExtend;
      }
    });
  }

  // extends the task list panel
  private void triggerExtend() {
    TranslateTransition tt = new TranslateTransition(Duration.millis(1000), this);
    tt.setFromY(0);
    tt.setToY(-150);
    tt.play();

    RotateTransition rt = new RotateTransition(Duration.millis(1000), triangleDecoration);
    rt.setFromAngle(0);
    rt.setToAngle(180);
    rt.play();
    
    initializeExtendedPanel();
  }

  // compresses the task list panel
  private void triggerCompress() {
    TranslateTransition tt = new TranslateTransition(Duration.millis(1000), this);
    tt.setFromY(-150);
    tt.setToY(0);
    tt.play();

    RotateTransition rt = new RotateTransition(Duration.millis(1000), triangleDecoration);
    rt.setFromAngle(180);
    rt.setToAngle(360);
    rt.play();

    initializePanel();
  }

  // todo - refactor this bullsh...
  private void initializePanel() {
    getChildren().clear();

    setPrefWidth(300);
    setPrefHeight(350);
    
    ArrayList<TaskPanel> taskPanelList = new ArrayList<>();

    for (Task task : taskList) {
      TaskPanel testPanel = new TaskPanel(task);
      taskPanelList.add(testPanel);
    }

    ObservableList<TaskPanel> observableTaskPanelList = FXCollections.observableArrayList(taskPanelList);
    ListView<TaskPanel> listView = new ListView<>(observableTaskPanelList);
    listView.setPrefWidth(300);
    listView.setPrefHeight(335);
    listView.setLayoutY(sizeManagerPanel.getPrefHeight());

    getChildren().add(sizeManagerPanel);
    getChildren().add(listView);
  }

  private void initializeExtendedPanel() {
    getChildren().clear();

    setPrefHeight(500);

    ArrayList<TaskPanel> tasksList = new ArrayList<>();

    ArrayList<TaskPanel> taskPanelList = new ArrayList<>();

    for (Task task : taskList) {
      TaskPanel testPanel = new TaskPanel(task);
      taskPanelList.add(testPanel);
    }
    
    ObservableList<TaskPanel> observableTaskPanelList = FXCollections.observableArrayList(taskPanelList);
    ListView<TaskPanel> listView = new ListView<>(observableTaskPanelList);
    listView.setPrefWidth(300);
    listView.setPrefHeight(485);
    listView.setLayoutY(sizeManagerPanel.getPrefHeight());

    getChildren().add(sizeManagerPanel);
    getChildren().add(listView);
  }

}
