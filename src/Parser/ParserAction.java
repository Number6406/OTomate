package Parser;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;
import Actions.$Action;

public class ParserAction{
	public List<$Action> list = new LinkedList<>();
	public ParserAction(String file){
		
		try{
		SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        
        XMLAction a=new XMLAction();
        File f=new File(this.getClass().getResource(file).getFile());
        //System.out.println("filename : " + file);
        parser.parse(f, a);
        
        list=a.list;
        
	      } catch (DOMException e) {
	          e.printStackTrace();
	       } catch (ParserConfigurationException e) {
	          e.printStackTrace();
	       } catch (TransformerFactoryConfigurationError e) {
	          e.printStackTrace();
	       } catch (SAXException e) {
	          e.printStackTrace();
	       } catch (IOException e) {
	          // TODO Auto-generated catch block
	  e.printStackTrace();
        
        
	       }

	}
}