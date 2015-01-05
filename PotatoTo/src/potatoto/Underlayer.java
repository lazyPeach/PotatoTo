package potatoto;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import model.CountdownTimer;
import model.Styler;
import model.report.ReportFactory;
import model.report.ReportGenerator;
import view.TimerPreferences;

public class Underlayer extends AnchorPane {

  private MenuBar menuBar;
  private Menu menuLogOut;
  private Menu menuReport;
  private Menu menuTask;
  private Menu menuPref;
  private Menu styleMenu;

  
  private MenuItem logOutItem;
  private MenuItem timerItem;
  private MenuItem graphicReportItem;
  private MenuItem textReportItem;
  private MenuItem xmlReportItem;
  private MenuItem detailedTaskItem;
  private MenuItem timerPanelItem;
  private MenuItem defaultStyleItem;
  private MenuItem jamaicaStyleItem;

  
  private ScreenManager screenManager;
  private CountdownTimer timer;
  private Styler styler;

  public Underlayer(ScreenManager screenManager, CountdownTimer timer, Styler styler) {
    this.screenManager = screenManager;
    this.styler = styler;
    this.timer = timer;
    menuBar = new MenuBar();
   
    menuLogOut = new Menu("log out");
    menuReport = new Menu("report");
    menuTask = new Menu("potato");
    menuPref = new Menu("preferences");
    styleMenu = new Menu("style");
    
    logOutItem = new MenuItem("log out");
    timerItem = new MenuItem("timer session");
    graphicReportItem = new MenuItem("graphic report");
    textReportItem = new MenuItem("text report");
    xmlReportItem = new MenuItem("xml report");
    detailedTaskItem = new MenuItem("detailed task list");
    timerPanelItem = new MenuItem("potato");
    defaultStyleItem = new MenuItem("default");
    jamaicaStyleItem = new MenuItem("jamaica");
    
    menuBar.getMenus().addAll(menuTask, menuReport, menuPref, menuLogOut);
    menuBar.setVisible(false);
    menuBar.setPrefWidth(300);
    
    menuLogOut.getItems().add(logOutItem);
    menuPref.getItems().addAll(timerItem, styleMenu);
    menuReport.getItems().addAll(graphicReportItem, textReportItem, xmlReportItem);
    menuTask.getItems().addAll(timerPanelItem, detailedTaskItem);
    styleMenu.getItems().addAll(defaultStyleItem, jamaicaStyleItem);
    
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
    
    detailedTaskItem.setOnAction((ActionEvent t) -> {
      screenManager.setScreen(PotatoTo.taskID);
      
    });
    
    timerPanelItem.setOnAction((ActionEvent t) -> {
      screenManager.setScreen(PotatoTo.mainID);
    });
    
    textReportItem.setOnAction((ActionEvent t) -> {
      ReportGenerator reportGenerator = ReportFactory.getReportGenerator(0);
      reportGenerator.generateReport();
    });
    
    xmlReportItem.setOnAction((ActionEvent t) -> {
      ReportGenerator reportGenerator = ReportFactory.getReportGenerator(1);
      reportGenerator.generateReport();
    });
    
    graphicReportItem.setOnAction((ActionEvent t) -> {
      screenManager.setScreen(PotatoTo.reportID);
    });
    
    defaultStyleItem.setOnAction((ActionEvent t) -> {
      styler.setStyle(Styler.DEFAULT_STYLE);
    });
    
    jamaicaStyleItem.setOnAction((ActionEvent t) -> {
      styler.setStyle(Styler.JAMAICA_STYLE);
    });
    
  }

}
