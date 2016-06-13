package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Grille;

public class RaF extends $Action{
	
	public RaF(String succes, String echec) {
		super(succes,echec);
		// TODO Auto-generated constructor stub
	}

	public RaF(){
		valeur = 0;
	}
	
	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g){}
	
	public String toString(){
		return("ne fait rien.");
	}
}
