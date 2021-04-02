import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AjouterPromotion extends FenetrePersonnalisee{
        /* le magasin où l'employé travaille*/
		private Magasin magasin;
        /* les différentes composantes de la fenêtre */
		private JButton bBack = new BoutonPersonnalise("Retour");
		private JLabel path;
		private JButton ajouter = new BoutonPersonnalise("Ajouter");
		private JLabel IdArticle = new JLabel("Id de l'article :");
		private JTextField saisieIdArticle = new JTextField();
		private JLabel Pourcentage = new JLabel("Pourcentage de promotion :");
		private JTextField saisiePourcentage = new JTextField();
		
        /*constructeur*/
		public AjouterPromotion(Magasin magasin) {
			super();
			this.magasin = magasin;
			this.initComposant();
		}

		private void initComposant() {
			this.setLayout(null);
			path = new JLabel(magasin.getNomMagasin() + " > Promotion > Ajouter", SwingConstants.CENTER);
			path.setBounds(85, 0, 200, 30);
			this.add(path);
			bBack.setBounds(0, 0, 85, 30);
			this.add(bBack);
			bBack.addActionListener(new ActionBack());
			IdArticle.setBounds(0, 50, 300, 30);
			this.add(IdArticle);
			saisieIdArticle.setBounds(25, 80, 250, 30);
			this.add(saisieIdArticle);
			Pourcentage.setBounds(0, 110, 300, 30);
			this.add(Pourcentage);
			saisiePourcentage.setBounds(25, 140, 250, 30);
			this.add(saisiePourcentage);
			ajouter.setBounds(0, this.getHeight() - 100, this.getWidth(), 50);
			this.add(ajouter);
			ajouter.addActionListener(new ActionAjouter());
		}
		/*Listener du bouton Retour*/
		class ActionBack implements ActionListener  {
	        public void actionPerformed(ActionEvent ev){
	                dispose();
	                new PromotionOption(magasin);
	        }
	    }
		/*listener du bouton Ajouter */
		class ActionAjouter implements ActionListener  {
	        public void actionPerformed(ActionEvent ev){
	        	boolean valide = false;
	        	/*verifier que le salaire saisi est bien en chiffres*/
	        	try {
					int IdArticle = Integer.parseInt(saisieIdArticle.getText());
	        			float pourcentage = Float.parseFloat(saisiePourcentage.getText());
					if(pourcentage>100){
						pourcentage= 100;
					} else if(pourcentage<0) {
						pourcentage = 0;
					}
	        			valide = true;
	        			Promotions promo = new Promotions(IdArticle,pourcentage);
	        			magasin.ajouterPromotion(promo);
	        		} catch(NumberFormatException e) {
	        			JOptionPane.showMessageDialog(getComponent(0), "L'Id et le pourcentage doit être saisi en chiffres !", "Warning", JOptionPane.WARNING_MESSAGE);
	        			saisieIdArticle.setText("");
		        		saisiePourcentage.setText("");
	        		}
				if (valide) {
	        		// vider les cases
		        	saisieIdArticle.setText("");
		        	saisiePourcentage.setText("");
	        		}
	        	
	        }
	    }
	}
