import java.io.File;
import java.util.Map;
import java.util.Stack;
import java.io.FileWriter;
import java.io.BufferedWriter;
public class XmlWriter {
    Stack<String>  elements;
    StringBuilder sb;
    int currentIndent;
    public XmlWriter(){
        currentIndent = 0;
        elements = new Stack<>();
        sb = new StringBuilder();
    }
    public XmlWriter(Stack<String>  elements){
        elements = elements;
        currentIndent = elements.size();
        sb = new StringBuilder();
    }
    public void writeStartDocument(String encoding){
        sb.append("<?xml version=\"1.0\" encoding=\"").append(encoding).append("\" ?>\n");
    }
    public void writeStartDocument(){
        sb.append("<?xml version=\"1.0\"  ?>\n");
    }
    public void writeStartElement(String elementName){
        int indent  = currentIndent;
        while(indent>0){
            sb.append("\t");
            indent--;
        }
        sb.append("<").append(elementName).append(">\n");
        elements.push(elementName);
        currentIndent++;
    }
    public void writeStartElementWithAttr(String elementName, Map<String,String> attributes){
        int indent  = currentIndent;
        while(indent>0){
            sb.append("\t");
            indent--;
        }
        sb.append("<").append(elementName);
        for(Map.Entry attribute: attributes.entrySet()){
            sb.append(" ").append(attribute.getKey()).append("=\"").append(attribute.getValue()).append("\"");
        }
        sb.append(">\n");
        elements.push(elementName);
        currentIndent++;
    }
    public void writeStartEndElement( String elementName,String data){
        int indent = currentIndent;
        while(indent>0){
            sb.append("\t");
            indent--;
        }
        sb.append("<").append(elementName).append(">").append(data).append("</").append(elementName).append(">\n");
    }
    public void writeStartEndElementWithAttr( String elementName,String data,Map<String,String> attributes){
        int indent = currentIndent;
        while(indent>0){
            sb.append("\t");
            indent--;
        }
        sb.append("<").append(elementName);
        for(Map.Entry attribute: attributes.entrySet()){
            sb.append(" ").append(attribute.getKey()).append("=\"").append(attribute.getValue()).append("\"");
        }
        sb.append(">").append(data).append("</").append(elementName).append(">\n");
    }

    public void writeEndElement() throws XmlwriterException {
        if(currentIndent == 0){
            throw new XmlwriterException("No start element found.");
        }else {
            int indent  = currentIndent-1;
            String elementName = elements.pop();
            while(indent>0){
                sb.append("\t");
                indent--;
            }
            sb.append("</").append(elementName).append(">\n");
            currentIndent--;
        }

    }
    public StringBuilder getDocument(){
        return this.sb;
    }
    public DocumentElements getDocumentElements(){
        DocumentElements documentElements =  new DocumentElements();
        documentElements.setStringBuilder(sb);
        documentElements.setElements(elements);
        return documentElements;
    }
    public boolean SaveDocument(String location,String fileName){
        File file;
       try{
           if(location.charAt(location.length()-1) == '\\'){
                file = new File(location + fileName + ".xml");
            }else{
                file = new File(location +"\\"+ fileName + ".xml");
           }
           FileWriter fr = new FileWriter(file, false);
           BufferedWriter bw = new BufferedWriter(fr);
           bw.write(this.sb.toString());
           bw.close();
           fr.close();
           sb = new StringBuilder();
           return true;
       }catch (Exception e){
           System.out.println(e.getMessage());
           return false;
       }
    }
     class XmlwriterException extends Exception{
        XmlwriterException (String str)
        {
            super(str);
        }
    }
}
