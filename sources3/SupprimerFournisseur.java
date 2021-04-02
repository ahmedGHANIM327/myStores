import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SupprimerFournisseur extends FenetrePersonnalisee {
	private Magasin magasin;
	private JLabel path;
	private JButton bBack = new BoutonPersonnalise("Retour");
	private JLabel instruction = new JLabel("\n Rechercher par Nom");
	private JTextField barreRecherche = new JTextField();
	private JButton delete = new BoutonPersonnalise("Supprimer");
	private JTextArea resultatRecherche = new JTextArea();
	
	
	public SupprimerFournisseur(Magasin magasin) {
		super();
		this.magasin = magasin;
		initComposant();
	}

	private void initComposant() {
		this.setLayout(new BorderLayout());
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.add(instruction,BorderLayout.CENTER);
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(bBack, BorderLayout.WEST);
        path = new JLabel(magasin.getNomMagasin() + " > Fournisseurs > Supprimer", SwingConstants.CENTER);        
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
                new MenuFournisseurs(magasin);
        }
    }
    /*Listener du bouton Delete après la saisie du nom du fournisseur */
	class ActionDelete implements ActionListener  {
        public void actionPerformed(ActionEvent ev){
            String nom_fournisseur = barreRecherche.getText();
            boolean present = magasin.supprimerFournisseur(nom_fournisseur);
            if (present) {
                resultatRecherche.setText("\n\n" + nom_fournisseur + " a bien été supprimé de la base des données !" );
                
            } else {
                resultatRecherche.setText("\n\n" + nom_fournisseur + " n'est pas enregistré dans la base des données !" );
            }
        }
    }
}

