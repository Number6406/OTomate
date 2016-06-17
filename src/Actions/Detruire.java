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
		if (l.get(0) == 17) {	//objet destructible sur ma case?
			int aux;
			aux = p.getInventaire();
			p.setInventaire(7);	//on r�cup�re l'objet 8 en detruisant l'objet 12
			if(aux != 0)
				g.Pos(p.getPosition()).setValeur(aux);
			else
				g.Pos(p.getPosition()).setValeur(2);
			effect = true;
        	if(p.getInactivite()<20)
        		p.setInactivite(20);
		}
		else { // Echec de l'action
			effect = false;
			p.setInactivite(p.getInactivite()-1);
		}	 
	}
}
