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

public class ParserAutomate{
	public Case[][] act;
	public int[][] auto;
	public int nb_etat;
	public int etat_init;
	public ParserAutomate(String file){
		
		try{
		SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        
        XMLAutomate a=new XMLAutomate();
        File f=new File(this.getClass().getResource(file).getFile());
        //System.out.println("filename : " + file);
        parser.parse(f, a);
        
        act=a.act;
        auto=a.auto;
        nb_etat=a.nb_etats;
        etat_init=a.etat_init;
        
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