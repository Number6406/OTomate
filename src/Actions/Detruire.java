package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Grille;

public class Detruire extends $Action {
	
	public Detruire(String succes, String echec) {
		super(succes,echec);
		// TODO Auto-generated constructor stub
	}
	private boolean effect = false;

	public Detruire(){
		valeur = 14;
	}
	
	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g){
		if(l.get(0) == 18){
			int aux;
			aux = p.getInventaire();
			p.setInventaire(8);
			Grille.Pos(p.getPosition()).setValeur(aux);
			effect=true;
		}
	}
	public String toString(){
		if(effect == true)
			return("a detruit un cactus et a recolte de l eau.");
		else
			return ("mais il n y a pas de chose a detruire ici.");
	}
}

