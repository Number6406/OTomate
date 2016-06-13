package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Grille;

public abstract class $Action {
	protected int valeur;
	protected String succes;
	protected String echec;

	public $Action(String succes,String echec){
		this.succes=succes;	
		this.echec=echec;	
	}
	
	public $Action(){
		valeur = -1;
	}
	
	public abstract void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g);

}
