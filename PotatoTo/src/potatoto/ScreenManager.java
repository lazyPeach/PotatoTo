package potatoto;

import javafx.util.Duration;
import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class ScreenManager extends StackPane {

  private final HashMap<String, Node> screens;

  public ScreenManager() {
    super();
    screens = new HashMap<>();
  }

  public void addScreen(String name, Node screen) {
    screens.put(name, screen);
  }

  public Node getScreen(String name) {
    return screens.get(name);
  }

  // This method tries to displayed the screen with a predefined name.
  // First it makes sure the screen has been already loaded.  Then if there is more than
  // one screen the new screen is been added second, and then the current screen is removed.
  // If there isn't any screen being displayed, the new screen is just added to the root.
  public boolean setScreen(final String name) {
    if (screens.get(name) != null) {                    //screen already loaded
      final DoubleProperty opacity = opacityProperty();

      if (!getChildren().isEmpty()) {                   //if there is more than one screen
        KeyFrame start = new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0));

        Timeline fade = new Timeline(
                start,
                new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent t) {
                    getChildren().remove(0);                    //remove the displayed screen
                    getChildren().add(0, screens.get(name));     //add the screen
                    Timeline fadeIn = new Timeline(
                            new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                            new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
                    fadeIn.play();
                  }
                }, new KeyValue(opacity, 0.0)));
        fade.play();

      } else {
        setOpacity(0.0);
        getChildren().add(screens.get(name));       //no one else been displayed, then just show
        Timeline fadeIn = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
        fadeIn.play();
      }
      return true;
    } else {
      System.out.println("screen hasn't been loaded!!! \n");
      return false;
    }

  }

  public void removeScreen(String name) {
    if (screens.remove(name) == null) {
      System.out.println("Screen didn't exist");
    }
  }

}
