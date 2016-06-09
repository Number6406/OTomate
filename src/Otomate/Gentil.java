package Otomate;

public class Gentil extends $Personnage{
	
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