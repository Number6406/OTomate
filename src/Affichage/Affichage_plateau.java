package Affichage;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import Otomate.Personnage;
import Otomate.Grille;

public class Affichage_plateau extends JPanel {
	static final int TAILLECASE = 16;
	
	Color Color_int(int i){
	switch(i){
	case 1:
	return Color.BLACK;
	case 2:
	return Color.red;
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
	default:
	return Color.white;
	}
	}
	
	void Affiche_case(Graphics graph,int image,int x,int y){
		System.out.println("case " + x + " "  + y + "avec image : " + image + "faites !");
		graph.setColor(Color_int(image));
		graph.fillRect(16*x+1,16*y+1, 16, 16);
	}
	
	Affichage_plateau(){
	}
	
	public void Charger(Graphics grap,Grille g){
	//int Nb = personnages.size();
	int maxx=20*16;//=this.getWidth();
	int maxy=20*16;//=this.getHeight();
	int i,j;
	
	System.out.println("coucou " + maxx + " " + maxy);
	
	maxx=maxx/TAILLECASE;
	maxy=maxy/TAILLECASE;
	
	if(g.tailleX<maxx){
		maxx=g.tailleX;
	}
	
	if(g.tailleY<maxy){
		maxy=g.tailleY;
	}
	
	grap.setColor(Color.BLACK);
	grap.fillRect(10, 10, 100, 100);
	
	System.out.println("coucou " + maxx + " " + maxy);	
	for(j=0;j<maxy;j++){
		for(i=0;i<maxx;i++){
			Affiche_case(grap,g.g[i][j].element,i,j);
		}
	}
	super.paintComponent(grap);
	
	
	
	}
	
	
}
