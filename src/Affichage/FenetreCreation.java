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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * Fenêtre de création de personnes et choix des automates en prévision de début de partie
 */
public class FenetreCreation extends FenetreBase {
    
    FenetreNouvellePartie configPartie = null;
    List<Joueur> lJoueurs = new ArrayList<Joueur>();
    
    // Elements SWING
    JTabbedPane ongletsJoueurs = new JTabbedPane();
    
    List<JPanel> panelJ = new ArrayList<JPanel>();
    
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
        for(int i=1; i<=nbJ; i++) {
            JPanel pJoueur = new JPanel(new GridBagLayout());
            panelJ.add(pJoueur);
            
            GridBagConstraints c = new GridBagConstraints();
            c.gridwidth = 1;
            c.gridheight = 1;
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 0;
            c.weighty = 0;
            c.fill = GridBagConstraints.NONE;
            
            ongletsJoueurs.add("Joueur" + i, pJoueur);
            JTextField userName = new JTextField("Joueur " + i);
            c.gridy = 1;
            pJoueur.add(userName, c);
            
            c.gridy = 2;
            pJoueur.add(new JLabel("Automate Méchant : "), c);
            c.gridx = 1;
            c.weightx = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            JTextField textAutoM = new JTextField("chemin");
            pJoueur.add(textAutoM, c);
            c.gridx = 2;
            c.weightx = 0;
            c.fill = GridBagConstraints.NONE;
            JButton bFichierM = new JButton("Fichier");
            pJoueur.add(bFichierM, c);
            bFichierM.addActionListener((ActionEvent e) -> {
                JFileChooser fileM = new JFileChooser();
                int returnValue = fileM.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileM.getSelectedFile();
                    textAutoM.setText(selectedFile.getAbsolutePath());
                }
            });
            
            c.gridy = 3;
            pJoueur.add(new JLabel("Automates Gentils : "), c);
        }
        
        this.add(pan_b, BorderLayout.SOUTH);
        pan_b.add(bAnnuler, BorderLayout.WEST);
        pan_b.add(bValider, BorderLayout.EAST);
        
    }
    
    public void setPrevious(FenetreNouvellePartie f) {
        this.configPartie = f;
    }
    
    
}
