package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Gentil;
import Otomate.Grille;

public class Soigner extends $Action{
	
	public Soigner(String succes, String echec) {
		super(succes,echec);
		// TODO Auto-generated constructor stub
	}

	private boolean effect = false;

	public Soigner(){
		valeur = 12;
	}
	
	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g){
		if(p instanceof Gentil){
			if(l.get(5) == 17){
				if (((Gentil) p).getRemede() == 1){
					if(((Gentil) p).getInfecte() == true)
						((Gentil) p).setInfecte (false);
					((Gentil) p).setRemede (0);
					effect=true;
				}
				else if(((Gentil) p).getRemede() == 2){
					if(((Gentil) p).getSaignement() == true)
						((Gentil) p).setSaignement(false);
					((Gentil) p).setRemede (0);
					effect=true;
				}
			}
		}
	}
	
	public String toString(){
		if(effect == true)
			return ("a soigne son infection/saignement.");
		else
			return ("n'a pas pu se soigner.");
	}
}
