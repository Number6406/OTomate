package Actions;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Otomate.$Personnage;
import Otomate.Gentil;
import Otomate.Grille;
import Otomate.Joueur;
import Otomate.Univers;
public class testActions {

	public static void main(String[] args) {
		
		Grille G = new Grille();
		Univers U = new Univers(1);
		
		
		$Personnage A = new Gentil("AutomateenXML.xml",Color.cyan);
		$Personnage B = new Gentil("AutomateenXML.xml",Color.pink);
		
		System.out.println(A.getAutomate().toString());
		
		List<Joueur> listJoueurs = new ArrayList<>();
		listJoueurs.add(new Joueur(A));
		listJoueurs.add(new Joueur(B));
		
		
		List<$Personnage> listPerso = new ArrayList<>();
		listPerso.add(A);
		listPerso.add(B);
		
		List<Boolean> lc = G.recupcond(A, G.condparser("conditions.xml") ,U.getObjets(),listJoueurs);

		System.out.println("SIZE bool : " + lc.size());
		
		List<Integer> listCond = G.conditions(A,lc);
		
		$Action aFaire = G.takeOne(G.actionsPossibles(A, listCond));

		System.out.println("Before : " + aFaire.toString());	
		aFaire.todo(listCond, A, listPerso, G);
		System.out.println(aFaire.toString());
		
		
		$Action todo = new DeplOuest();	
		System.out.println(todo.toString());	
		todo.todo(listCond, A, listPerso, G);
		System.out.println(todo.toString());
		
	}

}
