open Printf

(* LES TYPES *)

	type direction =
	  | N
	  | S
	  | E
	  | O

	type action =
	  | Rien
	  | Avancer of direction
	  | Attaquer of direction
	  | Ramasser
	  | Pieger
	  | Utiliser
	  | Soigner
	  | Fuir
	  | Detruire
	  | Fouiller

	type condition =
	  | Vide
	  | Chemin of direction
	  | Ennemi of direction
	  | Comestible
	  | Arme1
	  | Obstacle of direction
	  | Fouillable
	  | Surprise
	  | Destructible
	type etat = int
	type transition = etat * condition * action * etat
	type automate = transition list
	type tradautomate = (int * int * int * int) list
	
	let listeDesConditions = [
	Vide;
	Chemin(N);
	Chemin(S);
	Chemin(E);
	Chemin(O);
	Ennemi(N);
	Ennemi(S);
	Ennemi(E);
	Ennemi(O);
	Comestible;
	Arme1;
	Obstacle(N);
	Obstacle(S);
	Obstacle(E);
	Obstacle(O);
	Fouillable;
	Surprise;
	Destructible
	]
  
(* TRADUCTION DES CONDITIONS COMPLEXES EN ENTIER *)
   
	(* SPECIFICATION
	| PROFIL |
	| dir_to_int : direction -> int
	| BUT |
	| renvoie la représentation numérique d'une direction
	*)    
	let (dir_to_int: direction -> int) = function
	  | N -> 0
	  | S -> 1
	  | E -> 2
	  | O -> 3

	(* SPECIFICATION
	| PROFIL |
	| condition_to_int : condition -> int
	| BUT |
	| renvoie la représentation numérique d'une condition
	*)  
	let (condition_to_int: condition -> int) =  function
	  | Vide -> 0
	  | Chemin(dir) -> 1 + (dir_to_int dir) (* 1..4 *)
	  | Ennemi(dir) -> 5 + (dir_to_int dir) (* 5..8 *)
	  | Comestible -> 9
	  | Arme1 -> 10
	  | Obstacle(dir) -> 11 + (dir_to_int dir) (* 11..14 *)
	  | Fouillable -> 15
	  | Surprise -> 16
	  | Destructible -> 16
	  | _ -> 0

	(* SPECIFICATION
	| PROFIL |
	| action_to_int : action -> int
	| BUT |
	| renvoie la représentation numérique d'une action
	*) 
	let (action_to_int: action -> int) = function
	  | Rien -> 0
	  | Avancer(dir) -> 1 + (dir_to_int dir) (* 1..4 *)
	  | Attaquer(dir) -> 5 + (dir_to_int dir) (* 5..8 *)
	  | Ramasser -> 9
	  | Pieger -> 10
	  | Utiliser -> 11
	  | Soigner -> 12
	  | Fuir -> 13
	  | Detruire -> 14
	  | Fouiller -> 15
	  | _ -> 0


