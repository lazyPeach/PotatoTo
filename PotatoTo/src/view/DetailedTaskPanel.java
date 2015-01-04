package view;

import entities.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
  private UpdateTaskPanel updateTaskPanel;

  private int selectedListIndex;

  public DetailedTaskPanel(TaskManager taskManager) {
    this.taskManager = taskManager;

    searchField = new TextField();
    addRemovePanel = new AnchorPane();
    listView = new ListView<>();
    addBtn = new Button();
    removeBtn = new Button();
    addTaskPanel = new AddTaskPanel();
    updateTaskPanel = new UpdateTaskPanel();

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

    populateTaskList(taskManager.getTaskList());

    listView.setPrefWidth(300);
    listView.setPrefHeight(500 - (searchField.getPrefHeight() + addRemovePanel.getPrefHeight()));
    listView.setLayoutY(searchField.getPrefHeight());

    addTaskPanel.setLayoutY(500 - (addRemovePanel.getPrefHeight() + addTaskPanel.getPrefHeight()));
    addTaskPanel.setVisible(false);

    updateTaskPanel.setVisible(false);

    getChildren().add(searchField);
    getChildren().add(addRemovePanel);
    getChildren().add(listView);
    getChildren().add(addTaskPanel);
    getChildren().add(updateTaskPanel);
  }

  private void populateTaskList(List<Task> taskList) {
    ArrayList<TaskPanel> taskPanelList = new ArrayList<>();
    for (Task task : taskList) {
      TaskPanel testPanel = new TaskPanel(task);
      taskPanelList.add(testPanel);
    }

    ObservableList<TaskPanel> observableTaskPanelList = FXCollections.observableArrayList(taskPanelList);
    listView.setItems(observableTaskPanelList);
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

  private List<Task> getSearchedTasks(String titleSearch) {
    List<Task> allTasks = taskManager.getTaskList();
    List<Task> retTasks = new ArrayList<>();

    System.out.println("title search is " + titleSearch);

    for (Task task : allTasks) {
      if (task.getTaskName().toLowerCase().contains(titleSearch.toLowerCase())) {
        retTasks.add(task);
      }
    }

    return retTasks;
  }

  private void initializeHandlers() {

    searchField.setOnKeyReleased((KeyEvent t) -> {
      List<Task> remainingTasks = getSearchedTasks(searchField.getText());
      populateTaskList(remainingTasks);
    });

    listView.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> {
      selectedListIndex = newValue.intValue();
      addTaskPanel.setVisible(false);

      Task selectedTask = listView.getItems().get(newValue.intValue()).getTask();
      updateTaskPanel.initializePanel(selectedTask);

    });

    listView.setOnMouseClicked((MouseEvent mouseEvent) -> {
      if (mouseEvent.getY() < listView.getPrefHeight() / 2) {
        updateTaskPanel.setLayoutY(mouseEvent.getY() + searchField.getPrefHeight());
      } else {
        updateTaskPanel.setLayoutY(mouseEvent.getY() + searchField.getPrefHeight() - updateTaskPanel.getPrefHeight());

      }
      updateTaskPanel.setVisible(true);
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
      estimatedSessionsBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);
      estimatedSessionsBox.getSelectionModel().selectFirst();

      priorityBox.setPrefWidth(190);
      priorityBox.setPrefHeight(20);
      priorityBox.setLayoutY(77);
      priorityBox.setLayoutX(100);
      priorityBox.getItems().addAll("low", "med", "high");
      priorityBox.getSelectionModel().selectFirst();

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
      task.setScheduledSessions((int) estimatedSessionsBox.getValue());
      task.setPriority((String) priorityBox.getValue());

      return task;
    }
  }

  private class UpdateTaskPanel extends AnchorPane {

    private Label taskNameLbl;
    private Label estimatedSessionsLbl;
    private Label priorityLbl;
    private ChoiceBox estimatedSessionsBox;
    private ChoiceBox priorityBox;
    private Button updateBtn;
    private Button cancelBtn;
    private Task task;

    UpdateTaskPanel() {
      taskNameLbl = new Label();
      estimatedSessionsLbl = new Label();
      priorityLbl = new Label();
      estimatedSessionsBox = new ChoiceBox();
      priorityBox = new ChoiceBox();
      updateBtn = new Button();
      cancelBtn = new Button();

      initializeElements();
      initializeBtnHandlers();
      initializeStyle();
    }

    private void initializeElements() {
      estimatedSessionsLbl.setText("Estimated: ");
      estimatedSessionsLbl.setLayoutY(30);
      estimatedSessionsLbl.setLayoutX(10);

      priorityLbl.setText("Priority: ");
      priorityLbl.setLayoutY(60);
      priorityLbl.setLayoutX(10);

      estimatedSessionsBox.setPrefWidth(190);
      estimatedSessionsBox.setPrefHeight(20);
      estimatedSessionsBox.setLayoutY(27);
      estimatedSessionsBox.setLayoutX(100);
      estimatedSessionsBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);

      priorityBox.setPrefWidth(190);
      priorityBox.setPrefHeight(20);
      priorityBox.setLayoutY(57);
      priorityBox.setLayoutX(100);
      priorityBox.getItems().addAll("high", "med", "low");

      updateBtn.setText("update");
      updateBtn.setPrefHeight(20);
      updateBtn.setPrefWidth(100);
      updateBtn.setLayoutX(25);
      updateBtn.setLayoutY(100);

      cancelBtn.setText("cancel");
      cancelBtn.setPrefHeight(20);
      cancelBtn.setPrefWidth(100);
      cancelBtn.setLayoutX(175);
      cancelBtn.setLayoutY(100);
    }

    public void initializePanel(Task task) {
      getChildren().clear();

      this.task = task;

      setPrefWidth(300);
      setPrefHeight(130);

      StackPane titleCentered = new StackPane();
      taskNameLbl.setText(task.getTaskName());
      titleCentered.getChildren().add(taskNameLbl);
      titleCentered.setStyle("-fx-background-color: transparent");
      titleCentered.setPrefHeight(20);
      titleCentered.setPrefWidth(200);
      titleCentered.setLayoutX(50);

      estimatedSessionsBox.getSelectionModel().select(task.getScheduledSessions());

      priorityBox.getSelectionModel().select(task.getPriority());

      getChildren().add(titleCentered);
      getChildren().add(estimatedSessionsLbl);
      getChildren().add(priorityLbl);
      getChildren().add(estimatedSessionsBox);
      getChildren().add(priorityBox);
      getChildren().add(updateBtn);
      getChildren().add(cancelBtn);
    }

    private void initializeStyle() {
      setId("addTaskPanel");
      taskNameLbl.getStyleClass().add("addTaskLabel");
      estimatedSessionsLbl.getStyleClass().add("addTaskLabel");
      priorityLbl.getStyleClass().add("addTaskLabel");
      estimatedSessionsBox.getStyleClass().add("addTaskBox");
      priorityBox.getStyleClass().add("addTaskBox");
      updateBtn.getStyleClass().add("taskButtons");
      cancelBtn.getStyleClass().add("taskButtons");
    }

    private void initializeBtnHandlers() {
      updateBtn.setOnMouseClicked((MouseEvent t) -> {
        task.setScheduledSessions((int) estimatedSessionsBox.getValue());
        task.setPriority((String) priorityBox.getValue());
        setVisible(false);
      });

      cancelBtn.setOnMouseClicked((MouseEvent t) -> {
        setVisible(false);
      });
    }

  }

}
