package Parser;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import Otomate.Action2;


public class XMLAction extends DefaultHandler{
	   
	   boolean bid=false;
	   boolean btype=false;
	   boolean bd=false;
	   boolean bo=false;
	   boolean bcond=false;
	   boolean bname=false;
	   
	   int id;
	   int type;
	   int direction;
	   int objet;
	   List<Integer> cond=new LinkedList<Integer>();
	   List<Action2> list=new LinkedList<>();
	   String name;
	   
	static final int nb_cond=12;
	public void startElement(String uri, 
			   String localName, String qName, Attributes attributes)
			      throws SAXException {
				  if (qName.equalsIgnoreCase("id")){
					  bid=true;
				  }
			      if (qName.equalsIgnoreCase("type")) {
			    	  btype=true;
			      } else if (qName.equalsIgnoreCase("direction")) {
			         bd = true;
			      } else if (qName.equalsIgnoreCase("objet")) {
			         bo = true;
			      } else if (qName.equalsIgnoreCase("cond")) {
			         bcond = true;
			      }
			      else if (qName.equalsIgnoreCase("name")) {
			         bname = true;
			      }
			   }

			   @Override
			   public void endElement(String uri, 
			   String localName, String qName) throws SAXException {
			      if (qName.equalsIgnoreCase("Case")) {
			         System.out.println("End Element :" + name + " " + id +" "+ type+ " "+direction + " "+objet);
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
				   	else if (btype){
				   	type = Integer.parseInt(lecture);
				   	btype=false;
				  } else if (bd) {
					 direction= Integer.parseInt(lecture);
			         bd = false;
			      } else if (bo) {
			         objet= Integer.parseInt(lecture);
			    	 bo = false;
			      } else if (bcond) {
			    	  cond.add(Integer.parseInt(lecture));
			    	 bcond = false;
			      } else if (bname) {
			    	 name=lecture;
			         bname = false;
			         list.add(new Action2(type,direction,objet,cond,name));
			      }
			   }
			   
			   public void startDocument() throws SAXException {
				   
			   }
			   
			   public void endDocument() throws SAXException {
			
			   }
			}
