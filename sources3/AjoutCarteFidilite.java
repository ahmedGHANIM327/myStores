import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AjoutCarteFidilite extends FenetrePersonnalisee{
    private Magasin magasin;
	private CarteFidelite CarteFidelite;
	private JButton bBack = new BoutonPersonnalise("Retour");
	private JLabel path;
	private JButton ajouter = new BoutonPersonnalise("Ajouter");
	
	private JLabel IdFidelite = new JLabel("Id Carte de fidelité :");
	private JTextField saisie_id = new JTextField();
	
	private JLabel Score = new JLabel("le score :");
	private JTextField saisie_score = new JTextField();
	
	
	
	Color c = new Color(110,90,120);
	
	public AjoutCarteFidilite(Magasin magasin) {
		    super();
		    this.magasin = magasin;
			this.initComposant();
		}

		private void initComposant() {
			this.setLayout(null);
			path = new JLabel(magasin.getNomMagasin() + " > Carte de fidilité > Ajouter", SwingConstants.CENTER);
			path.setBounds(85, 0, 200, 30);
			this.add(path);
			bBack.setBounds(0, 0, 85, 30);
			this.add(bBack);
			bBack.addActionListener(new ActionBack());
			IdFidelite.setBounds(0, 50, 300, 30);
			this.add(IdFidelite);
			saisie_id.setBounds(25, 80, 250, 30);
			this.add(saisie_id);
			Score.setBounds(0, 110, 300, 30);
			this.add(Score);
			saisie_score.setBounds(25, 140, 250, 30);
			this.add(saisie_score);
			ajouter.setBounds(0, 550, this.getWidth(), 50);
			this.add(ajouter);
			ajouter.addActionListener(new ActionAjouter());
		}
		/*Listener du bouton Retour*/
		class ActionBack implements ActionListener  {
	        public void actionPerformed(ActionEvent ev){
	                dispose();
	                new MenuCarte(magasin);
	        }
	    }
		/*listener du bouton Ajouter */
		class ActionAjouter implements ActionListener  {
	        public void actionPerformed(ActionEvent ev){
	        	boolean valide = false;
	        	/*verifier que le salaire saisi est bien en chiffres*/
	        	try {
					int IdFidelite = Integer.parseInt(saisie_id.getText());
	        		int score = Integer.parseInt(saisie_score.getText());
					if(score<0) {
						score = 0;
					}
	        			valide = true;
	        			CarteFidelite Carte = new CarteFidelite(IdFidelite,score);
                        		Carte.AjouterPoint(score);
	        			magasin.ajouterCarteFidelite(Carte);
	        		} catch(NumberFormatException e) {
	        			JOptionPane.showMessageDialog(getComponent(0), "L'Identifiant et le score doit être saisi en chiffres !", "Warning", JOptionPane.WARNING_MESSAGE);
	        			saisie_id.setText("");
		        		saisie_score.setText("");
	        		}
				if (valide) {
	        		// vider les cases
		        	saisie_id.setText("");
		        	saisie_score.setText("");
	        		}
	        	
	        }
	    }
	}
