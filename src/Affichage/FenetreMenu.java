/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affichage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Otomate.Jeu;
import java.awt.Desktop;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class FenetreMenu extends FenetreBase {

    private static final long serialVersionUID = 1L;

    // Elements du menu
    JLabel titre = new JLabel("Code4Dead");

    JPanel panel_b = new JPanel();
    Dimension d = new Dimension(200, 50);
    JButton b_jouer = new JButton("Jouer");
    JButton b_credits = new JButton("Crédits");
    JButton b_charger = new JButton("Charger");
    JButton b_quitter = new JButton("Quitter");

    // Liste des univers
    List<String> univers = new ArrayList<String>();

    JDialog saveW = null;

    public FenetreMenu() throws IOException {
        super(500, 300, "Code4Dead");
        this.setLayout(new BorderLayout());

        this.add(titre, BorderLayout.NORTH);
        titre.setSize(800, 100);
        titre.setHorizontalTextPosition(JLabel.CENTER);

        this.add(panel_b, BorderLayout.SOUTH);
        panel_b.add(b_jouer);
        panel_b.add(b_charger);
        panel_b.add(b_credits);
        b_credits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop desk = Desktop.getDesktop();
                try {
                    desk.open(new File(this.getClass().getResource("../credit.txt").getFile()));
                } catch (IOException ex) {
                    Logger.getLogger(FenetreMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        panel_b.add(b_quitter);
        
        b_quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Definition des univers disponibles, ici en dur
        univers.add("Humains VS. Zombies");
        univers.add("Robots VS. Virus");

        b_jouer.addActionListener((ActionEvent e) -> {
            // Ouvre une fenetre de nouvelle partie au clic
            FenetreNouvellePartie fNvPartie = new FenetreNouvellePartie(univers);
            dispose();
        });

        b_charger.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (saveW == null) {
                    saveW = new JDialog();
                    saveW.setLocationRelativeTo(null);
                    saveW.setLayout(new BorderLayout());
                    saveW.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                    saveW.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e){
                            saveW.dispose();
                            saveW = null;
                        }
                    });
                    saveW.setVisible(true);
                    JTextField chemin = new JTextField();
                    JButton bchemin = new JButton("Fichier");
                    bchemin.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFileChooser f = new JFileChooser("./");
                            int returnValue = f.showOpenDialog(null);
                            if (returnValue == JFileChooser.APPROVE_OPTION) {
                                File selectedFile = f.getSelectedFile();
                                chemin.setText(selectedFile.getAbsolutePath());
                            }

                        }
                    });
                    JButton bvalider = new JButton("Charger");
                    bvalider.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String path = chemin.getText();
                            if (path.equals("")) {
                                JOptionPane.showMessageDialog(panel_b,
                                        "Tous les champs ne sont pas remplis",
                                        "Erreur",
                                        JOptionPane.ERROR_MESSAGE,
                                        null);
                            } else {
                                try {
                                    Jeu.charger(path);
                                    saveW.dispose();
                                    saveW = null;
                                } catch (IOException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });

                    JButton bquitter = new JButton("Annuler");
                    bquitter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            saveW.dispose();
                            saveW = null;
                        }
                    });

                    saveW.add(new JLabel("Choisissez ou charger votre partie."), BorderLayout.NORTH);

                    JPanel pchoix = new JPanel(new BorderLayout());
                    saveW.add(pchoix, BorderLayout.CENTER);
                    pchoix.add(chemin, BorderLayout.CENTER);
                    pchoix.add(bchemin, BorderLayout.EAST);

                    JPanel panelb = new JPanel(new BorderLayout());
                    saveW.add(panelb, BorderLayout.SOUTH);
                    panelb.add(bvalider, BorderLayout.WEST);
                    panelb.add(bquitter, BorderLayout.EAST);

                    saveW.pack();
                } else {
                    saveW.toFront();
                }
            }
        });
    }
}
