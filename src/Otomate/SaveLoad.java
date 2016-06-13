package Otomate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/*Grille
 * Couleur:id:
 * (Personnage)
 * 
 * 
 * 
 * 
 * 
 * 
 */
public final class SaveLoad {

	private Jeu jeu;
	private String name;
	SaveLoad(final Jeu jeu,String name){
		this.jeu=jeu;
		this.name=name;
	}

	@SuppressWarnings("null")
	public void save() throws IOException{
		File f=new File(name);
		FileOutputStream fin =new FileOutputStream(f);
		int currentChar=0;
		
		int i,j;
		fin.write(((Integer)jeu.plateau.tailleX)); //tailleX
		fin.write(new Character('\n'));
		fin.write(((Integer)jeu.plateau.tailleY)); //tailleY
		fin.write((new Character('\n')));
		for(j=0;j<jeu.plateau.tailleY;j++){
			for(i=0;i<jeu.plateau.tailleX;i++){
				fin.write(((Integer)jeu.plateau.get(i, j).element));
				fin.write(new Character(':'));
				fin.write((Integer)(((jeu.plateau.get(i, j).piegee))?1:0));
				fin.write(' ');
			}
		fin.write(new Character('\n'));
		}
		
	$Personnage pe;
	for(i=0;i<jeu.joueurs.size();i++){
		fin.write(((Integer)jeu.joueurs.get(i).getCouleur().getRed()));
		fin.write(new Character(' '));
		
		fin.write(((Integer)jeu.joueurs.get(i).getCouleur().getGreen()));
		fin.write(new Character(' '));
		
		fin.write(((Integer)jeu.joueurs.get(i).getCouleur().getBlue()));
		fin.write(new Character(' '));
		
		fin.write(new Character(':'));
		fin.write(jeu.joueurs.get(i).getName().getBytes());
		fin.write(new Character('\0'));
		
		for(j=0;j<jeu.joueurs.get(i).getSizePersonnages();j++){
			pe=jeu.joueurs.get(i).getPersonnagesI(j);
			fin.write(((Integer)pe.getPosition().getX()));
			fin.write(new Character(' '));
			fin.write(((Integer)pe.getPosition().getY()));
			fin.write(new Character(' '));
			if(pe instanceof Gentil){
			fin.write(((Integer)(((Gentil) pe).getArme())));
			fin.write(new Character(' '));
			fin.write(((Integer)(((Gentil) pe).getDrogue())));
			fin.write(new Character(' '));
			fin.write(((Integer)(((Gentil) pe).getInventaire())));
			fin.write(new Character(' '));
			fin.write(((Integer)(((Gentil) pe).getRemede())));
			fin.write(new Character(' '));
			}
			else{
				fin.write(((Integer)(((Mechant) pe).getInventaire())));
			}
			fin.write(new Character(';'));
			fin.write(jeu.plateau.getCoinsAutomates().get(currentChar).getX());
			fin.write(new Character(':'));
			fin.write(jeu.plateau.getCoinsAutomates().get(currentChar).getY());
			fin.write(new Character(' '));
			fin.write(pe.getAutomate().nbconditions());
			fin.write(new Character(':'));
			fin.write(pe.getAutomate().nbetats());
			currentChar++;
			fin.write('\n');
			
		}
	}
	
	}
	
}