(* TRADUCTION DE TRANSITION ET D'AUTOMATE *)
   
	(* SPECIFICATION
	| PROFIL |
	| traduction_transition : transition -> int * int * int * int
	| BUT |
	| renvoie la représentation numérique d'une transition
	*)
	let (traduction_transition: transition -> int * int * int * int) = fun (src,condition,action,tgt) ->
	   (src, condition_to_int condition, action_to_int action, tgt)
	   
	(* SPECIFICATION
	| PROFIL |
	| traduction_automate : automate -> (int * int * int * int) list
	| BUT |
	| renvoie la représentation numérique d'un automate
	*)
	let (traduction_automate: automate -> (int * int * int * int) list) = fun automate ->
	   List.map traduction_transition automate ;;



(* CREATION D'AUTOMATE *)
   
	(* FONCTIONS POUR GÉNÉRER CERTAINES PARTIES DE L'AUTOMATE *)
	(* SPECIFICATION
	| PROFIL |
	| bouger : etat -> etat -> automate
	| BUT |
	| Renvoie un automate qui fais bouger dans chaque direction depuis un etat vers l'autre 
	| EXEMPLE |
	| bouger 1 2 renvoie
	| [(1,Chemin(N),Avancer(N),2); (1,Chemin(E),Avancer(E),2); (1,Chemin(S),Avancer(S),2); (1,Chemin(O),Avancer(O),2);
	*)
	let (bouger: etat -> etat -> automate) = fun src tgt ->
		  List.map  (fun directions -> (src, Chemin(directions), Avancer(directions), tgt) ) [N;S;E;O]
	
	(* SPECIFICATION
	| PROFIL |
	| attaquer : etat -> etat -> automate
	| BUT |
	| Renvoie un automate qui fais attaquer dans chaque direction depuis un etat vers l'autre 
	| EXEMPLE |
	| attaquer 1 2 renvoie
	| [(1,Ennemi(N),Attaquer(N),2); (1,Ennemi(E),Attaquer(E),2); (1,Ennemi(S),Attaquer(S),2); (1,Ennemi(O),Attaquer(O),2);
	*)
	let (attaquer: etat -> etat -> automate) = fun src tgt ->
		  List.map  (fun direction -> (src, Ennemi(direction), Attaquer(direction), (tgt+(dir_to_int direction)) )) [N;S;E;O]

	(* SPECIFICATION
	| PROFIL |
	| fairePourTout : etat -> action -> etat -> automate
	| BUT |
	| Renvoie un automate qui fais l'action pour toutes les conditions depuis l'état donné vers un autre
	| EXEMPLE |
	| fairepourtout 3 Soigner 4
	*)
	let (fairePourTout: etat -> action -> etat -> automate) = fun src act tgt ->
		  List.map  (fun condition -> (src, condition, act, tgt)) listeDesConditions
		  

(* ECRITURE DE L'AUTOMATE EN XML *)
	(* SPECIFICATION
	| PROFIL |
	| nbetats : (int*int*int*int) list -> int
	| BUT |
	| Renvoie le nombre d'etats de l'automate : ie. le nombre le plus grand *) 
	let rec nbetats (liste:(int*int*int*int) list): int = match liste with
	  |[]->0
	  |(x,y,z,t)::s -> max (max x t) (nbetats s) ;;

	(* SPECIFICATION
	| PROFIL |
	| ecrireListe : (int*int*int*int) list -> out_channel -> int
	| BUT |
	| Ecrit une liste dans un fichier (l'entiers sert à eviter des beugs) *) 
	let rec ecrireListe(liste:tradautomate)(writefile:out_channel):int =
	  match liste with 
	  |[]->fprintf writefile "";
		0
	  |(x,y,z,t)::s->
		  fprintf writefile "\t<case>\n";
		  fprintf writefile "\t\t<etat>%d</etat>\n" x;
		  fprintf writefile "\t\t<condition>%d</condition>\n" y;
		  fprintf writefile "\t\t<action>%d</action>\n" z;
		  fprintf writefile "\t\t<transition>%d</transition>\n" t;
		  fprintf writefile "\t</case>\n";
		  ecrireListe s writefile ;
		  1
	;;
	
	(* SPECIFICATION
	| PROFIL |
	| toXML : automate -> int -> String 
	| BUT |
	| Ecrit un automate d'etat initial donné dans le fichier dont le nom et fourni en paramètre *) 
	let toXML(automate:automate)(etat_init:int)(name) =
	  let liste = traduction_automate automate in
	  let writefile = open_out name in
		fprintf writefile "<automate>\n";
		fprintf writefile "\t<nb_etats>%d</nb_etats>\n" (nbetats liste);
		fprintf writefile "\t<etat_init>%d</etat_init>\n" etat_init;
		ecrireListe liste writefile;
		fprintf writefile "</automate>\n";
		close_out writefile ;;
