package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Mechant;
import Otomate.Grille;

public class DeplNord extends $Action {

	public DeplNord(String succes, String echec) {
		super(succes, echec);
		// TODO Auto-generated constructor stub
	}

	public DeplNord() {
		valeur = 1;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if (l.get(1) == 1) { // 1 = chemin au nord avec le deuxieme element de
								// la liste qui regarde la case au nord
			p.getPosition().setX(p.getPosition().getY() - 1);
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
