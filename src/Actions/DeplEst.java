package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Mechant;
import Otomate.Grille;

public class DeplEst extends $Action {

	public DeplEst(String succes, String echec) {
		super(succes, echec);
		// TODO Auto-generated constructor stub
	}

	public DeplEst() {
		valeur = 3;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if (l.get(2) == 3) { // 3 = chemin a l'est et 3eme element de la liste ->
								// regarde l'est
			p.getPosition().setX(p.getPosition().getX() + 1);
			effect = true;
		}
		else {
			effect = false;
		}
		if (p instanceof Mechant) {
			if (Grille.Pos(p.getPosition()).piegee == true){
				((Mechant) p).setVie(((Mechant) p).getVie() - 20);
				Grille.Pos(p.getPosition()).piegee = false;		//le piege a une utilisation unique
			}
		}
	}
}
