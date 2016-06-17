/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affichage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author bonhourg
 */
public class t {
    
    public t() {
        FenetreBase f = new FenetreBase(500,200,"test") {};
        f.setLayout(new GridLayout(4,1));
        Image pause = null;
        Image play = null;
        Image step = null;
        Image faster = null;
        try {
           play = ImageIO.read(new File(this.getClass().getResource("../Graphics/Icons/play.png").getFile()));
           pause = ImageIO.read(new File(this.getClass().getResource("../Graphics/Icons/pause.png").getFile()));
           step = ImageIO.read(new File(this.getClass().getResource("../Graphics/Icons/step.png").getFile()));
           faster = ImageIO.read(new File(this.getClass().getResource("../Graphics/Icons/faster.png").getFile()));
        } catch (IOException ex) {
        }
        f.add(new JButton(new ImageIcon(play)));
        f.add(new JButton(new ImageIcon(pause)));
        f.add(new JButton(new ImageIcon(step)));
        f.add(new JButton(new ImageIcon(faster)));
        f.pack();
    }    
}

