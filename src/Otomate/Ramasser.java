package Otomate;

import java.util.List;

public class Ramasser extends $Action{

	private boolean effect = false;
	private boolean drogue = false;
	int valeur = 9;
    
	public void todo(List<Integer> l, $Personnage p, Grille g){
		if(l.get(0) != 0){		// 0 = case vide
			int aux;
			if(l.get(0) == 9){		//comestible
				aux = p.inventaire;
				p.inventaire = g.Pos(p.position).getValeur();
				g.Pos(p.position).setValeur(aux);
			}
			if(p instanceof Gentil){
				if(l.get(0) == 10){		//couteau
					aux = ((Gentil) p).arme;
					((Gentil) p).arme = g.Pos(p.position).getValeur();
					g.Pos(p.position).setValeur(aux);
				}
				else if(l.get(0) == 16){		//seringue
					if(((Gentil) p).drogue != 0){
						aux = Grille.random(0, 101);
						if(aux<10){
							p.viemax += 10;
						}
						else if(aux<20){
							p.viemax -= 10;
						}
						else if(aux<30){
							((Gentil) p).vie += 5;
							if(((Gentil) p).vie > ((Gentil) p).viemax)
								((Gentil) p).vie = ((Gentil) p).viemax;
							((Gentil) p).efdrogue = 3;
						}
						else if(aux < 40){
							((Gentil) p).vie -= 5;
							((Gentil) p).efdrogue = 3;
						}
						else if(aux < 60){
							((Gentil) p).efdrogue = 2;
						}
						else if(aux < 80){
							((Gentil) p).vie += 10;
							if(((Gentil) p).vie > ((Gentil) p).viemax)
								((Gentil) p).vie = ((Gentil) p).viemax;
						}
						else{
							((Gentil) p).vie -= 10;
						}
						drogue=true;
					}
				}
			}
			effect = true;
		}
	}
	
	public String toString(){
		if((effect == true)&&(drogue == false))
			return("Le personnage a ramasse quelque chose.");
		else if((effect == true)&&(drogue == false)){
			return("Le personnage a ramasse de la drogue et s'est pique, petit filou.");
		}
		else
			return ("Il n'y a rien a recolter");
	}
}
