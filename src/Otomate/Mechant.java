package Otomate;

public class Mechant extends $Personnage{
    
    public Mechant(String file){
        super(file);
    }
    
    public String toString(){
    	return "// Méchant //\n" + super.toString();
    }
}