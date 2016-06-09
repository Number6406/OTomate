package Otomate;

import java.util.List;

public class DeplEst extends $Action{

	private boolean effect = false;
	int valeur = 3;
	
	public void todo(List<Integer> l, $Personnage p){
		if(l.get(2) == 3){		// 3 = chemin au sud et 3eme element de la liste -> regarde l'est
			p.position.setX(p.position.getX()+1);
			effect = true;
		}
	}
	
	public String toString(){
		if(effect == true)
			return("Le personnage se deplace a l'est.");
		else
			return ("Echec, le personnage n'a pas franchi l'obstacle :D.");
	}
}
