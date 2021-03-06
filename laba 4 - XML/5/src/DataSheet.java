import org.w3c.dom.*;

import javax.xml.crypto.Data;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Properties;

public class DataSheet {
    private Document document;

    public DataSheet(Document document) {
        this.document = document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public void showAllDoc(){
        System.out.println("Root element "+document.getDocumentElement().getTagName());
        for(Node child=document.getDocumentElement().getFirstChild();child!=null;child=child.getNextSibling()){
            System.out.println(child.getNodeName()+" "+child.getAttributes().item(0).getNodeValue());
            System.out.println(child.getChildNodes().item(0).getNodeName()+" "+child.getChildNodes().item(0).getTextContent());
            System.out.println(child.getChildNodes().item(1).getNodeName()+" "+child.getChildNodes().item(1).getTextContent());
        }
    }

//    public static void print(Document doc){
//        Element rootEl=doc.getDocumentElement();
//        System.out.println("Root el"+rootEl.getNodeName());
//        System.out.println("childs: ");
//        stepThrout(rootEl);
//    }
//    public static void stepThrout(Node start){
//        System.out.println(start.getNodeName()+" = "+start.getNodeValue());
//        NamedNodeMap attr=start.getAttributes();
//        if (attr!=null)
//            for(int j=0;j<attr.getLength();j++)
//                System.out.println("Attr : "+attr.item(j).getNodeName()+" val "+attr.item(j).getNodeValue());
//        for(Node child=start.getFirstChild();child!=null;child=child.getNextSibling()) {
//            stepThrout(child);
//        }
//    }

    public int getCountOfData(){
        return document.getElementsByTagName("data").getLength();
    }

    public double getX(int pos){
        //System.out.println(document.getElementsByTagName("x").item(pos).getFirstChild().getNodeValue());
        return Double.parseDouble(document.getElementsByTagName("x").item(pos).getTextContent());
    }

    public void setX(double val,int pos){
        //document.getElementsByTagName("x").item(pos).getFirstChild().setNodeValue(String.valueOf(val));
        document.getElementsByTagName("x").item(pos).setTextContent(String.valueOf(val));
    }
    public double getY(int pos){
        //System.out.println(document.getElementsByTagName("x").item(pos).getFirstChild().getNodeValue());
        return Double.parseDouble(document.getElementsByTagName("y").item(pos).getTextContent());
    }

    public void setY(double val,int pos){
        document.getElementsByTagName("y").item(pos).setTextContent(String.valueOf(val));
    }

    public Element createNEwElement(String date,double x,double y){
        Element data=document.createElement("data");
        data.setAttribute("date",date);

        Element tmp=document.createElement("x");
        tmp.appendChild(document.createTextNode(String.valueOf(x)));
        data.appendChild(tmp);

        tmp=document.createElement("y");
        tmp.appendChild(document.createTextNode(String.valueOf(y)));
        data.appendChild(tmp);
        return  data;
    }

    public void addData(Element data){
        document.getDocumentElement().appendChild(data);
    }

    public void removeData(int pos){
        document.getDocumentElement().removeChild(document.getElementsByTagName("data").item(pos));
    }

    public void insertData(int pos,Node node){
        document.getDocumentElement().insertBefore(node,document.getElementsByTagName("data").item(pos));
    }

    public void replaceDataEl(int pos,Node node){
        document.getDocumentElement().replaceChild(node,document.getElementsByTagName("data").item(pos));
    }

    public void saveDocument(File file) throws TransformerException, FileNotFoundException {
        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"data.dtd");
        transformer.setOutputProperty(OutputKeys.ENCODING,"windows-1251");
        DOMSource xmlSource=new DOMSource(document);
        StreamResult result=new StreamResult(new FileOutputStream(file));
        transformer.transform(xmlSource,result);
    }

}
