package model;

import dao.TaskDao;
import dao.TaskDaoImpl;
import entities.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

  TaskDao taskDao = new TaskDaoImpl();
  private List<Task> taskList;

  public TaskManager() {
    taskList = new ArrayList<>();
    
    populateTaskList();
  }

  public List<Task> getTaskList() {
    return taskDao.getAll();
  }

  public void removeTask(Task task) {
    taskDao.delete(task.getTaskId());
  }
  
  public void createTask(Task task) {
    taskDao.create(task);
  }
  
  
  
  private void populateTaskList() {
    for (int i = 0; i < 20; i++) {
      Task t = new Task();
      taskList.add(t);
    }
  }
  
  
  
}
