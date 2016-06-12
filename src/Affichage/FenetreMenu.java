/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affichage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 */
public class FenetreMenu extends FenetreBase {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Elements du menu
    JLabel titre = new JLabel("OTomatamer");
    
    JPanel panel_b = new JPanel();
    Dimension d = new Dimension(200,50);
    BoutonBasique b_jouer = new BoutonBasique(d,"Jouer");
    BoutonBasique b_credits = new BoutonBasique(d,"Crédits");
    BoutonBasique b_quitter = new BoutonBasique(d,"Quitter");
    
    // Liste des univers
    List<String> univers = new ArrayList<String>();
    
    public FenetreMenu() {        
        super(500,300,"ANGRY OTOMATE");
        this.setLayout(new BorderLayout());
        
        this.add(titre, BorderLayout.NORTH);
        titre.setSize(800,100);
        titre.setHorizontalTextPosition(JLabel.CENTER);
        
        this.add(panel_b, BorderLayout.SOUTH);
        panel_b.add(b_jouer);
        panel_b.add(b_credits);
        panel_b.add(b_quitter);
        
        // Definition des univers en DUR pour le moment
        univers.add("Humains VS. Zombies");
        univers.add("Robots VS. Virus");
        
        b_jouer.addActionListener((ActionEvent e) -> {
            // Ouvre une fenetre de nouvelle partie au clic
            FenetreNouvellePartie fNvPartie = new FenetreNouvellePartie(univers);
        });
        
        b_quitter.addActionListener((ActionEvent e) -> {
            // Ouvre une fenetre de nouvelle partie au clic
            dispose();
        });
        
    }
 
    public static void main(String[] args) {
        FenetreMenu f = new FenetreMenu();
    }
}