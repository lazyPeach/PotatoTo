package view;

import entities.Task;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import model.TaskManager;

public class ReportPanel extends AnchorPane {

  private TaskManager taskManager;
  private AnchorPane pieChartPanel;
  private AnchorPane barChartPanel;

  public ReportPanel(TaskManager taskManager) {
    this.taskManager = taskManager;

    pieChartPanel = new AnchorPane();
    barChartPanel = new AnchorPane();

    initializePanel();
    initializeStyle();
  }

  private void initializePanel() {
    setPrefHeight(500);
    setPrefWidth(300);

    initializePieChart();
    initializeBarChart();

    getChildren().add(pieChartPanel);
    getChildren().add(barChartPanel);
  }

  private void initializePieChart() {
    pieChartPanel.setPrefHeight(200);
    pieChartPanel.setPrefWidth(300);

    PieChart pieChart = new PieChart();

    List<Task> allTasks = taskManager.getTaskList();
    int high = 0, medium = 0, low = 0;

    for (Task task : allTasks) {
      if (task.getPriority().equals("high")) {
        high++;
      }

      if (task.getPriority().equals("med")) {
        medium++;
      }

      if (task.getPriority().equals("low")) {
        low++;
      }
    }

    ObservableList<PieChart.Data> pieChartData
            = FXCollections.observableArrayList(
                    new PieChart.Data("high", high),
                    new PieChart.Data("medium", medium),
                    new PieChart.Data("low", low));

    pieChart.setTitle("Task priorities");
    pieChart.setData(pieChartData);
    pieChart.setLabelsVisible(false);
    pieChart.setLegendSide(Side.RIGHT);

    pieChart.setPrefHeight(200);
    pieChart.setPrefWidth(300);

    pieChartPanel.setPrefHeight(200);
    pieChartPanel.getChildren().add(pieChart);
  }

  private void initializeBarChart() {
    List<Task> allTasks = taskManager.getTaskList();
    int totalE = 0, totalF = 0,
        highE = 0, highF = 0,
        mediumE = 0, mediumF = 0,
        lowE = 0, lowF = 0;

    for (Task task : allTasks) {
      if (task.getPriority().equals("high")) {
        highE += task.getScheduledSessions();
        highF += task.getFinishedSessions();
      }

      if (task.getPriority().equals("med")) {
        mediumE += task.getScheduledSessions();
        mediumF += task.getFinishedSessions();
      }

      if (task.getPriority().equals("low")) {
        lowE += task.getScheduledSessions();
        lowF += task.getFinishedSessions();
      }
      
      totalE += task.getScheduledSessions();
      totalF += task.getFinishedSessions();
    }
    
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
    
    String total = "total";
    String high = "high";
    String medium = "med";
    String low = "low";
    
    barChart.setTitle("Session report");
    xAxis.setLabel("priority");
    yAxis.setLabel("sessions");
    
    XYChart.Series series1 = new XYChart.Series();
    series1.setName("estimated tasks");
    series1.getData().add(new XYChart.Data(total, totalE));
    series1.getData().add(new XYChart.Data(high, highE));
    series1.getData().add(new XYChart.Data(medium, mediumE));
    series1.getData().add(new XYChart.Data(low, lowE));

    XYChart.Series series2 = new XYChart.Series();
    series2.setName("finished tasks");
    series2.getData().add(new XYChart.Data(total, totalF));
    series2.getData().add(new XYChart.Data(high, highF));
    series2.getData().add(new XYChart.Data(medium, mediumF));
    series2.getData().add(new XYChart.Data(low, lowF));
    
    barChart.getData().addAll(series1, series2);
    
    barChartPanel.getChildren().add(barChart);
    barChart.setPrefWidth(300);
    barChart.setPrefHeight(300);
    barChartPanel.setLayoutY(pieChartPanel.getPrefHeight());
    
  }

  private void initializeStyle() {
    setId("reportPanel");

  }

}
