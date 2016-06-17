package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Gentil;
import Otomate.Grille;
import Otomate.Jeu;
import Otomate.Mechant;
import Otomate.Objet;

public class Ramasser extends $Action {
	
	String effet;
	int ramasse;
	
	public Ramasser(String succes, String echec) {
		super(succes, echec);
		// TODO Auto-generated constructor stub
	}

	private boolean drogue = false;

	public Ramasser() {
		valeur = 9;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		effet = " rien ?";
		ramasse = g.Pos(p.getPosition()).getValeur();
		effect = false;
		drogue = false;
		if (l.get(0) != 0) { // 0 = case vide
			System.err.println("id : "+l.get(0));
			int aux;
			if (l.get(0) == 9) { // comestible
				aux = p.getInventaire();
				p.setInventaire(g.Pos(p.getPosition()).getValeur());
				g.Pos(p.getPosition()).setValeur(aux);
			}
			if (p instanceof Gentil) {
				if (l.get(0) == 10) { // arme
					Objet arme = ((Gentil) p).getArme();
					((Gentil) p).setArme(g.getUnivers().getObjets().get(g.Pos(p.getPosition()).getValeur()));
					if (arme!=null){ 
			            g.Pos(p.getPosition()).setValeur(arme.getId()); 
			          } 
			          else{ 
			            g.Pos(p.getPosition()).setValeur(0); 
			          } 
				}
				else if (l.get(0) == 16) { // seringue
					
					if (((Gentil) p).getDrogue() == 0) {	//dans le cas ou le personnage est sain
						aux = Grille.random(0, 101);
						System.err.println("LE PERSONNAGE RAMASSE UNE DROGUE");
						if (aux < 10) {	//bonus hp max
							p.setViemax(p.getViemax() + 10);
							p.setVie(p.getVie() + 10);
							((Gentil) p).setDrogue(1);
							
							effet = "gagne 10 PV max";
						}
						else if (aux < 20) {	//malus hp max
							p.setViemax(p.getViemax() - 10);
							System.err.println("viemax : " + p.getViemax());
							p.setVie(p.getVie() - 10);
							if(p.getVie()<p.getViemax())
								p.setVie(p.getViemax());	//barre de hp = hp max si hp > hp max
							((Gentil) p).setDrogue(2);

							effet = "perds 10 PV max";
						}
						else if (aux < 30) {	//bonus hp/tour
							p.setVie(p.getVie() + 5);
							if (p.getVie() > p.getViemax())
								p.setVie(p.getViemax());
							((Gentil) p).setEfdrogue(3);
							((Gentil) p).setDrogue(3);

							effet = "gagne 5 PV pendant 3 tours";
						}
						else if (aux < 40) {	//malus hp/tour
							p.setVie(p.getVie() - 5);
							((Gentil) p).setEfdrogue(3);
							((Gentil) p).setDrogue(4);
							
							effet = "perds 5 PV pendant 3 tours";
						}
						else if (aux < 60) {
							((Gentil) p).setEfdrogue(2);
							if(aux<50)	{//adrenaline = joue 2 tours / row sur 2 row
								((Gentil) p).setDrogue(5);
								effet = "joue 2 actions";
							}
							else { //passe 2 tour
								((Gentil) p).setDrogue(6);
								effet = "passe un tour";
							}
						}
						else if (aux < 80) {	//bonus vie
							((Gentil) p).setVie(((Gentil) p).getVie() + 10);
							if (((Gentil) p).getVie() > ((Gentil) p).getViemax())
								((Gentil) p).setVie(((Gentil) p).getViemax());
							((Gentil) p).setDrogue(7);
							effet = "gagne 10 PV";
						}
						else {	//malus vie
							((Gentil) p).setVie(((Gentil) p).getVie() - 10);
							((Gentil) p).setDrogue(8);
							effet = "perds 10 PV";
						}
						drogue = true;
			        	System.out.println("LE PERSONNAGE EST DROGUE AVEC LA DROGUE NUMERO : "+((Gentil) p).getDrogue());
						g.Pos(p.getPosition()).setValeur(0);
					}
				}
			} // Fin gentil
			else if(p instanceof Mechant){
				if(l.get(0) == 10 || l.get(0) == 16){
					aux = p.getInventaire();
					p.setInventaire(g.Pos(p.getPosition()).getValeur());
					g.Pos(p.getPosition()).setValeur(aux);					
				}
			}
			effect = true;
        	if(p.getInactivite()<20)
        		p.setInactivite(20);
		}
		else { // Echec de l'action
			effect = false;
			p.setInactivite(p.getInactivite()-1);
		}	 
	}

	public String toString() {
		if ((effect == true) && (drogue == false))
			return ("a ramasse " + Jeu.univers.getObjets().get(ramasse).getName());
		else if ((effect == true) && (drogue == true)) {
			return ("a ramasse " + Jeu.univers.getObjets().get(ramasse).getName() + " et " + effet);
		} else
			return ("essaye de ramasser quelque chose. Mais il n'y a rien.");
	}
}
