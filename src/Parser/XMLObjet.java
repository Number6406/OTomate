package Parser;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import Otomate.Objet;


public class XMLObjet extends DefaultHandler{
	boolean bid=false;
	boolean buse=false;
	boolean btype=false;
	boolean bname=false;
	boolean bpas=false;
	boolean bpath=false;
	
	boolean bimG = false;
	boolean bimM = false;
	
	
	int id;
	int use;
	int type;
	String name;
	boolean passable;
	String path;
	Objet c;
	List<Objet> list= new LinkedList<Objet>();
	public String imageGentil;
	public String imageMechant;
	
	public void startElement(String uri, String localName, String qName, Attributes attributes)
			      throws SAXException {
				  if (qName.equalsIgnoreCase("id")){
					  bid=true;
				  }
			      if (qName.equalsIgnoreCase("use")) {
			    	  buse=true;
			      } else if (qName.equalsIgnoreCase("type")) {
			         btype = true;
			      } else if (qName.equalsIgnoreCase("name")){
			    	  bname=true;
			      } else if (qName.equalsIgnoreCase("passage")){
			    	  bpas=true;
			      } else if (qName.equalsIgnoreCase("path")){
			    	  bpath=true;
			      } else if (qName.equalsIgnoreCase("imageG")){
			    	  bimG=true;
			      } else if (qName.equalsIgnoreCase("imageM")){
			    	  bimM=true;
			      }
	}

			   @Override
			   public void endElement(String uri, 
			   String localName, String qName) throws SAXException {
			      if (qName.equalsIgnoreCase("objet")) {
			         System.out.println("End Element :" + name + " " + id +" "+ type + " "+ use + " " + passable + " " + path);
			      }
			   }

			   @Override
			   public void characters(char ch[], int start, int length) throws SAXException {
				   String lecture = new String(ch,start,length);
				   	if (bid){
				   		id=Integer.parseInt(lecture);
				   		bid=false;
				   	}
				   	else if (buse){
				   		use=Integer.parseInt(lecture);
				   		buse=false;
				  } else if (bname){
						name=lecture;
						bname=false;
				  } else if (btype) {
					 	type=Integer.parseInt(lecture);
					 	btype=false;
				  }else if (bpas){
					   passable=(Integer.parseInt(lecture)==1);
					   bpas=false;
				  }
				  else if (bpath){
					   path=lecture;
					   c=new Objet(id,type,use,name,passable,path);
					   list.add(c);
					   bpath=false;
				  } else if (bimG){
					   imageGentil=lecture;
					   bimG=false;
				  } else if (bimM){
					   imageMechant=lecture;
					   bimM=false;
				  }
			   }
			   
			   public void startDocument() throws SAXException {
				   
			   }
			   
			   public void endDocument() throws SAXException {
			
			   }
			}
