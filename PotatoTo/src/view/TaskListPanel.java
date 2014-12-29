package view;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
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

public class TaskListPanel extends AnchorPane {

  private Pane sizeManagerPane;
  private Polygon triangleDecoration;
  private boolean shouldExtend = true;

  public TaskListPanel() {
    sizeManagerPane = new Pane();

    initializeSizeManagerPane();

    initializePanel();

    setStyle("-fx-background-color: #00ff00;");
  }

  private void initializeSizeManagerPane() {
    sizeManagerPane.setPrefWidth(300);
    sizeManagerPane.setPrefHeight(15);
    sizeManagerPane.setStyle("-fx-background-color: #ffff00;");

    triangleDecoration = new Polygon(new double[]{150, 5, 153, 10, 147, 10});
    triangleDecoration.setFill(Color.BLACK);
    triangleDecoration.setStroke(Color.BLACK);

    sizeManagerPane.getChildren().add(triangleDecoration);

    // i want this to be handled internally because it doesn't have anything to do with model so 
    // there is no reason to handle this event from controller
    sizeManagerPane.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent t) {
        System.out.println("clicked on extend");
        if (shouldExtend) {
          triggerExtend();
        } else {
          triggerCompress();
        }
        shouldExtend = !shouldExtend;

      }
    });

  }

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

  private void initializePanel() {
    getChildren().clear();
    
    setPrefWidth(300);
    setPrefHeight(350);

    ArrayList<TaskPanel> tasksList = new ArrayList<>();

    for (int i = 0; i < 20; i++) {
      TaskPanel testPanel = new TaskPanel(i);
      tasksList.add(testPanel);
    }
    ObservableList<TaskPanel> tasks = FXCollections.observableArrayList(tasksList);
    ListView<TaskPanel> listView = new ListView<>(tasks);
    listView.setPrefWidth(300);
    listView.setPrefHeight(335);
    listView.setLayoutY(sizeManagerPane.getPrefHeight());

    getChildren().add(sizeManagerPane);
    getChildren().add(listView);
  }

  private void initializeExtendedPanel() {
    getChildren().clear();

    setPrefHeight(500);

    ArrayList<TaskPanel> tasksList = new ArrayList<>();

    for (int i = 0; i < 20; i++) {
      TaskPanel testPanel = new TaskPanel(i);
      tasksList.add(testPanel);
    }
    ObservableList<TaskPanel> tasks = FXCollections.observableArrayList(tasksList);
    ListView<TaskPanel> listView = new ListView<>(tasks);
    listView.setPrefWidth(300);
    listView.setPrefHeight(485);
    listView.setLayoutY(sizeManagerPane.getPrefHeight());

    getChildren().add(sizeManagerPane);
    getChildren().add(listView);

  }

}
