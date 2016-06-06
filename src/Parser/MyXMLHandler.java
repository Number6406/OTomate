package Parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import Otomate.Case;


public class MyXMLHandler extends DefaultHandler{
	   
	   boolean binit = false;
	   boolean bNbEtat = false;
	   boolean bEtat = false;
	   boolean bCond = false;
	   boolean bTransition = false;
	   boolean bAction = false;
	  	
	int etat_init;   
	int[][] auto;
	Case[][] act;
	int nb_etats;
	static final int nb_cond=12;
	int i,j;
	public void startElement(String uri, 
			   String localName, String qName, Attributes attributes)
			      throws SAXException {
				  if (qName.equalsIgnoreCase("etat_init")){
					  binit=true;
				  }
			      if (qName.equalsIgnoreCase("nb_etats")) {
			    	  bNbEtat=true;
			      } else if (qName.equalsIgnoreCase("etat")) {
			         bEtat = true;
			      } else if (qName.equalsIgnoreCase("condition")) {
			         bCond = true;
			      } else if (qName.equalsIgnoreCase("transition")) {
			         bTransition = true;
			      }
			      else if (qName.equalsIgnoreCase("action")) {
			         bAction = true;
			      }
			      else if(qName.equalsIgnoreCase("case")){
			    	 
			      }
			   }

			   @Override
			   public void endElement(String uri, 
			   String localName, String qName) throws SAXException {
			      if (qName.equalsIgnoreCase("Case")) {
			         //System.out.println("End Element :" + qName + " " + i +" "+ j);
			      }
			   }

			   @Override
			   public void characters(char ch[], 
			      int start, int length) throws SAXException {
				   String lecture = new String(ch,start,length);
				   	if (bNbEtat){
					  nb_etats= Integer.parseInt(lecture);
					  auto= new int[nb_cond][nb_etats+1];
					  act = new Case[nb_cond][nb_etats+1];
					  for(i=0;i<nb_cond;i++){
						  for(j=0;j<nb_etats+1;j++){
							  act[i][j]=new Case();
						  }
					  }
					  i=0;
					  j=0;
					  
					  bNbEtat=false;
				   	}
				   	else if (binit){
				   	etat_init= Integer.parseInt(lecture);
				   	binit=false;
				  } else if (bEtat) {
					 j= Integer.parseInt(lecture);
			         bEtat = false;
			      } else if (bCond) {
			         i= Integer.parseInt(lecture);
			    	 bCond = false;
			      } else if (bTransition) {
			         auto[i][j]=Integer.parseInt(lecture);
			    	 bTransition = false;
			      } else if (bAction) {
			    	 act[i][j].element=Integer.parseInt(lecture);
			         bAction = false;
			      }
			   }
			   
			   public void startDocument() throws SAXException {
				   
			   }
			   
			   public void endDocument() throws SAXException {
			
			   }
			}