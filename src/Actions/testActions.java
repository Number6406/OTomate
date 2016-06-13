package Actions;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Otomate.$Personnage;
import Otomate.Gentil;
import Otomate.Grille;
import Otomate.Joueur;
import Otomate.Objet;
import Parser.ParserAction;
public class testActions {

	public static void main(String[] args) {
		Grille G = new Grille();
		$Personnage A = new Gentil("Automate1.xml");
		A.setColor(Color.cyan);
		$Personnage B = new Gentil("Automate1.xml");
		B.setColor(Color.pink);
		
		ParserAction P = new ParserAction("ActionsZombie.xml");
		List<$Action> la = P.list;
		
		List<$Personnage> listPerso = new ArrayList<>();
		
		//List<Objet> listContenus = G.objparser("ContenusZombie.xml");
		
		//List<Integer> listCond = G.recupcond(A, G.condparser("ActionsZombie.xml"),listContenus,listJoueurs);
		
		
		$Action todo = new DeplOuest();	
		System.out.println(todo.toString());	
		//todo.todo(listCond, A, listPerso, G);
		System.out.println(todo.toString());
	}

}
