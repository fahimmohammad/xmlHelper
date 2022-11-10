import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.io.FileWriter;
import java.io.BufferedWriter;
public class XmlWriter {
    Stack<String>  elements;
    StringBuilder sb;
    int currentIndent;
    public XmlWriter(){
        currentIndent = 0;
        this.elements = new Stack<>();
        this.sb = new StringBuilder();
    }
    public XmlWriter(Stack<String>  elements){
        this.elements = elements;
        currentIndent = this.elements.size();
        this.sb = new StringBuilder();
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
        Set<String> keySet = attributes.keySet();
        for(String key:keySet){
            sb.append(" ").append(key).append("=\"").append(attributes.get(key)).append("\"");
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
        Set<String> keySet = attributes.keySet();
        for(String key:keySet){
            sb.append(" ").append(key).append("=\"").append(attributes.get(key)).append("\"");
        }
        sb.append(">").append(data).append("</").append(elementName).append(">\n");
    }

    public void writeEndElement(){
        int indent  = currentIndent-1;
        String elementName = elements.pop();
        while(indent>0){
            sb.append("\t");
            indent--;
        }
        sb.append("</").append(elementName).append(">\n");
        currentIndent--;
    }
    public StringBuilder getDocument(){
        return this.sb;
    }
    public DocumentElements getDocumentElements(){
        DocumentElements documentElements =  new DocumentElements();
        documentElements.setStringBuilder(this.sb);
        documentElements.setElements(this.elements);
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
           return true;
       }catch (Exception e){
           System.out.println(e.getMessage());
           return false;
       }
    }

   static class DocumentElements{
        StringBuilder docString;
        Stack<String>  elements;
        DocumentElements(){}
        StringBuilder getStringBuilder(){
            return this.docString;
        }
        void setStringBuilder(StringBuilder docString){
            this.docString = docString;
        }
        Stack<String> getElements(){
            return this.elements;
        }
        void setElements(Stack<String> elements){
            this.elements = elements;
        }
    }
}
