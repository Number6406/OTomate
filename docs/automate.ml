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
  | Frapper
  | Rien

type condition =
  | Vide
  | Chemin of direction
  | Ennemi
  | Comestible
  | Arme1
  | Arme2

type etat = int
type transition = etat * condition * action * etat
type automate = transition list



(* EXEMPLE D'AUTOMATE *)
      
	
let aut1 = 	
    [
    (1,Comestible,Ramasser,1) ;
    (1,Chemin,Avancer,1) ;
    (1,Arme1,Ramasser,2) ;
    (1,Arme2,Ramasser,3) ;
    (1,Ennemi,Frapper,4) ;
    
    (2,Arme2,Ramasser,3) ;
    (2,Chemin,Avancer,2) ;
    (2,Ennemi,Frapper,4) ;
    
    (3,Chemin,Avancer,3) ;
    (3,Ennemi,Frapper,4) ;
    
    (4,Ennemi,Frapper,1) ;
    (4,Arme1,Ramasser,2) ;
    (4,Arme2,Ramasser,3) ;
    ]

  [ (1, Ennemi(N), Tourner_vers(N), 2) ;
    (1, Ennemi(S), Tourner_vers(S), 2) ;
    (1, Ennemi(E), Tourner_vers(E), 2) ;
    (1, Ennemi(O), Tourner_vers(O), 2) ;

    (2, Ennemi(N), Frapper, 3) ;
    (3, Vide, Avancer, 4) ;
    (4, Amoi(C), Restaurer, 1)
  ]
;;
    
   
(* ON PEUT GÉNÉRER CERTAINES PARTIES DE L'AUTOMATE *)
let (bouger: etat -> etat -> automate) = fun src tgt ->
      List.map  (fun directions -> (src, Chemin(directions), Avancer(directions), tgt) ) [N;S;E;O]

let aut2 =
  (bouger 1 1) 
    @
  (bouger 2 2) 
    @
  (bouger 3 3) 
    @
  [ (1, Ennemi, Frapper, 4) ;
    (1, Arme1, Ramasser, 2) ;
    (1, Arme2, Ramasser, 3) ;
    (1, Comestible, Ramasser, 1) ;
  
    (2, Ennemi, Frapper, 4) ;
    (2, Arme2, Ramasser, 3) ;
    
    (3, Ennemi, Frapper, 4) ;
    
    (4, Ennemi, Frapper, 4) ;
    (4, Arme1, Ramasser, 2) ;
    (4, Arme2, Ramasser, 3) ;
    (4, Comestible, Ramasser, 1) ;
    (4, Vide, Rien, 1) ;
  ]
;;


    
(* NON DETERMINISME 

   Notez que cet automate est non-déterministe. 
   Il se peut qu'il y ait un ennemi au nord et au sud ;
   dans ce cas les deux premières transitions sont exécutables.

   Dans le simulateur java :
   Il faut en choisir par tirage au sort une parmi celles qui sont exécutables.
*)


   
(* TRADUCTION DES CONDITIONS COMPLEXES EN ENTIER *)
  
let (cellule_to_int: cellule -> int) = function
  | C -> 0
  | N -> 1
  | S -> 2
  | E -> 3
  | O -> 4

let (condition_to_int: condition -> int) =  function
  | Vide -> 0
  | Ami(cellule) -> 1 + (cellule_to_int cellule) (* 1..5 *)
  | Ennemi(cellule) -> 6 + (cellule_to_int cellule) (* 6..10 *)
  | Comestible(cellule) -> 11 + (cellule_to_int cellule) (* 11..15 *)
  | _ -> 0

let (action_to_int: action -> int) = function
   | Attendre -> 0
   | Avancer -> 1
   | Reculer -> 2
   | Frapper -> 3
   | Prendre -> 4
   | Tourner_vers (cellule) -> 5 + (cellule_to_int cellule)
   | _ -> 0

   
let (traduction_transition: transition -> int * int * int * int) = fun (src,condition,action,tgt) ->
   (src, condition_to_int condition, action_to_int action, tgt)

let (traduction_automate: automate -> (int * int * int * int) list) = fun automate ->
   List.map traduction_transition automate ;;


let trad_aut1 = traduction_automate aut1 ;;

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