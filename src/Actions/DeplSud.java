package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Mechant;
import Otomate.Grille;

public class DeplSud extends $Action {

	public DeplSud(String succes, String echec) {
		super(succes, echec);
		// TODO Auto-generated constructor stub
	}

	public DeplSud() {
		valeur = 2;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if (l.get(3) == 2) { // 2 = chemin au sud et 4eme element de la liste ->
								// regarde le sud
			p.getPosition().setY(p.getPosition().getY() + 1);
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
