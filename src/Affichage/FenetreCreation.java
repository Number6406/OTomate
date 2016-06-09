/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affichage;

import Otomate.Joueur;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Fenêtre de création de personnes et choix des automates en prévision de début de partie
 */
public class FenetreCreation extends FenetreBase {
    
    FenetreNouvellePartie configPartie = null;
    List<Joueur> lJoueurs = new ArrayList<Joueur>();
    
    // Elements SWING
    JTabbedPane ongletsJoueurs = new JTabbedPane();
    
    JPanel pan_b = new JPanel(new BorderLayout());
    JButton bValider = new JButton("Commencer");
    JButton bAnnuler = new JButton("Annuler");
    
    public FenetreCreation(int ratio, int nbP, int nbJ) {
        super(500, 600, "Création des joueurs pour la partie");
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
        for(int i=0; i<nbJ; i++) {
            JPanel panelJ = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridwidth = 0;
            c.gridheight = 0;
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 0;
            c.weighty = 0;
            c.fill = GridBagConstraints.NONE;
            
            ongletsJoueurs.add(panelJ);
        }
        
        this.add(pan_b, BorderLayout.SOUTH);
        this.add(bAnnuler);
        this.add(bValider);
        
    }
    
    public void setPrevious(FenetreNouvellePartie f) {
        this.configPartie = f;
    }
    
    
}
