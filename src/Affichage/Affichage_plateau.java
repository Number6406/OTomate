package Affichage;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;
import Otomate.Personnage;
import Otomate.Grille;

public class Affichage_plateau extends JPanel {
	private int TAILLECASE = 16;
	
	private Grille gr;
	private List<Personnage> perso;
	
	void Affiche_perso(Graphics graph,int i,int x,int y){
		graph.setColor(Color_int(11));
		graph.fillOval(TAILLECASE*x, TAILLECASE*y, TAILLECASE, TAILLECASE);
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
	//	System.out.println("case " + x + " "  + y + " avec image : " + image + " faites !");
		graph.setColor(Color_int(image));
		graph.fillRect(TAILLECASE*x,TAILLECASE*y, TAILLECASE, TAILLECASE);
	}
	
	Affichage_plateau(Grille g,List<Personnage> perso){
		gr =g ;
		this.perso=perso;
	}
	
@Override
public void paintComponent(Graphics g){
	super.paintComponents(g);
	
	int Nb = perso.size();
	int maxx=this.getWidth();
	int maxy=this.getHeight();
	int i,j;
	TAILLECASE=16;
//	System.out.println("Version 1 : Width : " + this.getWidth() + " Height : " + this.getHeight());
	
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
//		System.out.println("je rendre l� !");
		TAILLECASE=((this.getHeight())/maxy);
		}
	}
	
		TAILLECASE--;
//		System.out.println("Version 2 : Width : " + this.getWidth() + " Height : " + this.getHeight());
		
		
	
	System.out.println("coucou " + maxx + " " + maxy);	
	for(j=0;j<maxy;j++){
		for(i=0;i<maxx;i++){
			Affiche_case(g,gr.get(i,j).element,i,j);
		}
	}
	
	for(i=0;i<Nb;i++){
		Affiche_perso(g,i,perso.get(i).position.getX(),perso.get(i).position.getY());
	}
	
	}
	
}
