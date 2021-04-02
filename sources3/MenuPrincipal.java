import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MenuPrincipal extends FenetrePersonnalisee{


	private Magasin magasin;
	private String locationlist = "ListeMagasins";	
	private JButton retour = new BoutonPersonnalise("Retour");
	private JButton info = new BoutonPersonnalise("Info Magasin");
	private JButton stock = new BoutonPersonnalise("Stock");
	private JButton Commande = new BoutonPersonnalise("Commandes");
	private JButton Fournisseur = new BoutonPersonnalise("Fournisseurs");
	private JButton factures = new BoutonPersonnalise("Factures");
	private JButton promo = new BoutonPersonnalise("Promotions");
	private JButton employes= new BoutonPersonnalise("Employés");
	private JButton carte = new BoutonPersonnalise("Cartes de Fidélités");
	private JLabel chemin ;
	
	public MenuPrincipal(String s) throws IOException{
		super();
		magasin(s);
		this.magasin.setNomMagasin(s); 
		this.InitComposant();
	}

		private void InitComposant() {

		this.setLayout(null);
		chemin = new JLabel(magasin.getNomMagasin() , SwingConstants.CENTER);
		this.add(chemin);
		chemin.setBounds(85, 0, 200, 30);
		this.add(retour);
		gererRetour();
		retour.setBounds(0, 0, 85, 30);
		this.add(info);
		gererInfo();
		info.setBounds(this.getWidth() / 4, this.getHeight() / 8, this.getWidth() / 2, 50);
		this.add(stock);
		gererStock();
		stock.setBounds(this.getWidth() / 4, this.getHeight() / 8 + 60, this.getWidth() / 2, 50);
		this.add(Commande);
		gererCommandes();
		Commande.setBounds(this.getWidth() / 4, this.getHeight() / 8 + 130, this.getWidth() / 2, 50);
		this.add(Fournisseur);
		gererFournisseurs();
		Fournisseur.setBounds(this.getWidth() / 4, this.getHeight() / 8 + 200, this.getWidth() / 2, 50);
		this.add(factures);
		gererFactures();
		factures.setBounds(this.getWidth() / 4, this.getHeight() / 8 + 270, this.getWidth() / 2, 50);
		this.add(promo);
		gererPromo();
		promo.setBounds(this.getWidth() / 4, this.getHeight() / 8 + 340, this.getWidth() / 2, 50);
		this.add(employes);
		gererEmployes();
		employes.setBounds(this.getWidth() / 4, this.getHeight() / 8 + 410, this.getWidth() / 2, 50);
		this.add(carte);
		gererCarte();
		carte.setBounds(this.getWidth() / 4, this.getHeight() / 8 + 480, this.getWidth() / 2, 50);
	}

	public void gererRetour() {
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					dispose();
					Magasins m = new Magasins();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void gererInfo() {
		info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					dispose();
					new InfoMagasin(magasin);
			}
		});
	}

	public void gererEmployes() {
		employes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					dispose();
					EmployeOptions emp = new EmployeOptions(magasin);
			}
		});
	}

	public void gererPromo() {
		promo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					dispose();
					new PromotionOption(magasin);
			}
		});
	}

    	public void gererCommandes() {
        Commande.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				dispose();
				MenuCommandes emp = new MenuCommandes(magasin);
		}
	    });
	}

	public void gererFactures() {
        factures.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				dispose();
				new FacturesOption(magasin);
		}
	    });
	}

	public void gererFournisseurs() {
        Fournisseur.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				dispose();
				MenuFournisseurs emp = new MenuFournisseurs(magasin);
		}
	    });
	}
	
	public void gererStock() {
        stock.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ListerStock(magasin);
		}
	    });
	}

    public void gererCarte() {
        carte.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				dispose();
				new MenuCarte(magasin);
		}
	    });
	}

	public void magasin(String m) throws IOException{
		File file1= new File(locationlist +"/"+m+"/InfoMagasin.txt");
		FileReader fw = new FileReader(file1.getAbsoluteFile());
   		BufferedReader bw = new BufferedReader(fw);
		String line = bw.readLine();
		bw.close();
		String[] s = line.split("@");
		magasin = new Magasin(s[0],s[1]);
	}
	
}
