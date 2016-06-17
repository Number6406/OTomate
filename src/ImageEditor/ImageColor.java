package ImageEditor;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;


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
}
