/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapGenerator;

import Otomate.Case;
import Otomate.Objet;

/**
 *
 */
public class GrillePattern {
    
    private Case[][] g;
    
    public GrillePattern(int x, int y, Objet base) {
        g = new Case[x][y];
        for(int i=0; i<x; i++) {
            for(int j=0; j<y; j++) {
                g[i][j] = new Case(base.getId());
            }
        }
    }
    
    public void setCase(int x, int y, Objet val) {
        if(x<g.length && y < g[x].length) {
            g[x][y].element = val.getId();
        }
    }
    
    public Case getCase(int x, int y) {
        return g[x][y];
    }
    
    public void afficher() {
        for(int i=0; i<4; i ++) {
            for(int j=0;j<4;j++) {
                System.out.print(g[i][j].element + " ");
            }
            System.out.println("");
        }
    }
    
}
