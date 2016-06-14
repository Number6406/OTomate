package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Grille;

public class Detruire extends $Action {

	public Detruire(String succes, String echec) {
		super(succes, echec);
		// TODO Auto-generated constructor stub
	}

	public Detruire() {
		valeur = 14;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if (l.get(0) == 17) {	//objet destructible sur ma case?
			int aux;
			aux = p.getInventaire();
			p.setInventaire(8);	//on récupère l'objet 8 en detruisant l'objet 12
			Grille.Pos(p.getPosition()).setValeur(aux);
			effect = true;
		}
		else {
			effect = false;
		}
	}
}
