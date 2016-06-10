/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affichage;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Une classe permettant de générer un sélectionneur de personnage facilement
 */
public class AutomatePicker extends JPanel {
    
    JTextField chemin = new JTextField();
    Button bChoix = new Button("C");
    Button bEdit = new Button("E");
    Button bSupp = new Button("D");
    
    public AutomatePicker() {
        super();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.weightx = 1;
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(chemin,c);

        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        c.gridx += 1;
        this.add(bChoix, c);
        bChoix.addActionListener((ActionEvent e) -> {
            JFileChooser f = new JFileChooser();
            int returnValue = f.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = f.getSelectedFile();
                chemin.setText(selectedFile.getAbsolutePath());
            }
        });

        c.gridx += 1;
        this.add(bEdit, c);

        c.gridx += 1;
        this.add(bSupp, c);
        bSupp.addActionListener((ActionEvent e) -> {
            chemin.setText("");
        });
    }
    
}
