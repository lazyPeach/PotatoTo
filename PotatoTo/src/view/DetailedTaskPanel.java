package view;

import entities.Task;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import model.TaskManager;

public class DetailedTaskPanel extends AnchorPane {

  private TaskManager taskManager;
  private TextField searchField;
  private AnchorPane addRemovePanel;
  private ListView<TaskPanel> listView;
  private Button addBtn;
  private Button removeBtn;
  private AddTaskPanel addTaskPanel;

  private int selectedListIndex;

  public DetailedTaskPanel(TaskManager taskManager) {
    this.taskManager = taskManager;

    searchField = new TextField();
    addRemovePanel = new AnchorPane();
    listView = new ListView<>();
    addBtn = new Button();
    removeBtn = new Button();
    addTaskPanel = new AddTaskPanel();

    selectedListIndex = -1;

    initializePanel();
    initializeStyle();
    initializeHandlers();
  }

  private void initializePanel() {
    setPrefHeight(500);
    setPrefWidth(300);

    searchField.setLayoutY(1);
    searchField.setPrefHeight(40);
    searchField.setPrefWidth(300);
    searchField.setPromptText("task name");

    addRemovePanel.setPrefHeight(20);
    addRemovePanel.setPrefWidth(300);
    addRemovePanel.setLayoutY(500 - addRemovePanel.getPrefHeight());
    initializeAddRemovePanel();

    ArrayList<TaskPanel> taskPanelList = new ArrayList<>();
    List<Task> taskList = taskManager.getTaskList();

    for (Task task : taskList) {
      TaskPanel testPanel = new TaskPanel(task);
      taskPanelList.add(testPanel);
    }

    ObservableList<TaskPanel> observableTaskPanelList = FXCollections.observableArrayList(taskPanelList);
    listView.setItems(observableTaskPanelList);
    listView.setPrefWidth(300);
    listView.setPrefHeight(500 - (searchField.getPrefHeight() + addRemovePanel.getPrefHeight()));
    listView.setLayoutY(searchField.getPrefHeight());

    addTaskPanel.setLayoutY(500 - (addRemovePanel.getPrefHeight() + addTaskPanel.getPrefHeight()));
    addTaskPanel.setVisible(false);

    getChildren().add(searchField);
    getChildren().add(addRemovePanel);
    getChildren().add(listView);
    getChildren().add(addTaskPanel);

  }

  private void initializeAddRemovePanel() {
    addBtn.setText("add new task");
    addBtn.setPrefHeight(20);
    addBtn.setPrefWidth(100);
    addBtn.setLayoutX(25);

    removeBtn.setText("remove task");
    removeBtn.setPrefHeight(20);
    removeBtn.setPrefWidth(100);
    removeBtn.setLayoutX(175);

    addRemovePanel.getChildren().add(addBtn);
    addRemovePanel.getChildren().add(removeBtn);
  }

  private void initializeStyle() {
    setId("detailedTaskPanel");
    searchField.setId("searchField");
    addRemovePanel.setStyle("-fx-background-color: transparent");
    listView.setId("taskList");
    addBtn.getStyleClass().add("taskButtons");
    removeBtn.getStyleClass().add("taskButtons");

  }

  private void initializeHandlers() {
    searchField.setOnKeyReleased((KeyEvent t) -> {
      System.out.println("until now you typed " + searchField.getText());
    });

    listView.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> {
      selectedListIndex = newValue.intValue();
      TaskPanel selectedPanel = listView.getItems().get(newValue.intValue());
    });

    addBtn.setOnMouseClicked((MouseEvent t) -> {
      if (addTaskPanel.isVisible()) {
        Task newTask = addTaskPanel.getNewTask();
        listView.getItems().add(new TaskPanel(newTask));
        taskManager.createTask(newTask);
        addTaskPanel.setVisible(false);

      } else {
        addTaskPanel.setVisible(true);

        TranslateTransition tt = new TranslateTransition(Duration.millis(1000), addTaskPanel);
        tt.setFromY(200);
        tt.setToY(0);
        tt.play();
      }

    });

    removeBtn.setOnMouseClicked((MouseEvent t) -> {
      if (selectedListIndex < 0) {
        return;
      }

      taskManager.removeTask(listView.getItems().get(selectedListIndex).getTask());
      listView.getItems().remove(selectedListIndex);
      selectedListIndex = -1;
    });
  }

  private class AddTaskPanel extends AnchorPane {

    private Label taskNameLbl;
    private Label estimatedSessionsLbl;
    private Label priorityLbl;
    private TextField taskNameField;
    private ChoiceBox estimatedSessionsBox;
    private ChoiceBox priorityBox;

    AddTaskPanel() {
      taskNameLbl = new Label();
      estimatedSessionsLbl = new Label();
      priorityLbl = new Label();
      taskNameField = new TextField();
      estimatedSessionsBox = new ChoiceBox();
      priorityBox = new ChoiceBox();

      initializePanel();
      initializeStyle();
    }

    private void initializePanel() {
      setPrefWidth(300);
      setPrefHeight(120);

      taskNameLbl.setText("Task name:");
      taskNameLbl.setLayoutY(20);
      taskNameLbl.setLayoutX(10);

      estimatedSessionsLbl.setText("Estimated: ");
      estimatedSessionsLbl.setLayoutY(50);
      estimatedSessionsLbl.setLayoutX(10);
      
      priorityLbl.setText("Priority: ");
      priorityLbl.setLayoutY(80);
      priorityLbl.setLayoutX(10);
      
      taskNameField.setPrefWidth(190);
      taskNameField.setPrefHeight(20);
      taskNameField.setLayoutY(17);
      taskNameField.setLayoutX(100);
      
      estimatedSessionsBox.setPrefWidth(190);
      estimatedSessionsBox.setPrefHeight(20);
      estimatedSessionsBox.setLayoutY(47);
      estimatedSessionsBox.setLayoutX(100);
      estimatedSessionsBox.getItems().addAll(1,2,3,4,5,6,7,8,9);
      
      priorityBox.setPrefWidth(190);
      priorityBox.setPrefHeight(20);
      priorityBox.setLayoutY(77);
      priorityBox.setLayoutX(100);
      priorityBox.getItems().addAll("high", "med", "low");
      
      getChildren().add(taskNameLbl);
      getChildren().add(estimatedSessionsLbl);
      getChildren().add(priorityLbl);
      getChildren().add(taskNameField);
      getChildren().add(estimatedSessionsBox);
      getChildren().add(priorityBox);
    }

    private void initializeStyle() {
      setId("addTaskPanel");
      taskNameLbl.getStyleClass().add("addTaskLabel");
      estimatedSessionsLbl.getStyleClass().add("addTaskLabel");
      priorityLbl.getStyleClass().add("addTaskLabel");
      taskNameField.setId("addTaskField");
      estimatedSessionsBox.getStyleClass().add("addTaskBox");
      priorityBox.getStyleClass().add("addTaskBox");
    }
    
    public Task getNewTask() {
      Task task = new Task();
      
      task.setTaskName(taskNameField.getText());
      task.setFinishedSessions(0);
      
      if (estimatedSessionsBox.getValue() == null)
        task.setScheduledSessions(0);
      else
        task.setScheduledSessions((int)estimatedSessionsBox.getValue());
      
      if (priorityBox.getValue() == null)
        task.setPriority("low");
      else
        task.setPriority((String) priorityBox.getValue());

      return task;
    }
  }
  
}
