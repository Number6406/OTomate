package Affichage;

import java.awt.Dimension;
import javax.swing.JButton;

/**
 * Classe permettant de simplifier la création de boutons
 */
public class BoutonBasique extends JButton {
    
	private static final long serialVersionUID = 1L;

	public BoutonBasique(int l, int h, String texte) {
        super(texte);
        Dimension d = new Dimension(l, h);
        this.definirTaille(d);
    }
    
    public BoutonBasique(Dimension d, String texte) {
        super(texte);
        this.definirTaille(d);
    }
    
    public BoutonBasique(String texte) {
        super(texte);
    }
    
    public void definirTaille(Dimension d) {
        this.setMinimumSize(d);
        this.setSize(d);
        this.setMaximumSize(d);
    }
}
