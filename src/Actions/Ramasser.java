package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Gentil;
import Otomate.Grille;

public class Ramasser extends $Action{

	public Ramasser(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private boolean effect = false;
	private boolean drogue = false;
	private boolean pompe = false;
	int valeur = 9;
    
	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g){
		if(l.get(0) != 0){		// 0 = case vide
			int aux;
			if(l.get(0) == 9){		//comestible
				aux = p.getInventaire();
				p.setInventaire(Grille.Pos(p.getPosition()).getValeur());
				Grille.Pos(p.getPosition()).setValeur(aux);
			}
			if(p instanceof Gentil){
				if(l.get(0) == 10){		//couteau
					if(((Gentil) p).getArme() != 25){
						aux = ((Gentil) p).getArme();
						((Gentil) p).setArme(Grille.Pos(p.getPosition()).getValeur());
						Grille.Pos(p.getPosition()).setValeur(aux);
					}
				}
				else if(l.get(0) == 16){		//seringue
					if(((Gentil) p).getDrogue() != 0){
						aux = Grille.random(0, 101);
						if(aux<10){
							p.setViemax(p.getViemax() + 10);
						}
						else if(aux<20){
							p.setViemax(p.getViemax() - 10);
						}
						else if(aux<30){
							((Gentil) p).setVie(((Gentil) p).getVie()+5);
							if(((Gentil) p).getVie() > ((Gentil) p).getViemax())
								((Gentil) p).setVie(((Gentil) p).getViemax());
							((Gentil) p).setEfdrogue (3);
						}
						else if(aux < 40){
							((Gentil) p).setVie( ((Gentil) p).getVie()-5);
							((Gentil) p).setEfdrogue (3);
						}
						else if(aux < 60){
							((Gentil) p).setEfdrogue (2);
						}
						else if(aux < 80){
							((Gentil) p).setVie( ((Gentil) p).getVie()-10);
							if(((Gentil) p).getVie() > ((Gentil) p).getViemax())
								((Gentil) p).setVie(((Gentil) p).getViemax());
						}
						else{
							((Gentil) p).setVie( ((Gentil) p).getVie()-10);
						}
						drogue=true;
					}
				}
			}
			effect = true;
			if(((Gentil) p).getArme() == 25){
				effect = false;
				pompe = true;
			}
		}
	}
	
	public String toString(){
		if((effect == true)&&(drogue == false))
			return("a ramasse quelque chose.");
		else if((effect == true)&&(drogue == false)){
			return("a ramasse de la drogue et s'est pique, petit filou.");
		}
		else if(pompe == true)
			return("tente de ramasser un couteau mais préfère garder son fusil a pompe.");
		else
			return ("mais il n'y a rien a recolter");
	}
}
