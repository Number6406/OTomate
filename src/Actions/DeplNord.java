package Actions;


import java.util.List;

import Otomate.$Personnage;
import Otomate.Mechant;
import Otomate.Grille;
public class DeplNord extends $Action{
	
	public DeplNord(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private boolean effect = false;
	
	public DeplNord(){
		valeur = 1;
	}
	
	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g){
		if(l.get(1) == 1){		//1 = chemin au nord avec le deuxieme element de la liste qui regarde la case au nord
			p.getPosition().setX(p.getPosition().getY()-1);
			effect = true;
		}
		if (p instanceof Mechant){
			if (Grille.Pos(p.getPosition()).piegee==true)
			((Mechant) p).setVie(((Mechant) p).getVie()-20);
		}
	}
	
	public String toString(){
		if(effect == true)
			return("se déplace vers le nord.");
		else
			return ("ne peut pas se deplacer au nord, il y a un obstacle.");
	}
}
