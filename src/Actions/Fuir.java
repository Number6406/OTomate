package Actions;


import java.util.List;

import Otomate.$Personnage;
import Otomate.Coordonnees;
import Otomate.Gentil;
import Otomate.Grille;
import Otomate.Jeu;
import Otomate.Mechant;
import PathFinding.*;

public class Fuir extends $Action {

	public Fuir(String succes, String echec) {
		super(succes, echec);
	}

	public Fuir() {
		valeur = 12;
	}
	
	int dist(Coordonnees c1, Coordonnees c2){
		return Math.abs( (c1.getX()-c2.getX()) + c1.getY()-c2.getY());
	}
	
	public Gentil prox($Personnage p, List<$Personnage> l){
		int max = l.size();
		int minDist=Jeu.plateau.tailleX()+Jeu.plateau.tailleY(),mini=0,i;
		for(i=0;i<max;i++){
			if(l.get(i) instanceof Gentil){
				int current=dist(p.getPosition(),l.get(i).getPosition());
				if(current<minDist){
					minDist=current;
					mini=i;
				}
			}
		}
		return (Gentil) l.get(mini);
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		Coordonnees cnord, csud, cest, couest;
		cnord=p.getPosition().CalculCase(1);
		csud=p.getPosition().CalculCase(3);
		cest=p.getPosition().CalculCase(2);
		couest=p.getPosition().CalculCase(4);
		
		if (p instanceof Gentil) {
			int rnd;
			if (l.get(1) == 5) { // ennemi au nord
				if (l.get(2) == 7) { // ennemi a l'est
					if (l.get(3) == 6) { // ennemi au sud
						if (l.get(4) == 8) {
						} // ennemi a l'ouest -> ne bouge pas il est coince
						
						if (p.getPosition().getX()>0 && g.Pos(couest).getValeur() != 3 && g.Pos(couest).getValeur() != 5) {	//passage a l'ouest
							p.setPosition(couest); // seule case disponible -->
							// a l'ouest
							effect = true;
				        	if(p.getInactivite()<20)
				        		p.setInactivite(20);
						}
					}
					else if (l.get(4) == 8) {
						if (p.getPosition().getY()<g.tailleY()-1 && g.Pos(csud).getValeur() != 3 && g.Pos(csud).getValeur() != 5) {	//passage au sud
							p.setPosition(csud); // seule case disponible --> au
							// sud
							effect = true;
				        	if(p.getInactivite()<20)
				        		p.setInactivite(20);
						}
					}
					else {
						rnd = Grille.random(1, 3); // ennemi au nord et a l'est
						// = choix de la fuite
						// random
						if (p.getPosition().getX()>0 && rnd == 1 && g.Pos(couest).getValeur() != 3 && g.Pos(couest).getValeur() != 5) {	//passage ouest
							p.setPosition(couest);
							effect = true;
				        	if(p.getInactivite()<20)
				        		p.setInactivite(20);
						}
						else if (p.getPosition().getY()<g.tailleY()-1 && g.Pos(csud).getValeur() != 3 && g.Pos(csud).getValeur() != 5) {	//passage sud
							p.setPosition(csud);
							effect = true;
				        	if(p.getInactivite()<20)
				        		p.setInactivite(20);
						}
					}
				}
				else if (l.get(3) == 6) {
					if (l.get(4) == 8) {
						if (p.getPosition().getX()<g.tailleX()-1 && g.Pos(cest).getValeur() != 3 && g.Pos(cest).getValeur() != 5) {	//passage est
							p.setPosition(cest);
							effect = true;
				        	if(p.getInactivite()<20)
				        		p.setInactivite(20);
						}
					}
					else {
						rnd = Grille.random(1, 3); // ennemi au nord et au sud =
						// choix de la fuite random
						if (p.getPosition().getX()<g.tailleX()-1 && rnd == 1 && g.Pos(cest).getValeur() != 3 && g.Pos(cest).getValeur() != 5) {	//passage est
							p.setPosition(cest);
							effect = true;
				        	if(p.getInactivite()<20)
				        		p.setInactivite(20);
						}
						else if (p.getPosition().getX()>0 && g.Pos(couest).getValeur() != 3 && g.Pos(couest).getValeur() != 5) {	//passage ouest
							p.setPosition(couest);
							effect = true;
				        	if(p.getInactivite()<20)
				        		p.setInactivite(20);
						}
					}
				}
				else {
					rnd = Grille.random(1, 4); // ennemi present au nord
					if (p.getPosition().getX()<g.tailleX()-1 && rnd == 1 && g.Pos(cest).getValeur() != 3 && g.Pos(cest).getValeur() != 5) {	//passage est
						p.setPosition(cest);
						effect = true;
			        	if(p.getInactivite()<20)
			        		p.setInactivite(20);
					}
					else if (p.getPosition().getY()<g.tailleY()-1 && rnd == 2 && g.Pos(csud).getValeur() != 3 && g.Pos(csud).getValeur() != 5) {	//passage sud
						p.setPosition(csud);
						effect = true;
			        	if(p.getInactivite()<20)
			        		p.setInactivite(20);
					}
					else if ( p.getPosition().getX()>0 && g.Pos(couest).getValeur() != 3 && g.Pos(couest).getValeur() != 5) {	//passage ouest
						p.setPosition(couest);
						effect = true;
			        	if(p.getInactivite()<20)
			        		p.setInactivite(20);
					}
				}
			}
			else if (l.get(2) == 7) {
				if (l.get(3) == 6) {
					if (l.get(4) == 8) { // fuite vers le nord seule case non
						// occupee
						if (g.Pos(cnord).getValeur() != 3 && g.Pos(cnord).getValeur() != 5 && p.getPosition().getY()>0) {	//passage nord
							p.setPosition(cnord);
							effect = true;
				        	if(p.getInactivite()<20)
				        		p.setInactivite(20);
						}
					}
					else {
						rnd = Grille.random(1, 3); // chois aleatoire entre nord
						// et ouest
						if (p.getPosition().getY()>0 && rnd == 1 && g.Pos(cnord).getValeur() != 3 && g.Pos(cnord).getValeur() != 5) {	//passage nord
							p.setPosition(cnord);
							effect = true;
				        	if(p.getInactivite()<20)
				        		p.setInactivite(20);
						}
						else if (p.getPosition().getX()>0 && g.Pos(couest).getValeur() != 3 && g.Pos(couest).getValeur() != 5) {	//passage ouest
							p.setPosition(couest);
							effect = true;
				        	if(p.getInactivite()<20)
				        		p.setInactivite(20);
						}
					}
				}
				else if (l.get(4) == 8) {
					rnd = Grille.random(1, 4);
					if (p.getPosition().getY()>0 && rnd == 1 && g.Pos(cnord).getValeur() != 3 && g.Pos(cnord).getValeur() != 5) {	//passage nord
						p.setPosition(cnord);
						effect = true;
			        	if(p.getInactivite()<20)
			        		p.setInactivite(20);
					}
					else if (p.getPosition().getY()<g.tailleY()-1 && rnd == 2 && g.Pos(csud).getValeur() != 3 && g.Pos(csud).getValeur() != 5) {	//passage sud
						p.setPosition(csud);
						effect = true;
			        	if(p.getInactivite()<20)
			        		p.setInactivite(20);
					}
					else if (p.getPosition().getX()>0 && g.Pos(couest).getValeur() != 3 && g.Pos(couest).getValeur() != 5) {	//passage ouest
						p.setPosition(couest);
						effect = true;
			        	if(p.getInactivite()<20)
			        		p.setInactivite(20);
					}
				}
			}
			else if (l.get(3) == 6) {
				if (l.get(4) == 8) {
					rnd = Grille.random(1, 3);
					if (p.getPosition().getY()>0 && rnd == 1 && g.Pos(cnord).getValeur() != 3 && g.Pos(cnord).getValeur() != 5) {	//passage nord
						p.setPosition(cnord);
						effect = true;
			        	if(p.getInactivite()<20)
			        		p.setInactivite(20);
					}
					else if (p.getPosition().getX()<g.tailleX()-1 && g.Pos(cest).getValeur() != 3 && g.Pos(cest).getValeur() != 5) {	//passage est
						p.setPosition(cest);
						effect = true;
			        	if(p.getInactivite()<20)
			        		p.setInactivite(20);
					}
				}
				else {
					rnd = Grille.random(1, 4);
					if (p.getPosition().getY()>0 && rnd == 1 && g.Pos(cnord).getValeur() != 3 && g.Pos(cnord).getValeur() != 5) {	//passage nord
						p.setPosition(cnord);
						effect = true;
			        	if(p.getInactivite()<20)
			        		p.setInactivite(20);
					}
					else if (p.getPosition().getY()<g.tailleY()-1 && rnd == 2 && g.Pos(cest).getValeur() != 3 && g.Pos(cest).getValeur() != 5) {	//passage est
						p.setPosition(cest);
						effect = true;
			        	if(p.getInactivite()<20)
			        		p.setInactivite(20);
					}
					else if (p.getPosition().getX()>0 && g.Pos(couest).getValeur() != 3 && g.Pos(couest).getValeur() != 5) {	//passage ouest
						p.setPosition(couest);
						effect = true;
			        	if(p.getInactivite()<20)
			        		p.setInactivite(20);
					}
				}
			}
			else if (l.get(4) == 8) {
				rnd = Grille.random(1, 4);
				if (p.getPosition().getY()>0 && rnd == 1 && g.Pos(cnord).getValeur() != 3 && g.Pos(cnord).getValeur() != 5) {	//passage nord
					p.setPosition(cnord);
					effect = true;
		        	if(p.getInactivite()<20)
		        		p.setInactivite(20);
				}
				else if (p.getPosition().getX()<g.tailleX()-1 && rnd == 2 && g.Pos(cest).getValeur() != 3 && g.Pos(cest).getValeur() != 5) {	//passage est
					p.setPosition(cest);
					effect = true;
		        	if(p.getInactivite()<20)
		        		p.setInactivite(20);
				}
				else if (p.getPosition().getY()<g.tailleY()-1 && g.Pos(csud).getValeur() != 3  && g.Pos(csud).getValeur() != 5) {	//passage sud
					p.setPosition(csud);
					effect = true;
		        	if(p.getInactivite()<20)
		        		p.setInactivite(20);
				}
			}
			else { // aucun ennemi a proximite ne fait rien
				effect = false;
				p.setInactivite(p.getInactivite()-1);
			}
		}
		else if (p instanceof Mechant) {
			Coordonnees ppp=prox(p,lp).getPosition();
			Pfind path = new Pfind(p.getPosition(),ppp);
			Coordonnees c=p.getPosition().CalculCase(path.getDirection());
			if( !c.occupee() ){
				//if(g.get(c.getX(), c.getY()).Passable(Jeu.univers.getObjets())) 
					p.setPosition(c);
					effect = true;
		        	if(p.getInactivite()<20)
		        		p.setInactivite(20);
			}
			else{
				//Ne fais rien
				effect = false;
				p.setInactivite(p.getInactivite()-1);
			}
		}
		else {
			effect = false;
			p.setInactivite(p.getInactivite()-1);
		}
	}
}