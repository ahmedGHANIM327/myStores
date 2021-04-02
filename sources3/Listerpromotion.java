import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Listerpromotion extends FenetrePersonnalisee {
	private Magasin magasin;
	private JLabel path;
	private JButton bBack = new BoutonPersonnalise("Retour");
	private JLabel instruction = new JLabel("\n Rechercher par Id Article");
	private JTextField barreRecherche = new JTextField();
	private JButton search = new BoutonPersonnalise("Search");
	private JTextArea resultatRecherche = new JTextArea();

	public Listerpromotion(Magasin magasin) {
		super();
		this.magasin = magasin;
		path = new JLabel(magasin.getNomMagasin() + " > Promotions > Liste", SwingConstants.CENTER);
		initComposant();
	}

	private void initComposant() {
		this.setLayout(new BorderLayout());
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.add(instruction, BorderLayout.CENTER);
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(bBack, BorderLayout.WEST);
		panel1.add(path, BorderLayout.EAST);
		panel3.add(panel1, BorderLayout.NORTH);
		bBack.addActionListener(new ActionBack());
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(barreRecherche, BorderLayout.CENTER);
		panel2.add(search, BorderLayout.EAST);
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

	/* Listener du bouton retour */
	class ActionBack implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			dispose();
			new PromotionOption(magasin);
		}
	}

	/* Listener du bouton search */
	class ActionSearch implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try{
					int Id = Integer.parseInt(barreRecherche.getText());
					Promotions promo = magasin.searchPromo(barreRecherche.getText());
					if (promo == null) {
						resultatRecherche.setText("\n\n\n  Aucun resultat trouvé !");
					} else {
						resultatRecherche.setText("\n\n    Id Article : " + promo.getIdArticle()
								+ "\n\n    Pourcentage  :  " + promo.getPromo());
					}
			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(getComponent(0), "L'Id doit être saisi en chiffres !", "Warning", JOptionPane.WARNING_MESSAGE);
				barreRecherche.setText("");
	 		}
		}
	}
}
