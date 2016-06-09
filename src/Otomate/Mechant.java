package Otomate;

public class Mechant extends $Personnage{
    
	protected int vie; 
	
    public Mechant(String file){
        super(file);
        vie = viemax;
    }
    
    public String toString(){
    	return "// Méchant //\n" + super.toString();
    }
}