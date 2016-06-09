package Otomate;

import java.util.LinkedList;
import java.util.List;
/*
    { 0 : rien
    { 1 : deplacement
k € { 2 : attaque
    { 3 : ramassage
    { 4 : utiliser

    { 0 : SP
    { 1 : N
d € { 2 : S
    { 3 : E
    { 4 : O

o € list<int>

name --> String

cond < condMax

 */
public class Action2 {
private int type;
private int direction;
private int objet;
private String name;
private List<Integer> cond= new LinkedList<Integer>();

public Action2(int type,int direction,int objet, List<Integer> cond,String name){
	this.setType(type);
	this.setDirection(direction);
	this.setObjet(objet);
	this.setName(name);
	this.cond=cond;
}

public boolean ActionPossible(List<Conditions2> c,Grille g,Coordonnees pos,List<Objet> l,$Personnage jo,List<Joueur> lj){
	int i,max=cond.size();
	for(i=0;i<max;i++){
		if(!c.get(cond.get(i)).estVrai(g, pos, l, jo, lj)){
			return false;
		}
	}
return true;
}
/*
public boolean estpossible(List<Conditions2> l){
	int i,j,max,max2;
	max=cond.size();
	max2=l.size();
	for(i=0;i<max;i++){
		for(j=0;j<max2 && j>=0;j++){
			if(cond.get(i)==l.get(j).getId()){j=-1;}
		}
		if(j!=-1) return false;
	}
return true;
}
*/
public String directionToString(int d){
	switch(d){
		case 1:
			return "Nord";
		case 2:
			return "Est";
		case 3:
			return "Sud";
		case 4:
			return "Ouest";
	}
return "";
}

public String toString(List<Objet> lo,List<Joueur> lj,Coordonnees c,Grille g){
	switch(type){
		case 0:
			return "Ne fais rien";
		
		case 1:
			if(direction!=0) return ("Se deplace vers le "+directionToString(direction));
			else return "Ne bouges pas";
		case 2:
			if(direction==0) return "Ne fais quasiment rien";
			else {
			Coordonnees next=c.CalculCase(direction);
			int i,j;
			for(i=0;i<lj.size();i++){
				for(j=0;j< lj.get(i).getSizePersonnages();j++){
					if(lj.get(i).getPersonnagesI(j).getPosition().getX()==next.getX() && lj.get(i).getPersonnagesI(j).getPosition().getY()==next.getY()){
						return ("Attaque en vers le "+directionToString(direction)+" vers "+lj.get(i).getName());
					}
				}			
			}
			return ("Attaque en vers le "+directionToString(direction)+" Mais ne tape personne ");
			}
		case 3:
			return ("Ramasse "+lo.get(g.get(c.getX(), c.getY()).getValeur()).name);
		case 4:
			return ("Utilise un objet ");
	}	
return "";
}

public int getType() {
	return type;
}

public void setType(int type) {
	this.type = type;
}

public int getDirection() {
	return direction;
}

public void setDirection(int direction) {
	this.direction = direction;
}

public int getObjet() {
	return objet;
}

public void setCond(List<Integer> c){
	cond=c;
}

public List<Integer> getCond(){
	return cond;
}

public void setObjet(int objet) {
	this.objet = objet;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}


}
