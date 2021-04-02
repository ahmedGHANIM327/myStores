import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SupprimerEmploye extends FenetrePersonnalisee {
	private Magasin magasin;
	private JLabel path;
	private JButton bBack = new BoutonPersonnalise("Retour");
	private JLabel instruction = new JLabel("\n Rechercher par Nom Prenom");
	private JTextField barreRecherche = new JTextField();
	private JButton delete = new BoutonPersonnalise("Supprimer");
	private JTextArea resultatRecherche = new JTextArea();
	
	public SupprimerEmploye(Magasin magasin) {
		super();
		this.magasin = magasin;
		path = new JLabel(magasin.getNomMagasin() + " > Employés > Supprimer", SwingConstants.CENTER);
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
		panel2.add(delete,  BorderLayout.EAST);
        delete.addActionListener(new ActionDelete());
		panel3.add(panel2, BorderLayout.SOUTH);
		this.add(panel3, BorderLayout.NORTH);
		JPanel panel4 = new JPanel(new BorderLayout());
		panel4.add(resultatRecherche, BorderLayout.CENTER);
		this.add(panel4, BorderLayout.CENTER);
		resultatRecherche.setEditable(false);
        resultatRecherche.setFont(new java.awt.Font("Serif", 1, 14));
        resultatRecherche.setForeground(Color.BLUE);
		

	}
	
    /*Listener du bouton Retour */
	class ActionBack implements ActionListener  {
        public void actionPerformed(ActionEvent ev){
                dispose();
                new EmployeOptions(magasin);
        }
    }
    /*Listener du bouton Delete après la saisie du nom et prenom de l'employé */
	class ActionDelete implements ActionListener  {
        public void actionPerformed(ActionEvent ev){
            String[] infos = barreRecherche.getText().split(" ");
            if (infos.length == 2) {
                if (magasin.contientEmploye(infos[0], infos[1])) {

                    try{
                        magasin.supprimerEmploye(infos[0], infos[1]);
                        resultatRecherche.setText("\n\n" + infos[0] + "  " + infos[1] + " a bien été supprimé de la base des données !" );
                    } catch (IOException e) {
                        resultatRecherche.setText("Erreur accés à la base de données! Veuillez réessayer ultérieurement .");
                    }
                
                } else {
                    resultatRecherche.setText("\n\n" + infos[0] + "  " + infos[1] + " n'est pas enregistré dans la base des données !" );
                }
            }
        }
    }
}

