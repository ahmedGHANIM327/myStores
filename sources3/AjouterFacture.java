import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;

public class AjouterFacture extends FenetrePersonnalisee {
	// Element de la fenetre
	private Color c = new Color(110, 90, 120); // couleur des boutons
	private Object[][] data = {};
	private JFrame ecran = new JFrame("Attention"); // fenetre pour les message d'erreurs signalés
	private DefaultTableModel tableur;
	private JTable table;
	private JScrollPane n;
	private JButton plus; // bouton ajouter article à la facture
	private JLabel insert; // = Ajouter Article
	private JPanel pa; // plan south
	private JPanel planfacture = new JPanel(); // plan nord
	private JPanel plan = new JPanel();
	private JButton finaliser = new BoutonPersonnalise("Finaliser"); // finaliser la commande
	private JButton retour = new BoutonPersonnalise("retour");
	private JLabel chemin; // nom du magasin > factures
	private JLabel id_facture = new JLabel("Id Facture :"); // le numéro de la facture
	private JTextField saisie_idfacture = new JTextField(); // zone d'ecreture du numéro de la facture
	private JLabel total = new JLabel("Total à payer :"); // La somme à payer
	private JLabel totalfacture = new JLabel(); // zone d'ecreture de la somme à payer
	private JButton fidelite = new BoutonPersonnalise("Avez-vous une carte de fidelité ?"); // tester si le clent est fidèle pour
																					// profiter de son score
	private String location = "ListeMagasins/"; // localisation des magasins
	private Magasin magasin; // magasin de travail
	private String totalpayer = "0";

