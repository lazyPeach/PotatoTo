package view;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;

public class DigitPanel extends AnchorPane {

  //seven segment display
  //    A
  //  B   F
  //    G
  //  C   E
  //    D     
  private Polygon segmentA;
  private Polygon segmentB;
  private Polygon segmentC;
  private Polygon segmentD;
  private Polygon segmentE;
  private Polygon segmentF;
  private Polygon segmentG;

  // A - 0, B - 1, C - 2, D - 3, E - 4, F - 5, G - 6
  boolean[][] sevenSegmentConfig = {
    {true, true, true, true, true, true, false},      // 0
    {false, false, false, false, true, true, false},  // 1
    {true, false, true, true, false, true, true},     // 2
    {true, false, false, true, true, true, true},     // 3
    {false, true, false, false, true, true, true},    // 4
    {true, true, false, true, true, false, true},     // 5
    {true, true, true, true, true, false, true},      // 6
    {true, false, false, false, true, true, false},   // 7
    {true, true, true, true, true, true, true},       // 8
    {true, true, false, true, true, true, true}       // 9
  };

  public DigitPanel() {
    segmentA = new Polygon(new double[]{10, 10, 15, 5, 45, 5, 50, 10, 45, 15, 15, 15});
    segmentB = new Polygon(new double[]{10, 15, 15, 20, 15, 65, 10, 70, 5, 65, 5, 20});
    segmentC = new Polygon(new double[]{10, 80, 15, 85, 15, 130, 10, 135, 5, 130, 5, 85});
    segmentD = new Polygon(new double[]{10, 140, 15, 135, 45, 135, 50, 140, 45, 145, 15, 145});
    segmentE = new Polygon(new double[]{50, 135, 45, 130, 45, 85, 50, 80, 55, 85, 55, 130});
    segmentF = new Polygon(new double[]{50, 70, 45, 65, 45, 20, 50, 15, 55, 20, 55, 65});
    segmentG = new Polygon(new double[]{10, 75, 15, 70, 45, 70, 50, 75, 45, 80, 15, 80});

    initializePanel();
    initializeStyle();
  }
  
  private void initializeStyle() {
    setId("digitPanel");
    segmentA.getStyleClass().add("digit");
    segmentB.getStyleClass().add("digit");
    segmentC.getStyleClass().add("digit");
    segmentD.getStyleClass().add("digit");
    segmentE.getStyleClass().add("digit");
    segmentF.getStyleClass().add("digit");
    segmentG.getStyleClass().add("digit");

  }

  private void initializePanel() {
    setPrefHeight(150);
    setPrefWidth(60);

    getChildren().add(segmentA);
    getChildren().add(segmentB);
    getChildren().add(segmentC);
    getChildren().add(segmentD);
    getChildren().add(segmentE);
    getChildren().add(segmentF);
    getChildren().add(segmentG);

    update(0);
  }

  public void update(int number) {
    setVisibility(sevenSegmentConfig[number % 10]);
  }
  
  private void setVisibility(boolean... visibility) {
    segmentA.setVisible(visibility[0]);
    segmentB.setVisible(visibility[1]);
    segmentC.setVisible(visibility[2]);
    segmentD.setVisible(visibility[3]);
    segmentE.setVisible(visibility[4]);
    segmentF.setVisible(visibility[5]);
    segmentG.setVisible(visibility[6]);
  }

}
