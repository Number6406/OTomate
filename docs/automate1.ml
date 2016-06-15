open AutomateToX

let automate = 
[(1,Chemin(E),Avancer(E),1);
(1,Arme,Ramasser,1);
(1,Surprise,Ramasser,1);
(1,Comestible,Ramasser,1);
(1,Obstacle(E),Avancer(S),2);
(2,Chemin(S),Avancer(S),2);
(2,Arme,Ramasser,2);
(2,Surprise,Ramasser,2);
(2,Comestible,Ramasser,2);
(2,Obstacle(S),Avancer(O),3);
(3,Chemin(O),Avancer(O),3);
(3,Arme,Ramasser,3);
(3,Surprise,Ramasser,3);
(3,Comestible,Ramasser,3);
(3,Obstacle(O),Avancer(N),4);
(4,Chemin(N),Avancer(N),4);
(4,Arme,Ramasser,4);
(4,Surprise,Ramasser,4);
(4,Comestible,Ramasser,4);
(4,Obstacle(N),Avancer(E),1);
]
;;

toXML automate 1 "automateDeplacement.xml";;

