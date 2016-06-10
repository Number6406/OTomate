package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Coordonnees;
import Otomate.Gentil;
import Otomate.Mechant;
import Otomate.Grille;

public class AttEst extends $Action{

	private boolean effect = false;
	int valeur = 7;
    
	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp){
		if(l.get(2) == 7){		// 7 = ennemi a l'est et 3 regard a l'est
			$Personnage e = null;
			Coordonnees card = p.getPosition();
			card.setX(card.getX()+1);
			int s = lp.size();
			int i=0;
			while(i<s){
				if(lp.get(i).getPosition() == card){
					e = lp.get(i);
					i=s;
				}
			}
			if(p instanceof Gentil){
				if(e != null && e instanceof Mechant)
					((Mechant) e).setVie(((Mechant) e).getVie()-(p.getDmg() + ((Gentil) p).getArme()));
				
			}
			else
				if(e != null && e instanceof Gentil){
					((Gentil) e).setVie(((Gentil) e).getVie()-p.getDmg());
					if(Grille.random(0, 101) > 24)
						((Gentil)e).setSaignement(true);
					if(Grille.random(0,101) > 4)
						((Gentil)e).setInfecte(true);				
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
			return(" attaque l'ennemi a l'est.");
		else
			return (" n'a rien fait, il n'y a pas d'ennemi a l'est.");
	}
}
