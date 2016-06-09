package Otomate;

import java.util.List;

public class Soigner extends $Action{
	
	private boolean effect = false;
	int valeur = 12;
	
	public void todo(List<Integer> l, $Personnage p){
		if(p instanceof Gentil){
			if (((Gentil) p).remede == 1){
				if(((Gentil) p).infecte == true)
					((Gentil) p).infecte = false;
				((Gentil) p).remede = 0;
				effect=true;
			}
			else if(((Gentil) p).remede == 2){
				if(((Gentil) p).saignement == true)
					((Gentil) p).saignement = false;
				((Gentil) p).remede = 0;
				effect=true;
			}
		}
	}
	
	public String toString(){
		if(effect == true)
			return ("a soigne son infection/saignement");
		else
			return ("et ben non dommage");
	}
}
