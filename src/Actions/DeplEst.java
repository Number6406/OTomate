package Actions;


import java.util.List;
import Otomate.$Personnage;
import Otomate.Mechant;
import Otomate.Grille;

public class DeplEst extends $Action{

	private boolean effect = false;
	int valeur = 3;
	
	public void todo(List<Integer> l, $Personnage p,Grille g){
		if(l.get(2) == 3){		// 3 = chemin au sud et 3eme element de la liste -> regarde l'est
			p.getPosition().setX(p.getPosition().getX()+1);
			effect = true;
		}
		if (p instanceof Mechant){
			if (Grille.Pos(p.getPosition()).piegee==true)
			((Mechant) p).setVie(((Mechant) p).getVie()-20);
		}
	}
	
	public String toString(){
		if(effect == true)
			return("Le personnage se deplace a l'est.");
		else
			return ("Echec, le personnage n'a pas franchi l'obstacle :D.");
	}
}
