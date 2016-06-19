/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapGenerator;

import Otomate.Case;
import Otomate.Jeu;
import Otomate.Objet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 
 */
public abstract class $Pattern {
    
    public int sizeX, sizeY; // Dimenssions de la Grille de génération de pattern
    GrillePattern grille; // Contenu de la Grille de pattern finale
    int entityRatio; // Un pourcentage de taux d'apparition du contenu
    Objet base; // Le contenu de base de la Grille de génération (herbe par exemple)
    List<Objet> possibleContent; // List du contenu possible
    List<Objet> contenu;
    
    public $Pattern(int x, int y, int ratio, Objet base, List<Objet> content) {
        possibleContent = new LinkedList<Objet>();
        sizeX = x; sizeY = y; entityRatio = ratio; this.base = base; possibleContent = content;
        contenu = Jeu.univers.getObjets();
    }
    
    public $Pattern() { contenu = Jeu.univers.getObjets(); possibleContent = new LinkedList<Objet>(); }
    
    public $Pattern(int x, int y, int ratio) { sizeX = x; sizeY = y; entityRatio = ratio; contenu = Jeu.univers.getObjets(); possibleContent = new LinkedList<Objet>(); }
    
    public void generer() {
        grille = new GrillePattern(sizeX, sizeY, base);
        for(int i=0; i<sizeX; i++) {
            for(int j=0; j<sizeY; j++) {
                if(random(0, 100) <= entityRatio) {
                    grille.setCase(i, j, possibleContent.get(random(0,possibleContent.size())));
                }
            }
        }
    }
    
    public Case getCase(int x, int y) {
        return grille.getCase(x, y);
    }
    
    public static int random(int min, int max){
        Random r = new Random();
        int k = min + r.nextInt(max - min);

        return k;
    }
    
}
