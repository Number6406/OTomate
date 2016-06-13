package Actions;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Otomate.$Personnage;
import Otomate.Gentil;
import Otomate.Grille;
public class testActions {

	public static void main(String[] args) {
		Grille G = new Grille();
		$Personnage A = new Gentil("Automate1.xml");
		A.setColor(Color.cyan);
		$Personnage B = new Gentil("Automate1.xml");
		B.setColor(Color.pink);
		
		List<$Personnage> listPerso = new ArrayList<>();
		
		List<Integer> listCond = new ArrayList<Integer>(6);
		listCond.add(1);
		listCond.add(2);
		listCond.add(3);
		listCond.add(4);
		listCond.add(5);
		listCond.add(6);
		
		
		$Action todo = new DeplOuest();	
		System.out.println(todo.toString());	
		todo.todo(listCond, A, listPerso, G);
		System.out.println(todo.toString());
	}

}
