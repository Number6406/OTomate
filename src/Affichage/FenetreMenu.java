/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affichage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 */
public class FenetreMenu extends FenetreBase {
    
    // Elements du menu
    JLabel titre = new JLabel("OTomatamer");
    
    JPanel panel_b = new JPanel();
    Dimension d = new Dimension(200,50);
    BoutonBasique b_jouer = new BoutonBasique(d,"Jouer");
    BoutonBasique b_credits = new BoutonBasique(d,"Crédits");
    BoutonBasique b_quitter = new BoutonBasique(d,"Quitter");
    
    
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
        
        
        b_jouer.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                // Ouvre une fenêtre de nouvelle partie au clic
                FenetreNouvellePartie fNvPartie = new FenetreNouvellePartie(null);
            }
        });
        
        b_quitter.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                // Ouvre une fenêtre de nouvelle partie au clic
                dispose();
            }
        });
        
    }
 
    public static void main(String[] args) {
        FenetreMenu f = new FenetreMenu();
    }
}