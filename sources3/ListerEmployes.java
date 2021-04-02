import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ListerEmployes extends FenetrePersonnalisee{
	private Magasin magasin;
	private JLabel path;
	private JButton bBack = new BoutonPersonnalise("Retour");
	private JLabel instruction = new JLabel("\n Rechercher par: Nom Prenom/ nom / prenom/ poste");
	private JTextField barreRecherche = new JTextField();
	private JButton search = new BoutonPersonnalise("Search");
	private JTextArea resultatRecherche = new JTextArea();
	
	public ListerEmployes(Magasin magasin) {
		super();
		this.magasin = magasin;
		path = new JLabel(magasin.getNomMagasin() + " > Employés > Liste", SwingConstants.CENTER);
		initComposant();
	}

	private void initComposant() {
		this.setLayout(new BorderLayout());
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.add(instruction,BorderLayout.CENTER);
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(bBack, BorderLayout.WEST);
		panel1.add(path, BorderLayout.CENTER);
		panel3.add(panel1, BorderLayout.NORTH);
		bBack.addActionListener(new ActionBack());
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(barreRecherche,  BorderLayout.CENTER);
		panel2.add(search,  BorderLayout.EAST);
        search.addActionListener(new ActionSearch());
		panel3.add(panel2, BorderLayout.SOUTH);
		this.add(panel3, BorderLayout.NORTH);
		JPanel panel4 = new JPanel(new BorderLayout());
		panel4.add(resultatRecherche, BorderLayout.CENTER);
		this.add(panel4, BorderLayout.CENTER);
		resultatRecherche.setEditable(false);
        resultatRecherche.setFont(new java.awt.Font("Serif", 1, 14));
        resultatRecherche.setForeground(Color.BLUE);
		
	}

    /*Listener du bouton retour */
	class ActionBack implements ActionListener  {
        public void actionPerformed(ActionEvent ev){
                dispose();
                new EmployeOptions(magasin);
        }
    }

    /*Listener du bouton search */
	class ActionSearch implements ActionListener  {
        public void actionPerformed(ActionEvent ev){
            if ( !barreRecherche.getText().equals("")) {
                String[] recherche = barreRecherche.getText().split(" ");
                if (recherche.length == 2) {//recherche par nom et prenom
		            Employe employe = null;
                    try{
                        employe = magasin.searchEmploye(recherche[0], recherche[1]);
                    } catch (IOException e) {
                        resultatRecherche.setText("Erreur accés à la base de données! Veuillez réessayer ultérieurement .");
                    }
                    if (employe == null) {
                        resultatRecherche.setText("\n\n\n  Aucun resultat trouvé !");
                    } else {
                        resultatRecherche.setText(employe.toString());
                    }
                }
                if (recherche.length == 1) {
                    try {ArrayList<Employe> list = magasin.searchEmploye(recherche[0]);
                        StringBuilder s = new StringBuilder();
                        for (Employe e : list) {
                            s.append(e.getNom()+" "+e.getPrenom() + "\n");
                        }
                        resultatRecherche.setText(s.toString());
                    } catch(IOException e) {
                        resultatRecherche.setText("Erreur accés à la base de données! Veuillez réessayer ultérieurement .");
                    }
                }
            }   
        }
    }
}
