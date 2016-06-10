package Otomate;

public class Mechant extends $Personnage{
    
	private int vie; 
	
    public Mechant(String file){
        super(file);
        vie = viemax;
    }
    public int getVie(){
        return viemax;
    }
    
    public void setVie(int Vie){
        this.vie=Vie;
    }
    public String toString(){
    	return "// Méchant //\n" + super.toString();
    }
}