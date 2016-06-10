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
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Une classe permettant de générer un sélectionneur de personnage facilement
 */
public class AutomatePicker extends JPanel {
    
    JTextField chemin = new JTextField();
    Button bChoix = new Button("C");
    Button bEdit = new Button("E");
    Button bSupp = new Button("D");
    
    String camlExt = ".ml";
    
    public AutomatePicker() {
        super();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.weightx = 1;
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(chemin,c);
        chemin.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(!checkExtension()) {
                    new JDialog();
                }
            }
        });

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
    
    public boolean checkExtension() {
        String nomFichier = this.chemin.getText();
        try{
            // Vérification si le fichier existe sur le disque
            BufferedReader b = new BufferedReader(new FileReader(nomFichier));
            
            // Vérification de l'extension
            if (nomFichier.lastIndexOf(".") > 0) {
                // On récupère l'extension du fichier
                String ext = nomFichier.substring(nomFichier.lastIndexOf("."));
                // Si le fichier n'a pas la bonne extension
                if (ext != camlExt) {
                    return false;
                }
            } else {
                // sinon c'est que le fichier n'a pas d'extension
                return false;
            }
            
        } catch (FileNotFoundException fnfe) {
           return false;
        }
        
        return true;
    }
    
}
