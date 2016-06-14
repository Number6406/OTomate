package Actions;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Otomate.$Personnage;
import Otomate.Gentil;
import Otomate.Grille;
import Otomate.Jeu;
import Otomate.Joueur;
import Otomate.Objet;
import Parser.ParserAction;
public class testActions {

	public static void main(String[] args) {
		
		Grille G = new Grille();/*
		$Personnage A = new Gentil("AutomateenXML.xml",Color.cyan);
		$Personnage B = new Gentil("AutomateenXML.xml",Color.pink);
		
		ParserAction Pz = new ParserAction("ActionsHumain.xml");
		List<$Action> laz = Pz.list;
		ParserAction Ph = new ParserAction("ActionsZombie.xml");
		List<$Action> lah = Ph.list;
		System.out.println("SIZE : " + lah.size());
		for($Action Ac : lah){
			if(Ac!=null) System.out.println(Ac.toString());
		}
		for($Action Ac : laz){
			if(Ac!=null) System.out.println(Ac.toString());
		}
		
		List<Joueur> listJoueurs = new ArrayList<>();
		listJoueurs.add(new Joueur(A));
		listJoueurs.add(new Joueur(B));
		
		
		List<$Personnage> listPerso = new ArrayList<>();
		*/		
		Jeu.debutPartie();
		/*
		List<Boolean> lc = G.recupcond(A, G.condparser("conditions.xml") ,listContenus,listJoueurs);

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
		*/
	}

}
