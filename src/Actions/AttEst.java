package Actions;

import java.util.List;
import java.util.Random;

import Otomate.$Personnage;
import Otomate.Coordonnees;
import Otomate.Joueur;
import Otomate.Gentil;
import Otomate.Mechant;
import Otomate.Grille;

public class AttEst extends $Action{

	private boolean effect = false;
	int valeur = 7;
    
	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp){
		if(l.get(2) == 7){		// 7 = ennemi a l'est et 3 regard a l'est
			$Personnage e = null;
			Coordonnees card = p.position;
			card.setX(card.getX()+1);
			int s = lp.size();
			int i=0;
			while(i<s){
				if(lp.get(i).position == card){
					e = lp.get(i);
					i=s;
				}
			}
			if(p instanceof Gentil){
				if(e != null && e instanceof Mechant)
					((Mechant) e).vie -= p.dmg + ((Gentil) p).arme;
				
			}
			else
				if(e != null && e instanceof Gentil){
					((Gentil) e).vie -= p.dmg;
					if(Grille.random(0, 101) > 24)
						((Gentil)e).saignement = true;
					if(Grille.random(0,101) > 4)
						((Gentil)e).infecte = true;					
				}
			effect = true;
		}
		/*if(p instanceof Gentil){
			if(((Gentil) p).efdrogue != 0){
				if(((Gentil) p).drogue == 3){
					((Gentil) p).vie += 5;
					if(((Gentil) p).vie > ((Gentil) p).viemax)
						((Gentil) p).vie = ((Gentil) p).viemax;
				}
				if(((Gentil) p).drogue == 4){
					((Gentil) p).vie -= 5;
				}
				if(((Gentil) p).drogue == 5){
					((Gentil) p).paralysie=2;
				}
				if(((Gentil) p).drogue == 6){
					((Gentil) p).paralysie=0;
				}					
				//((Gentil) p).efdrogue --;  PENSER A LE METTRE A LA FIN DE LA GRANDE BOUCE DE TOUR
			}
			else 
				((Gentil) p).drogue = false;
		}*/
	}
	
	public String toString(){
		if(effect == true)
			return("Le personnage attaque l'ennemi a l'est.");
		else
			return ("Echec, pas d'ennemi a l'est:D.");
	}
}
