package Affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.LinkedList;
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
import Otomate.Jeu;
import Otomate.Joueur;
import Otomate.Objet;
import Otomate.historique.Evenement;
import Otomate.historique.Historique;
import Otomate.historique.Tour;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
    public static final int X = 1260;
    public static final int Y = 800;

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
    JButton b_step;
    JTabbedPane tp_onglets;
    JScrollPane scroll_history;
    JTable tab_history;
    JScrollPane scroll_legende;
    JTable tab_legende;

    public FenetreJeu() {
        super();
        this.setSize(X, Y);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
    }

    public void charger() throws IOException {

        Grille g = Jeu.plateau;
        List<Objet> lo = Jeu.univers.getObjets();
        List<$Personnage> persoL = new LinkedList<>();
        Historique h = Jeu.historique;
        List<Joueur> l = Jeu.joueurs;
        int i, j, max = l.size(), max2;

        for (i = 0; i < max; i++) {
            max2 = l.get(i).getSizePersonnages();
            for (j = 0; j < max2; j++) {
                persoL.add(l.get(i).getPersonnagesI(j));
            }
        }

        // Chargement des différents éléments des fenetres
        toolbar = new JMenuBar();
        pan_info = new JPanel();
        label_perso = new JLabel();
        tab_perso = new JTable(new DefaultTableModel(new Object[]{"Perso", "PV"}, 0) {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tab_perso.getTableHeader().setReorderingAllowed(false);
        scroll_perso = new JScrollPane(tab_perso);
        pan_interraction = new JPanel();
        b_start = new JButton(new ImageIcon(ImageIO.read(new File(this.getClass().getResource("../Graphics/Icons/play.png").getFile()))));
        b_pause = new JButton(new ImageIcon(ImageIO.read(new File(this.getClass().getResource("../Graphics/Icons/pause.png").getFile()))));
        ImageIcon fast1 = new ImageIcon(ImageIO.read(new File(this.getClass().getResource("../Graphics/Icons/fast1.png").getFile())));
        ImageIcon fast2 = new ImageIcon(ImageIO.read(new File(this.getClass().getResource("../Graphics/Icons/fast2.png").getFile())));
        ImageIcon fast3 = new ImageIcon(ImageIO.read(new File(this.getClass().getResource("../Graphics/Icons/fast3.png").getFile())));
        b_fast = new JButton(fast2);

        b_step = new JButton(new ImageIcon(ImageIO.read(new File(this.getClass().getResource("../Graphics/Icons/step.png").getFile()))));
        tp_onglets = new JTabbedPane();
        tab_history = new JTable(new DefaultTableModel(new Object[]{"Tour", "Action"}, 0) {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tab_history.getTableHeader().setReorderingAllowed(false);
        scroll_history = new JScrollPane(tab_history);
        tab_legende = new JTable(new DefaultTableModel(new Object[]{"Id", "Img", "Nom", "Obstacle"}, 0) {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class getColumnClass(int column) {
                if (column == 1) {
                    return ImageIcon.class;
                }
                return Object.class;
            }
        });
        tab_legende.getTableHeader().setReorderingAllowed(false);
        scroll_legende = new JScrollPane(tab_legende);
        pan_plateau = new AffichagePlateau(g, persoL, lo);
        pan_plateau.setBackground(Color.LIGHT_GRAY);
        scroll_plateau = new JScrollPane(pan_plateau);

        this.add(toolbar, BorderLayout.NORTH);
        toolbar.setSize(X, 20);

        this.add(pan_info, BorderLayout.WEST);
        pan_info.setSize(XINFO, Y);

        this.add(scroll_plateau, BorderLayout.CENTER);
        scroll_plateau.setSize(this.getWidth() - XINFO, this.getHeight() - 20);

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
        pan_info.add(label_perso, infoConstraints);
        label_perso.setText("Personnages");
        label_perso.setHorizontalAlignment(JLabel.CENTER);

        infoConstraints.gridy = 1;
        infoConstraints.gridheight = 1;
        infoConstraints.weighty = 0.2;
        pan_info.add(scroll_perso, infoConstraints);
        ((DefaultTableModel) tab_perso.getModel()).addRow(new Object[]{"TEST", 18});

        infoConstraints.gridy = 2;
        infoConstraints.gridheight = 1;
        infoConstraints.weighty = 0;

        pan_info.add(pan_interraction, infoConstraints);
        pan_interraction.setLayout(new GridLayout());
        
        // Insertion de tous les boutons de gestion du temps dans l'affichage
        pan_interraction.add(b_start);
        pan_interraction.add(b_pause);
        pan_interraction.add(b_fast);
        b_fast.addActionListener((ActionEvent e) -> { // Listener pour la vitesse du jeu
            if (Jeu.period <= 250) { // Si vitesse maximale, on revient à une vitesse minimale
                Jeu.period = 1000;
                b_fast.setIcon(fast2);
            } else if (Jeu.period <= 500) { // Vitesse intermédiaire vers vitesse max
                Jeu.period = 250;
                b_fast.setIcon(fast1);
            } else { // Vitesse minimale vers intermédiaire
                Jeu.period = 500;
                b_fast.setIcon(fast3);
            }
            System.out.println("Vitesse de jeu : " + Jeu.period);
        });
        pan_interraction.add(b_step);

        infoConstraints.gridy = 3;
        infoConstraints.gridheight = 1;
        infoConstraints.weighty = 0.5;
        pan_info.add(tp_onglets, infoConstraints);

        // Affichage de l'historique
        tp_onglets.add("Historique", scroll_history);
        for (int i1 = h.nbTour(); i1 > 0; i1--) {
            Tour t = h.getTour(i1);
            for (int j1 = 0; j1 < t.nbEvenement(); j1++) {
                Evenement e = t.getEvenement(j1);
                ((DefaultTableModel) tab_history.getModel()).addRow(new Object[]{i1 + 1, e.toString()});
            }
            if (i1 != 0) {
                ((DefaultTableModel) tab_history.getModel()).addRow(new Object[]{"", ""});
            }
        }
        
        // instanciation de la légende
        tp_onglets.add("Legende", scroll_legende);
        for (Objet o : lo) {
            String obs;
            if (o.estPassable()) {
                obs = "NON";
            } else {
                obs = "OUI";
            }
            // Création d'une nouvelle ligne dans la JTable
            ((DefaultTableModel) tab_legende.getModel()).addRow(
                new Object[]{
                    o.getId(),
                    new ImageIcon(getClass().getResource(o.getPath())), // Affichage de l'icone
                    o.getName(),
                    obs
                }
            );
        }

    }

    public static void main(String[] args) throws IOException {
        FenetreJeu f = new FenetreJeu();
        f.charger();
    }

}
