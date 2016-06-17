package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Grille;

public class Detruire extends $Action {

	public Detruire(String succes, String echec) {
		super(succes, echec);
	}

	public Detruire() {
		valeur = 13;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if (l.get(0) == 17) {
			int aux;
			aux = p.getInventaire();
			p.setInventaire(7);
			if(aux != 0)
				g.Pos(p.getPosition()).setValeur(aux);
			else
				g.Pos(p.getPosition()).setValeur(2);
			effect = true;
		}
		else {
			effect = false;
		}
	}
}
