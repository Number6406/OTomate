package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Coordonnees;
import Otomate.Gentil;
import Otomate.Grille;
import Otomate.Mechant;

public class Fuir extends $Action{

	public Fuir(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private boolean effect = false;
	private boolean inno = false;
	int valeur = 13;
	
//Retourne le parcours le plus court (l'entier calculant la 'distance' minimale
	public int min($Personnage p, int i, Coordonnees c2){
		int aux=0, aux2 = 0;
		if(i<0){
			aux = p.getPosition().getX() - c2.getX();
			if(aux<0)
				i=-aux;
			else
				i=aux;
			aux = p.getPosition().getY() - c2.getY();
			if(aux<0)
				i += -aux;
			else
				i += aux;
		}
		else{
			aux = p.getPosition().getX() - c2.getX();
			if(aux<0)
				aux = -aux;
			aux2 = p.getPosition().getY() - c2.getY();
			if(aux2<0)
				aux2 = -aux2;
			aux += aux2;
		}
		if(aux<i)
			i=aux;
		return i;
	}
	
	public Gentil prox($Personnage p, List<$Personnage> l){
		int s = l.size();
		int i, mini = -1, mini2;
		Gentil e = null;
		for(i=0; i<s; i++){
			if(l.get(i) instanceof Gentil){
				if(e==null){
					e = (Gentil) l.get(i);
					mini = min(p,mini,l.get(i).getPosition());
				}
				else{
					mini2 = min(p,mini,l.get(i).getPosition());
					if(mini2<mini){
						mini = mini2;
						e = (Gentil) l.get(i);
					}
				}
			}
		}
		return e;
	}
	
	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g){
		if(p instanceof Gentil){
			Coordonnees cnord, csud, cest, couest;
			int rnd;
			cnord = csud = cest = couest = p.getPosition();
			cnord.setY(cnord.getY()-1);
			csud.setY(csud.getX()+1);
			cest.setX(cest.getX()+1);
			couest.setY(couest.getX()-1);
			if(l.get(1) == 5){			//ennemi au nord
				if(l.get(2) == 7){		//ennemi a l'est
					if(l.get(3) == 6){	//ennemi au sud
						if(l.get(4) == 8){}	//ennemi a l'ouest
						if(Grille.Pos(cnord).getValeur() == 0){
							p.setPosition(couest);	//seule case disponible --> a l'ouest
							effect = true;
						}
					}
					else if(l.get(4) == 8){
						if(Grille.Pos(csud).getValeur() == 0){
							p.setPosition(csud);		//seule case disponible --> au sud
							effect = true;
						}
					}
					else{
						rnd = Grille.random(1, 3);	//ennemi au nord et a l'est = choix de la fuite random
						if(rnd == 1 && Grille.Pos(couest).getValeur() == 0){
							p.setPosition(couest);
							effect = true;
						}
						else if(Grille.Pos(csud).getValeur() == 0){
							p.setPosition(csud);
							effect = true;
						}
					}
				}
				else if(l.get(3) == 6){
					if(l.get(4) == 8){
						if(Grille.Pos(cest).getValeur() == 0){
							p.setPosition(cest);
							effect = true;
						}
					}
					else{
						rnd = Grille.random(1, 3);	//ennemi au nord et au sud = choix de la fuite random
						if(rnd == 1 && Grille.Pos(cest).getValeur() == 0){
							p.setPosition(cest);
							effect = true;
						}
						else if(Grille.Pos(couest).getValeur() == 0){
							p.setPosition(couest);
							effect = true;
						}
					}
				}
				else{
					rnd = Grille.random(1, 4);	//ennemi present au nord
					if(rnd == 1 && Grille.Pos(cest).getValeur() == 0){
						p.setPosition(cest);
						effect = true;
					}
					else if(rnd == 2 && Grille.Pos(csud).getValeur() == 0){
						p.setPosition(csud);
						effect = true;
					}
					else if(Grille.Pos(couest).getValeur() == 0){
						p.setPosition(couest);
						effect = true;
					}
				}
			}
			else if(l.get(2) == 7){
				if(l.get(3) == 6){
					if(l.get(4) == 8){		//fuite vers le nord seule case non occupée
						if(Grille.Pos(cnord).getValeur() == 0){
							p.setPosition(cnord);
							effect = true;
						}
					}
					else{
						rnd = Grille.random(1, 3);	//chois aleatoire entre nord et ouest
						if(rnd == 1 && Grille.Pos(cnord).getValeur() == 0){
							p.setPosition(cnord);
							effect = true;
						}
						else if(Grille.Pos(couest).getValeur() == 0){
							p.setPosition(couest);
							effect = true;
						}
					}
				}
				else if(l.get(4) == 8){
					rnd = Grille.random(1, 4);
					if(rnd == 1 && Grille.Pos(cnord).getValeur() == 0){
						p.setPosition(cnord);
						effect = true;
					}
					else if(rnd == 2 && Grille.Pos(csud).getValeur() == 0){
						p.setPosition(csud);
						effect = true;
					}
					else if(Grille.Pos(couest).getValeur() == 0){
						p.setPosition(couest);
						effect = true;
					}
				}
			}
			else if(l.get(3) == 6){
				if(l.get(4) == 8){
					rnd = Grille.random(1, 3);
					if(rnd == 1 && Grille.Pos(cnord).getValeur() == 0){
						p.setPosition(cnord);
						effect = true;
					}
					else if(Grille.Pos(cest).getValeur() == 0){
						p.setPosition(cest);
						effect = true;
					}
				}
				else{
					rnd = Grille.random(1, 4);
					if(rnd == 1 && Grille.Pos(cnord).getValeur() == 0){
						p.setPosition(cnord);
						effect = true;
					}
					else if(rnd == 2 && Grille.Pos(cest).getValeur() == 0){
						p.setPosition(cest);
						effect = true;
					}
					else if(Grille.Pos(couest).getValeur() == 0){
						p.setPosition(couest);
						effect = true;
					}
				}
			}
			else if(l.get(4) == 8){
				rnd = Grille.random(1, 4);
				if(rnd == 1 && Grille.Pos(cnord).getValeur() == 0){
					p.setPosition(cnord);
					effect = true;
				}
				else if(rnd == 2 && Grille.Pos(cest).getValeur() == 0){
					p.setPosition(cest);
					effect = true;
				}
				else if(Grille.Pos(csud).getValeur() == 0){
					p.setPosition(csud);
					effect = true;
				}
			}
			else{							//aucun ennemi a proximite on se deplace aleatoirement
				rnd = Grille.random(1, 5);
				if(rnd == 1 && Grille.Pos(cnord).getValeur() == 0){
					p.setPosition(cnord);
					effect = true;
				}
				else if(rnd == 2 && Grille.Pos(cest).getValeur() == 0){
					p.setPosition(cest);
					effect = true;
				}
				else if(rnd == 3 && Grille.Pos(csud).getValeur() == 0){
					p.setPosition(csud);
					effect = true;
				}
				else if(Grille.Pos(couest).getValeur() == 0){
					p.setPosition(couest);
					effect = true;
				}
			}
			inno = true;
		}
		else if(p instanceof Mechant){
			Gentil e;
			int x, y, x2, y2;
			e = prox(p, lp);
			x = p.getPosition().getX() - e.getPosition().getX();
			y = p.getPosition().getY() - e.getPosition().getY();
			if(x<0)
				x2 = -x;
			else
				x2 = x;
			if(y<0)
				y2 = -y;
			else
				y2 = y;
			if((x2<y2 && x2 != 0) || y == 0){
				if(x<0)
					p.getPosition().setX(p.getPosition().getX()+1);
				else
					p.getPosition().setX(p.getPosition().getX()-1);
			}
			else{
				if(y<0)
					p.getPosition().setY(p.getPosition().getY()+1);
				else
					p.getPosition().setY(p.getPosition().getY()-1);
			}
			effect = true;
		}
	}
	
	public String toString(){
		if(effect == true && inno == true)
			return("s'enfuit.");
		else if(effect == true){
			return("a flaire un ennemi a proximite.");
		}
		else
			return ("n'a pas reussi a se deplacer.");
	}
}
