package Otomate;

public class Gentil extends $Personnage{
	
	int armure,arme;
    
	public Gentil(String file){
		super(file);
		armure = arme = 0;
    }
	
	public String toString(){
    	return "// Gentil //\n" + super.toString();
    }
}