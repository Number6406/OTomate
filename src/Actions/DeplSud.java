package Actions;

import java.util.List;
import Otomate.$Personnage;
import Otomate.Coordonnees;
import Otomate.Joueur;
import Otomate.Gentil;
import Otomate.Mechant;
import Otomate.Grille;

public class DeplSud extends $Action{
	
	private boolean effect = false;
	int valeur = 2;
	
	public void todo(List<Integer> l, $Personnage p, Grille g){
		if(l.get(3) == 2){		// 2 = chemin au sud et 4eme element de la liste -> regarde le sud
			p.position.setY(p.position.getY()+1);
			effect = true;
		}
		if (p instanceof Mechant){
			if (g.Pos(p.position).piegee==true)
			((Mechant) p).vie-=20;
		}
	}
	
	public String toString(){
		if(effect == true)
			return("Le personnage se deplace au sud.");
		else
			return ("Echec, le personnage n'a pas franchi l'obstacle :D.");
	}
}