	public AjouterFacture(Magasin magasin) {
		super();
		// caracteristique de la fenetre
		//this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(800, 1000);
		
		// plan du nord
		planfacture.setPreferredSize(new Dimension(0, 50));
		planfacture.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		planfacture.setLayout(new GridLayout(2, 2));
		
		// bouton retour
		
		retour.addActionListener(new ActionBack());
		planfacture.add(retour);
		
		// plan du path
		chemin = new JLabel(magasin.getNomMagasin() + " > Factures > AjouterFacture");
		chemin.setHorizontalAlignment(JLabel.CENTER);
		chemin.setVerticalAlignment(JLabel.CENTER);
		planfacture.add(chemin);
		
		// numero de la factue
		planfacture.add(id_facture);
		planfacture.add(saisie_idfacture);
		this.add(planfacture, BorderLayout.NORTH);
		
		// plan du sud
		plan.setPreferredSize(new Dimension(0, 120));
		plan.setLayout(new GridLayout(3, 1));
		
		/// add additional items
		pa = new JPanel();
		pa.setPreferredSize(new Dimension(0, 60));
		pa.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		
		// add(pa,BorderLayout.SOUTH);
		plus = new BoutonPersonnalise("+");
		insert = new JLabel("Ajouter un Article");
		pa.setLayout(new GridLayout(2, 2));
		pa.add(insert, BorderLayout.NORTH);
		
		// bouton d'ajout d'article dans la facture
		
		pa.add(plus, BorderLayout.NORTH);
		pa.add(total);
		totalfacture.setHorizontalAlignment(JLabel.CENTER);
		totalfacture.setVerticalAlignment(JLabel.CENTER);
		totalfacture.setText(totalpayer + " £");
		pa.add(totalfacture);
		plan.add(pa);
		
		// bouton de traitement si le client est fidéle
		fidelite.addActionListener(new ActionFidele());
		plan.add(fidelite);
		// bouton creation de la facture
		plan.add(finaliser);
		add(plan, BorderLayout.SOUTH);
		// Action du bouton plus pour ajouter article à la facture
		plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] article; // les caracteristique de l'article à ajouter
				String Id; // Id de l'article que l'utilisateur veut ajouter
				String Quantite; // La quantité de l'article
				try {
					Id = JOptionPane.showInputDialog(null, " L'Id de l'article", "ajouter une ligne",
							JOptionPane.YES_NO_CANCEL_OPTION);
					article = Article(Integer.parseInt(Id));
					while (article == null) {
						Id = JOptionPane.showInputDialog(null, "Article introuvable! ressayer", "ajouter une ligne",
								JOptionPane.YES_NO_CANCEL_OPTION);
						article = Article(Integer.parseInt(Id));
					}
					Quantite = JOptionPane.showInputDialog(null, " La quantite ", "ajouter une ligne",
							JOptionPane.YES_NO_CANCEL_OPTION);
					while (Integer.parseInt(Quantite) > Integer.parseInt(article[2])) {
						Quantite = JOptionPane.showInputDialog(null, " La quantite doit etre < " + article[2],
								"ajouter une ligne", JOptionPane.YES_NO_CANCEL_OPTION);
					}
					//gererQuantiteStock(Integer.parseInt(Id), Integer.parseInt(Quantite)); // on soustraie la quantite
																							// acheté du stock
					String prixtotal = "" + ((Float.parseFloat(article[3])
							- (Float.parseFloat(article[4]) / 100) * Float.parseFloat(article[3]))
							* Float.parseFloat(Quantite));
					if (table.getSelectedRow() != -1) {
						tableur.insertRow(table.getSelectedRow(),
								new String[] { Id, article[1], Quantite, article[3], prixtotal });
						table.setRowSelectionInterval(table.getSelectedRow(), table.getSelectedRow());
					} else
						tableur.addRow(new String[] { Id, article[1], Quantite, article[3], prixtotal });
					article = null;
					totalpayer = "" + (Float.parseFloat(totalpayer) + Float.parseFloat(prixtotal));
					totalfacture.setText(totalpayer + " £");
				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(ecran, "case vide", " Attention ", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		// Les données du tableau
		// data = {};

		// Les titres des colonnes
		String title[] = { "Id Article", "Nom Article", "Quantite", "Prix unité", "prix total" };
		tableur = new DefaultTableModel(data, title);
		table = new JTable(tableur) {
		};
		n = new JScrollPane(table);
		add(n, BorderLayout.CENTER);
		this.magasin = magasin;
		location = location + magasin.getNomMagasin() + "/";
		gererFinaliser();
	}

	// Fonction qui retourne les caracteristique d'un article s'il existe =
	// [Idarticle,NomArticle,Quantite dans stock,prix unite , pourcentage de
	// promotion]
	public String[] Article(int Id) {
		try {
			String[] resultat;
			String Stock;
			String test;
			File fichierPromo = new File(location + "Promotions.txt");
			BufferedReader readerPromo = new BufferedReader(new FileReader(fichierPromo));
			String lignePromo = null;
			File fichierStock = new File(location + "Stock.txt");
			BufferedReader reader = new BufferedReader(new FileReader(fichierStock));
			String ligneStock = null;
			boolean trouve = false;
			ligneStock = reader.readLine();
			// test = ligneStock.split("@")[0];
			while (ligneStock != null & !trouve) {
				test = ligneStock.split("@")[0];
				if (test.equals("" + Id)) {
					trouve = true;
				} else {
					ligneStock = reader.readLine();
					// test = ligneStock.split("@")[0];
				}
			}
			reader.close();
			if (ligneStock == null) {
				return null;
			} else {
				Stock = ligneStock;
			}
			trouve = false;
			lignePromo = readerPromo.readLine();
			while (lignePromo != null & !trouve) {
				test = lignePromo.split("@")[0];
				if (test.equals("" + Id)) {
					trouve = true;
				} else {
					lignePromo = readerPromo.readLine();
				}
			}
			readerPromo.close();
			// test de ligne stock//////////////////////////////////////////////////
			if (lignePromo == null) {
				Stock = Stock + "@0";
				resultat = Stock.split("@");
				return resultat;
			} else {
				String[] line = lignePromo.split("@");
				Stock = Stock + "@" + line[1];
				resultat = Stock.split("@");
				return resultat;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// gestion de la quantite de l'article qu'on a ajoute dans la facture
	// Id : Id de l'article qu'on a ajouté
	// Quantite : la quantite qu'on a acheté.
	public void gererQuantiteStock(int Id, int Quantite) {
		try {
			String test;
			String[] lignetest;
			File fichier = new File(location + "Stock.txt");
			File fichierTemp = new File(location + "Stock_temp.txt");
			BufferedReader reader = new BufferedReader(new FileReader(fichier));
			PrintWriter pw = new PrintWriter(new FileWriter(fichierTemp));
			String ligne = reader.readLine();
			while (ligne != null) {
				lignetest = ligne.split("@");
				test = lignetest[0];
				if (!test.equals("" + Id)) {
					pw.println(ligne);
					pw.flush();
				} else {
					if (Integer.parseInt(lignetest[2]) - Quantite > 0) {
						ligne = null;
						ligne = lignetest[0] + "@" + lignetest[1] + "@" + ""
								+ (Integer.parseInt(lignetest[2]) - Quantite) + "@" + lignetest[3];
						pw.println(ligne);
						pw.flush();
						ligne = null;
					}
				}
				ligne = reader.readLine();
			}
			pw.close();
			reader.close();
			fichier.delete();
			fichierTemp.renameTo(fichier);
			fichierTemp.delete();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	// Gestion de la bouton finaliser
	public void gererFinaliser() {
		finaliser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File fichier = new File(location + "Factures/" + saisie_idfacture.getText());
					FileWriter fw = new FileWriter(fichier, true);
					PrintWriter pw = new PrintWriter(fw);
					int nbligne = table.getRowCount();
					int nbColone = table.getColumnCount();
					String ligne = new String();
					for (int i = 0; i < nbligne; i++) {
						gererQuantiteStock(Integer.parseInt(""+table.getValueAt(i, 0)), Integer.parseInt(""+table.getValueAt(i, 2)));
						for (int j = 0; j < nbColone; j++) {
							ligne = ligne + table.getValueAt(i, j) + "@";
						}
						magasin.ajouterCommande(ligne.split("@"), saisie_idfacture.getText());
					}
					pw.println("---> Total à payer : " + totalfacture.getText());
					pw.flush();
					dispose();
					new FacturesOption(magasin);
				} catch (IOException a) {
					JOptionPane.showMessageDialog(ecran, "Veuillez saisir le numéro de facture", " Attention ", JOptionPane.WARNING_MESSAGE);

				}

			}
		});
	}

    /*Listener du bouton Retour */
	class ActionBack implements ActionListener  {
        public void actionPerformed(ActionEvent ev){
                dispose();
                new FacturesOption(magasin);
        }
	}

    /*Listener du bouton fidelite */
	class ActionFidele implements ActionListener  {
        public void actionPerformed(ActionEvent ev){
		String Id;
		try {	
			String score = totalfacture.getText().split(" ")[0];
			Id = JOptionPane.showInputDialog(null, " L'Id de la carte de fidelite", "Client fidele ?",
							JOptionPane.YES_NO_CANCEL_OPTION);
			int id = Integer.parseInt(Id);
			CarteFidelite carte = magasin.searchCarteFidelite(Id);
			if (carte == null) {
					JOptionPane.showMessageDialog(ecran, "Client non fidele", " Attention ", JOptionPane.WARNING_MESSAGE);
			} else {
				int option = JOptionPane.showConfirmDialog(null, "Si vous voulez ajouter des points cliquez sur OUI , sinon NON ?", "Arrêt de l'animation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(option == JOptionPane.YES_OPTION){
					magasin.supprimerCarteFidelite(Id);
					int point = (int)(Float.parseFloat(score)/100);  // chaque 100 euro correspond à 1 point
					carte.AjouterPoint(point);
					magasin.ajouterCarteFidelite(carte);
				}else if (option == JOptionPane.NO_OPTION){
					int euroScore = carte.getScore()/10;  // chaque 10 points correspond à 1 euro
					magasin.supprimerCarteFidelite(Id);
					if(euroScore <= (int)(Float.parseFloat(score)) ){
						carte.setScore(0);
						magasin.ajouterCarteFidelite(carte);
						int scorefinal = (int)(Float.parseFloat(score))-euroScore;
						totalfacture.setText(""+scorefinal+" £");
					}else{
						int scorefinal = (int)(carte.getScore())-(int)(Float.parseFloat(score))*10;
						carte.setScore(scorefinal);
						magasin.ajouterCarteFidelite(carte);
						totalfacture.setText(""+0+ "£");
					}
				}
			}
		} catch (NumberFormatException a) {
			JOptionPane.showMessageDialog(ecran, "case vide", " Attention ", JOptionPane.WARNING_MESSAGE);
		}
		
        }
	}

}
