package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Mechant;
import Otomate.Grille;

public class DeplOuest extends $Action {

	public DeplOuest(String succes, String echec) {
		super(succes, echec);
	}

	public DeplOuest() {
		valeur = 4;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if (l.get(4) == 4 && p.getPosition().getX()>0) { // 4 = chemin a l'ouest et 5eme element de la liste
								// -> regarde l'ouest
			p.getPosition().setX(p.getPosition().getX() - 1);
			effect = true;
        	if(p.getInactivite()<20)
        		p.setInactivite(20);
		}
		else { // Echec de l'action
			effect = false;
			p.setInactivite(p.getInactivite()-1);
		}	 
		if (p instanceof Mechant) {
			if (g.Pos(p.getPosition()).piegee == true){
				((Mechant) p).setVie(((Mechant) p).getVie() - 20);
				g.Pos(p.getPosition()).piegee = false;		//le piege a une utilisation unique
			}
		}
	}
}
