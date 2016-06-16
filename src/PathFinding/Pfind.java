package PathFinding;

import Otomate.Coordonnees;

public class Pfind {
	private Path p;
	private TileBasedMap map=new TileBasedMap();
	private int max=100;
	private int next;
	
	public Pfind(Coordonnees from,Coordonnees To){
	
	AStarPathFinder pf = new AStarPathFinder(map, max, false);
	p=pf.findPath(new Mover(), from.getX(), from.getY(), To.getX(), To.getY());
	int x=p.getStep(0).getX();
	int y=p.getStep(0).getY();
	
	if(x>from.getX()){
	next=2;	
	}
	else if(y>from.getY()){
	next=3;
	}
	else if(y<from.getY()){
	next=1;
	}
	else{
	next=4;
	}
	}
	
	public int getDirection(){
		return next;
	}

}
