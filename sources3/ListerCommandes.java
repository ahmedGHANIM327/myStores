import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ListerCommandes extends FenetrePersonnalisee {
	private Magasin magasin;
	private JLabel path;
	private JButton bBack = new BoutonPersonnalise("Retour");
	private JLabel instruction = new JLabel("\n Rechercher par fournisseur");
	private JTextField barreRecherche = new JTextField();
	private JButton search = new BoutonPersonnalise("Search");
	private JTextField resultatRecherche = new JTextField();
	DefaultTableModel tableur;
	JTable table;
	JScrollPane scroll;
	JPanel panel4;

	public ListerCommandes(Magasin magasin) {
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
		path = new JLabel(magasin.getNomMagasin() + " > Commandes > Liste", SwingConstants.CENTER);
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
		resultatRecherche.setFont(new java.awt.Font("Serif", 1, 14));
		resultatRecherche.setForeground(Color.BLUE);
		search.addActionListener(new ActionSearch());
		;

		// Les données du tableau
		String[][] data = {};

		// Les titres des colonnes
		String title[] = { "Id Commande", "Fournisseur", "Ref Article", "Nom Article", "Quantité", "Prix par unité" };
		tableur = new DefaultTableModel(data, title);
		tableur = magasin.lister_commandes(tableur);
		table = new JTable(tableur) {
		};
		scroll = new JScrollPane(table);

		panel4.add(scroll, BorderLayout.CENTER);

	}

	class ActionBack implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			dispose();
			new MenuCommandes(magasin);
		}
	}

	/* Listener du bouton Search */
	class ActionSearch implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if (!barreRecherche.getText().equals("")) {
				ArrayList<Commander> commande = magasin.searchCommande(barreRecherche.getText());
				if (commande.size() == 0) {
					JOptionPane.showMessageDialog(getComponent(0), "\n\n\n  Aucun résultat trouvé !",
							"Information Commande", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String detail_commande = "\n Id:" + commande.get(0).getIdCommande() + "\n" + " Fournisseur: "
							+ commande.get(0).getFournisseur().getNomFournisseur();
					for (int i = 0; i < commande.size(); i++) {
						detail_commande = detail_commande + "\n" + "\n\n    Nom de l'article  :  "
								+ commande.get(i).getNomArticle() + "\n\n    Ref  :  " + commande.get(i).getref()
								+ "\n\n    Quantite  :  " + commande.get(i).getQuantite() + "\n\n    Prix    : "
								+ commande.get(i).getPrix() + "€";
					}
					JOptionPane.showMessageDialog(getComponent(0), detail_commande, "Infos Commande",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
}
