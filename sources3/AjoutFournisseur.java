import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;



public class AjoutFournisseur extends FenetrePersonnalisee {

		private Magasin magasin;
		private JButton bBack = new BoutonPersonnalise("Retour");
		private JLabel path;
		private JButton ajouter = new BoutonPersonnalise("Ajouter");
		private JLabel id_fournisseur = new JLabel("Id Fournisseur :");
		private JTextField saisie_id = new JTextField();
		private JLabel nom = new JLabel("Nom :");
		private JTextField saisie_nom = new JTextField();
        private JLabel adresse = new JLabel("Adresse :");
        private JTextField saisie_adresse = new JTextField();
		private JLabel mail = new JLabel("Adresse Mail:");
        private JTextField saisie_mail = new JTextField();

		public AjoutFournisseur (Magasin magasin) {
			super();
			this.magasin = magasin;
			this.initComposant();
		}

		private void initComposant() {
			this.setLayout(new GridLayout(7,1));
            JPanel panel_haut = new JPanel();
            panel_haut.setLayout(new GridLayout(1,2));
			path = new JLabel(magasin.getNomMagasin() + " > Fournisseurs > Ajouter", SwingConstants.CENTER);
			path.setBounds(85, 0, 200, 30);
			panel_haut.add(path);
			bBack.setBounds(0, 0, 85, 30);
			panel_haut.add(bBack);
			bBack.addActionListener(new ActionBack());

            this.add(panel_haut);
            this.add(new JPanel());
            this.add(new JPanel());
            JPanel panel_info = new JPanel();
            panel_info.setLayout(new GridLayout(4,2));
            /* Saisie id_fournisseur */
			id_fournisseur.setBounds(0, 50, 300, 30);
			panel_info.add(id_fournisseur);
			saisie_id.setBounds(this.getWidth()/5, 80, 250, 30);
			panel_info.add(saisie_id);
            /* Saisie nom */
			nom.setBounds(0, 110, 300, 30);
			panel_info.add(nom);
			saisie_nom.setBounds(this.getWidth()/5, 140, 250, 30);
			panel_info.add(saisie_nom);
            /* Saisie adresse */
			adresse.setBounds(0, 170, 250, 30);
			panel_info.add(adresse);
			saisie_adresse.setBounds(this.getWidth()/5, 200, 250, 30);
			panel_info.add(saisie_adresse);
            /* Saisie mail*/
			mail.setBounds(0, 230, 250, 30);
		    panel_info.add(mail);
			saisie_mail.setBounds(this.getWidth()/5, 260, 250, 30);
			panel_info.add(saisie_mail);
            this.add(panel_info);
            this.add(new JPanel());
            this.add(new JPanel());
			ajouter.setBounds(0, this.getHeight() - 70, this.getWidth(), 30);
			this.add(ajouter);
			ajouter.addActionListener(new ActionAjouter());
		}
		

		class ActionBack implements ActionListener  {
	        public void actionPerformed(ActionEvent ev){
	                dispose();
	                new MenuFournisseurs(magasin);
	        }
	    }
		
		class ActionAjouter implements ActionListener {
	        public void actionPerformed(ActionEvent ev) {
	        	boolean valide = false;
	        	while (!valide) {
	        		try {
                        int id_fournisseur = Integer.parseInt(saisie_id.getText());
                        valide = true; 
                        Fournisseur fournisseur = new Fournisseur(id_fournisseur, saisie_nom.getText(), saisie_adresse.getText(), saisie_mail.getText());
	        			magasin.ajouterFournisseur(fournisseur);

	        		} catch(NumberFormatException e) {
                        JOptionPane.showMessageDialog(getComponent(0), "L'identifiant du fournisseur est un entier !", "Warning", JOptionPane.WARNING_MESSAGE);
	            			saisie_id.setText("10");
                   }
	        		// vider les cases
		        	finally {
                        saisie_id.setText("0");
		            	saisie_nom.setText("");
		            	saisie_adresse.setText("");
		            	saisie_mail.setText("");
                    }
	        	}
	        	
	        }
	    }
	}
