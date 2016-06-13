package Otomate;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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

	SaveLoad(final Jeu jeu, String name) {
		this.jeu = jeu;
		this.name = name;
	}

	@SuppressWarnings({ "static-access", "resource" })
	public void save() throws IOException {
		File f = new File(name);
		FileOutputStream fin = new FileOutputStream(f);
		int currentChar = 0;

		int i, j;
		fin.write(((Integer) jeu.plateau.tailleX)); // tailleX
		fin.write(new Character('\n'));
		fin.write(((Integer) jeu.plateau.tailleY)); // tailleY
		fin.write((new Character('\n')));
		for (j = 0; j < jeu.plateau.tailleY; j++) {
			for (i = 0; i < jeu.plateau.tailleX; i++) {
				fin.write(((Integer) jeu.plateau.get(i, j).element));
				fin.write(new Character(':'));
				fin.write((Integer) (((jeu.plateau.get(i, j).piegee)) ? 1 : 0));
				fin.write(' ');
			}
			fin.write(new Character('\n'));
		}
		fin.write(jeu.joueurs.size());
		fin.write('\n');
		$Personnage pe;
		for (i = 0; i < jeu.joueurs.size(); i++) {
			fin.write(((Integer) jeu.joueurs.get(i).getCouleur().getRed()));
			fin.write(new Character(' '));

			fin.write(((Integer) jeu.joueurs.get(i).getCouleur().getGreen()));
			fin.write(new Character(' '));

			fin.write(((Integer) jeu.joueurs.get(i).getCouleur().getBlue()));
			fin.write(new Character(' '));

			fin.write(new Character(':'));
			fin.write(jeu.joueurs.get(i).getName().getBytes());
			fin.write(new Character('\0'));
			
			fin.write(jeu.joueurs.get(i).getSizePersonnages());
			fin.write(new Character('\\'));
			
			fin.write(jeu.joueurs.get(i).mechant?(1):(0));
			fin.write(new Character(';'));
			for (j = 0; j < jeu.joueurs.get(i).getSizePersonnages(); j++) {
				pe = jeu.joueurs.get(i).getPersonnagesI(j);
				fin.write(((Integer) pe.getPosition().getX()));
				fin.write(new Character(' '));
				fin.write(((Integer) pe.getPosition().getY()));
				fin.write(new Character(' '));
				if (pe instanceof Gentil) {
					fin.write(((Integer) (((Gentil) pe).getArme())));
					fin.write(new Character(' '));
					fin.write(((Integer) (((Gentil) pe).getDrogue())));
					fin.write(new Character(' '));
					fin.write(((Integer) (((Gentil) pe).getInventaire())));
					fin.write(new Character(' '));
					fin.write(((Integer) (((Gentil) pe).getRemede())));
					fin.write(new Character(' '));
				} else {
					fin.write(((Integer) (((Mechant) pe).getInventaire())));
					fin.write(new Character(' '));
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
	
	public String lire(FileInputStream fout, char fin) throws IOException {
		String buf = "";
		byte[] b = new byte[1];
		fout.read(b);
		while (b[0] != fin) {
			buf += b[0];
			fout.read(b);
		}
		return buf;
	}

	public void load() throws IOException {
		File f = new File(name);
		FileInputStream fout = new FileInputStream(f);
		jeu.plateau.setTailleX(Integer.getInteger(lire(fout, '\n')));
		jeu.plateau.setTailleY(Integer.getInteger(lire(fout, '\n')));
		for (int j = 0; j < jeu.plateau.tailleX; j++) {
			for (int i = 0; i < jeu.plateau.tailleY; i++) {
				jeu.plateau.set(Integer.getInteger(lire(fout, ':')), i, j);
				jeu.plateau.setP(((Integer.getInteger(lire(fout, ' ')) == 1) ? (true) : (false)), i, j);
			}
		}
		fout.skip(1);
		Joueur nouv;
		$Personnage pe;
		int nbJou = Integer.getInteger(lire(fout, '\n')),nbPers;
		List<$Personnage> l;
		List<Coordonnees> c = new LinkedList<Coordonnees>();
		for(int i=0; i<nbJou; i++) {
			nouv = new Joueur();
			nouv.setCouleur(new Color(Integer.getInteger(lire(fout, ' ')), Integer.getInteger(lire(fout, ' ')), Integer.getInteger(lire(fout, ' '))));
			fout.skip(1);
			nouv.setName(lire(fout, '\0'));
			nbPers = Integer.getInteger(lire(fout, '\\'));
			nouv.setMechant(Integer.getInteger(lire(fout, ';'))==1);
			l = new LinkedList<$Personnage>();
			for(int j=0; j<nbPers; j++) {
				if(nouv.estMechant()) {
					pe = new Mechant();
				} else {
					pe = new Gentil();
				}
				pe.setPosition(new Coordonnees(Integer.getInteger(lire(fout, ' ')),Integer.getInteger(lire(fout, ' '))));
				if(nouv.estMechant()) {
					pe.setInventaire(Integer.getInteger(lire(fout, ' ')));
				} else {
					pe.setArme(Integer.getInteger(lire(fout, ' ')));
					pe.setDrogue(Integer.getInteger(lire(fout, ' ')));
					pe.setInventaire(Integer.getInteger(lire(fout, ' ')));
					pe.setRemede(Integer.getInteger(lire(fout, ' ')));
				}
				fout.skip(1);
				c.add(new Coordonnees(Integer.getInteger(lire(fout, ':')), Integer.getInteger(lire(fout, ' '))));
				pe.getAutomate().setNbCond(Integer.getInteger(lire(fout, ':')));
				pe.getAutomate().setNbEtats(Integer.getInteger(lire(fout, '\n')));
				l.add(pe);
			}
			jeu.joueurs.add(nouv);
		}
	}

}
