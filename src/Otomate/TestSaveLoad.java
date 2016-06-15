package Otomate;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import Otomate.historique.Historique;
import Parser.ParserConditions;

public class TestSaveLoad {
	
	public static void main(String[] args) throws IOException {
		Jeu jeu = new Jeu();
		jeu.plateau = new Grille();
		jeu.historique = new Historique();
		// Variables définies grâce au menu d'affichage ->
		int nbJoueurs = 2;
		int nbPersoParJoueur = 2;
		int nZombie = 2; // Variable possiblement tirée au sort
		int nbPersoParZombie = 2;
		List<String> xmlsGentils = new LinkedList<String>();
		String fichiers = new File("Conditions.xml").toString();
		xmlsGentils.add("AutomateenXML.xml");
		List<String> xmlsMechants = new LinkedList<String>();
		xmlsMechants.add("AutomateenXML.xml");
		List<List<String>> xmls = new LinkedList<>();
		xmls.add(xmlsGentils);
		xmls.add(xmlsMechants);
		List<Conditions> listCond = new LinkedList<>();
		List<Objet> listCont = new LinkedList<>();
		List<Color> couleurs = new LinkedList<>();
		couleurs.add(Color.RED);
		couleurs.add(Color.BLUE);

		ParserConditions p1 = new ParserConditions(fichiers);
		//ParserObjet p2 = new ParserObjet("objet.xml");
		listCond = jeu.plateau.condparser(fichiers);
		// System.out.println("Encore avant : " + p1.list.size());
		//listCont = jeu.plateau.objparser("objet.xml");
		// <- Fin variables
		int j, p;
		jeu.initJoueurs(nbPersoParZombie, nZombie, xmls, couleurs);
		jeu.refPersos = new LinkedList<Integer>();
		jeu.plateau.initialisergrille(jeu.joueurs);
		jeu.univers = new Univers(1);
		SaveLoad s = new SaveLoad(jeu, "TAMER.txt");
		s.save();
		SaveLoad suu = new SaveLoad("TAMER.txt");
		suu.load();
	}
}