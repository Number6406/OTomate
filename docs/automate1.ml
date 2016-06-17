open AutomateToX

let automate = 
[(1,Vide,Pieger,2);
(1,Arme,Ramasser,3);
(1,Comestible,Ramasser,2);
(1,Surprise,Ramasser,2);

(2,Comestible,Ramasser,2);
(2,Chemin(E),Avancer(E),2);
(2,Chemin(N),Avancer(N),2);
(2,Obstacle(N),Avancer(E),4);
(2,Arme,Ramasser,3);

(3,Ennemi(N),Attaquer(N),3);
(3,Ennemi(E),Attaquer(E),3);
(3,Ennemi(S),Attaquer(S),3);
(3,Ennemi(O),Attaquer(O),3);
(3,Chemin(O),Avancer(E),3);
(3,Chemin(S),Avancer(E),3);

(4,Chemin(E),Avancer(E),4);
(4,Chemin(S),Avancer(S),4);
(4,Comestible,Ramasser,4);
(4,Obstacle(S),Avancer(O),2);
(4,Arme,Ramasser,3);
]
;;

toXML automate 1 "AutomateCharlesLeBg.xml";;

