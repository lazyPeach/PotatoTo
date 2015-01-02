package dao;

import entities.Task;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TaskDaoImpl implements TaskDao {

  @Override
  public void create(Task task) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Task read(Integer taskId) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void update(Task task) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void delete(Integer taskId) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<Task> getAll() {
    List<Task> taskList = new ArrayList<>();

    try {
      File fXmlFile = new File("data/potato.xml");
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fXmlFile);
      doc.getDocumentElement().normalize();

      NodeList nList = doc.getElementsByTagName("task");

      for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Task tempTask = new Task();

          Element eElement = (Element) nNode;

          tempTask.setTaskId(Integer.parseInt(eElement.getAttribute("id")));
          tempTask.setTaskName(eElement.getAttribute("name"));
          tempTask.setScheduledSessions(Integer.parseInt(eElement.getAttribute("scheduledSessions")));
          tempTask.setFinishedSessions(Integer.parseInt(eElement.getAttribute("finishedSessions")));
          
          taskList.add(tempTask);
        }

      }
    } catch (IOException | NumberFormatException | ParserConfigurationException | SAXException e) {
      System.out.println("Exception caught while parsing the xml");
    }

    return taskList;
  }

}
