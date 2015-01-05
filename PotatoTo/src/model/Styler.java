package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Styler {
  
  public static String DEFAULT_STYLE = "resources/style/defaultStyle.css";
  public static String JAMAICA_STYLE = "resources/style/jamaica.css";
  
  private StringProperty style;
  
  public Styler() {
    style = new SimpleStringProperty();
  }
  
  public final String getStyle() {
    return style.get();
  }

  public final void setStyle(String value) {
    style.set(value);
  }

  public StringProperty styleProperty() {
    return style;
  }
  
}
