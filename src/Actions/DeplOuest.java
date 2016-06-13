package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Mechant;
import Otomate.Grille;

public class DeplOuest extends $Action {

	public DeplOuest(String succes, String echec) {
		super(succes, echec);
		// TODO Auto-generated constructor stub
	}

	public DeplOuest() {
		valeur = 4;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if (l.get(4) == 4) { // 4 = chemin a l'ouest et 5eme element de la liste
								// -> regarde l'ouest
			p.getPosition().setX(p.getPosition().getX() - 1);
			effect = true;
		} else {
			effect = false;
		}
		if (p instanceof Mechant) {
			if (Grille.Pos(p.getPosition()).piegee == true)
				((Mechant) p).setVie(((Mechant) p).getVie() - 20);
		}
	}
}
