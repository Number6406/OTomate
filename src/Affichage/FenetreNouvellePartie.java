/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affichage;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 */
public class FenetreNouvellePartie extends FenetreBase {
    
    // Valeurs prises en compte
    private int maxJ = 8;
    private int minJ = 2;
    private int maxR = 10;
    private int minR = 1;
    private int ratio = 1;
    private int maxP = 5;
    private int minP = 1;
    private String tratio = "Ratio infecté / sain";
    
    // Elements
    JPanel pan_corps = new JPanel(new GridLayout(1, 2));
    JPanel pan_partie = new JPanel(new GridBagLayout());
    JPanel pan_option = new JPanel();
    
    JLabel ljoueurs = new JLabel("Nombre de joueurs : ");
    SpinnerModel smj = new SpinnerNumberModel(2, minJ, maxJ, 1);
    JSpinner spin_joueurs = new JSpinner(smj);
    JLabel lratio = new JLabel(tratio + "(" + ratio + ")");
    JSlider slider_ratio = new JSlider(minR, maxR, ratio);
    JLabel lratiof = new JLabel("1 personnage sain");
    JLabel lnbpersos = new JLabel("Nb. persos / joueur max.");
    SpinnerModel smp = new SpinnerNumberModel(1, minP, maxP, 1);
    JSpinner spin_persos = new JSpinner(smp);
   
    JPanel pan_radio = new JPanel(new GridBagLayout());
    JScrollPane scroll_u = new JScrollPane(pan_radio);
    ButtonGroup radio_univers = new ButtonGroup();
    
    JPanel pan_b = new JPanel(new BorderLayout());
    JButton b_annuler = new JButton("Annuler");
    JButton b_suivant = new JButton("Suivant");
    
    public FenetreNouvellePartie(List<String> univers) {
        super(500, 300, "Création d'une nouvelle partie");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        
        this.add(pan_corps, BorderLayout.NORTH);
        
        pan_corps.add(pan_partie);
        pan_partie.setBorder(BorderFactory.createTitledBorder("Options de jeu"));
        
        pan_partie.add(ljoueurs, c);
        c.gridx = 1;
        pan_partie.add(spin_joueurs, c);
                
        c.gridx = 0;
        c.gridy = 1;
        pan_partie.add(lratio, c);
        c.gridx = 1;
        pan_partie.add(slider_ratio, c);
        slider_ratio.addChangeListener((ChangeEvent e) -> {
            lratio.setText(tratio + "(" + slider_ratio.getValue() + ")");
        });
        
        c.gridx = 0;
        c.gridy = 2;
        pan_partie.add(lnbpersos, c);
        c.gridx = 1;
        pan_partie.add(spin_persos, c);
        
        pan_corps.add(pan_option);
        pan_option.setBorder(BorderFactory.createTitledBorder("Options spécifiques"));
        
        pan_corps.add(pan_option);
        pan_option.add(scroll_u);
        
        c.gridx = 0;
        c.gridy = 0;
        for(String u : univers) {
            JRadioButton bcu = new JRadioButton(u);
            bcu.setSelected(true);
            radio_univers.add(bcu);
            pan_radio.add(bcu, c);
            c.gridy += 1;
        }
        
        this.add(pan_b, BorderLayout.SOUTH);
        pan_b.add(b_annuler, BorderLayout.WEST);
        pan_b.add(b_suivant, BorderLayout.EAST);
        
        b_annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        b_suivant.addActionListener((ActionEvent e) -> {
            this.setVisible(false);
            FenetreCreation fCreation = new FenetreCreation(getRatio(), getNbP(), getNbJ());
            fCreation.setPrevious(this);
        });
        
    }
    
    public static void main(String[] args) {
        List<String> u = new ArrayList<String>();
        u.add("Humain vs. Zombie");
        u.add("Robot vs. Virus");
        new FenetreNouvellePartie(u);
    }
    
    public int getRatio() {
        return ((Integer) slider_ratio.getValue());
    }
    
    public int getNbP() {
        return ((Integer) spin_persos.getValue());
    }
    
    public int getNbJ() {
        return ((Integer) spin_joueurs.getValue());
    }
    
}
