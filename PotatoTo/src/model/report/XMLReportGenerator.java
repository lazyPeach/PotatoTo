package model.report;

import entities.Task;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import model.TaskManager;

public class XMLReportGenerator implements ReportGenerator {

  @Override
  public void generateReport() {
    String reportFile = "report.xml";
    TaskManager tm = new TaskManager();
    List<Task> tasks = tm.getTaskList();

    try {
      // create an XMLOutputFactory
      XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
      // create XMLEventWriter
      XMLEventWriter eventWriter;
      eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(reportFile));

      // create an EventFactory
      XMLEventFactory eventFactory = XMLEventFactory.newInstance();
      XMLEvent end = eventFactory.createDTD("\n");
      // create and write Start Tag
      StartDocument startDocument = eventFactory.createStartDocument();
      eventWriter.add(startDocument);

      // create config open tag
      StartElement configStartElement = eventFactory.createStartElement("", "", "task");
      eventWriter.add(configStartElement);
      eventWriter.add(end);

      // Write the nodes
      for (Task task : tasks) {
        createNode(eventWriter, "name", task.getTaskName());
        createNode(eventWriter, "estimatedSessions", String.valueOf(task.getScheduledSessions()));
        createNode(eventWriter, "finishedSessions", String.valueOf(task.getFinishedSessions()));
        createNode(eventWriter, "priority", task.getPriority());

      }

      eventWriter.add(eventFactory.createEndElement("", "", "task"));
      eventWriter.add(end);
      eventWriter.add(eventFactory.createEndDocument());
      eventWriter.close();
    } catch (FileNotFoundException | XMLStreamException e) {
      System.out.println("some exception... ignore or deal with it later");
    }

  }

  private void createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException {
    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    XMLEvent end = eventFactory.createDTD("\n");
    XMLEvent tab = eventFactory.createDTD("\t");
    // create Start node
    StartElement sElement = eventFactory.createStartElement("", "", name);
    eventWriter.add(tab);
    eventWriter.add(sElement);
    // create Content
    Characters characters = eventFactory.createCharacters(value);
    eventWriter.add(characters);
    // create End node
    EndElement eElement = eventFactory.createEndElement("", "", name);
    eventWriter.add(eElement);
    eventWriter.add(end);
  }

}
