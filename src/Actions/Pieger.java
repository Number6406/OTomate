package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Gentil;
import Otomate.Grille;

public class Pieger extends $Action {

	public Pieger(String succes, String echec) {
		super(succes, echec);
	}

	public Pieger() {
		valeur = 10;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if(p instanceof Gentil){
			if ((g.Pos(p.getPosition()).getPiegee() == false) && (l.get(0) == 0 || l.get(0) == 15) && ((Gentil) p).getPiege() == 0) {	//est piegeable toute case vide ou fouillable
				g.Pos(p.getPosition()).setPiegee(true);
				((Gentil) p).setPiege(10);
				effect = true;
	        	if(p.getInactivite()<20)
	        		p.setInactivite(20);
			}
		}
		else {
			effect = false;
			p.setInactivite(p.getInactivite()-1);
		}
	}
}
