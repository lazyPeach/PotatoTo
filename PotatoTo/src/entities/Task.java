package entities;

public class Task {

  private int taskId;
  private String taskName;
  private int scheduledSessions;
  private int finishedSessions;
  private String priority;

  public Task() {
    taskName = new String("test task");
    scheduledSessions = 0;
    finishedSessions = 0;
    priority = "low";
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

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  @Override
  public String toString() {
    return "Task{" + "taskId=" + taskId + ", taskName=" + taskName + ", scheduledSessions=" + scheduledSessions + ", finishedSessions=" + finishedSessions + ", priority=" + priority + '}';
  }
  
  
  
}
