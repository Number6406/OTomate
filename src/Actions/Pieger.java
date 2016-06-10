package Actions;


import java.util.List;

import Otomate.$Personnage;
import Otomate.Grille;
public class Pieger extends $Action{
	
	public Pieger(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private boolean effect = false;
	int valeur = 10;
	
	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g){
		if((Grille.Pos(p.getPosition()).piegee==false)&&(l.get(0) == 0 || l.get(0) == 15)){
			Grille.Pos(p.getPosition()).piegee = true;
			effect = true;
		}
	}
	
	public String toString(){
		if(effect == true)
			return ("place discretement un piege sur la case.");
		else
			return ("ne peut pas poser de piege sur la case.");
	}

}
