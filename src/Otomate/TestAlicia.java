package Otomate;

import java.awt.Color;

public class TestAlicia {

	public static void main(String[] args) {
		//Jeu.initPartie();
		
		Mechant m = new Mechant("AutomateenXML.xml",Color.cyan,"John");
		System.out.println(m.toString());

	}

}
