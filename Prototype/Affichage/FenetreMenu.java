/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affichage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 */
public class FenetreMenu extends JFrame {
    
    // Elements du menu
    JLabel titre = new JLabel("OTomatamer");
    JButton b_jouer = new JButton("Jouer");
    JButton b_credits = new JButton("Crédits");
    JButton b_quitter = new JButton("Quitter");
    
    public FenetreMenu() {
        super();
        this.setVisible(true);
        this.setSize(800,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setResizable(false);
        
        // début de définition des contraintes
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 0;
        c.weighty = 0;
        
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(titre,c);
        titre.setHorizontalTextPosition(JLabel.CENTER);
        
        c.fill = GridBagConstraints.NONE;
        c.gridy = 2;
        this.add(b_jouer,c);
        b_jouer.setSize(200,50);
        b_jouer.setLocation(300, 200);
        c.gridy = 3;
        this.add(b_credits,c);
        b_credits.setSize(200,50);
        b_credits.setLocation(300, 250);
        c.gridy = 4;
        this.add(b_quitter,c);
        b_quitter.setSize(200,50);
        b_quitter.setLocation(300, 300);
    }
 
    public static void main(String[] args) {
        FenetreMenu f = new FenetreMenu();
    }
}