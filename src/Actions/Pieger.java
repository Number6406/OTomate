package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Grille;

public class Pieger extends $Action {

	public Pieger(String succes, String echec) {
		super(succes, echec);
		// TODO Auto-generated constructor stub
	}

	public Pieger() {
		valeur = 10;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if ((Grille.Pos(p.getPosition()).piegee == false) && (l.get(0) == 0 || l.get(0) == 15)) {
			Grille.Pos(p.getPosition()).piegee = true;
			effect = true;
		} else {
			effect = false;
		}
	}
}
