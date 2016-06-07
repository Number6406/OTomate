package Affichage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import Otomate.$Personnage;
import Otomate.Grille;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Affichage_plateau extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int TAILLECASE = 16;
	
	private Grille gr;
	private List<$Personnage> perso;
        private List<BufferedImage> tiles;
        Image chara;
	
	void Affiche_perso(Graphics graph,int i,int x,int y){
            graph.setColor(Color_int(11));
            graph.drawImage(chara, x*TAILLECASE, y*TAILLECASE, TAILLECASE, TAILLECASE, this);
            //graph.fillOval(TAILLECASE*x, TAILLECASE*y, TAILLECASE, TAILLECASE);
	}
        
        void loadTiles() {
            tiles = new ArrayList<>();
            try {
                for(int i=0; i<=10; i++) {
                    BufferedImage img;
                    System.out.println("../Graphics/Tiles/Zombie/"+i+".jpg");
                    img = ImageIO.read(new File(this.getClass().getResource("../Graphics/Tiles/Zombie/"+i+".jpg").getFile())); //Version Linux
                    tiles.add(img);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
            
            //test perso
            try {
                chara = new ImageIcon(this.getClass().getResource("../Graphics/Chara/1.gif")).getImage();
            } catch (Exception e) {};
            
        }
	
	Color Color_int(int i){
            switch(i){
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
	
	void Affiche_case(Graphics graph,int image,int x,int y){
            //System.out.println("case " + x + " "  + y + " avec image : " + image + " faites !");
            //graph.setColor(Color_int(image));
            //graph.fillRect(TAILLECASE*x,TAILLECASE*y, TAILLECASE, TAILLECASE);
            if(image < tiles.size()){
                graph.drawImage(tiles.get(image), x*TAILLECASE, y*TAILLECASE, TAILLECASE, TAILLECASE, null);
            }
            else{
            	graph.setColor(Color_int(image));
            	graph.fillRect(TAILLECASE*x,TAILLECASE*y, TAILLECASE, TAILLECASE);
            }
	}
	
	Affichage_plateau(Grille g,List<$Personnage> perso){
            gr =g ;
            this.perso=perso;
            loadTiles();
	}
	
        @Override
        public void paintComponent(Graphics g){
            super.paintComponents(g);
           // this.setBackground(Color.lightGray);
            int Nb = perso.size();
            int maxx=this.getWidth();
            int maxy=this.getHeight();
            int i,j;
            TAILLECASE=16;
            // System.out.println("Version 1 : Width : " + this.getWidth() + " Height : " + this.getHeight());

            this.setPreferredSize( new Dimension(gr.tailleX*16,gr.tailleY*16));
            maxx=maxx/TAILLECASE;
            maxy=maxy/TAILLECASE;

            if(gr.tailleX<maxx){
                maxx=gr.tailleX;
            }

            if(gr.tailleY<maxy){
                maxy=gr.tailleY;
            }


            if(maxx<(this.getWidth()/16) || maxy<(this.getHeight()/16)){
                TAILLECASE=((this.getWidth())/maxx);

                if(TAILLECASE > (this.getHeight()/maxy)){
                    TAILLECASE=((this.getHeight())/maxy);
                }
            }

            TAILLECASE--;
          //  System.out.println("Width : " + this.getWidth() + " Height : " + this.getHeight()+ " Size : " + tiles.size());



            // System.out.println("coucou " + maxx + " " + maxy);	
            for(j=0;j<maxy;j++){
                for(i=0;i<maxx;i++){
                    Affiche_case(g,gr.get(i,j).element,i,j);
                }
            }

            for(i=0;i<Nb;i++){
                Affiche_perso(g,i,perso.get(i).getPosition().getX(),perso.get(i).getPosition().getY());
            }

            }
	
        }