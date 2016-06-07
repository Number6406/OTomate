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


}
