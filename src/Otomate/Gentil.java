package Otomate;

public class Gentil extends $Personnage{
	 /*arme = 25.pompe
	  * 	  15.couteau
	  * correspond au degats supp
	  */
	
	int arme, remede;
	boolean infecte, saignement, drogue;
    
	public Gentil(String file){
		super(file);
		arme = remede = 0;
		infecte = saignement = drogue = false;
    }
	
	public String toString(){
    	return "// Gentil //\n" + super.toString();
    }
}