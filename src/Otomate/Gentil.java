package Otomate;

public class Gentil extends $Personnage{
	 /*arme = 25.pompe
	  * 	  15.couteau
	  * correspond au degats supp
	  */
	
	/*type drogue = 0.nickel
	 * 			1.bonus hp max
	 * 			2.malus hp.max
	 * 			3. + hp/tour
	 * 			4. - hp/tour
	 * 			5.adrenaline
	 * 			6. passe
	 * 			7. + vie
	 * 			8. - vie
	 */
	
	/*remede = 1.antidote
	 * 			2.bandage
	 */
	
	int vie;		//vie actuelle NE PAS DEPASSER viemax
	int arme, remede, drogue;
	int paralysie, piege;			//nb de tour a jouer, att avant prochain piege
	int efdrogue, efsaignement; //calcul le nombre de tours restant et effet a effectuer
	boolean infecte, saignement;
    
	public Gentil(String file){
		super(file);
		arme = remede = drogue  = 0;
		infecte = saignement = false;
		vie = viemax;
		paralysie = 1;
		piege = 10;
    }
	
	public String toString(){
    	return "// Gentil //\n" + super.toString();
    }
}