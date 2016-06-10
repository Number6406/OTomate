package Actions;


import java.util.List;
import Otomate.$Personnage;
import Otomate.Mechant;
import Otomate.Grille;
public class DeplNord extends $Action{
	
	private boolean effect = false;
	int valeur = 1;
	
	public void todo(List<Integer> l, $Personnage p, Grille g){
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
			return("Le personnage se déplace vers le nord.");
		else
			return("Le personnage n'a pas réussi a franchir l'obstacle :(.");
	}
}
