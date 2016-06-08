/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affichage;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;

/**
 *
 */
public class FenetreNouvellePartie extends JFrame {
    
    // Elements
    JPanel pan_corps = new JPanel(new GridLayout(1, 2));
    JPanel pan_partie = new JPanel(new GridLayout(3, 2));
    JPanel pan_option = new JPanel();
    
    JLabel ljoueurs = new JLabel("Nombre de joueurs : ");
    JSpinner spin_joueurs = new JSpinner();
    JLabel lratio = new JLabel("Un infecté pour ...");
    JSlider slider_ratio = new JSlider(1, 10);
    JLabel lratiof = new JLabel("1 personnage sain");
    JLabel lnbpersos = new JLabel("Nombre de personnages par joueur max.");
    JSpinner spin_persos = new JSpinner();
   
    ButtonGroup radio_univers = new ButtonGroup();
    
    JPanel pan_b = new JPanel(new BorderLayout());
    JButton b_annuler = new JButton("Annuler");
    JButton b_suivant = new JButton("Suivant");
    
    public FenetreNouvellePartie(List<String> univers) {
        super("Création d'une nouvelle partie");
        this.setVisible(true);
        this.setSize(800,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        
        this.add(pan_corps, BorderLayout.CENTER);
        
        pan_corps.add(pan_partie);
        pan_partie.setBorder(BorderFactory.createTitledBorder("Options de jeu"));
        
        pan_partie.add(ljoueurs);
        pan_partie.add(spin_joueurs);
        pan_partie.add(lratio);
        pan_partie.add(slider_ratio);
        pan_partie.add(lnbpersos);
        pan_partie.add(spin_persos);
        
        pan_corps.add(pan_option);
        pan_option.setBorder(BorderFactory.createTitledBorder("Options spécifiques"));
        
        pan_corps.add(pan_option);
        
        for(String u : univers) {
            JRadioButton bcu = new JRadioButton(u);
            radio_univers.add(bcu);
            pan_option.add(bcu);
        }
        
        this.add(pan_b, BorderLayout.SOUTH);
        pan_b.add(b_annuler, BorderLayout.WEST);
        pan_b.add(b_suivant, BorderLayout.EAST);
        
    }
    
    public static void main(String[] args) {
        List<String> u = new ArrayList<String>();
        u.add("Humain vs. Zombie");
        u.add("Robot vs. Virus");
        new FenetreNouvellePartie(u);
    }
    
}
