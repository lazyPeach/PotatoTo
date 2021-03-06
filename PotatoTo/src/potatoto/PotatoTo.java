package potatoto;

import controller.LoginController;
import controller.MainController;
import controller.TaskController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.CountdownTimer;
import model.Styler;
import model.TaskManager;
import util.Dimensions;
import view.DetailedTaskPanel;
import view.LoginPanel;
import view.MainPanel;
import view.ReportPanel;

public class PotatoTo extends Application {

  public static String loginID = "login";
  public static String mainID = "main";
  public static String taskID = "task";
  public static String reportID = "report";

  @Override
  public void start(Stage primaryStage) {

    CountdownTimer timer = new CountdownTimer();
    TaskManager taskManager = new TaskManager();
    Styler styler = new Styler();

    ScreenManager screenManager = new ScreenManager();
    Underlayer scenePanel = new Underlayer(screenManager, timer, styler);
    screenManager.setMenuBar(scenePanel.getMenuBar());

    LoginPanel loginPanel = new LoginPanel();
    MainPanel mainPanel = new MainPanel(timer, taskManager);
    DetailedTaskPanel taskPanel = new DetailedTaskPanel(taskManager);
    ReportPanel reportPanel = new ReportPanel(taskManager);
    
    screenManager.addScreen(loginID, loginPanel);
    screenManager.addScreen(mainID, mainPanel);
    screenManager.addScreen(taskID, taskPanel);
    screenManager.addScreen(reportID, reportPanel);
    
    LoginController loginContrller = new LoginController();
    loginContrller.setScreenManager(screenManager);
    loginContrller.setPanel(loginPanel);

    MainController mainController = new MainController(mainPanel, timer, taskManager);
    mainController.setScreenManager(screenManager);
    
    TaskController taskController = new TaskController(taskPanel, taskManager);
    taskController.setScreenManager(screenManager);
    
    screenManager.setScreen(reportID);

    Group root = new Group();
    root.getChildren().add(scenePanel);

    Scene scene = new Scene(root, Dimensions.APPLICATION_WIDTH, Dimensions.APPLICATION_HEIGHT);
    scene.getStylesheets().add(Styler.JAMAICA_STYLE);

    primaryStage.setTitle("PotatoTo");
    primaryStage.setScene(scene);
    primaryStage.show();

    primaryStage.setOnCloseRequest((WindowEvent t) -> {
      timer.stop();
    });
    
    styler.styleProperty().addListener((ObservableValue<? extends String> ov, String oldStyle, String newStyle) -> {
      scene.getStylesheets().remove(oldStyle);
      scene.getStylesheets().add(newStyle);
      
    });
  }
  
}
