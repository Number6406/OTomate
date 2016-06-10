package Actions;


import java.util.List;

import Otomate.$Personnage;
import Otomate.Coordonnees;
import Otomate.Gentil;
import Otomate.Mechant;
import Otomate.Grille;

public class AttOuest extends $Action{

	public AttOuest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private boolean effect = false;
	int valeur = 8;
    
	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g){
		if(l.get(4) == 8){		// 8 = ennemi au nord et 5 regard au nord
			$Personnage e = null;
			Coordonnees card = p.getPosition();
			card.setY(card.getY()-1);
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
	}
	
	public String toString(){
		if(effect == true)
			return("attaque l'ennemi a l'ouest.");
		else
			return ("n'a rien fait, il n'y a pas d'ennemi a l'ouest.");
	}
}
