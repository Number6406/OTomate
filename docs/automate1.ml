open AutomateToX

let automate = [(1,Chemin(N),Avancer(N),1);
(1,Chemin(E),Avancer(E),1);
(1,Chemin(S),Avancer(S),1);
(1,Chemin(O),Avancer(O),1);
(1,Arme1,Ramasser,2);
(2,Ennemi(N),Attaquer(N),3);
(2,Ennemi(S),Attaquer(S),3);
(2,Ennemi(E),Attaquer(E),3);
(2,Ennemi(O),Attaquer(O),3);
]
@
(fairePourTout 3 Soigner 4)
@
(fairePourTout 4 (Avancer(S)) 1)
;;

toXML automate 1 "automate1.xml";;

