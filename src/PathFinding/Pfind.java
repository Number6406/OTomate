package PathFinding;

import Otomate.Coordonnees;

public class Pfind {
	private Path p;
	private TileBasedMap map=new TileBasedMap();
	private int max=2000;
	private int next;
	
	public Pfind(Coordonnees from,Coordonnees To){
	if(from.getX()==To.getX() && from.getY()==To.getY()){
		next=0;
		return;
	}
	
	AStarPathFinder pf = new AStarPathFinder(map, max, false);
	p=pf.findPath(new Mover(), from.getX(), from.getY(), To.getX(), To.getY());
	if(p.getLength()>1){
	int x=p.getStep(1).getX();
	int y=p.getStep(1).getY();
	if(x>from.getX()){
	next=2;	
	}
	else if(y>from.getY()){
	next=3;
	}
	else if(y<from.getY()){
	next=1;
	}
	else if(x<from.getX()){
	next=4;
	}
	else{
	next=0;
	}
	}
	else{
		next=0;
	}
	}
	public int getDirection(){
		return next;
	}

}
