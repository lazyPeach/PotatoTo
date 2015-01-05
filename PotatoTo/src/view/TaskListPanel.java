package view;

import java.util.ArrayList;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import entities.Task;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.TaskManager;

public class TaskListPanel extends AnchorPane {

  private Pane sizeManagerPanel;
  private Polygon triangleDecoration;
  private ListView<TaskPanel> listView;
  private HBox header;

  private boolean shouldExtend = false;
  private TaskManager taskManager;

  public TaskListPanel(TaskManager taskManager) {
    this.taskManager = taskManager;
    sizeManagerPanel = new Pane();
    listView = new ListView<>();
    header = new HBox();

    initializeSizeManagerPanel();
    initializeHeader();
    initializePanel();
    initializeStyle();
  }

  private void initializeStyle() {
    setId("taskPanel");
    triangleDecoration.setId("triangleDecoration");
    sizeManagerPanel.setId("sizeManagerPanel");
    listView.setId("taskList");
    header.setId("taskHeader");
  }

  private void initializeHeader() {
    header.setPrefWidth(300);
    header.setPrefHeight(20);

    Label taskNameLbl = new Label("Task name");
    Label finishedSessionsLbl = new Label("D");
    Label scheduledSessionsLbl = new Label("E");

    taskNameLbl.setPrefWidth(210);
    finishedSessionsLbl.setPrefWidth(20);
    scheduledSessionsLbl.setPrefWidth(20);

    taskNameLbl.setId("taskHeader");
    finishedSessionsLbl.setId("taskHeader");
    scheduledSessionsLbl.setId("taskHeader");

    header.getChildren().add(taskNameLbl);
    header.getChildren().add(finishedSessionsLbl);
    header.getChildren().add(scheduledSessionsLbl);
  }

  private void initializeSizeManagerPanel() {
    sizeManagerPanel.setPrefWidth(300);
    sizeManagerPanel.setPrefHeight(15);

    triangleDecoration = new Polygon(new double[]{150, 5, 153, 10, 147, 10});

    sizeManagerPanel.getChildren().add(triangleDecoration);

    // This should be handled internally because it doesn't have anything to do with model so 
    // there is no reason to handle this event from controller
    sizeManagerPanel.setOnMouseClicked((MouseEvent t) -> {
      if (shouldExtend) {
        triggerExtend();
      } else {
        triggerCompress();
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

    initializePanel();

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

    tt.setOnFinished((ActionEvent t) -> {
      initializePanel();
    });

  }

  private void initializePanel() {
    getChildren().clear();

    if (shouldExtend) {
      setPrefWidth(300);
      setPrefHeight(500);
    } else {
      setPrefWidth(300);
      setPrefHeight(350);
    }

    ArrayList<TaskPanel> taskPanelList = new ArrayList<>();

    List<Task> taskList = taskManager.getTaskList();

    for (Task task : taskList) {
      TaskPanel testPanel = new TaskPanel(task);
      taskPanelList.add(testPanel);
    }

    ObservableList<TaskPanel> observableTaskPanelList = FXCollections.observableArrayList(taskPanelList);

    listView.setItems(observableTaskPanelList);
    listView.setPrefWidth(300);
    listView.setPrefHeight(getPrefHeight() - sizeManagerPanel.getPrefHeight() - header.getPrefHeight());
    listView.setLayoutY(sizeManagerPanel.getPrefHeight() + header.getPrefHeight());

    header.setLayoutY(sizeManagerPanel.getPrefHeight());

    getChildren().add(sizeManagerPanel);
    getChildren().add(header);
    getChildren().add(listView);

    shouldExtend = !shouldExtend;
  }

}
