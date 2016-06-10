package Actions;

import java.util.List;
import Otomate.$Personnage;
import Otomate.Mechant;
import Otomate.Grille;

public class DeplSud extends $Action{
	
	private boolean effect = false;
	int valeur = 2;
	
	public void todo(List<Integer> l, $Personnage p, Grille g){
		if(l.get(3) == 2){		// 2 = chemin au sud et 4eme element de la liste -> regarde le sud
			p.getPosition().setX(p.getPosition().getY()+1);
			effect = true;
		}
		if (p instanceof Mechant){
			if (Grille.Pos(p.getPosition()).piegee==true)
			((Mechant) p).setVie(((Mechant) p).getVie()-20);
		}
	}
	
	public String toString(){
		if(effect == true)
			return(" se deplace au sud.");
		else
			return (" ne peut pas se deplacer au sud, il y a un obstacle.");
	}
}
