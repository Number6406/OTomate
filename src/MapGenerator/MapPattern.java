/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapGenerator;

/**
 * Génère une map avec des items de base
 */
public class MapPattern extends $Pattern {
    
    public MapPattern(int x, int y, int ratio) {
        super(x,y,ratio);
    
        base = contenu.get(0);
        possibleContent.add(contenu.get(1));
        possibleContent.add(contenu.get(2));
        possibleContent.add(contenu.get(3));
        possibleContent.add(contenu.get(5));
        possibleContent.add(contenu.get(6));
        possibleContent.add(contenu.get(7));
        possibleContent.add(contenu.get(8));
        possibleContent.add(contenu.get(12));
        possibleContent.add(contenu.get(13));
        
        generer();
    }
    
}
