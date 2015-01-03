package potatoto;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class Underlayer extends AnchorPane {

  private MenuBar menuBar;
  private Menu menuLogOut;
  private MenuItem logOutItem;
  private ScreenManager screenManager;

  public Underlayer(ScreenManager screenManager) {
    this.screenManager = screenManager;
    menuBar = new MenuBar();
    menuLogOut = new Menu("Log out");
    logOutItem = new MenuItem("Log out");
    
    menuBar.getMenus().addAll(menuLogOut);
    menuBar.setVisible(false);
    menuBar.setPrefWidth(300);
    
    menuLogOut.getItems().add(logOutItem);
    
    
    getChildren().add(screenManager);
    getChildren().add(menuBar);
    
    setMenuHandlers();
  }
  
  public MenuBar getMenuBar() {
    return menuBar;
  }
  
  private void setMenuHandlers() {
    logOutItem.setOnAction((ActionEvent t) -> {
      System.out.println("clicked");
      screenManager.setScreen(PotatoTo.loginID);
    });
  }

}
