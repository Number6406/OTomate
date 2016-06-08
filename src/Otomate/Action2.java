package Otomate;

import java.util.LinkedList;
import java.util.List;

public class Action2 {
int type;
int direction;
int objet;
String name;
List<Integer> cond= new LinkedList<Integer>();

public Action2(int type,int direction,int objet, List<Integer> cond,String name){
	this.type=type;
	this.direction=direction;
	this.objet=objet;
	this.name=name;
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


}
