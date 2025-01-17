package Affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Otomate.$Personnage;
import Otomate.Grille;

import javax.swing.JScrollPane;

/**
 *
 * @author bonhourg
 */
public class FenetreJeu extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Constantes
    public static final int XINFO = 300;
    public static final int X=1260;
    public static final int Y=800;
    
    // Contenu de la frame
    JMenuBar toolbar;
    JPanel pan_info;
    JScrollPane scroll_plateau;
    AffichagePlateau pan_plateau;
    JLabel label_perso;
    JScrollPane scroll_perso;
    JTable tab_perso;
    JPanel pan_interraction;
    JButton b_start;
    JButton b_pause;
    JButton b_fast;
    JTabbedPane tp_onglets;
    JScrollPane scroll_history;
    JTable tab_history;
    JScrollPane scroll_legende;
    JTable tab_legende;
    
    public FenetreJeu() {
        super();
        this.setSize(X,Y);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
    }
    
    public void charger(Grille g,List<$Personnage> persoL) {
        // Chargement des différents éléments des fenetres
        toolbar = new JMenuBar();
        pan_info = new JPanel();
        label_perso = new JLabel();
        tab_perso = new JTable(new DefaultTableModel(new Object[] {"Perso","PV"}, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row,int column){
                return false;
            }
        });
        tab_perso.getTableHeader().setReorderingAllowed(false);
        scroll_perso = new JScrollPane(tab_perso);
        pan_interraction = new JPanel();
        b_start = new JButton();
        b_pause = new JButton();
        b_fast = new JButton();
        tp_onglets = new JTabbedPane();
        tab_history = new JTable(new DefaultTableModel(new Object[] {"Tour", "Action"}, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row,int column){
                return false;
            }
        });
        tab_history.getTableHeader().setReorderingAllowed(false);
        scroll_history = new JScrollPane(tab_history);
        tab_legende = new JTable(new DefaultTableModel(new Object[] {"Id", "Img", "Obs", "Dgt"}, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row,int column){
                return false;
            }
        });
        tab_legende.getTableHeader().setReorderingAllowed(false);
        scroll_legende = new JScrollPane(tab_legende);
        pan_plateau = new AffichagePlateau(g,persoL);
        pan_plateau.setBackground(Color.LIGHT_GRAY);
        scroll_plateau = new JScrollPane(pan_plateau);
        
        this.add(toolbar, BorderLayout.NORTH);
        toolbar.setSize(X, 20);
        
        this.add(pan_info, BorderLayout.WEST);
        pan_info.setSize(XINFO, Y);
        
        this.add(scroll_plateau, BorderLayout.CENTER);
        scroll_plateau.setSize(this.getWidth()-XINFO, this.getHeight()-20);

        pan_info.setLayout(new GridBagLayout());
        GridBagConstraints infoConstraints = new GridBagConstraints();       	
       
        // début de définition des contraintes
        infoConstraints.gridx = 0;
        infoConstraints.gridy = 0;
        infoConstraints.gridheight = 1;
        infoConstraints.gridwidth = 1;
        infoConstraints.weightx = 1;
        infoConstraints.weighty = 0;
        
        infoConstraints.fill = GridBagConstraints.BOTH;
        pan_info.add(label_perso,infoConstraints);
        label_perso.setText("Personnages");
        label_perso.setHorizontalAlignment(JLabel.CENTER);
        
        infoConstraints.gridy = 1;
        infoConstraints.gridheight = 1;
        infoConstraints.weighty = 0.2;
        pan_info.add(scroll_perso,infoConstraints);
        ((DefaultTableModel) tab_perso.getModel()).addRow(new Object[]{"TEST", 18});
        
        infoConstraints.gridy = 2;
        infoConstraints.gridheight = 1;
        infoConstraints.weighty = 0;
        
        pan_info.add(pan_interraction,infoConstraints);
        pan_interraction.setLayout(new GridLayout());
        
        pan_interraction.add(b_start);
        b_start.setText("►");
        pan_interraction.add(b_pause);
        b_pause.setText("■");
        pan_interraction.add(b_fast);
        b_fast.setText("»");
        
        infoConstraints.gridy = 3;
        infoConstraints.gridheight = 1;
        infoConstraints.weighty = 0.5;
        pan_info.add(tp_onglets,infoConstraints);
        
        tp_onglets.add("Historique",scroll_history);
        ((DefaultTableModel) tab_history.getModel()).addRow(new Object[]{"TEST", "Bla"});
        ((DefaultTableModel) tab_history.getModel()).addRow(new Object[]{"TEST", "Bla"});
        ((DefaultTableModel) tab_history.getModel()).addRow(new Object[]{"TEST", "Bla"});
        ((DefaultTableModel) tab_history.getModel()).addRow(new Object[]{"TEST", "Bla"});
        ((DefaultTableModel) tab_history.getModel()).addRow(new Object[]{"TEST", "Bla"});
        ((DefaultTableModel) tab_history.getModel()).addRow(new Object[]{"TEST", "Bla"});
        ((DefaultTableModel) tab_history.getModel()).addRow(new Object[]{"TEST", "Bla"});
        ((DefaultTableModel) tab_history.getModel()).addRow(new Object[]{"TEST", "Bla"});
        
        tp_onglets.add("Legende",scroll_legende);
        
    }

}