package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Grille;

/*
Actions :
    1 Deplacement Nord
    2 Deplacement Sud
    3 Deplacement Est
    4 Deplacement Ouest
    5 Attaque Nord
    6 Attaque Sud
    7 Attaque Est
    8 Attaque Ouest
    9 Ramasser
    10 Piéger
    11 Manger/Restaurer
    12 Soigner
    13 Fuir
    14 Détruire
    15 Fouiller
*/
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
	
	public int getValeur(){
		return valeur;
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
