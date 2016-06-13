/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageEditor;

import Affichage.FenetreBase;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Classe permettant de modifier les couleurs d'une image
 */
public class ImageColor {
    
    // Images en entrée qui sera répliquée et utilisée pour obtenir une image aux bonnes couleurs
    private BufferedImage iEntree;
    
    public ImageColor(BufferedImage i) {
        this.iEntree = i;
    }
    
    public ImageColor() {
        
    }
    
    public int toRGB(int red, int green, int blue) {
        Color c = new Color(red, green, blue);
        return c.getRGB();
    }
    
    public BufferedImage changeColor(int rgbEntree, int rgbSortie) {
        
        ColorModel cm = iEntree.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = iEntree.copyData(null);
        BufferedImage iSortie = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        
        int width = iSortie.getWidth();
        int height = iSortie.getHeight();
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(iSortie.getRGB(i, j) == rgbEntree) {
                    iSortie.setRGB(i, j, rgbSortie); 
                }
            }
        }
        return iSortie;
        
    }
    
    public void setBuffer(BufferedImage bi) {
        this.iEntree = bi;
    }
     
    public void test() throws IOException {
        iEntree = ImageIO.read(new File(this.getClass().getResource("../Graphics/Chara/1.png").getFile()));
        int cEntree = toRGB(10, 64, 7);
        int cSortie = toRGB(0, 0, 255);
        
        BufferedImage bf2 = changeColor(cEntree, cSortie);
        
        FenetreBase f = new FenetreBase(200, 200, "BITE") {};
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.red);
                g.fillRect(0, 0, 100, 200);
                g.drawImage(iEntree, 0, 0, 100, 100, f);
                g.drawImage(bf2, 0, 100, 100, 100, f);
            }
        };
        f.add(p);
        
        f.setResizable(true);
        
    }
    
}
