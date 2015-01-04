package model.report;

public class ReportFactory {
  public static ReportGenerator getReportGenerator(int id) {
    switch(id) {
    case 0:
      return new TextReportGenerator();
    case 1:
      return new XMLReportGenerator();
    default:
      return null;
    }
  }  
}
