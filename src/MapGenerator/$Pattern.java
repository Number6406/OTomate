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
 * Un Pattern permet de générer des structures sur la map, de forme rectangulaire, suivant certains critères
 */
public abstract class $Pattern {
    
    public int sizeX = 5, sizeY = 5; // Dimenssions de la Grille de génération de pattern
    GrillePattern grille = null; // Contenu de la Grille de pattern finale
    int entityRatio = 10; // Un pourcentage de taux d'apparition du contenu
    List<Objet> contenu; // Liste des objets connus par l'univers
    Objet base; // Le contenu de base de la Grille de génération (herbe par exemple)
    List<Objet> possibleContent; // List du contenu possible
    
    /**
     * Fonction de construction la plus complète, permettant de définir des dimenssions, un objet de base, et une liste d'objets trouvables sur la structure
     * Voir un exemple dans VillePattern
     * @param x taille horizontale de la structure (en nombre de cases)
     * @param y taille verticale de la structure
     * @param ratio pourcentage de possibilité d'apparition d'un objet sur la case définie
     * @param base l'objet de base sur le fond
     * @param content Les divers objets disponibles sur la structure
     */
    public $Pattern(int x, int y, int ratio, Objet base, List<Objet> content) {
        if(x > Jeu.plateau.tailleX()) x = Jeu.plateau.tailleX(); if(y > Jeu.plateau.tailleY()) y = Jeu.plateau.tailleX(); // on ne peut pas dépasser cette dimenssion car risque de conflits
        possibleContent = new LinkedList<Objet>();
        sizeX = x; sizeY = y; entityRatio = ratio; this.base = base; possibleContent = content;
        contenu = Jeu.univers.getObjets();
    }
    
    public $Pattern() { 
        
        contenu = Jeu.univers.getObjets(); possibleContent = new LinkedList<Objet>();
    }
    
    /**
     * Créé un pattern à partir de dimenssion et d'un ratio
     * @param x
     * @param y
     * @param ratio 
     */
    public $Pattern(int x, int y, int ratio) { 
        if(x > Jeu.plateau.tailleX()) x = Jeu.plateau.tailleX(); if(y > Jeu.plateau.tailleY()) y = Jeu.plateau.tailleX(); // on ne peut pas dépasser cette dimenssion car risque de conflits
        sizeX = x; sizeY = y; entityRatio = ratio; contenu = Jeu.univers.getObjets(); possibleContent = new LinkedList<Objet>(); 
    }
    
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
