package controller;

import javafx.event.ActionEvent;
import potatoto.PotatoTo;
import potatoto.ScreenManager;
import view.LoginPanel;

public class LoginController implements ManagedScreen {

  private ScreenManager manager;
  private LoginPanel loginPanel;

  @Override
  public void setScreenManager(ScreenManager screenManager) {
    manager = screenManager;
  }

  public void setPanel(LoginPanel loginPanel) {
    this.loginPanel = loginPanel;

    initialize();
  }

  private void initialize() {
    loginPanel.setLoginBtnHandler((ActionEvent t) -> {
      String[] credentials = loginPanel.getCredentials();

      if (credentials[0].equals("") || credentials[1].equals("")) {
        loginPanel.displayErrorMessage();
      } else {
        manager.setScreen(PotatoTo.mainID);
      }
    });
  }

}
