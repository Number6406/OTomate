open Printf
(* LES TYPES *)
    
type direction =
  | N
  | S
  | E
  | O
    
type cellule =
  | C (* case *)
  | D of direction

type action =
  | Avancer of direction
  | Ramasser
  | Frapper of direction
  | Rien

type armes =
  | Arc
  | Sabre

type condition =
  | Chemin of cellule
  | Ennemi of direction
  | Comestible
  | Arme of armes

type etat = int
type transition = etat * condition * action * etat
type automate = transition list
type tradautomate = (int * int * int * int) list

  
(* TRADUCTION DES CONDITIONS COMPLEXES EN ENTIER *)
   
    
let (dir_to_int: direction -> int) = function
  | N -> 0
  | S -> 1
  | E -> 2
  | O -> 3
  
let (cellule_to_int: cellule -> int) = function
  | C -> 0
  | D(x) -> 1 + (dir_to_int x)
 

let (arme_to_int : armes -> int) = function
  | Arc -> 0
  | Sabre -> 1


let (condition_to_int: condition -> int) =  function
  | Chemin(cellule) -> (cellule_to_int cellule) (* 1..4 *)
  | Ennemi(direction) -> 5 + (dir_to_int direction) (* 5..8 *)
  | Comestible -> 9
  | Arme(armes) -> 10 + (arme_to_int armes) (* 10..11 *)
  | _ -> 0

let (action_to_int: action -> int) = function
  | Rien -> 1
  | Avancer(direction) -> 2 + (dir_to_int direction) (* 2..5 *)
  | Ramasser -> 6
  | Frapper(direction) -> 7 + (dir_to_int direction) (* 7..10 *)
  | _ -> 0

   
let (traduction_transition: transition -> int * int * int * int) = fun (src,condition,action,tgt) ->
   (src, condition_to_int condition, action_to_int action, tgt)

let (traduction_automate: automate -> (int * int * int * int) list) = fun automate ->
   List.map traduction_transition automate ;;




(* EXEMPLE D'AUTOMATE *)
   
(* ON PEUT GÉNÉRER CERTAINES PARTIES DE L'AUTOMATE *)
let (bouger: etat -> etat -> automate) = fun src tgt ->
      List.map  (fun directions -> (src, Chemin(D directions), Avancer(directions), tgt) ) [N;S;E;O]

let (attaquer: etat -> etat -> automate) = fun src tgt ->
      List.map  (fun direction -> (src, Ennemi(direction), Frapper(direction), (tgt+(dir_to_int direction)) )) [N;S;E;O]

let aut1 =
  (bouger 1 1) 
    @
  (bouger 2 2) 
    @
  (bouger 3 3) 
    @
  (attaquer 1 4) 
    @
  (attaquer 2 4) 
    @
  (attaquer 3 4) 
    @
  (attaquer 4 4) 
    @
  [ (1, Arme(Arc), Ramasser, 2) ;
    (1, Arme(Sabre), Ramasser, 3) ;
    (1, Comestible, Ramasser, 1) ;
  
    (2, Arme(Sabre), Ramasser, 3) ;
    
    (4, Arme(Arc), Ramasser, 2) ;
    (4, Arme(Sabre), Ramasser, 3) ;
    (4, Comestible, Ramasser, 1) ;
    (4, Chemin(C), Rien, 1) ;
  ]
;;

let automatamere =
  (bouger 1 1) 
    @
  [ (1, Comestible, Ramasser, 1) ]
    @
  (bouger 2 2) 
    @
  (attaquer 2 5)
    @
  [ (2, Arme(Sabre), Ramasser, 3) ]
    @
  (attaquer 3 9)
    @
  (bouger 3 3) 
    @
  [ (5, Chemin(D N), Avancer(N), 2); 
    (6, Chemin(D S), Avancer(S), 2);
    (7, Chemin(D E), Avancer(E), 2); 
    (8, Chemin(D O), Avancer(O), 2);  
    (9, Chemin(D N), Avancer(N), 3); 
    (10, Chemin(D S), Avancer(S), 3);
    (11, Chemin(D E), Avancer(E), 3); 
    (12, Chemin(D O), Avancer(O), 3);  
  ]
;;

let automatazombie =
  (bouger 1 1)
  @
  (attaquer 1 2)
  @
  (bouger 2 1)
  @
  (bouger 3 1)
  @
  (bouger 4 1)
  @
  (bouger 5 1)
  @
  [
    (2, Ennemi(N), Frapper(N), 2); 
    (3, Ennemi(S), Frapper(S), 3);
    (4, Ennemi(E), Frapper(E), 4); 
    (5, Ennemi(O), Frapper(O), 5);
    ]
    ;;

let autosimple =


[(1, Chemin(D N), Avancer(N), 1); 
    (1, Chemin(D S), Avancer(S), 1);
    (1, Chemin(D E), Avancer(E), 1); 
    (1, Chemin(D O), Avancer(O), 1);]
;;
(* NON DETERMINISME 

   Notez que cet automate est non-déterministe. 
   Il se peut qu'il y ait un ennemi au nord et au sud ;
   dans ce cas les deux premières transitions sont exécutables.

   Dans le simulateur java :
   Il faut en choisir par tirage au sort une parmi celles qui sont exécutables.
*)

let trad_aut1 = traduction_automate aut1 ;;

let tradtamere = traduction_automate automatamere;;

let tradzombie = traduction_automate automatazombie;;

let tradsimple = traduction_automate autosimple;;
(* On obtient
   [ (1, 7, 6, 2); 
     (1, 8, 7, 2); 
     (1, 9, 8, 2); 
     (1, 10, 9, 2); 
     (2, 7, 3, 3);
     (3, 0, 1, 4); 
     (4, 0, 0, 1) ]

  à partir duquel on peut constuire le tableau des transitions et celui des actions.

 *)

   
(* LE SIMULATEUR examine le voisinage de la position (x,y) du personnage 
   
         ?      |  Ennemi(N)    |  ?
 ------------------------------------------------
  Comestible(O) | Comestible(C) | Comestible(E)
 ------------------------------------------------
         ?      |  Ennemi(S)    |  ?

qui correspond aux conditions 

    ?  | 7  | ?
    15 | 11 | 14
     ? | 8  | ? 

Supposons que l'automate du personnage soit dans l'état 1,
le simulateur cherche les transitions exécutables de l'automate 
dans l'état 1 sur les conditions/symboles {7,8,11,14,15}
il y en a deux transitions possibles :

 (1, 7, 6, 2)  et (1, 8, 7, 2);

Le simulateur en prend tire une parmi celle là est l'exécute.
*)
 
(* Renvoie le nombre d'etats de l'automate : ie. le nombre le plus grand *) 
let rec nbetatsauto (liste:(int*int*int*int) list): int = match liste with
  |[]->0
  |(x,y,z,t)::s -> max x (nbetatsauto s) ;;

(*Met le nombre d'etats dans une variable*)
let nb_etats = (nbetatsauto tradtamere);;
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

let toXML(liste:tradautomate)(etat_init:int) =
  let writefile = open_out "AutomateenXML.xml" in
    fprintf writefile "<automate>\n";
    fprintf writefile "\t<nb_etats>%d</nb_etats>\n" (nbetatsauto liste);
    fprintf writefile "\t<etat_init>%d</etat_init>\n" etat_init;
    ecrireListe liste writefile;
    fprintf writefile "</automate>\n";
    close_out writefile ;;
  
toXML tradtamere 1;;
toXML tradzombie 1;;
toXML tradsimple 1;;
