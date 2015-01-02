package potatoto;

import controller.LoginController;
import controller.MainController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.CountdownTimer;
import model.TaskManager;
import view.LoginPanel;
import view.MainPanel;

public class PotatoTo extends Application {
  
  public static String loginID = "login";
  public static String mainID = "main";
  
  @Override
  public void start(Stage primaryStage) {
    ScreenManager screenManager = new ScreenManager();
    
    CountdownTimer timer = new CountdownTimer();
    TaskManager taskManager = new TaskManager();
    
    LoginPanel loginPanel = new LoginPanel();
    MainPanel mainPanel = new MainPanel(timer, taskManager);
    
    screenManager.addScreen(loginID, loginPanel);
    screenManager.addScreen(mainID, mainPanel);
    
    LoginController loginContrller = new LoginController();
    loginContrller.setScreenManager(screenManager);
    loginContrller.setPanel(loginPanel);
    
    MainController mainController = new MainController(timer, taskManager);
    mainController.setScreenManager(screenManager);
    mainController.setMainPanel(mainPanel);
    
    screenManager.setScreen(mainID);
    
    Group root = new Group();
    root.getChildren().addAll(screenManager);
    Scene scene = new Scene(root, 300, 500);
    
    
    
    primaryStage.setTitle("Hello World!");
    primaryStage.setScene(scene);
    primaryStage.show();
    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

      @Override
      public void handle(WindowEvent t) {
        timer.stop();
        //srialize taskList
        System.out.println("trying to close biatce");
      }
    });
  }
  
}
