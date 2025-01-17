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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
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
    private String tratio = " sain(s) pour 1 infecté";

    // Elements
    JPanel pan_corps = new JPanel(new GridLayout(1, 2));
    JPanel pan_partie = new JPanel(new GridBagLayout());
    JPanel pan_option = new JPanel();

    JLabel ljoueurs = new JLabel("Nombre de joueurs : ");
    SpinnerModel smj = new SpinnerNumberModel(2, minJ, maxJ, 1);
    JSpinner spin_joueurs = new JSpinner(smj);
    JLabel lratio = new JLabel();
    JSlider slider_ratio = new JSlider(minR, maxR, ratio);
    JLabel lratiof = new JLabel();
    JLabel lnbpersos = new JLabel("Nb. persos / joueur max.");
    SpinnerModel smp = new SpinnerNumberModel(1, minP, maxP, 1);
    JSpinner spin_persos = new JSpinner(smp);

    JPanel pan_radio = new JPanel(new GridBagLayout());
    JScrollPane scroll_u = new JScrollPane(pan_radio);
    ButtonGroup radio_univers = new ButtonGroup();

    JPanel pan_b = new JPanel(new BorderLayout());
    JButton b_annuler = new JButton("Annuler");
    JButton b_suivant = new JButton("Suivant");

    public FenetreNouvellePartie(List<String> univers) throws IOException {
        super(500, 300, "Création d'une nouvelle partie");
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
        lratio.setText("<html><b>" +slider_ratio.getValue() + "</b>" + tratio + "</html>");
        slider_ratio.addChangeListener((ChangeEvent e) -> {
            lratio.setText("<html><b>" +slider_ratio.getValue() + "</b>" + tratio + "</html>");
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
        
        int cle_radio = 1;
        for (String u : univers) {
            JRadioButton bcu = new JRadioButton(u);
            bcu.setMnemonic(cle_radio++);
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
                try {
					new FenetreMenu();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                dispose();
            }
        });

        b_suivant.addActionListener((ActionEvent e) -> {
            this.setVisible(false);
            FenetreCreation fCreation = null;
			try {
				fCreation = new FenetreCreation(getRatio(), getNbP(), getNbJ(), getUniv());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            fCreation.setPrevious(this);
        });

    }

    public static void main(String[] args) throws IOException {
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
    
    public int getUniv() {
        ButtonModel selectedModel = radio_univers.getSelection();
        if (selectedModel != null) {
            // and dislay it
            //System.out.println("On choisit le modèle : "+ selectedModel.getMnemonic());
            return selectedModel.getMnemonic();
        }
        return 1; // par défaut, on prend zombie
    }

}
