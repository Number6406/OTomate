package Otomate;

public class Gentil extends $Personnage{
    
	public Gentil(String file){
		super(file);
    }
	
	public String toString(){
    	return "// Gentil //\n" + super.toString();
    }
}