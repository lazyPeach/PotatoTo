package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import potatoto.PotatoTo;
import potatoto.ScreenManager;
import view.LoginPanel;

public class LoginController implements ManagedScreen  {

  private ScreenManager manager;
  private LoginPanel panel;
  
  @Override
  public void setScreenManager(ScreenManager screenManager) {
    manager = screenManager;
  }
  
  public void setPanel(LoginPanel panel) {
    this.panel = panel;
    
    initialize();
  }
  
  private void initialize() {
    panel.setLoginBtnHandler(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent t) {
        System.out.println("clicked on login");
        manager.setScreen(PotatoTo.mainID);
      }
    });
  }
  
}
