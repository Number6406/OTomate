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
		$Personnage A = new Gentil("AutomateenXML.xml",Color.cyan);
		//$Personnage B = new Gentil("AutomateenXML.xml",Color.pink);
		
		ParserAction P = new ParserAction("ActionsHumain.xml");
		List<$Action> la = P.list;
		System.out.println("SIZE : " + la.size());
		for($Action Ac : la){
			System.out.println(Ac.toString());
		}
		
		List<Joueur> listJoueurs = new ArrayList<>();
		
		
		List<$Personnage> listPerso = new ArrayList<>();
		
		List<Objet> listContenus = G.objparser("objet.xml");
		System.out.println("SIZE :" + listContenus.size());
		
		List<Integer> listCond = G.conditions(A,G.recupcond(A, G.condparser("ActionsZombie.xml"),listContenus,listJoueurs));
		
		$Action aFaire = G.takeOne(G.actionsPossibles(A, listCond));

		System.out.println(aFaire.toString());	
		aFaire.todo(listCond, A, listPerso, G);
		System.out.println(aFaire.toString());
		
		
		$Action todo = new DeplOuest();	
		System.out.println(todo.toString());	
		todo.todo(listCond, A, listPerso, G);
		System.out.println(todo.toString());
		
	}

}
