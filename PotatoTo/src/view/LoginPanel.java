package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.Dimensions;

public class LoginPanel extends AnchorPane {

  private Label usernameLabel;
  private Label passwordLabel;
  private Label errorLabel;
  private TextField usernameField;
  private PasswordField passwordField;
  private Button loginBtn;

  public LoginPanel() {
    usernameLabel = new Label();
    passwordLabel = new Label();
    errorLabel = new Label();
    usernameField = new TextField();
    passwordField = new PasswordField();
    loginBtn = new Button();

    initializePanel();
    initializeStyle();
  }

  private void initializeStyle() {
    setId("loginPanel");
    usernameLabel.getStyleClass().add("loginLabel");
    passwordLabel.getStyleClass().add("loginLabel");
    errorLabel.setId("loginError");
    usernameField.getStyleClass().add("loginField");
    passwordField.getStyleClass().add("loginField");
    loginBtn.setId("loginBtn");
  }

  private void initializePanel() {
    setPrefWidth(Dimensions.APPLICATION_WIDTH);
    setPrefHeight(Dimensions.APPLICATION_HEIGHT);

    usernameLabel.setText("Username");
    usernameLabel.setLayoutX(Dimensions.LABEL_OFFSET_X);
    usernameLabel.setLayoutY(Dimensions.LABEL_OFFSET_Y);

    passwordLabel.setText("Password");
    passwordLabel.setLayoutX(Dimensions.LABEL_OFFSET_X);
    passwordLabel.setLayoutY(usernameLabel.getLayoutY() + Dimensions.LABEL_OFFSET_Y);

    errorLabel.setText("Wrong username or password");
    errorLabel.setLayoutY(4 * Dimensions.LABEL_OFFSET_Y);
    errorLabel.setVisible(false);

    usernameField.setLayoutX(Dimensions.LABEL_OFFSET_X);
    usernameField.setLayoutY(usernameLabel.getLayoutY() + Dimensions.FIELD_OFFSET);
    usernameField.setPrefHeight(Dimensions.FIELD_PREF_HEIGHT);
    usernameField.setPrefWidth(Dimensions.FIELD_PREF_WIDTH);
    usernameField.setPromptText("username");

    passwordField.setLayoutX(Dimensions.LABEL_OFFSET_X);
    passwordField.setLayoutY(passwordLabel.getLayoutY() + Dimensions.FIELD_OFFSET);
    passwordField.setPrefHeight(Dimensions.FIELD_PREF_HEIGHT);
    passwordField.setPrefWidth(Dimensions.FIELD_PREF_WIDTH);
    passwordField.setPromptText("password");

    loginBtn.setText("Log in");
    loginBtn.setLayoutX(75);
    loginBtn.setLayoutY(350);
    loginBtn.setPrefHeight(Dimensions.BUTTON_PREF_HEIGHT);
    loginBtn.setPrefWidth(Dimensions.BUTTON_PREF_WIDTH);

    getChildren().add(usernameLabel);
    getChildren().add(passwordLabel);
    getChildren().add(errorLabel);
    getChildren().add(usernameField);
    getChildren().add(passwordField);
    getChildren().add(loginBtn);
  }

  public void setLoginBtnHandler(EventHandler<ActionEvent> handler) {
    loginBtn.setOnAction(handler);
  }
  
  public String[] getCredentials() {
    String[] credentials = new String[2];
    credentials[0] = usernameField.getText();
    credentials[1] = passwordField.getText();
        
    return credentials;
  }
  
  public void displayErrorMessage() {
    errorLabel.setVisible(true);
  }

}
