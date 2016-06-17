/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affichage;

import javax.swing.JDialog;
import Otomate.$Personnage;
import Otomate.Gentil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author bonhourg
 */
public class FenetreInfoPersonnage extends JDialog  {
    
    public FenetreInfoPersonnage(JFrame f, $Personnage p) {
        
        super(f, "Fiche personnage : " + p.getNom());
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        
        JPanel entete = new JPanel(new BorderLayout());
        this.add(entete, BorderLayout.NORTH);
        
        JPanel infoEntete = new JPanel(new GridLayout(3,1));
        // #TODO :Set le sprite du perso dans la partie gauche !//
        
        
        entete.add(infoEntete);
        infoEntete.add(new JLabel("<html>"+p.getNomHtml()+"</html>"));
        infoEntete.add(new JLabel("PV : " + p.getVie() + "/" + p.getViemax()));
        infoEntete.add(new JLabel("Effets : " + p.getEffets()));
        
        JPanel items = new JPanel(new GridLayout(3,1));
        this.add(items, BorderLayout.CENTER);
        if(p instanceof Gentil) {
            items.add(new JLabel("Consommable : " + p.getInventaire()));
            items.add(new JLabel("Arme : " + ((Gentil) p).getArme()));
            items.add(new JLabel("Remede : " + ((Gentil) p).getRemede()));    
        } else {
            items.add(new JLabel("Inventaire : " + p.getInventaire()));
        }
        
        this.pack();
        
    }
    
}
