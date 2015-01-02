package dao;

import entities.Task;
import java.util.List;

public interface TaskDao {
  
  public void create(Task task); 
  
  public Task read(Integer taskId);
  
  public void update(Task task);
  
  public void delete(Integer taskId);
  
  public List<Task> getAll();
}
