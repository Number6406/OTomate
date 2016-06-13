package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Grille;

public abstract class $Action {
	protected int valeur;
	protected String succes;
	protected String echec;
	protected boolean effect = false;

	public $Action(String succes,String echec){
		this.succes=succes;	
		this.echec=echec;	
	}
	
	public $Action(){
		valeur = -1;
	}
	
	/**
	 * 
	 * @param l, la liste des conditions environnantes
	 * @param p, le personnage qui fait l'action
	 * @param lp, la liste des personnages du jeu
	 * @param g, la grille de jeu
	 */
	public abstract void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g);
	
	public String toString(){
		if(effect == true)
			return succes;
		else
			return echec;
	}
	
}
