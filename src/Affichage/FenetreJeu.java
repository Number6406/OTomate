/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Otomate.Grille;
import Otomate.Personnage;

/**
 *
 * @author bonhourg
 */
public class FenetreJeu extends JFrame {
    
    // Constantes
    public static final int XINFO = 300;
    
    // Contenu de la frame
    JMenuBar toolbar;
    JPanel pan_info;
    Affichage_plateau pan_plateau;
    JLabel label_perso;
    JTable tab_perso;
    JPanel pan_interraction;
    JButton b_start;
    JButton b_pause;
    JButton b_fast;
    JTabbedPane tp_onglets;
    JTable tab_history;
    JTable tab_legende;
    
    public FenetreJeu() {
        super();
        
        this.setSize(1280,720);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
    }
    
    public void charger(Grille g,List<Personnage> persoL) {
        toolbar = new JMenuBar();
        pan_info = new JPanel();
        label_perso = new JLabel();
        tab_perso = new JTable(new DefaultTableModel(new Object[] {"Perso","PV"}, 0));
        pan_interraction = new JPanel();
        b_start = new JButton();
        b_pause = new JButton();
        b_fast = new JButton();
        tp_onglets = new JTabbedPane();
        tab_history = new JTable(new DefaultTableModel(new Object[] {"Tour", "Action"}, 0));
        tab_legende = new JTable(new DefaultTableModel(new Object[] {"Id", "Img", "Obs", "Dgt"}, 0));
        
        this.add(toolbar);
        toolbar.setSize(MAXIMIZED_HORIZ, 20);
        
        this.add(pan_info);
        pan_info.setSize(XINFO, this.getHeight());
        pan_info.setLayout(new GridBagLayout());
        GridBagConstraints infoConstraints = new GridBagConstraints();
        pan_info.setBackground(Color.red);
        
        pan_plateau = new Affichage_plateau(g,persoL);
        this.add(pan_plateau);
        pan_plateau.setSize(this.getWidth()-pan_info.getWidth(), this.getHeight());
        pan_plateau.setBackground(Color.BLUE);
        pan_plateau.setLocation(XINFO, 0);    

        // début de définition des contraintes
        infoConstraints.gridx = 0;
        infoConstraints.gridy = 0;
        infoConstraints.gridheight = 1;
        infoConstraints.gridwidth = 1;
        infoConstraints.weightx = 1;
        infoConstraints.weighty = 1;
        
        infoConstraints.fill = GridBagConstraints.BOTH;
        pan_info.add(label_perso, infoConstraints);
        label_perso.setText("Personnages");
        label_perso.setBackground(Color.GREEN);
        
        infoConstraints.gridy = 2;
        infoConstraints.gridheight = 9;
        pan_info.add(tab_perso, infoConstraints);
        ((DefaultTableModel) tab_perso.getModel()).addRow(new Object[]{"TEST", 18});
        
        infoConstraints.gridy = 1;
        infoConstraints.gridheight = 2;
        pan_info.add(pan_interraction, infoConstraints);
        pan_interraction.setLayout(new GridLayout());
        
        pan_interraction.add(b_start);
        b_start.setText("►");
        
        pan_interraction.add(b_pause);
        b_pause.setText("■");
        
        pan_interraction.add(b_fast);
        b_fast.setText("»");
        
        infoConstraints.gridy = 6;
        infoConstraints.gridheight = 18;
        pan_info.add(tp_onglets, infoConstraints);
        
        tp_onglets.add(tab_history);
        
        tp_onglets.add(tab_legende);
        
        System.out.println("panPlateau : " + pan_plateau.getWidth() + "/" + pan_plateau.getHeight() + " | " + pan_plateau.getX() + ":" + pan_plateau.getY());
    }
    
}
