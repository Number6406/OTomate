package Otomate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class SaveLoad {

	private Jeu jeu;
	private String name;

	SaveLoad(final Jeu jeu, String name) {
		this.jeu = jeu;
		this.name = name;
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
	
	public String lire(FileInputStream fout, char fin) throws IOException {
		String buf="";
		byte[] b = new byte[1];
		fout.read(b);
		while (b[0] != fin) {
			buf+=b[0];
			fout.read(b);
		}
		return buf;
	}

	public void load() throws IOException {
		File f = new File(name);
		FileInputStream fout = new FileInputStream(f);
		jeu.plateau.tailleX = Integer.getInteger(lire(fout, ' '));
		buf="";
		fout.read(b);
		while (b[0] != '\n') {
			buf+=b[0];
			fout.read(b);
		}
		jeu.plateau.tailleY = Integer.getInteger(buf);
		buf="";
		for(int j=0;j<jeu.plateau.tailleX;j++) {
			for(int i=0;i<jeu.plateau.tailleY;i++) {
				fout.read(b);
				while(b[0] != ':') {
					buf+=b[0];
					fout.read(b);
				}
				jeu.plateau.set(Integer.getInteger(buf),i,j);
				buf="";
				fout.read(b);
				buf+=b[0];
				fout.read(b);
				jeu.plateau.setP(((Integer.getInteger(buf)==1)?(true):(false)),i,j);
				buf="";
			}
		}
		
	}

}
