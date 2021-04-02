import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MenuCarte extends FenetrePersonnalisee{
	private Magasin magasin;
	private JLabel path;
	private JButton bAdd = new BoutonPersonnalise("Ajouter une carte de fidélité");
	private JButton bList = new BoutonPersonnalise("Liste des cartes de fidélités");
    private JButton bSupp = new BoutonPersonnalise("Supprimer une carte de fidélité");
	private JButton bBack = new BoutonPersonnalise("Retour");
	
	Color c = new Color(110,90,120);
	public MenuCarte(Magasin magasin) {
		super();
		this.magasin = magasin;
		this.InitComposant();
	}

	private void InitComposant() {
		this.setLayout(null);
		path = new JLabel(magasin.getNomMagasin() + " > Carte de fidélité ", SwingConstants.CENTER);
		this.add(path);
		path.setBounds(85, 0, 200, 30);
		this.add(bBack);
		gererBBack();
		bBack.setBounds(0, 0, 85, 30);
		this.add(bAdd);
		gererBAdd();
		bAdd.setBounds(this.getWidth()/4, this.getHeight()/3, this.getWidth()/2, 50);
		this.add(bSupp);
		gererBSupp();
		bSupp.setBounds(this.getWidth()/4, this.getHeight()/3 + 200, this.getWidth()/2, 50);
		this.add(bList);
		gererBList();
		bList.setBounds(this.getWidth()/4, this.getHeight()/3 + 100, this.getWidth()/2, 50);

		
	}
	// Listener de button "Retour"
	public void gererBBack() {
		bBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				dispose();
				MenuPrincipal m = new MenuPrincipal(magasin.getNomMagasin());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	//  Listener de button "liste des cartes de fidélités"
	public void gererBList() {
		bList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ListerCarteFidelite(magasin);
			}
		});
	}
	// Listener de button "ajouter une carte de fidélité"
	public void gererBAdd() {
		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new AjoutCarteFidilite(magasin);
			}
		});
	}
	// Listener de button "supprimer une carte de fidelité"
	public void gererBSupp() {
		bSupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new SupprimerCarteFidelite(magasin);
			}
		});
	}

}
