package Actions;


import java.util.List;

import Otomate.$Personnage;
import Otomate.Coordonnees;
import Otomate.Gentil;
import Otomate.Grille;
import Otomate.Mechant;
import PathFinding.*;

public class Fuir extends $Action {

	public Fuir(String succes, String echec) {
		super(succes, echec);
		// TODO Auto-generated constructor stub
	}

	public Fuir() {
		valeur = 12;
	}

	// Retourne le parcours le plus court (l'entier calculant la 'distance'
	// minimale
	public int min($Personnage p, int i, Coordonnees c2) {
		int aux = 0, aux2 = 0;
		if (i < 0) {
			aux = p.getPosition().getX() - c2.getX();
			if (aux < 0)
				i = -aux;
			else
				i = aux;
			aux = p.getPosition().getY() - c2.getY();
			if (aux < 0)
				i += -aux;
			else
				i += aux;
		} else {
			aux = p.getPosition().getX() - c2.getX();
			if (aux < 0)
				aux = -aux;
			aux2 = p.getPosition().getY() - c2.getY();
			if (aux2 < 0)
				aux2 = -aux2;
			aux += aux2;
		}
		System.out.println("aux="+aux);
		if (aux < i && aux != 0)
			i = aux;
		return i;
	}

	//Retourne le personnage (cote gentil) le plus proche
	public Gentil prox($Personnage p, List<$Personnage> l) {
		int s = l.size();
		int i, mini = -1, mini2;
		Gentil e = null;
		for (i = 0; i < s; i++) {
			if (l.get(i) instanceof Gentil) {
				if (e == null) {
					e = (Gentil) l.get(i);
					mini = min(p, mini, l.get(i).getPosition());
				} else {
					mini2 = min(p, mini, l.get(i).getPosition());
					if (mini2 < mini) {
						mini = mini2;
						e = (Gentil) l.get(i);
					}
				}
			}
		}
		return e;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		Coordonnees cnord, csud, cest, couest;
		//cnord =  new Coordonnees (p.getPosition());
		//csud =  new Coordonnees (p.getPosition());
		//cest =  new Coordonnees (p.getPosition());
		//couest = new Coordonnees (p.getPosition());
		//cnord.setY(cnord.getY() - 1);
		//csud.setY(csud.getX() + 1);
		//cest.setX(cest.getX() + 1);
		//couest.setX(couest.getX() - 1);
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
						}
					}
					else if (l.get(4) == 8) {
						if (p.getPosition().getY()<g.tailleY()-1 && g.Pos(csud).getValeur() != 3 && g.Pos(csud).getValeur() != 5) {	//passage au sud
							p.setPosition(csud); // seule case disponible --> au
							// sud
							effect = true;
						}
					}
					else {
						rnd = Grille.random(1, 3); // ennemi au nord et a l'est
						// = choix de la fuite
						// random
						if (p.getPosition().getX()>0 && rnd == 1 && g.Pos(couest).getValeur() != 3 && g.Pos(couest).getValeur() != 5) {	//passage ouest
							p.setPosition(couest);
							effect = true;
						}
						else if (p.getPosition().getY()<g.tailleY()-1 && g.Pos(csud).getValeur() != 3 && g.Pos(csud).getValeur() != 5) {	//passage sud
							p.setPosition(csud);
							effect = true;
						}
					}
				}
				else if (l.get(3) == 6) {
					if (l.get(4) == 8) {
						if (p.getPosition().getX()<g.tailleX()-1 && g.Pos(cest).getValeur() != 3 && g.Pos(cest).getValeur() != 5) {	//passage est
							p.setPosition(cest);
							effect = true;
						}
					}
					else {
						rnd = Grille.random(1, 3); // ennemi au nord et au sud =
						// choix de la fuite random
						if (p.getPosition().getX()<g.tailleX()-1 && rnd == 1 && g.Pos(cest).getValeur() != 3 && g.Pos(cest).getValeur() != 5) {	//passage est
							p.setPosition(cest);
							effect = true;
						}
						else if (p.getPosition().getX()>0 && g.Pos(couest).getValeur() != 3 && g.Pos(couest).getValeur() != 5) {	//passage ouest
							p.setPosition(couest);
							effect = true;
						}
					}
				}
				else {
					rnd = Grille.random(1, 4); // ennemi present au nord
					if (p.getPosition().getX()<g.tailleX()-1 && rnd == 1 && g.Pos(cest).getValeur() != 3 && g.Pos(cest).getValeur() != 5) {	//passage est
						p.setPosition(cest);
						effect = true;
					}
					else if (p.getPosition().getY()<g.tailleY()-1 && rnd == 2 && g.Pos(csud).getValeur() != 3 && g.Pos(csud).getValeur() != 5) {	//passage sud
						p.setPosition(csud);
						effect = true;
					}
					else if ( p.getPosition().getX()>0 && g.Pos(couest).getValeur() != 3 && g.Pos(couest).getValeur() != 5) {	//passage ouest
						p.setPosition(couest);
						effect = true;
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
						}
					}
					else {
						rnd = Grille.random(1, 3); // chois aleatoire entre nord
						// et ouest
						if (p.getPosition().getY()>0 && rnd == 1 && g.Pos(cnord).getValeur() != 3 && g.Pos(cnord).getValeur() != 5) {	//passage nord
							p.setPosition(cnord);
							effect = true;
						}
						else if (p.getPosition().getX()>0 && g.Pos(couest).getValeur() != 3 && g.Pos(couest).getValeur() != 5) {	//passage ouest
							p.setPosition(couest);
							effect = true;
						}
					}
				}
				else if (l.get(4) == 8) {
					rnd = Grille.random(1, 4);
					if (p.getPosition().getY()>0 && rnd == 1 && g.Pos(cnord).getValeur() != 3 && g.Pos(cnord).getValeur() != 5) {	//passage nord
						p.setPosition(cnord);
						effect = true;
					}
					else if (p.getPosition().getY()<g.tailleY()-1 && rnd == 2 && g.Pos(csud).getValeur() != 3 && g.Pos(csud).getValeur() != 5) {	//passage sud
						p.setPosition(csud);
						effect = true;
					}
					else if (p.getPosition().getX()>0 && g.Pos(couest).getValeur() != 3 && g.Pos(couest).getValeur() != 5) {	//passage ouest
						p.setPosition(couest);
						effect = true;
					}
				}
			}
			else if (l.get(3) == 6) {
				if (l.get(4) == 8) {
					rnd = Grille.random(1, 3);
					if (p.getPosition().getY()>0 && rnd == 1 && g.Pos(cnord).getValeur() != 3 && g.Pos(cnord).getValeur() != 5) {	//passage nord
						p.setPosition(cnord);
						effect = true;
					}
					else if (p.getPosition().getX()<g.tailleX()-1 && g.Pos(cest).getValeur() != 3 && g.Pos(cest).getValeur() != 5) {	//passage est
						p.setPosition(cest);
						effect = true;
					}
				}
				else {
					rnd = Grille.random(1, 4);
					if (p.getPosition().getY()>0 && rnd == 1 && g.Pos(cnord).getValeur() != 3 && g.Pos(cnord).getValeur() != 5) {	//passage nord
						p.setPosition(cnord);
						effect = true;
					}
					else if (p.getPosition().getY()<g.tailleY()-1 && rnd == 2 && g.Pos(cest).getValeur() != 3 && g.Pos(cest).getValeur() != 5) {	//passage est
						p.setPosition(cest);
						effect = true;
					}
					else if (p.getPosition().getX()>0 && g.Pos(couest).getValeur() != 3 && g.Pos(couest).getValeur() != 5) {	//passage ouest
						p.setPosition(couest);
						effect = true;
					}
				}
			}
			else if (l.get(4) == 8) {
				rnd = Grille.random(1, 4);
				if (p.getPosition().getY()>0 && rnd == 1 && g.Pos(cnord).getValeur() != 3 && g.Pos(cnord).getValeur() != 5) {	//passage nord
					p.setPosition(cnord);
					effect = true;
				}
				else if (p.getPosition().getX()<g.tailleX()-1 && rnd == 2 && g.Pos(cest).getValeur() != 3 && g.Pos(cest).getValeur() != 5) {	//passage est
					p.setPosition(cest);
					effect = true;
				}
				else if (p.getPosition().getY()<g.tailleY()-1 && g.Pos(csud).getValeur() != 3  && g.Pos(csud).getValeur() != 5) {	//passage sud
					p.setPosition(csud);
					effect = true;
				}
			}
			else { // aucun ennemi a proximite ne fait rien
				
			}
		}
		else if (p instanceof Mechant) {
			Coordonnees ppp=prox(p,lp).getPosition();
			Pfind path = new Pfind(p.getPosition(),ppp);
			Coordonnees c=p.getPosition().CalculCase(path.getDirection());
			if(!(ppp.getX()==c.getX() && ppp.getY()==c.getY())){
				//if(g.get(c.getX(), c.getY()).Passable(Jeu.univers.getObjets())) 
					p.setPosition(c);
					effect = true;
			}
			else{
				//Ne fais rien
			}
		}
		else {
			effect = false;
		}
	}

	/*
	 * public String toString(){ if(effect == true && inno == true)
	 * return("s'enfuit."); else if(effect == true){ return(
	 * "a flaire un ennemi a proximite."); } else return (
	 * "n'a pas reussi a se deplacer."); }
	 */
}
