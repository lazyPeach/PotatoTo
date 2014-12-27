package view;

import java.awt.Paint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
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
    setStyle("-fx-background-color: #00ffff;");
  }
  
  private void initializePanel() {
    setPrefWidth(Dimensions.APPLICATION_WIDTH);
    setPrefHeight(Dimensions.APPLICATION_HEIGHT);

    usernameLabel.setText("Username");
    usernameLabel.setLayoutX(Dimensions.LABEL_OFFSET_X);
    usernameLabel.setLayoutY(Dimensions.LABEL_OFFSET_Y);
    usernameLabel.setFont(new Font(Dimensions.FONT_SIZE));
    
    passwordLabel.setText("Password");
    passwordLabel.setLayoutX(Dimensions.LABEL_OFFSET_X);
    passwordLabel.setLayoutY(usernameLabel.getLayoutY() + Dimensions.LABEL_OFFSET_Y);
    passwordLabel.setFont(new Font(Dimensions.FONT_SIZE));
    
    errorLabel.setText("Wrong username or password");
    errorLabel.setAlignment(Pos.CENTER);
    errorLabel.setLayoutX(Dimensions.LABEL_OFFSET_X/2f);
    errorLabel.setLayoutY(4 * Dimensions.LABEL_OFFSET_Y);
    errorLabel.setVisible(true);
    errorLabel.setFont(new Font(Dimensions.FONT_SIZE));
    errorLabel.setTextFill(Color.web("#ff0000"));
    
    usernameField.setLayoutX(Dimensions.LABEL_OFFSET_X);
    usernameField.setLayoutY(usernameLabel.getLayoutY() + Dimensions.FIELD_OFFSET);
    usernameField.setPrefHeight(Dimensions.FIELD_PREF_HEIGHT);
    usernameField.setPrefWidth(Dimensions.FIELD_PREF_WIDTH);
    usernameField.setPromptText("username");
    usernameField.setFont(new Font(Dimensions.FONT_SIZE));

    passwordField.setLayoutX(Dimensions.LABEL_OFFSET_X);
    passwordField.setLayoutY(passwordLabel.getLayoutY() + Dimensions.FIELD_OFFSET);
    passwordField.setPrefHeight(Dimensions.FIELD_PREF_HEIGHT);
    passwordField.setPrefWidth(Dimensions.FIELD_PREF_WIDTH);
    passwordField.setPromptText("password");
    passwordField.setFont(new Font(Dimensions.FONT_SIZE));
    
    loginBtn.setText("Log in");
    loginBtn.setAlignment(Pos.CENTER);
    loginBtn.setLayoutX(75);
    loginBtn.setLayoutY(350);
    loginBtn.setPrefHeight(Dimensions.BUTTON_PREF_HEIGHT);
    loginBtn.setPrefWidth(Dimensions.BUTTON_PREF_WIDTH);
    loginBtn.setTextAlignment(TextAlignment.CENTER);
    loginBtn.setFont(new Font(Dimensions.FONT_SIZE));
    
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
  
}
