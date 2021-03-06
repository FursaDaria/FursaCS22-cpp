import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MyErrorHandler implements ErrorHandler {
    @Override
    public void warning(SAXParseException exception) throws SAXException {
        System.out.println("Fatal Error: "+exception);
        System.out.println("Line: "+exception.getLineNumber()+"\ncolum"+exception.getColumnNumber());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        System.out.println("Fatal Error: "+exception);
        System.out.println("Line: "+exception.getLineNumber()+"\ncolum"+exception.getColumnNumber());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println("Fatal Error: "+exception);
        System.out.println("Line: "+exception.getLineNumber()+"\ncolum"+exception.getColumnNumber());
    }
}
