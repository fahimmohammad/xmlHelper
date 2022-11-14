import java.util.HashMap;
import java.io.IOException;


public class DemoWriter {

    public static void main(String[] args) throws   XmlWriter.XmlwriterException {
        XmlWriter xw = new XmlWriter();
        xw.writeStartDocument("utf-8"); // Document Encoding.
        xw.writeStartElement("Students");
        xw.writeStartElement("Student");
        xw.writeStartEndElement("Name","Jenifer");
        xw.writeStartEndElement("Grade","Five");
        xw.writeStartEndElement("Roll","010111");
        xw.writeEndElement();
        xw.writeStartElement("Student");
        HashMap<String,String > attributes= new HashMap<>();
        attributes.put("father","Doe");
        attributes.put("mother","Cameron Diaz");
        xw.writeStartEndElementWithAttr("Parents","Yes",attributes);
        xw.writeStartEndElement("Name","Jhon Doe");
        xw.writeStartEndElement("Grade","Five");
        xw.writeStartEndElement("Roll","010112");
        xw.writeEndElement();
        xw.writeEndElement();
        xw.SaveDocument("D:\\\\Projects\\\\XMLFiles","DemoFile");
    }
}
