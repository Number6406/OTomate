package Otomate;

import java.util.List; 

public class DeplOuest extends $Action{

	private boolean effect = false;
	int valeur = 4;
	
	public void todo(List<Integer> l, $Personnage p, Grille g){
		if(l.get(4) == 4){		// 4 = chemin a l'ouest et 5eme element de la liste -> regarde l'ouest
			p.position.setX(p.position.getX()-1);
			effect = true;
		}
		if (p instanceof Mechant){
			if (Grille.Pos(p.position).piegee==true)
			((Mechant) p).vie-=20;
		}
	}
	
	public String toString(){
		if(effect == true)
			return("Le personnage se deplace a l'ouest.");
		else
			return ("Echec, le personnage n'a pas franchi l'obstacle :D.");
	}
}
