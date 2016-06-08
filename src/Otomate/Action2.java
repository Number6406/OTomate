package Otomate;

import java.util.LinkedList;
import java.util.List;

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
