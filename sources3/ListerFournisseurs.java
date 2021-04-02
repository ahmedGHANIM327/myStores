import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class ListerFournisseurs extends FenetrePersonnalisee {
	private Magasin magasin;
	private JLabel path;
	private JButton bBack = new BoutonPersonnalise("Retour");
	private JLabel instruction = new JLabel("\n Rechercher par nom");
	private JTextField barreRecherche = new JTextField();
	private JButton search = new BoutonPersonnalise("Search");
	private JTextArea resultatRecherche = new JTextArea();

	public ListerFournisseurs(Magasin magasin) {
		super();
		this.magasin = magasin;
		initComposant();
	}

	private void initComposant() {
		this.setLayout(new BorderLayout());
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.add(instruction, BorderLayout.CENTER);
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(bBack, BorderLayout.WEST);
		path = new JLabel(magasin.getNomMagasin() + " > Fournisseurs > Liste", SwingConstants.CENTER);
		panel1.add(path, BorderLayout.EAST);
		panel3.add(panel1, BorderLayout.NORTH);
		bBack.addActionListener(new ActionBack());
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(barreRecherche, BorderLayout.CENTER);
		panel2.add(search, BorderLayout.EAST);
		panel3.add(panel2, BorderLayout.SOUTH);
		this.add(panel3, BorderLayout.NORTH);
		JPanel panel4 = new JPanel(new BorderLayout());
		panel4.add(resultatRecherche, BorderLayout.CENTER);
		this.add(panel4, BorderLayout.CENTER);
		resultatRecherche.setEditable(false);
		search.addActionListener(new ActionSearch());
		;
	}

	/* Listener du bouton retour */
	class ActionBack implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			dispose();
			new MenuFournisseurs(magasin);
		}
	}

	/* Listener du bouton search */
	class ActionSearch implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				if (!barreRecherche.getText().equals("")) {
					Fournisseur fournisseur = magasin.getFournisseur(barreRecherche.getText());
					resultatRecherche.setText("\n\n    Id : " + fournisseur.getIdFournisseur() + "\n\n    Nom  :  "
							+ fournisseur.getNomFournisseur() + "\n\n    Adresse  :  "
							+ fournisseur.getAdresseFournisseur() + "\n\n    Adresse Mail  :  "
							+ fournisseur.getMailFournisseur());
				}
			} catch (IOException e) {
				resultatRecherche.setText(" Veuillez réessayer ultérieurement !");
			} catch (FournisseurInconnuException e) {
				resultatRecherche.setText(" Ce fournisseur est inconnu !");
			}
		}
	}
}
