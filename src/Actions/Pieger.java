package Actions;


import java.util.List;
import Otomate.$Personnage;
import Otomate.Coordonnees;
import Otomate.Joueur;
import Otomate.Gentil;
import Otomate.Mechant;
import Otomate.Grille;
public class Pieger {
	
	private boolean effect = false;
	int valeur = 10;
	
	public void todo(List<Integer> l, $Personnage p, Grille g){
		if((g.Pos(p.getPosition()).piegee==false)&&(l.get(0) == 1 || l.get(0) == 5 || l.get(0) == 11)){
			g.Pos(p.getPosition()).piegee = true;
			effect = true;
		}
	}
	
	public String toString(){
		if(effect == true)
			return ("Attention au piege ! :p");
		else
			return ("Cool ma vie, oh salut les zombies, bien ou bien? sisi la famille bien? :°");
	}

}
