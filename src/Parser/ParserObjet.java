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
import Otomate.Objet;

public class ParserObjet{
	public List<Objet> list = new LinkedList<>();
	public String imageGentil;
	public String imageMechant;
	
	public ParserObjet(String file){
		
		try{
		SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        
        XMLObjet a=new XMLObjet();
        File f=new File(this.getClass().getResource(file).getFile());
        parser.parse(f, a);
        
        list=a.list;
        imageGentil = a.imageGentil;
        imageMechant = a.imageMechant;
	      } catch (DOMException e) {
	          e.printStackTrace();
	       } catch (ParserConfigurationException e) {
	          e.printStackTrace();
	       } catch (TransformerFactoryConfigurationError e) {
	          e.printStackTrace();
	       } catch (SAXException e) {
	          e.printStackTrace();
	       } catch (IOException e) {
	  e.printStackTrace();
        
        
	       }

	}
}