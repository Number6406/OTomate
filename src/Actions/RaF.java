package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Grille;

public class RaF extends $Action {

	public RaF(String succes, String echec) {
		super(succes, echec);
	}

	public RaF() {
		valeur = 0;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		effect = true;
	}
}
