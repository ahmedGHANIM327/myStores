import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class Magasin {

	private String Nom;
	private String Adress;
	private String location = "ListeMagasins/";

	public Magasin(String name, String addr) {
		Nom = name;
		Adress = addr;

	}

	public void start() throws IOException {
		File file = new File(location + this.Nom);
		file.mkdir();
		File fileinfo = new File(location + this.Nom + "/InfoMagasin.txt");
		fileinfo.createNewFile();
		EntrerInfo(this.Nom + "@" + this.Adress, location + this.Nom + "/InfoMagasin.txt");
		File fileStock = new File(location + this.Nom + "/Stock.txt");
		fileStock.createNewFile();
		File fileFournisseur = new File(location + this.Nom + "/Fournisseur.txt");
		fileFournisseur.createNewFile();
		File filePromo = new File(location + this.Nom + "/Promotions.txt");
		filePromo.createNewFile();
		File fileEmploye = new File(location + this.Nom + "/Employes.txt");
		fileEmploye.createNewFile();
		File filefact = new File(location + this.Nom + "/Factures");
		filefact.mkdir();
		File filecomm = new File(location + this.Nom + "/Commandes/");
		filecomm.mkdir();
		File filecartefidel = new File(location + this.Nom + "/CarteFidelite.txt");
		filecartefidel.createNewFile();
	}

	public String getNomMagasin() {
		return this.Nom;
	}

	public void setNomMagasin(String name) {
		this.Nom = name;
	}

	public String getAddrMagasin() {
		return this.Adress;
	}

////Gestion des employes 

	/* Vérifier si un employé travail dans ce magasin ou pas */
	public boolean contientEmploye(String nom, String prenom) {
		try {
			File fichier = new File(location + this.Nom + "/Employes.txt");
			BufferedReader reader = new BufferedReader(new FileReader(fichier));
			String ligne = null;
			boolean trouve = false;
			ligne = reader.readLine();
			while (ligne != null & !trouve) {
				if (ligne.contains(capitalize(nom) + "@" + capitalize(prenom))) {
					return true;
				} else {
					ligne = reader.readLine();
				}
			}
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getNombreEmploye() throws IOException {
		int nb = 0;
		String line;
		File file = new File(location + this.Nom + "/Employes.txt");
		FileReader fw = new FileReader(file.getAbsoluteFile());
		BufferedReader bw = new BufferedReader(fw);
		while (bw.readLine() != null) {
			nb += 1;
		}
		String s = String.valueOf(nb);
		return s;
	}

	// mettre en majuscule la prmière letre de nom en paramètre d'entrée
	private String capitalize(String nom) {
		return Character.toUpperCase(nom.charAt(0)) + nom.substring(1);
	}

	/* Enregistrer un nouvel employé dans le fichhier employestxt */
	public void ajouterEmploye(Employe newEmploye) throws IOException {
		EntrerInfo(
				capitalize(newEmploye.getNom()) + "@" + capitalize(newEmploye.getPrenom()) + "@" + newEmploye.getSexe()
						+ "@" + newEmploye.getPoste() + "@" + String.valueOf(newEmploye.getSalaire()),
				location + this.Nom + "/Employes.txt");

	}

	/**
	 * Supprimer un employe à partir de son nom et prenom /* precondition :
	 * magasin.contientEmploye(nom, prenom) === true
	 */
	public void supprimerEmploye(String nom, String prenom) throws IOException {
		File fichier = new File(location + this.Nom + "/Employes.txt");
		File fichierTemp = new File(location + this.Nom + "/Employes_temp.txt");
		BufferedReader reader = new BufferedReader(new FileReader(fichier));
		PrintWriter pw = new PrintWriter(new FileWriter(fichierTemp));
		String ligne = reader.readLine();
		while (ligne != null) {
			if (!ligne.contains(capitalize(nom) + "@" + capitalize(prenom))) {
				pw.println(ligne);
				pw.flush();
			}
			ligne = reader.readLine();
		}
		pw.close();
		reader.close();
		fichier.delete();
		fichierTemp.renameTo(fichier);
	}

	/**
	 * Rechercher un employé dans employes.txt à partir de son Nom et Prenom return
	 * Employe ou Null s'il n'est pas enregistré
	 */
	public Employe searchEmploye(String nom, String prenom) throws IOException {
		File fichier = new File(location + this.Nom + "/Employes.txt");
		BufferedReader reader = new BufferedReader(new FileReader(fichier));
		String ligne = null;
		boolean trouve = false;
		ligne = reader.readLine();
		while (ligne != null & !trouve) {
			if (ligne.contains(capitalize(nom) + "@" + capitalize(prenom))
					|| ligne.contains(capitalize(prenom) + "@" + capitalize(nom))) {
				trouve = true;
			} else {
				ligne = reader.readLine();
			}
		}
		reader.close();
		if (ligne == null) {
			// employe non enregistre
			return null;
		} else {
			String[] line = ligne.split("@");
			return new Employe(line[0], line[1], line[2], line[3], Float.parseFloat(line[4]));
		}
	}

	/**
	 * Rechercher un employé dans employes.txt à partir de son Nom ou Prenom return
	 * ArrayList contenant tous les employes ayant "nom" comme prenom ou nom
	 */
	public ArrayList<Employe> searchEmploye(String nom) throws IOException {
		File fichier = new File(location + this.Nom + "/Employes.txt");
		BufferedReader reader = new BufferedReader(new FileReader(fichier));
		String ligne = null;
		ligne = reader.readLine();
		ArrayList<Employe> list = new ArrayList<Employe>();
		while (ligne != null) {
			if (ligne.contains(capitalize(nom)) || ligne.contains(nom)) {
				String[] line = ligne.split("@");
				list.add(new Employe(line[0], line[1], line[2], line[3], Float.parseFloat(line[4])));
			}

			ligne = reader.readLine();

		}
		reader.close();
		return list;
	}

/////// Gestion des promotion

	/* Enregistrer un nouvel employé dans le fichhier employestxt */
	public void ajouterPromotion(Promotions promo) {

		try {
			EntrerInfo(String.valueOf(promo.getIdArticle()) + "@" + String.valueOf(promo.getPromo()),
					location + this.Nom + "/Promotions.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Supprimer un employe à partir de son nom et prenom /* precondition :
	 * magasin.contientEmploye(nom, prenom) === true
	 */
	public void supprimerPromotion(String Id) {
		try {
			File fichier = new File(location + this.Nom + "/Promotions.txt");
			File fichierTemp = new File(location + this.Nom + "/Promotions_temp.txt");
			BufferedReader reader = new BufferedReader(new FileReader(fichier));
			PrintWriter pw = new PrintWriter(new FileWriter(fichierTemp));

			String ligne = reader.readLine();
			while (ligne != null) {
				if (!ligne.contains(Id)) {
					pw.println(ligne);
					pw.flush();
				}
				ligne = reader.readLine();
			}
			pw.close();
			reader.close();
			fichier.delete();
			fichierTemp.renameTo(fichier);
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	/**
	 * Rechercher un employé dans employes.txt à partir de son Nom et Prenom return
	 * Employe ou Null s'il n'est pas enregistré
	 */
	public Promotions searchPromo(String Id) {

		try {
			File fichier = new File(location + this.Nom + "/Promotions.txt");
			BufferedReader reader = new BufferedReader(new FileReader(fichier));
			String ligne = null;
			boolean trouve = false;
			ligne = reader.readLine();
			while (ligne != null & !trouve) {
				if (ligne.contains(Id)) {
					trouve = true;
				} else {
					ligne = reader.readLine();
				}
			}
			reader.close();
			if (ligne == null) {
				// employe non enregistre
				return null;
			} else {
				String[] line = ligne.split("@");
				return new Promotions(Integer.parseInt(Id), Float.parseFloat(line[1]));
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	

	/* Ajouter la ligne "ligne" dans le fichier qui se trouve dans "loca" */
	public void EntrerInfo(String ligne, String loca) throws IOException {
		try {

			File file = new File(loca);

			// créer le fichier s'il n'existe pas
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(ligne);
			pw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

/// Gestion des commandes

	/* Ajouter la commande à Commandes.txt */
	public void ajouterCommande(Commander la_commande) {
		try {
			EntrerInfo(
					la_commande.getNomArticle() + " & " + la_commande.getref() + " & " + la_commande.getQuantite()
							+ " & " + la_commande.getPrix(),
					location + this.Nom + "/Commandes/" + Integer.toString(la_commande.getIdCommande()) + "_"
							+ la_commande.getFournisseur().getNomFournisseur());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Supprimer une commande à partir de son identifiant */
	public void supprimerCommande(int id_commande, int recu) throws FileNotFoundException, IOException {
		boolean present = false;
		String id = Integer.toString(id_commande);
		File repertoire = new File(location + this.Nom + "/Commandes/");
		File[] files_list = repertoire.listFiles();
		for (File f : files_list) {
			String filename = f.getName();
			if (filename.startsWith(id + "_")) {
				BufferedReader reader = new BufferedReader(new FileReader(f));
				String line = null;
				while ((line = reader.readLine()) != null) {
					String info_article[] = line.split(" & ");
                    if ( recu == JOptionPane.YES_OPTION) {
					    ajouterArticle(new Article(Integer.parseInt(info_article[1]), info_article[0],
							Integer.parseInt(info_article[2]), Float.parseFloat(info_article[3])));
                    }
				}
				f.delete();
				present = true;
				break;
			}
		}
		if (!present) {
			throw new CommandeInconnueException("Cette commande est inconnue!");
		}
	}

	/* Lister toutes les commandes */
	public DefaultTableModel lister_commandes(DefaultTableModel tableur) {
		try {
			int id_commande;
			String nom_fournisseur;
			int ref_article;
			int quantite;
			float prix;
			String line = null;
			File repertoire = new File(location + this.Nom + "/Commandes/");
			String[] files_list = repertoire.list();
			int ligne = 0;
			tableur.addRow(new String[] { "", "", "", "", "", "" });
			if (files_list.length > 0) {
				for (String path : files_list) {
					File f = new File(location + this.Nom + "/Commandes/" + path);
					BufferedReader reader = new BufferedReader(new FileReader(f));
					String filename = f.getName();
					if (!filename.startsWith(".")) {
						String info_commande[] = filename.split("_");
						tableur.setValueAt(info_commande[0], ligne, 0);
						tableur.setValueAt(info_commande[1], ligne, 1);
						while ((line = reader.readLine()) != null) {
							String info_article[] = line.split(" & ");
							tableur.setValueAt(info_article[1], ligne, 2);
							tableur.setValueAt(info_article[0], ligne, 3);
							tableur.setValueAt(info_article[2], ligne, 4);
							tableur.setValueAt(info_article[3], ligne, 5);
							ligne++;
							tableur.addRow(new String[] { "", "", "", "", "", "" });
						}
					}
				}

			}
			return tableur;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}

	}

	/* Rechercher une commande a partir de son fournisseur */
	public ArrayList<Commander> searchCommande(String nom_fournisseur) {

		try {
			File repertoire = new File(location + this.Nom + "/Commandes/");
			ArrayList<Commander> commande = new ArrayList<Commander>();
			File[] files_list = repertoire.listFiles();
			for (File f : files_list) {
				String filename = f.getName();
				int i = 0;

				if (nom_fournisseur.equals(filename.split("_")[1])) {
					BufferedReader reader = new BufferedReader(new FileReader(f));
					String line = null;
					while ((line = reader.readLine()) != null) {
						String info_commande[] = line.split(" & ");
						commande.add(new Commander(Integer.parseInt(filename.split("_")[0]), info_commande[0],
								Integer.parseInt(info_commande[1]), getFournisseur(nom_fournisseur),
								Integer.parseInt(info_commande[2]), Float.parseFloat(info_commande[3])));
					}

				}

			}
			return commande;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

// Gestion des fournisseurs

	/* Obtenir un fournisseur du magasin à partir de son nom */
	public Fournisseur getFournisseur(String nom_fournisseur) throws IOException, FileNotFoundException {
		File f = new File(location + this.Nom + "/Fournisseur.txt");
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = null;

		while ((line = reader.readLine()) != null) {
			String info_fournisseur[] = line.split(" & ", 0);
			if (info_fournisseur[1].equals(nom_fournisseur)) {
				return new Fournisseur(Integer.parseInt(info_fournisseur[0]), info_fournisseur[1], info_fournisseur[2],
						info_fournisseur[3]);
			}
		}

		throw new FournisseurInconnuException("Ce fournisseur est inconnu !");
	}

	/* Ajouter le fournisseur à Fournisseurs.txt */
	public void ajouterFournisseur(Fournisseur fournisseur) {
		try {
			EntrerInfo(
					fournisseur.getIdFournisseur() + " & " + fournisseur.getNomFournisseur() + " & "
							+ fournisseur.getAdresseFournisseur() + " & " + fournisseur.getMailFournisseur(),
					location + this.Nom + "/Fournisseur.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Supprimer un fournisseur de la liste de la liste des fournisseurs */
	public boolean supprimerFournisseur(String nom_fournisseur) {
		try {
			boolean present = false;
			File fichier = new File(location + this.Nom + "/Fournisseur.txt");
			File fichierTemp = new File(location + this.Nom + "/Fournisseurs_temp.txt");
			BufferedReader reader = new BufferedReader(new FileReader(fichier));
			PrintWriter pw = new PrintWriter(new FileWriter(fichierTemp));

			String line = null;
			while ((line = reader.readLine()) != null) {
				if (!line.split(" & ")[1].equals(nom_fournisseur)) {
					pw.println(line);
					pw.flush();
				} else {
					present = true;
				}
			}
			pw.close();
			reader.close();
			fichier.delete();
			fichierTemp.renameTo(fichier);
			return present;
		} catch (IOException e) {
			e.printStackTrace();
			return false;

		}
	}

/////// Gestion des Cartes de Fidélités

	/* Enregistrer un nouvel employé dans le fichhier employestxt */
	public void ajouterCarteFidelite(CarteFidelite carte) {

		try {
			EntrerInfo(String.valueOf(carte.getIdFidelite()) + "@" + String.valueOf(carte.getScore()),
					location + this.Nom + "/CarteFidelite.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Supprimer les cartes de fidélités
	public void supprimerCarteFidelite(String Id) {
		try {
			File fichier = new File(location + this.Nom + "/CarteFidelite.txt");
			File fichierTemp = new File(location + this.Nom + "/CarteFidelite_temp.txt");
			BufferedReader reader = new BufferedReader(new FileReader(fichier));
			PrintWriter pw = new PrintWriter(new FileWriter(fichierTemp));

			String ligne = reader.readLine();
			while (ligne != null) {
				if (!ligne.contains(Id)) {
					pw.println(ligne);
					pw.flush();
				}
				ligne = reader.readLine();
			}
			pw.close();
			reader.close();
			fichier.delete();
			fichierTemp.renameTo(fichier);
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	/* Obtenir une carte de fidélité du magasin à partir de son identifiant */
	public CarteFidelite getCarteFidelite(int id) throws IOException, FileNotFoundException {
		File f = new File(location + this.Nom + "/CarteFidelite.txt");
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = null;

		while ((line = reader.readLine()) != null) {
			String info_carte[] = line.split(" & ", 0);
			if (info_carte[0].equals(id)) {
				return new CarteFidelite(Integer.parseInt(info_carte[0]),Integer.parseInt(info_carte[1]));
			}
		}

		throw new CarteFideliteInconnuException("Cette carte est inconnue !");
	}

    public CarteFidelite searchCarteFidelite(String Id) {

		try {
			File fichier = new File(location + this.Nom + "/CarteFidelite.txt");
			BufferedReader reader = new BufferedReader(new FileReader(fichier));
			String ligne = null;
			boolean trouve = false;
			ligne = reader.readLine();
			while (ligne != null & !trouve) {
				if (ligne.contains(Id)) {
					trouve = true;
				} else {
					ligne = reader.readLine();
				}
			}
			reader.close();
			if (ligne == null) {
				// employe non enregistre
				return null;
			} else {
				String[] line = ligne.split("@");
				return new CarteFidelite(Integer.parseInt(Id),Integer.parseInt(line[1]));
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
////Gestion du stock

	/* savoir le nombre d'article dans un stock */
	public int getNombreArticle() throws IOException {
		int nb = 0;
		String line;
		File file = new File(location + this.Nom + "/Stock.txt");
		FileReader fw = new FileReader(file.getAbsoluteFile());
		BufferedReader bw = new BufferedReader(fw);
		while (bw.readLine() != null) {
			nb += 1;
		}
		return nb;
	}

	/* verifier si un article existe */
	public boolean contientArticle(String idarticle) {
		try {
			File fichier = new File(location + this.Nom + "/Employes.txt");
			BufferedReader reader = new BufferedReader(new FileReader(fichier));
			String ligne = null;
			boolean trouve = false;
			ligne = reader.readLine();
			while (ligne != null & !trouve) {
				if (ligne.contains(idarticle)) {
					return true;
				} else {
					ligne = reader.readLine();
				}
			}
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/* ajouter un article au stock */

	public void ajouterArticle(Article newarticle) {

		try {
			EntrerInfo(newarticle.getIdArticle() + "@" + newarticle.getNomArticle() 
					+ "@" + newarticle.getQuantite() + "@" + newarticle.getPrix(), location + this.Nom + "/Stock.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void supprimerArticle(String idarticle) {
		try {
			File fichier = new File(location + this.Nom + "/Stock.txt");
			File fichierTemp = new File(location + this.Nom + "/Stock_temp.txt");
			BufferedReader reader = new BufferedReader(new FileReader(fichier));
			PrintWriter pw = new PrintWriter(new FileWriter(fichierTemp));

			String ligne = reader.readLine();
			while (ligne != null) {
				if (!ligne.contains(idarticle)) {
					pw.println(ligne);
					pw.flush();
				}
				ligne = reader.readLine();
			}
			pw.close();
			reader.close();
			fichier.delete();
			fichierTemp.renameTo(fichier);
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public Article searchArticle(String Id) {

		try {
			File fichier = new File(location + this.Nom + "/Stock.txt");
			BufferedReader reader = new BufferedReader(new FileReader(fichier));
			String ligne = null;
			boolean trouve = false;
			ligne = reader.readLine();
			while (ligne != null) {
			String info_article[] = ligne.split("@");
			    if (info_article[0].equals(Id)) {
				    return new Article(Integer.parseInt(info_article[0]), info_article[1], Integer.parseInt(info_article[2]),
						    Float.parseFloat(info_article[3]));
			    }
                ligne = reader.readLine();
		    }
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

/// Gestion des factures

	/* comande pour l'ajout d'un article dans une facture*/
	public void ajouterCommande(String[] ligne, String Idfacture) {
		try {
			EntrerInfo(ligne[0] + "&" + ligne[1] + "&" + ligne[2] + "&" + ligne[3] + "&" + ligne[4] + "\n",
					location + this.Nom + "/Factures/" + Idfacture);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*Suprimer une facture*/
	public boolean suprimerFacture(String Id){
		boolean present = false;
		File repertoire = new File(location + this.Nom + "/Factures/");
		File[] files_list = repertoire.listFiles();
		for (File f : files_list) {
			String filename = f.getName();
			if (filename.equals(Id)) {
				File facture = new File(location + this.Nom + "/Factures/"+Id);
				facture.delete();
				present = true;
			}
		}
		return present;
	}

	

}
