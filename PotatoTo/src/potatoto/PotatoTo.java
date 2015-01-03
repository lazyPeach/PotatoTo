package potatoto;

import controller.LoginController;
import controller.MainController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.CountdownTimer;
import model.TaskManager;
import util.Dimensions;
import view.LoginPanel;
import view.MainPanel;

public class PotatoTo extends Application {

  public static String loginID = "login";
  public static String mainID = "main";

  @Override
  public void start(Stage primaryStage) {

    CountdownTimer timer = new CountdownTimer();
    TaskManager taskManager = new TaskManager();

    ScreenManager screenManager = new ScreenManager();
    Underlayer scenePanel = new Underlayer(screenManager, timer);
    screenManager.setMenuBar(scenePanel.getMenuBar());

    LoginPanel loginPanel = new LoginPanel();
    MainPanel mainPanel = new MainPanel(timer, taskManager);

    screenManager.addScreen(loginID, loginPanel);
    screenManager.addScreen(mainID, mainPanel);

    LoginController loginContrller = new LoginController();
    loginContrller.setScreenManager(screenManager);
    loginContrller.setPanel(loginPanel);

    MainController mainController = new MainController(mainPanel, timer, taskManager);
    mainController.setScreenManager(screenManager);

    screenManager.setScreen(mainID);

    Group root = new Group();
    root.getChildren().add(scenePanel);

    Scene scene = new Scene(root, Dimensions.APPLICATION_WIDTH, Dimensions.APPLICATION_HEIGHT);
    scene.getStylesheets().add("resources/style/defaultStyle.css");

    primaryStage.setTitle("PotatoTo");
    primaryStage.setScene(scene);
    primaryStage.show();

    primaryStage.setOnCloseRequest((WindowEvent t) -> {
      timer.stop();
    });

  }
}
