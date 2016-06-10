package Actions;

import java.util.List;
import Otomate.$Personnage;
import Otomate.Coordonnees;
import Otomate.Joueur;
import Otomate.Gentil;
import Otomate.Mechant;
import Otomate.Grille;

public class Detruire extends $Action {
	
	private boolean effect = false;
	int valeur = 14;
	
	public void todo(List<Integer> l, $Personnage p, Grille g){
		if(l.get(0) == 13){
			int aux;
			aux = p.getInventaire();
			p.setInventaire(g.Pos(p.getPosition()).getValeur());
			g.Pos(p.getPosition()).setValeur(aux);
		}
		}
	}

