package Otomate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class SaveLoad {

	private Jeu jeu;
	private String name;
	SaveLoad(final Jeu jeu,String name){
		this.jeu=jeu;
		this.name=name;
	}

	public void save() throws IOException{
		File f=new File(name);
		FileOutputStream fin =null;
		byte[] buf = new byte[1];
		
		int i,j;
		buf=((Integer)jeu.plateau.tailleX).toString().getBytes();
		fin.write(buf);
		buf=((Integer)jeu.plateau.tailleY).toString().getBytes();
		fin.write(buf);
		buf=(new Character('\n').toString().getBytes());
		for(j=0;j<jeu.plateau.tailleY;j++){
			for(i=0;i<jeu.plateau.tailleX;i++){
				buf=((Integer)jeu.plateau.get(i, j).element).toString().getBytes();
				fin.write(buf);
			}
		buf=new Character('\n').toString().getBytes();
		fin.write(buf);
		}
	$Personnage pe;
	for(i=0;i<jeu.joueurs.size();i++){
		buf=((Integer)jeu.joueurs.get(i).getCouleur().getRed()).toString().getBytes();
		fin.write(buf);
		buf=((Integer)jeu.joueurs.get(i).getCouleur().getGreen()).toString().getBytes();
		fin.write(buf);
		buf=((Integer)jeu.joueurs.get(i).getCouleur().getBlue()).toString().getBytes();
		fin.write(buf);
		buf=new Character(':').toString().getBytes();
		fin.write(buf);
		for(j=0;j<jeu.joueurs.get(i).getSizePersonnages();j++){
			pe=jeu.joueurs.get(i).getPersonnagesI(j);
			buf=((Integer)pe.getPosition().getX()).toString().getBytes();
			fin.write(buf);
			buf
		}
	}
	
	}
	
}
