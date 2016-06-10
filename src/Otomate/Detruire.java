package Otomate;

import java.util.List;

public class Detruire extends $Action {
	
	private boolean effect = false;
	int valeur = 14;
	
	public void todo(List<Integer> l, $Personnage p, Grille g){
		if(l.get(0) == 13){
			int aux;
			aux = p.inventaire;
			p.inventaire = g.Pos(p.position).getValeur();
			g.Pos(p.position).setValeur(aux);
		}
		}
	}

