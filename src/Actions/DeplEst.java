package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Jeu;
import Otomate.Mechant;
import Otomate.Grille;

public class DeplEst extends $Action {

	public DeplEst(String succes, String echec) {
		super(succes, echec);
	}

	public DeplEst() {
		valeur = 3;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if (l.get(2) == 3 && p.getPosition().getX()<Jeu.plateau.tailleY()-1 ) { // 3 = chemin a l'est et 3eme element de la liste ->
								// regarde l'est
			p.getPosition().setX(p.getPosition().getX() + 1);
			effect = true;
        	if(p.getInactivite()<20)
        		p.setInactivite(20);
		}
		else { // Echec de l'action
			effect = false;
			p.setInactivite(p.getInactivite()-1);
		}	 
		if (p instanceof Mechant) {
			if (g.Pos(p.getPosition()).getPiegee() == true){
				((Mechant) p).setVie(((Mechant) p).getVie() - 20);
				g.Pos(p.getPosition()).setPiegee(false);		//le piege a une utilisation unique
			}
		}
	}
}
