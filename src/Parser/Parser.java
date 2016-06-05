package Parser;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;



import Otomate.Case;

public class Parser{
	Case[][] act;
	int[][] auto;
	int nb_etat;
	
	public Parser(String file){
		
		try{
		SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        
        MyXMLHandler a=new MyXMLHandler();
        File f=new File(file);
        parser.parse(f, a);
        
        act=a.act;
        auto=a.auto;
        nb_etat=a.nb_etats;
        
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

		