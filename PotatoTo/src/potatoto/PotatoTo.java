package potatoto;

import controller.LoginController;
import controller.MainController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.LoginPanel;
import view.MainPanel;

public class PotatoTo extends Application {
  
  public static String loginID = "login";
  public static String mainID = "main";
  
  @Override
  public void start(Stage primaryStage) {
    ScreenManager screenManager = new ScreenManager();
    
    LoginPanel loginPanel = new LoginPanel();
    MainPanel mainPanel = new MainPanel();
    
    screenManager.addScreen(loginID, loginPanel);
    screenManager.addScreen(mainID, mainPanel);
    
    
    LoginController loginContrller = new LoginController();
    loginContrller.setScreenManager(screenManager);
    loginContrller.setPanel(loginPanel);
    
    MainController mainController = new MainController();
    mainController.setScreenManager(screenManager);
    mainController.setMainPanel(mainPanel);
    
    screenManager.setScreen(loginID);
    
    Group root = new Group();
    root.getChildren().addAll(screenManager);
    Scene scene = new Scene(root, 300, 500);
    
    primaryStage.setTitle("Hello World!");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  public static void main() {
    
  }
}
