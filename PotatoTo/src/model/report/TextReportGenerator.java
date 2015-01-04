package model.report;

import entities.Task;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import model.TaskManager;

public class TextReportGenerator implements ReportGenerator {

  @Override
  public void generateReport() {
    TaskManager tm = new TaskManager();
    List<Task> tasks = tm.getTaskList();

    try {
      File report = new File("report.txt");
      if (!report.exists()) {
        report.createNewFile();
      }
      
      FileWriter fw = new FileWriter(report);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("Yout task report:");
      bw.newLine();
      
      for (Task task : tasks) {
        bw.write("\t- Task name: " + task.getTaskName()
        + "; Scheduled sessions: " + task.getScheduledSessions()
        + "; Finished sessions:" + task.getFinishedSessions()
        + "; Task priority: " + task.getPriority());
        
        bw.newLine();
      }
      
      bw.close();
    } catch (Exception e) {
      System.out.println("bla");
    }
  }
  
}
