/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affichage;

import Otomate.Grille;
import Otomate.Joueur;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * Fenêtre de création de personnes et choix des automates en prévision de début de partie
 */
public class FenetreCreation extends FenetreBase {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	FenetreNouvellePartie configPartie = null;
    List<Joueur> lJoueurs = new ArrayList<Joueur>();
    
    // Elements SWING
    JTabbedPane ongletsJoueurs = new JTabbedPane();
    
    List<GiletteLaPerfectionAuMasculin> l = new LinkedList<>();
    JPanel pan_b = new JPanel(new BorderLayout());
    JButton bValider = new JButton("Commencer");
    JButton bAnnuler = new JButton("Annuler");
    
    public boolean cool(int ratio,int nbP,int nbJ){
    	int i;
    	boolean b=true;
    	for(i=0;i<nbJ;i++){
    		b=b&&(l.get(i)).cool();
    	}
    	return b;
    }
    
    
    
    public FenetreCreation(int ratio, int nbP, int nbJ) {
        super(500, 400, "Création des joueurs pour la partie");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                configPartie.setVisible(true);
                dispose();
            }
        });
        
        this.add(ongletsJoueurs, BorderLayout.CENTER);
        
        // Pour tous les joueurs, leur créer un onglet
       
        for(int i=1; i<=nbJ; i++) {
            GiletteLaPerfectionAuMasculin pJoueur = new GiletteLaPerfectionAuMasculin(nbP, i);
            l.add(pJoueur);
            ongletsJoueurs.add("Joueur" + i, pJoueur);
        }
        
        this.add(pan_b, BorderLayout.SOUTH);
        pan_b.add(bAnnuler, BorderLayout.WEST);
        pan_b.add(bValider, BorderLayout.EAST);
        
        bValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(cool(ratio,nbP,nbJ));
				int i;
				int nb=0;
				List<Integer> li= new LinkedList<>();
				for(i=0;i<l.size();i++){
					if(l.get(i).zombis){
						nb++;
						li.add(i);
					}
				}
				if(nb==1){
					System.out.println("le zombie sera le joueur "+(li.get(0)+1));
				}
				else if(nb>1){
					int id=Grille.random(0,li.size());
					System.out.println("le zombie sera le joueur "+(li.get(id)+1));
				}
				else if(nb==0){
					int id=Grille.random(0,l.size());
					System.out.println("le zombie sera le joueur "+(id+1));
				
			}
			}});
    }
    
    public void setPrevious(FenetreNouvellePartie f) {
        this.configPartie = f;
    }
    
    
}
