/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affichage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Classe donnant accès à la sélection d'automates pour une liste de personnages, simplement
 */
public class GentilPicker extends JLabel {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<JTextField> listeChemins = new ArrayList<JTextField>();
    List<AutomatePicker> l = new LinkedList<>();
    
	public boolean cool(){
		boolean b=true;
		int i;
		for(i=0;i<l.size();i++){
			b=b&&l.get(i).cool();
		}
	System.out.println("gentilPicker : " + b);
	return b;
	}
	
    public GentilPicker(int nbPerso) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));   
        for(int i=0; i<nbPerso; i++) {
            l.add(new AutomatePicker());
            this.add(l.get(i));
        }
        
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(200,200);
        f.setVisible(true);
        f.add(new GentilPicker(5));
    }
    
}
