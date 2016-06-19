/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapGenerator;

/**
 *
 */
public class DesertPattern extends $Pattern {
    
    public DesertPattern(int x, int y, int ratio) {
        super(x,y,ratio);
    
        base = contenu.get(2); // Sable
        possibleContent.add(contenu.get(11)); // chargement des cactus
        
        generer();
    }
    
}
