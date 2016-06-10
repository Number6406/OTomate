package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Coordonnees;
import Otomate.Grille;

public class Fuir extends $Action{

	private boolean effect = false;
	int valeur = 13;
	
	public void todo(List<Integer> l, $Personnage p, Grille g){
		Coordonnees cnord, csud, cest, couest;
		int rnd;
		cnord = csud = cest = couest = p.position;
		cnord.setY(cnord.getY()-1);
		csud.setY(csud.getX()+1);
		cest.setX(cest.getX()+1);
		couest.setY(couest.getX()-1);
		if(p instanceof Gentil){
			if(l.get(1) == 5){			//ennemi au nord
				if(l.get(2) == 7){		//ennemi a l'est
					if(l.get(3) == 6){	//ennemi au sud
						if(l.get(4) == 8){}	//ennemi a l'ouest
						if(Grille.Pos(cnord).getValeur() == 0)
							p.position = couest;	//seule case disponible --> a l'ouest
					}
					else if(l.get(4) == 8){
						if(Grille.Pos(csud).getValeur() == 0)
							p.position = csud;		//seule case disponible --> au sud
					}
					else{
						rnd = Grille.random(1, 3);	//ennemi au nord et a l'est = choix de la fuite random
						if(rnd == 1 && Grille.Pos(couest).getValeur() == 0)
							p.position = couest;
						else if(Grille.Pos(csud).getValeur() == 0)
							p.position = csud;
					}
				}
				else if(l.get(3) == 6){
					if(l.get(4) == 8){
						if(Grille.Pos(cest).getValeur() == 0)
							p.position = cest;
					}
					else{
						rnd = Grille.random(1, 3);	//ennemi au nord et au sud = choix de la fuite random
						if(rnd == 1 && Grille.Pos(cest).getValeur() == 0)
							p.position = cest;
						else if(Grille.Pos(couest).getValeur() == 0)
							p.position = couest;
					}
				}
				else{
					rnd = Grille.random(1, 4);	//ennemi present au nord
					if(rnd == 1 && Grille.Pos(cest).getValeur() == 0)
						p.position = cest;
					else if(rnd == 2 && Grille.Pos(csud).getValeur() == 0)
						p.position = csud;
					else
						p.position = couest;
				}
			}
			else if(l.get(2) == 7){
				if(l.get(3) == 6){
					if(l.get(4) == 8){		//fuite vers le nord seule case non occupée
						p.position = cnord;
					}
					else{
						rnd = Grille.random(1, 3);	//chois aleatoire entre nord et ouest
						if(rnd == 1)
							p.position = cnord;
						else
							p.position = couest;
					}
				}
				else if(l.get(4) == 8){
					rnd = Grille.random(1, 4);
					if(rnd == 1)
						p.position = cnord;
					else if(rnd == 2)
						p.position = csud;
					else
						p.position = couest;
				}
			}
			else if(l.get(3) == 6){
				if(l.get(4) == 8){
					rnd = Grille.random(1, 3);
					if(rnd == 1)
						p.position = cnord;
					else
						p.position = cest;
				}
				else{
					rnd = Grille.random(1, 4);
					if(rnd == 1)
						p.position = cnord;
					else if(rnd == 2)
						p.position = cest;
					else 
						p.position = couest
				}
			}
			else if(l.get(4) == 8){
				rnd = Grille.random(1, 4);
				if(rnd == 1)
					p.position = cnord;
				else if(rnd == 2)
					p.position = cest;
				else
					p.position = csud;
			}
			else{							//aucun ennemi a proximite on se deplace aleatoirement
				rnd = Grille.random(1, 5);
				if(rnd == 1)
					p.position = cnord;
				else if(rnd == 2)
					p.position = cest;
				else if(rnd == 3)
					p.position = csud;
				else
					p.position = couest;
			}
		}
	}
}
