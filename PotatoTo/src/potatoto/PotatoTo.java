package potatoto;

import controller.LoginController;
import controller.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

    ScreenManager screenManager = new ScreenManager();
    Underlayer scenePanel = new Underlayer(screenManager);
    screenManager.setMenuBar(scenePanel.getMenuBar());

    CountdownTimer timer = new CountdownTimer();
    TaskManager taskManager = new TaskManager();

    LoginPanel loginPanel = new LoginPanel();
    MainPanel mainPanel = new MainPanel(timer, taskManager);

    screenManager.addScreen(loginID, loginPanel);
    screenManager.addScreen(mainID, mainPanel);

    LoginController loginContrller = new LoginController();
    loginContrller.setScreenManager(screenManager);
    loginContrller.setPanel(loginPanel);

    MainController mainController = new MainController(mainPanel, timer, taskManager);
    mainController.setScreenManager(screenManager);

    screenManager.setScreen(loginID);

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
