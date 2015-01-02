package entities;

public class Task {

  private int taskId;
  private String taskName;
  private int scheduledSessions;
  private int finishedSessions;

  public Task() {
    taskName = new String("test task");
    scheduledSessions = 0;
    finishedSessions = 0;
  }

  public int getTaskId() {
    return taskId;
  }

  public void setTaskId(int taskId) {
    this.taskId = taskId;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public int getScheduledSessions() {
    return scheduledSessions;
  }

  public void setScheduledSessions(int scheduledSessions) {
    this.scheduledSessions = scheduledSessions;
  }

  public int getFinishedSessions() {
    return finishedSessions;
  }

  public void setFinishedSessions(int finishedSessions) {
    this.finishedSessions = finishedSessions;
  }
  
  
  
}
