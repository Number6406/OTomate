package Otomate;

import java.util.List;

import Actions.$Action;
import Parser.ParserAction;
import Parser.ParserConditions;
import Parser.ParserObjet;

/**
 * Permet la création d'un univers selon son entier
 * 1 : Humains vs. Zombies
 * 2 : Robots vs. Virus
 * @author alicia
 *
 */
public class Univers {
	
	// Attributs
	List<$Action> actionsGentil;
	List<$Action> actionsMechant;
	List<Objet> objets;
	List<Conditions> conditions;
	String nomUnivers;
	String nomGentils;
	String nomMechants;
	String imageGentil, imageMechant;
	int numero;
	
	// Getteurs
	
	public List<$Action> getActionsGentil(){
		return  actionsGentil;
	}
	
	public List<$Action> getActionsMechant(){
		return  actionsMechant;
	}

	public List<Objet> getObjets(){
		return  objets;
	}
	
	public List<Conditions> getConditions(){
		return  conditions;
	}
	
	public String getNom(){
		return nomUnivers;
	}

	public String getNomGentils() {
		return nomGentils;
	}

	public String getNomMechants() {
		return nomMechants;
	}
	
	public String spriteGentil(){
		return imageGentil;
	}
	
	public String spriteMechant(){
		return imageMechant;
	}
	
	public int numero(){
		return numero;
	}
	
	
	// Constructeur
	public Univers(int numeroUnivers){
		ParserAction Pg,Pm;
		ParserObjet Po;
		ParserConditions Pc;
		switch(numeroUnivers){
		case 1 :
			Pg = new ParserAction("ActionsHumain.xml");
			Pm = new ParserAction("ActionsZombie.xml");
			Po = new ParserObjet("ObjetsZombie.xml");
			nomUnivers = "Humains vs. Zombies";
			nomGentils = "humains";
			nomMechants = "zombies";
			break;
		case 2 :
			Pg = new ParserAction("ActionsRobot.xml");
			Pm = new ParserAction("ActionsVirus.xml");
			Po = new ParserObjet("ObjetsRobot.xml");
			nomUnivers = "Robots vs. Virus";
			nomGentils = "robots";
			nomMechants = "virus";
			break;
		default :
			Pg = new ParserAction("ActionsHumain.xml");
			Pm = new ParserAction("ActionsZombie.xml");
			Po = new ParserObjet("ObjetsZombie.xml");
			nomUnivers = "Humains vs. Zombies";
			nomGentils = "humains";
			nomMechants = "zombies";
		}
                System.out.println("1");
		actionsGentil = Pg.list;
                System.out.println("2");
		actionsMechant = Pm.list;
                System.out.println("3");
		objets = Po.list;
                System.out.println("4");
		imageGentil = Po.imageGentil;
                System.out.println("5");
		imageMechant = Po.imageMechant;
		
		Pc = new ParserConditions("Conditions.xml");
		conditions = Pc.list;
		this.numero = numeroUnivers;
	}
	
	
}
