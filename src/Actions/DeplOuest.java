package Actions;


import java.util.List; 
import Otomate.$Personnage;
import Otomate.Coordonnees;
import Otomate.Joueur;
import Otomate.Gentil;
import Otomate.Mechant;
import Otomate.Grille;
public class DeplOuest extends $Action{

	private boolean effect = false;
	int valeur = 4;
	
	public void todo(List<Integer> l, $Personnage p, Grille g){
		if(l.get(4) == 4){		// 4 = chemin a l'ouest et 5eme element de la liste -> regarde l'ouest
			p.getPosition().setX(p.getPosition().getX()-1);
			effect = true;
		}
		if (p instanceof Mechant){
			if (g.Pos(p.getPosition()).piegee==true)
			((Mechant) p).setVie(((Mechant) p).getVie()-20);
		}
	}
	
	public String toString(){
		if(effect == true)
			return("Le personnage se deplace a l'ouest.");
		else
			return ("Echec, le personnage n'a pas franchi l'obstacle :D.");
	}
}
