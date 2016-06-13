package Parser;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import Actions.*;

/*
1 : AttNord
2 : AttEst
3 : AttSud
4 : AttOuest
5 : deplNord
6 : deplEst
7 : deplSud
8 : deplOuest
9 : Detruire
10 : Fuir
11 : Manger
12 : Pieger
13 : Ramasser
14 : Soigner
15 :
 */

public class XMLAction extends DefaultHandler {

	boolean bid = false;
	boolean bsucces = false;
	boolean bechec = false;

	int id;
	List<$Action> list = new LinkedList<>();
	String succes;
	String echec;

	static final int nb_cond = 12;

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("id")) {
			bid = true;
		} else if (qName.equalsIgnoreCase("succes")) {
			bsucces = true;
		} else if (qName.equalsIgnoreCase("echec")) {
			bechec = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("Case")) {
			System.out.println("End Element :" + succes + " " + echec + " " + id);
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		String lecture = new String(ch, start, length);
		if (bid) {
			id = Integer.parseInt(lecture);
			bid = false;
		} else if (bsucces) {
			succes = lecture;
		}

		else if (bechec) {
			echec = lecture;
			switch (id) {
			case 0:
				list.add(new RaF(succes, echec));
				break;

			case 1:
				list.add(new AttNord(succes, echec));
				break;

			case 2:
				list.add(new AttEst(succes, echec));
				break;

			case 3:
				list.add(new AttSud(succes, echec));
				break;

			case 4:
				list.add(new AttOuest(succes, echec));
				break;

			case 5:
				list.add(new DeplNord(succes, echec));
				break;

			case 6:
				list.add(new DeplEst(succes, echec));
				break;

			case 7:
				list.add(new DeplSud(succes, echec));
				break;

			case 8:
				list.add(new DeplOuest(succes, echec));
				break;

			case 9:
				list.add(new Detruire(succes, echec));
				break;

			case 10:
				list.add(new Fuir(succes, echec));
				break;

			case 11:
				list.add(new Manger(succes, echec));
				break;

			case 12:
				list.add(new Pieger(succes, echec));
				break;

			case 13:
				list.add(new Ramasser(succes, echec));
				break;

			case 14:
				list.add(new Soigner(succes, echec));
				break;

			case 15:
				break;
			}
		}
	}

	public void startDocument() throws SAXException {

	}

	public void endDocument() throws SAXException {

	}
}
