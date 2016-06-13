

all : creerXML

automateToX.cmx : automateToX.ml
	ocamlopt -c automateToX.ml

automate1.cmx : automate1.ml
	ocamlopt -c automate1.ml

creerXML : automateToX.cmx automate1.cmx
	ocamlopt -o creerXML automateToX.cmx automate1.cmx

clean : 
	rm *.o *.cm? 
