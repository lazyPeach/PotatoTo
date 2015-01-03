package potatoto;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import model.CountdownTimer;
import view.TimerPreferences;

public class Underlayer extends AnchorPane {

  private MenuBar menuBar;
  private Menu menuLogOut;
  private Menu menuTimer;
  
  private MenuItem logOutItem;
  private MenuItem timerItem;
  
  private ScreenManager screenManager;
  private CountdownTimer timer;

  public Underlayer(ScreenManager screenManager, CountdownTimer timer) {
    this.screenManager = screenManager;
    this.timer = timer;
    menuBar = new MenuBar();
   
    menuLogOut = new Menu("log out");
    menuTimer = new Menu("timer");
    
    logOutItem = new MenuItem("log out");
    timerItem = new MenuItem("preferences");
    
    menuBar.getMenus().addAll(menuTimer, menuLogOut);
    menuBar.setVisible(true);
    menuBar.setPrefWidth(300);
    
    menuLogOut.getItems().add(logOutItem);
    menuTimer.getItems().add(timerItem);
    
    getChildren().add(screenManager);
    getChildren().add(menuBar);
    
    setMenuHandlers();
  }
  
  public MenuBar getMenuBar() {
    return menuBar;
  }
  
  private void setMenuHandlers() {
    logOutItem.setOnAction((ActionEvent t) -> {
      screenManager.setScreen(PotatoTo.loginID);
    });
    
    timerItem.setOnAction((ActionEvent t) -> {
      
      TimerPreferences timerPreferences = new TimerPreferences(timer);
      timerPreferences.setLayoutX(50);
      timerPreferences.setLayoutY(175);
      
      timerPreferences.toFront();
      
      getChildren().add(timerPreferences);
    });
    
  }

}
