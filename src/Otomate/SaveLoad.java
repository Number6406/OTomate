package Otomate;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public final class SaveLoad {

	private SaveJeu jeu;
	private String name;

	public SaveLoad(final SaveJeu jeu, String name) {
		this.jeu = jeu;
		this.name = name;
	}
	
	public SaveLoad(String nam) {
		jeu = new SaveJeu();
		name = nam;
	}
	
	public SaveJeu getJeu() {
		return jeu;
	}

	public void save() throws IOException {
		File f = new File(name);
		FileOutputStream fin = new FileOutputStream(f);
		int i, j;
		
		fin.write(jeu.univers.numero());
		fin.write(new Character('\n'));
		System.out.println(jeu.univers.numero());
		fin.write(jeu.joueurZombie);
		fin.write(new Character('\n'));
		System.out.println(jeu.joueurZombie);
		fin.write((jeu.plateau.tailleX())); // tailleX
		fin.write(new Character('\n'));
		System.out.println(jeu.plateau.tailleX());
		fin.write((jeu.plateau.tailleY())); // tailleY
		fin.write((new Character('\n')));
		System.out.println(jeu.plateau.tailleY());
		for (j = 0; j < jeu.plateau.tailleY(); j++) {
			for (i = 0; i < jeu.plateau.tailleX(); i++) {
				fin.write(((Integer) jeu.plateau.get(i, j).element));
				fin.write(new Character(':'));
				fin.write((Integer) (((jeu.plateau.get(i, j).piegee)) ? 1 : 0));
				fin.write(' ');
			}
			fin.write(new Character('\n'));
		}
		fin.write(jeu.plateau.getCoinsAutomates().size());
		fin.write(new Character(' '));
		System.out.println(jeu.plateau.getCoinsAutomates().size());
		for(j = 0; j < jeu.plateau.getCoinsAutomates().size(); j++) {
			fin.write(jeu.plateau.getCoinsAutomates().get(j).getX());
			fin.write(new Character(':'));
			fin.write(jeu.plateau.getCoinsAutomates().get(j).getY());
			fin.write(new Character(' '));
		}
		fin.write(jeu.plateau.getNbetats().size());
		fin.write(new Character(' '));
		System.out.println(jeu.plateau.getNbetats().size());
		for(j=0; j<jeu.plateau.getNbetats().size(); j++) {
			fin.write(jeu.plateau.getNbetats().get(j));
			fin.write(new Character(' '));
		}
		fin.write(jeu.joueurs.size());
		fin.write(new Character('\n'));
		System.out.println(jeu.joueurs.size());
		System.out.println("-----");
		$Personnage pe;
		for (i = 0; i < jeu.joueurs.size(); i++) {
			fin.write(((Integer) jeu.joueurs.get(i).getCouleur().getRed()));
			fin.write(new Character(' '));

			fin.write(((Integer) jeu.joueurs.get(i).getCouleur().getGreen()));
			fin.write(new Character(' '));

			fin.write(((Integer) jeu.joueurs.get(i).getCouleur().getBlue()));
			fin.write(new Character(' '));

			fin.write(new Character(':'));
			
			fin.write(jeu.joueurs.get(i).getName().getBytes(StandardCharsets.UTF_8));
			fin.write(new Character(';'));

			fin.write(jeu.joueurs.get(i).getSizePersonnages());
			fin.write(new Character('\\'));

			fin.write(jeu.joueurs.get(i).mechant ? (1) : (0));
			fin.write(new Character(';'));
			for (j = 0; j < jeu.joueurs.get(i).getSizePersonnages(); j++) {
				pe = jeu.joueurs.get(i).getPersonnagesI(j);
				fin.write(((Integer) pe.getPosition().getX()));
				fin.write(new Character(' '));
				
				fin.write(((Integer) pe.getPosition().getY()));
				fin.write(new Character(' '));
				if (pe instanceof Gentil) {
					fin.write(((Gentil) pe).estArme()?(1):(0));
					fin.write(new Character(' '));
					if(((Gentil) pe).estArme()) {
						fin.write(((((Gentil) pe).getArme().getId())));
						fin.write(new Character(' '));
					}
					fin.write(((Integer) (((Gentil) pe).getDrogue())));
					fin.write(new Character(' '));
					fin.write(((Integer) (((Gentil) pe).getInventaire())));
					fin.write(new Character(' '));
					fin.write(((Integer) (((Gentil) pe).getRemede())));
					fin.write(new Character(' '));
					fin.write(pe.getEtat());
					fin.write(new Character(':'));
					fin.write(pe.getViemax());
					fin.write(new Character(':'));
					fin.write(((Gentil) pe).getVie());
					fin.write(new Character(':'));
				} else {
					fin.write(((Integer) (((Mechant) pe).getInventaire())));
					fin.write(new Character(' '));
					fin.write(pe.getEtat());
					fin.write(new Character(':'));
					fin.write(pe.getViemax());
					fin.write(new Character(':'));
					fin.write(pe.getVie());
					fin.write(new Character(':'));
				}
				fin.write(pe.getDmg());
				fin.write(new Character(':'));
				//fin.write(new Character(';'));
				fin.write(pe.getAutomate().nbconditions());
				fin.write(new Character(':'));
				fin.write(pe.getAutomate().nbetats());
				fin.write(new Character('\n'));
				for(int k=0; k<pe.getAutomate().nbetats(); k++) {
					for(int l=0; l<pe.getAutomate().nbconditions(); l++) {
						fin.write(pe.getAutomate().transition(l, k));
						fin.write(new Character(':'));
					}
				}
			}
			//currentChar++;
			fin.write('\n');
		}
		fin.close();
	}
	
	public void actuGrille() {
		List<$Personnage> tacos = new LinkedList<$Personnage>();
		for(int i=0; i<jeu.joueurs.size(); i++) {
			for(int j=0; j<jeu.joueurs.get(i).getPersonnages().size(); j++) {
				tacos.add(jeu.joueurs.get(i).getPersonnagesI(j));
			}
		}
		for(int i=0; i<tacos.size(); i++) {
			int ix = jeu.plateau.getCoinsAutomates().get(i).getX();
			int iy = jeu.plateau.getCoinsAutomates().get(i).getY();
			int nc = tacos.get(i).getAutomate().nbconditions();
			int ne = tacos.get(i).getAutomate().nbetats();
			for(int j=0; j<ne; j++) {
				for(int k=0; k<nc; k++) {
					tacos.get(i).getAutomate().setAction(j, k, jeu.plateau.get(j+iy, k+ix));
				}
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
	
	public String lire_nom(FileInputStream fout, char fin) throws IOException {
		String buf = "";
		byte[] b = new byte[1];
		fout.read(b);
		while(b[0]!=fin) {
			buf += new String(b, StandardCharsets.UTF_8);
			fout.read(b);
		}
		return buf;
	}

	public void load() throws IOException {
		File f = new File(name);
		FileInputStream fout = new FileInputStream(f);
		jeu.univers = new Univers(Integer.parseInt(lire(fout, '\n')));
		jeu.joueurZombie = Integer.parseInt(lire(fout, '\n'));
		jeu.plateau = new Grille(Integer.parseInt(lire(fout, '\n')), Integer.parseInt(lire(fout, '\n')));
		jeu.plateau.setUnivers(jeu.univers);
		for (int j = 0; j < jeu.plateau.tailleX(); j++) {
			for (int i = 0; i < jeu.plateau.tailleY(); i++) {
				jeu.plateau.set(Integer.parseInt(lire(fout, ':')), i, j);
				jeu.plateau.setP(((Integer.parseInt(lire(fout, ' ')) == 1) ? (true) : (false)), i, j);
			}
			fout.skip(1);
		}
		int l = Integer.parseInt(lire(fout,' '));
		for (int j=0; j < l; j++) {
			jeu.plateau.getCoinsAutomates().add(new Coordonnees(Integer.parseInt(lire(fout, ':')),Integer.parseInt(lire(fout, ' '))));
		}
		l = Integer.parseInt(lire(fout, ' '));
		for (int j=0; j<l; j++) {
			jeu.plateau.getNbetats().add(Integer.parseInt(lire(fout, ' ')));
		}
		Joueur nouv;
		$Personnage pe;
		int nbJou = Integer.parseInt(lire(fout, '\n')), nbPers;
		int r,g,b;
		for (int i = 0; i < nbJou; i++) {
			nouv = new Joueur();
			r = Integer.parseInt(lire(fout,' '));
			g = Integer.parseInt(lire(fout,' '));
			b = Integer.parseInt(lire(fout,' '));
			if(r<0) {r+=256;}
			if(g<0) {g+=256;}
			if(b<0) {b+=256;}
			nouv.setCouleur(new Color(r,g,b));
			fout.skip(1);
			
			nouv.setName(lire_nom(fout, ';'));
			nbPers = Integer.parseInt(lire(fout, '\\'));
			nouv.setMechant(Integer.parseInt(lire(fout, ';')) == 1);
			for (int j = 0; j < nbPers; j++) {
				if (nouv.estMechant()) {
					pe = new Mechant();
				} else {
					pe = new Gentil();
				}
				pe.setNom(nouv.getName()+"_"+j);
				pe.setCouleur(nouv.getCouleur());
				pe.setPosition(new Coordonnees(Integer.parseInt(lire(fout, ' ')), Integer.parseInt(lire(fout, ' '))));
				if (nouv.estMechant()) {
					pe.setInventaire(Integer.parseInt(lire(fout, ' ')));
					pe.setEtat(Integer.parseInt(lire(fout, ':')));
				} else {
					if(Integer.parseInt(lire(fout, ' '))==1) {
						((Gentil) pe).setArme(jeu.univers.getObjets().get(Integer.parseInt(lire(fout, ' '))));
					}
					((Gentil) pe).setDrogue(Integer.parseInt(lire(fout, ' ')));
					pe.setInventaire(Integer.parseInt(lire(fout, ' ')));
					((Gentil) pe).setRemede(Integer.parseInt(lire(fout, ' ')));
					pe.setEtat(Integer.parseInt(lire(fout, ':')));
				}
				pe.setViemax(Integer.parseInt(lire(fout, ':')));
				pe.setVie(Integer.parseInt(lire(fout, ':')));
				pe.setDmg(Integer.parseInt(lire(fout, ':')));
				pe.getAutomate().setNbCond(Integer.parseInt(lire(fout, ':')));
				pe.getAutomate().setNbEtats(Integer.parseInt(lire(fout, '\n')));
				pe.getAutomate().newTrans();
				pe.getAutomate().newAction();
				for(int k=0; k<pe.getAutomate().nbetats(); k++) {
					for(int m=0; m<pe.getAutomate().nbconditions(); m++) {
						pe.getAutomate().setTransition(m,k,Integer.parseInt(lire(fout, ':')));
					}
				}
				nouv.getPersonnages().add(pe);
			}
			fout.skip(1);
			jeu.joueurs.add(nouv);
		}
		fout.close();
		actuGrille();
	}

}
