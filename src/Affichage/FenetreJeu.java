/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
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
        
        this.setSize(800, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
    }
    
    public void charger(Grille g) {
        toolbar = new JMenuBar();
        pan_info = new JPanel();

        label_perso = new JLabel();
        tab_perso = new JTable(new DefaultTableModel(new Object[] {"Perso","PV"}, 0));
        pan_interraction = new JPanel();
        b_start = new JButton();
        b_pause = new JButton();
        b_fast = new JButton();
        tp_onglets = new JTabbedPane();
        tab_history = new JTable();
        tab_legende = new JTable();
        
        this.add(toolbar);
        
        this.add(pan_info);
        pan_info.setSize(XINFO, this.getHeight());
        pan_info.setLayout(new GridBagLayout());
        pan_info.setBackground(Color.red);
        
        pan_plateau = new Affichage_plateau();
        this.add(pan_plateau);
        pan_plateau.Charger(this.getGraphics(), g);
        
        pan_plateau.setSize(this.getWidth()-pan_info.getWidth(), this.getHeight());
       // pan_plateau.setBackground(Color.BLUE);

        pan_plateau.setLocation(XINFO, 0);
        
        pan_info.add(label_perso);
        label_perso.setText("Personnages");
        
        pan_info.add(tab_perso);
        
        pan_info.add(pan_interraction);
        pan_interraction.setLayout(new GridLayout(1, 3));
        pan_interraction.setSize(MAXIMIZED_HORIZ, 30);
        
        pan_interraction.add(b_start);

        b_start.setText("►");
        
        pan_interraction.add(b_pause);
        b_pause.setText("■");
        
        pan_interraction.add(b_fast);
        b_fast.setText("»");
        
        System.out.println("panPlateau : " + pan_plateau.getWidth() + "/" + pan_plateau.getHeight() + " | " + pan_plateau.getX() + ":" + pan_plateau.getY());
    }
    
}
