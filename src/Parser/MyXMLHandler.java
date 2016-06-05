package Parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import Otomate.Case;


public class MyXMLHandler extends DefaultHandler{
	   
	   boolean bEtat = false;
	   boolean bLigne = false;
	   boolean bCol = false;
	   boolean bTransition = false;
	   boolean bAction = false;
	  	
	int[][] auto;
	Case[][] act;
	int nb_etats;
	static final int nb_cond=10;
	int i,j;
	public void startElement(String uri, 
			   String localName, String qName, Attributes attributes)
			      throws SAXException {
			      if (qName.equalsIgnoreCase("nb_etats")) {
			    	  bEtat=true;
			      } else if (qName.equalsIgnoreCase("ligne")) {
			         bLigne = true;
			      } else if (qName.equalsIgnoreCase("colonne")) {
			         bCol = true;
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
			      if (qName.equalsIgnoreCase("automate")) {
			         System.out.println("End Element :" + qName);
			      }
			   }

			   @Override
			   public void characters(char ch[], 
			      int start, int length) throws SAXException {
				   String lecture = new String(ch,start,length);
				   	if (bEtat){
					  nb_etats= Integer.parseInt(lecture);
					  auto= new int[nb_cond][nb_etats];
					  act = new Case[nb_cond][nb_etats];
					  for(i=0;i<nb_cond;i++){
						  for(j=0;j<nb_etats;j++){
							  act[i][j]=new Case();
						  }
					  }
					  i=0;
					  j=0;
					  
					  bEtat=false;
				  } else if (bLigne) {
					 j= Integer.parseInt(lecture);
			         bLigne = false;
			      } else if (bCol) {
			         i= Integer.parseInt(lecture);
			    	 bCol = false;
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