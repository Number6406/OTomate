package Affichage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import Otomate.$Personnage;
import Otomate.Case;
import Otomate.Grille;
import Otomate.Jeu;
import Otomate.Objet;
import java.awt.FontMetrics;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class AffichagePlateau extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int TAILLECASE = 32;
    private int MINCASE = 32;

    private Grille gr;
    private List<BufferedImage> tiles;
    private List<BufferedImage> piegeTiles;

    void Affiche_perso(Graphics graph, $Personnage p) {
        String nom = p.getNom();
        Color textColor = Color.WHITE;
        Color bgColor = p.getCouleur();
        
        FontMetrics fm = graph.getFontMetrics();
        Rectangle2D rect = fm.getStringBounds(nom, graph);
        
        int x = (int) (p.positionX()*TAILLECASE-(fm.stringWidth(nom)/2)+TAILLECASE/2);
        int y = (int) (p.positionY()*TAILLECASE+TAILLECASE*1.5);


        graph.setColor(bgColor);
        graph.fillRect(x,
                   y - fm.getAscent(),
                   (int) rect.getWidth(),
                   (int) rect.getHeight());

        graph.setColor(textColor);
        graph.drawString(nom, x, y);
        graph.drawImage(p.getSprite(), p.positionX() * TAILLECASE, p.positionY() * TAILLECASE, TAILLECASE, TAILLECASE, this);
    }

    void loadTiles(List<Objet> lo) {
        tiles = new ArrayList<BufferedImage>();
        piegeTiles = new ArrayList<BufferedImage>();
        try {
            for (int i = 0; i < lo.size(); i++) {
                BufferedImage img;
                img = ImageIO.read(new File(this.getClass().getResource(lo.get(i).getPath()).getFile())); //Version Linux
                tiles.add(img);
                
                if(lo.get(i).getPathPiege() != null) {
                    BufferedImage piegeImg;
                    piegeImg = ImageIO.read(new File(this.getClass().getResource(lo.get(i).getPathPiege()).getFile()));
                    piegeTiles.add(i, piegeImg);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    Color Color_int(int i) {
        switch (i) {
            case 1:
                return Color.BLACK;
            case 2:
                return Color.lightGray;
            case 3:
                return Color.blue;
            case 4:
                return Color.green;
            case 5:
                return Color.gray;
            case 6:
                return Color.yellow;
            case 7:
                return Color.cyan;
            case 8:
                return Color.DARK_GRAY;
            case 9:
                return Color.magenta;
            case 10:
                return Color.ORANGE;
            case 11:
                return Color.PINK;
            default:
                return Color.white;
        }
    }

    void Affiche_case(Graphics graph, Case c, int x, int y) {
        int image = c.element;
        if( c.piegee && image < piegeTiles.size() && piegeTiles.get(image) != null) {
            graph.drawImage(piegeTiles.get(image), x * TAILLECASE, y * TAILLECASE, TAILLECASE, TAILLECASE, null);
        } else {
            if (image < tiles.size()) {
                graph.drawImage(tiles.get(image), x * TAILLECASE, y * TAILLECASE, TAILLECASE, TAILLECASE, null);
            } else {
                graph.setColor(Color_int(image));
                graph.fillRect(TAILLECASE * x, TAILLECASE * y, TAILLECASE, TAILLECASE);
            }
        }
    }

    public AffichagePlateau(Grille g, List<Objet> lo) {
        gr = g;
        loadTiles(lo);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int maxx = this.getWidth();
        int maxy = this.getHeight();
        int i, j;
        TAILLECASE = MINCASE;

        this.setPreferredSize(new Dimension(gr.tailleX() * MINCASE, gr.tailleY() * MINCASE));
        maxx = maxx / TAILLECASE;
        maxy = maxy / TAILLECASE;

        if (gr.tailleX() < maxx) {
            maxx = gr.tailleX();
        }

        if (gr.tailleY() < maxy) {
            maxy = gr.tailleY();
        }

        if (maxx < (this.getWidth() / MINCASE) || maxy < (this.getHeight() / MINCASE)) {
            TAILLECASE = ((this.getWidth()) / maxx);

            if (TAILLECASE > (this.getHeight() / maxy)) {
                TAILLECASE = ((this.getHeight()) / maxy);
            }
        }

        TAILLECASE--;
	
        for (j = 0; j < maxy; j++) {
            for (i = 0; i < maxx; i++) {
                Affiche_case(g, gr.get(i, j), i, j);
            }
        }
        
        

        for (i = 0; i < Jeu.joueurs.size(); i++) {
            for(j=0;j<Jeu.joueurs.get(i).getSizePersonnages();j++) {
                Affiche_perso(g, Jeu.joueurs.get(i).getPersonnagesI(j));
            }
        }

    }

}
