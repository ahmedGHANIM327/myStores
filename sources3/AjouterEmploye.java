import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.Pattern;

public class AjouterEmploye extends FenetrePersonnalisee{
        /* le magasin où l'employé travaille*/
		private Magasin magasin;
        /* les différentes composantes de la fenêtre */
		private JButton bBack = new BoutonPersonnalise("Retour");
		private JLabel path;
		private JButton ajouter = new BoutonPersonnalise("Ajouter");
		private JLabel nomLabel = new JLabel("Nom :");
		private JTextField saisieNom = new JTextField();
		private JLabel prenomLabel = new JLabel("Prenom :");
		private JTextField saisiePrenom = new JTextField();
		private JLabel sexeLabel = new JLabel("Sexe :");
		String[] liste =  {"Femme", "Homme"};
		private JComboBox<String> sexe = new JComboBox<String>(liste);
		private JLabel posteLabel = new JLabel("Poste :");
		private JTextField saisiePoste = new JTextField();
		private JLabel salaireLabel = new JLabel("Salaire :");
		private JTextField saisieSalaire = new JTextField();

		
        /*constructeur*/
		public AjouterEmploye(Magasin magasin) {
			super();
			this.magasin = magasin;
			this.initComposant();
		}

		private void initComposant() {
			this.setLayout(null);
			path = new JLabel(magasin.getNomMagasin() + " > Employés > Ajouter", SwingConstants.CENTER);
			path.setBounds(85, 0, 200, 30);
			this.add(path);
			bBack.setBounds(0, 0, 85, 30);
			this.add(bBack);
			bBack.addActionListener(new ActionBack());
			nomLabel.setBounds(0, 50, 300, 30);
			this.add(nomLabel);
			saisieNom.setBounds(this.getWidth()/5, 80, 250, 30);
			this.add(saisieNom);
			prenomLabel.setBounds(0, 110, 300, 30);
			this.add(prenomLabel);
			saisiePrenom.setBounds(this.getWidth()/5, 140, 250, 30);
			this.add(saisiePrenom);
			sexeLabel.setBounds(0, 170, 250, 30);
			this.add(sexeLabel);
			sexe.setBounds(this.getWidth()/5, 200, 250, 30);
			this.add(sexe);
			posteLabel.setBounds(0, 230, 250, 30);
			this.add(posteLabel);
			saisiePoste.setBounds(this.getWidth()/5, 260, 250, 30);
			this.add(saisiePoste);
			salaireLabel.setBounds(0, 290, 250, 30);
			this.add(salaireLabel);
			saisieSalaire.setBounds(this.getWidth()/5, 320, 250, 30);
			this.add(saisieSalaire);
			ajouter.setBounds(0, this.getHeight() - 60, this.getWidth(), 30);
			this.add(ajouter);
			ajouter.addActionListener(new ActionAjouter());
		}
		/*Listener du bouton Retour*/
		class ActionBack implements ActionListener  {
	        public void actionPerformed(ActionEvent ev){
	                dispose();
	                new EmployeOptions(magasin);
	        }
	    }
		/*listener du bouton Ajouter */
		class ActionAjouter implements ActionListener  {
	        public void actionPerformed(ActionEvent ev){
	        	boolean valideSalaire, valideNom, validePrenom ;
                validePrenom = false;
                valideNom = false;
                valideSalaire = false;
                // vérifier que le nom est bien en caractères alphabétiques
                valideNom = Pattern.matches("[a-zA-Zàâäéèêëîïôöûüù]{1,}?[ -]?[a-zA-Zàâäéèêëîïôöûüù]*", saisieNom.getText());
                if (!valideNom)  {
                    JOptionPane.showMessageDialog(getComponent(0), 
                                    "Le nom doit être saisi en caractères !", 
                                    "Warning", JOptionPane.WARNING_MESSAGE);
                    //vider la case
	        		saisieNom.setText("");
                }
                // vérifier que le prénom est bien en caractères alphabétiques en utilisant les expressions régulieres
                validePrenom = Pattern.matches("[a-zA-Z àâäéèêëîïôöûüù]{1,}?[ -]?[a-zA-Z àâäéèêëîïôöûüù]*",saisiePrenom.getText());
                if (!validePrenom)  {//|| saisieNom.getText().equals("")
                    JOptionPane.showMessageDialog(getComponent(0), 
                                    "Le prénom doit être saisi en caractères !", 
                                    "Warning", JOptionPane.WARNING_MESSAGE);
                    //vider la case
	        		saisiePrenom.setText("");
                }
                float salaire = (float)0.0;
	        	/*verifier que le salaire saisi est bien en chiffres*/
	        	try {
	        	    salaire = Float.parseFloat(saisieSalaire.getText());
	        	    valideSalaire = true;
	        	} catch(NumberFormatException e) {
	        	    JOptionPane.showMessageDialog(getComponent(0), 
                                    "Le salaire doit être saisi en chiffres !", 
                                    "Warning", JOptionPane.WARNING_MESSAGE);
                    //vider la case
	        		saisieSalaire.setText("");
	        	}

				if (valideNom && validePrenom && valideSalaire) {
                    Employe employe = new Employe(saisieNom.getText(), 
                                        saisiePrenom.getText(), 
                                        (String) sexe.getSelectedItem(), 
                                        saisiePoste.getText(),salaire );
                    try {
	        	        magasin.ajouterEmploye(employe);
                    } catch(IOException e) {
                        JOptionPane.showMessageDialog(getComponent(0), 
                                    "Erreur d'accès à la base de données! Veuillez réessayer ultérieurement .", 
                                    "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
	        		// vider les cases
		        	saisieNom.setText("");
		        	saisiePrenom.setText("");
		        	saisiePoste.setText("");
		        	saisieSalaire.setText("");
	        	}
	        	
	        }
	    }
	}
