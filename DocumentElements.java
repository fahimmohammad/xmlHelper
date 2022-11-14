import java.util.Stack;

public class DocumentElements {  StringBuilder docString;
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
