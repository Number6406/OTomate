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
	
	int vie;		//vie actuelle NE PAS DEPASSER viemax
	int arme, remede;
	int paralysie, piege;			//nb de tour a jouer, att avant prochain piege
	int efdrogue, efsaignement; //calcul le nombre de tours restant et effet a effectuer
	boolean infecte, saignement, drogue;
    
	public Gentil(String file){
		super(file);
		arme = remede  = 0;
		infecte = saignement = drogue = false;
		vie = viemax;
		paralysie = 1;
		piege = 10;
    }
	
	public String toString(){
    	return "// Gentil //\n" + super.toString();
    }
}