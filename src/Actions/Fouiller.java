package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Gentil;
import Otomate.Grille;

public class Fouiller extends $Action{

	private boolean effect = false;
	private boolean anti = false, pompe = false;
	int valeur = 15;
	
	public void todo($Personnage p, List<Integer> l){
		if(p instanceof Gentil){
			int rnd;
			if(l.get(0) == 5){		//on se trouve dans un immeuble
				rnd = Grille.random(0, 101);
				if(rnd<30)			//trouve des bandages
					((Gentil) p).setRemede(2);
				else if(rnd<40){		//trouve un fusil à pompe
					((Gentil) p).setArme(25);
					pompe = true;
				}
				else if(rnd<70)		//trouve une boite de conserve
					p.setInventaire(7);
				else				//trouve une bouteille
					p.setInventaire(8);
			}
			else if(l.get(0) == 10){
				rnd = Grille.random(0, 101);
				if(rnd<50){			//Recupere un antidote
					((Gentil) p).setRemede(1);
					anti = true;
				}
				else				//Recupere des bandages
					p.setInventaire(2);
			}
			else if(l.get(0) == 11){
				rnd = Grille.random(0, 101);
				if(rnd<25){			//S'equipe d'un fusil a pompe
					((Gentil) p).setArme(25);
					pompe = true;
				}
				else if(rnd<40)		//Trouve des bandages
					((Gentil) p).setRemede(2);
				else				//Ramasse une bouteille
					p.setInventaire(8);
			}
			effect = true;
		}
	}
	
	public String toString(){
		if(effect == true && pompe == true)
			return(" a trouve un fusil a pompe lors de sa recherche :o.");
		else if((effect == true)&&(anti == true)){
			return("a trouve un antidote.");
		}
		else if(effect == true)
			return("a trouve quelque chose.");
		else
			return ("Il n'y a rien a fouiller.");
	}
}
