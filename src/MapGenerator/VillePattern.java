/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapGenerator;

import Otomate.Jeu;
import Otomate.Objet;
import java.util.List;

/**
 *
 * @author Number6406
 */
public class VillePattern extends $Pattern {
    
    public VillePattern(int x, int y, int ratio) {
        super(x,y,ratio);
    
        base = contenu.get(0);
        possibleContent.add(contenu.get(4)); // chargement des immeubles
        possibleContent.add(contenu.get(9)); // chargement des hopitaux
        possibleContent.add(contenu.get(10)); //chargement des granges
        
        generer();
    }
    
}
