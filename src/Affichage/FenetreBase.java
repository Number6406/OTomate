/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affichage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * Classe abstraite permettant de spécifier la création de toutes les fenêtres
 * plus simplement
 */
public abstract class FenetreBase extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FenetreBase(int l, int h, String nom) {

        super(nom);
        // On force des tailles minimales à la création histoire d'éviter de se retrouver dans de mauvaises situations
        if (l < 200 || h < 200) {
            this.setSize(l, h);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            this.setSize(l, h);
        }
        
        BufferedImage icon;
        try {
            icon = ImageIO.read(new File(this.getClass().getResource("/Graphics/Icons/c4d.png").getFile()));
            this.setIconImage(icon);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        this.setLocationRelativeTo(null);

        this.setVisible(true); // On affiche la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // La fermeture via l'action proncipale est possible
        this.setResizable(false); // On empêche de changer la taille de la fenêtre

    }

}
