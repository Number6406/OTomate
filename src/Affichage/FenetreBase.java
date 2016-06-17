package Affichage;

import javax.swing.JFrame;

/**
 * Classe abstraite permettant de spécifier la création de toutes les fenêtres
 * plus simplement
 */
public abstract class FenetreBase extends JFrame {

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
        
        this.setLocationRelativeTo(null);

        this.setVisible(true); // On affiche la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // La fermeture via l'action proncipale est possible
        this.setResizable(false); // On empêche de changer la taille de la fenêtre

    }

}
