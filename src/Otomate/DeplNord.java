package Otomate;

import java.util.List;

public class DeplNord extends $Action{
	
	private boolean effect = false;
	int valeur = 1;
	
	public void todo(List<Integer> l, $Personnage p){
		if(l.get(1) == 1){		//1 = chemin au nord avec le deuxieme element de la liste qui regarde la case au nord
			p.position.setY(p.position.getY()-1);
			effect = true;
		}
	}	
	
	public String toString(){
		if(effect == true)
			return("Le personnage se déplace vers le nord.");
		else
			return("Le personnage n'a pas réussi a franchir l'obstacle :(.");
	}
}
