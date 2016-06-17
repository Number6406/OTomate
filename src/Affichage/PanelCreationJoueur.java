package Affichage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PanelCreationJoueur extends JPanel {

	int i, nbP;
	Color maCouleur=null;
	GridBagConstraints c;
	JTextField userName;
	AutomatePicker mechant=null;
	GentilPicker listePersos=null;
	JScrollPane scrollP;
	JButton choixCouleur;
	boolean zombis;
	

	private static final long serialVersionUID = 1L;

	public PanelCreationJoueur(int nbP, int i) {
            super();
            this.setLayout(new GridBagLayout());
            this.i = i;
            this.nbP = nbP;
            switch(i){
            case 1:
            	maCouleur=Color.black;
            break;
            
            case 2:
            	maCouleur=Color.blue;
            break;
            
            case 3:
            	maCouleur=Color.red;
            break;
            
            case 4:
            	maCouleur=Color.green;
            break;
            
            case 5:
            	maCouleur=Color.yellow;
            break;
            
            case 6:
            	maCouleur=Color.pink;
            break;
            
            case 7:
            	maCouleur=Color.white;
            break;
            
            case 8:
            	maCouleur=Color.DARK_GRAY;
            break;
            
            default:
            	maCouleur=Color.orange;
            break;
            }
            // panelJ.add(this);

            c = new GridBagConstraints();
            userName = new JTextField("Joueur " + i);
            mechant = new AutomatePicker();
            listePersos = new GentilPicker(nbP);
            scrollP = new JScrollPane(listePersos);
            scrollP.setMinimumSize(new Dimension(200, 200));

            c.gridwidth = 1;
            c.gridheight = 1;
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 0;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;

            c.gridwidth = 2;
            this.add(new JLabel("Nom du Joueur :"), c);

            c.gridwidth = 1;
            c.gridx = 2;
            this.add(new JLabel("Couleur"), c);
            
            c.gridx = 3;
            this.add(new JLabel("Jouer Zombie ?"), c);

            c.gridy = 2;
            c.gridx = 0;
            c.gridwidth = 2;
            this.add(userName, c);
            
            c.gridx = 2;
            c.gridwidth = 1;
            choixCouleur=new JButton();
            this.add(choixCouleur, c);
            choixCouleur.setBackground(maCouleur);
            choixCouleur.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog jd = new JDialog();
                    JColorChooser jc = new JColorChooser();
                    
                    JButton jblalcolique = new JButton();
                    jblalcolique.setSize(new Dimension(50,50));
                    jd.add(jblalcolique);

                    jblalcolique.setText("ok");
                    jblalcolique.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            maCouleur=jc.getColor();
                            choixCouleur.setBackground(maCouleur);
                            jd.setVisible(false);

                        }
                    });
                    jd.add(jc);
                    jd.setSize(new Dimension(500,400));
                    jd.setLocationRelativeTo(null);
                    jd.setVisible(true);

                }
            });
            
            c.gridx = 3;
            JCheckBox jc = new JCheckBox();
            jc.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                            zombis = jc.isSelected();
                    }
            });
            this.add(jc, c);

            c.gridy = 3;
            c.gridx = 0;
            this.add(new JLabel("Automate Méchant : "), c);
            c.gridx = 1;
            c.gridwidth = 2;
            c.weightx = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            this.add(mechant, c);

            c.gridx = 0;
            c.gridy = 4;
            this.add(new JLabel("Automates Gentils : "), c);

            c.gridy = 5;
            c.gridheight = 1;
            c.gridwidth = 4;
            c.weighty = 1;
            c.fill = GridBagConstraints.BOTH;
            this.add(scrollP, c);

	}

	public boolean cool() {
		boolean bm,bp;
		if (mechant==null){
			bm=false;
		}
		else bm=mechant.cool();
		
		if(listePersos==null){
			bp=false;
		}
		else bp=listePersos.cool();
		
		return bm&&bp&&(maCouleur!=null);
	}
	
	List<String> Recup_Strings(){
		List<String> l=new LinkedList<>();
		int j;
		for(j=0;j<nbP;j++){
			l.add(listePersos.l.get(j).chemin.getText());
		}
		return l;
	}
        
        public String recupName() {
            return userName.getText();
        }

}
