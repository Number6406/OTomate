package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Coordonnees;
import Otomate.Joueur;
import Otomate.Gentil;
import Otomate.Mechant;
import Otomate.Grille;
public class Manger extends $Action{
	private boolean effect = false;
	int valeur = 11;
	
	public void todo(List<Integer> l, $Personnage p){
		if(p instanceof Gentil){
			if ((p.inventaire==7) || (p.inventaire == 6)){
				int gain = Grille.random(5,10);
				((Gentil)p).vie+=gain;
				if(((Gentil) p).vie > ((Gentil) p).viemax)
					((Gentil) p).vie = ((Gentil) p).viemax;
				p.inventaire=0;
				effect=true;
			}
		}
	}
	public String toString(){
		if(effect == true)
			return ("dit : Mmmh c'�tait bon lol");
		else
			return ("pense : C'est ballo y a rien � manger #Gfaim.");
	}
}
