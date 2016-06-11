package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Coordonnees;
import Otomate.Gentil;
import Otomate.Mechant;
import Otomate.Grille;
public class AttSud extends $Action{

	public AttSud(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private boolean effect = false;

	public AttSud(){
		valeur = 6;
	}
    
	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g){
		if(l.get(3) == 6){		// 6 = ennemi au sud et 3 regard au sud
			$Personnage e = null;
			Coordonnees card = p.getPosition();
			card.setY(card.getY()+1);
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
			return("attaque l'ennemi au sud.");
		else
			return ("n'a rien fait, il n'y a pas d'ennemi au sud.");
	}
}