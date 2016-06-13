package Parser;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import Otomate.Conditions2;


public class XMLConditions extends DefaultHandler{
	boolean bid=false;
	boolean bd=false;
	boolean btype=false;
	
	int id;
	int d;
	int type;
	Conditions2 c;
	List<Conditions2> list= new LinkedList<Conditions2>();
	
	public void startElement(String uri, 
			   String localName, String qName, Attributes attributes)
			      throws SAXException {
				  if (qName.equalsIgnoreCase("id")){
					  bid=true;
				  }
			      if (qName.equalsIgnoreCase("d")) {
			    	  bd=true;
			      } else if (qName.equalsIgnoreCase("type")) {
			         btype = true;
			    	 
			      }
			   }

			   @Override
			   public void endElement(String uri, 
			   String localName, String qName) throws SAXException {
			      if (qName.equalsIgnoreCase("cond")) {
			         //System.out.println("End Element :" + qName + " " + id +" "+ d+ " "+type);
			      }
			   }

			   @Override
			   public void characters(char ch[], 
			      int start, int length) throws SAXException {
				   String lecture = new String(ch,start,length);
				   	if (bid){
				   		id=Integer.parseInt(lecture);
				   		bid=false;
				   	}
				   	else if (bd){
				   		d=Integer.parseInt(lecture);
				   		bd=false;
				  } else if (btype) {
					//  System.out.println("probleme?");
					 	type=Integer.parseInt(lecture);
					 	c=new Conditions2(id,d,type);
					 	list.add(c);
					 	btype=false;
			   
				  }
				   	//System.out.println(lecture);
				   	//System.out.println("Au-dessus : " + list.size());
			   }
			   
			   public void startDocument() throws SAXException {
				   
			   }
			   
			   public void endDocument() throws SAXException {
			
			   }
			}
