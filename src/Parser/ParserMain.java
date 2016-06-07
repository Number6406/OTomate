package Parser;

public class ParserMain {
	public static void main(String[] args) {
	ParserAutomate a = new ParserAutomate("Parser/AutomateenXML.xml");
	int i,j;
	for(i=0;i<a.nb_etat;i++){
		for(j=0;j<12;j++){
			System.out.println("["+i+"]["+j+"] Case : "+a.act[i][j].element+" Trans : "+a.auto[i][j]);
		}
	}
	
	}
}
