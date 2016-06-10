/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affichage;

import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Classe donnant accès à la sélection d'automates pour une liste de personnages, simplement
 */
public class GentilPicker extends JLabel {
    
    List<JTextField> listeChemins = new ArrayList<JTextField>();
    
    public GentilPicker(int nbPerso) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        for(int i=0; i<nbPerso; i++) {
            this.add(new AutomatePicker());
        }
        
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(200,200);
        f.setVisible(true);
        f.add(new GentilPicker(5));
    }
    
}
