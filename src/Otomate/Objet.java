package Otomate;

public class Objet {
int type;
int use;
String name;
int passable;

public Objet(int type,int use,String name, int passable){
	this.type=type;
	this.use=use;
	this.name=name;
	this.passable=passable;
}

public int getType(){
	return type;
}

public int getUse(){
	return use;
}

public String getName(){
	return name;
}

public int getPassable(){
	return passable;
}

public void setType(int i){
	type=i;
}

public void setUse(int i){
	use=i;
}

public void setName(String name){
	this.name=name;
}

public void setPassable(int i){
	passable=i;
}

}
