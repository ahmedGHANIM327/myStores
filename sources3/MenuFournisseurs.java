import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MenuFournisseurs extends FenetrePersonnalisee{
	private Magasin magasin;
	private JLabel path;
	private JButton bAdd = new BoutonPersonnalise("Ajouter un fournisseur");
	private JButton bList = new BoutonPersonnalise("Liste des fournisseurs");
    private JButton bDelete = new BoutonPersonnalise("Supprimer un fournisseur");
	private JButton bBack = new BoutonPersonnalise("Retour");


	public MenuFournisseurs(Magasin magasin) {
		super();
        this.magasin = magasin;
		this.InitComposant();
	}

	private void InitComposant() {
		this.setLayout(null);
		path = new JLabel(magasin.getNomMagasin() + " > Fournisseurs ", SwingConstants.CENTER);
		this.add(path);
		path.setBounds(85, 0, 200, 30);
		this.add(bBack);
		gererBBack();
		bBack.setBounds(0, 0, 85, 30);
		this.add(bAdd);
		gererBAdd();
		bAdd.setBounds(this.getWidth()/4, this.getHeight()/3, this.getWidth()/2, 50);
		this.add(bList);
		gererBList();
		bList.setBounds(this.getWidth()/4, this.getHeight()/3 + 100, this.getWidth()/2, 50);
        this.add(bDelete);
		gererbDelete();
		bDelete.setBounds(this.getWidth()/4, this.getHeight()/3 + 200, this.getWidth()/2, 50);
		
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
	//  Listener de button "liste des fournisseurs"
	public void gererBList() {
		bList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ListerFournisseurs(magasin);
			}
		});
	}
	// Listener de button "ajouter un fournisseur"
	public void gererBAdd() {
		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new AjoutFournisseur(magasin);
			}
		});
	}
	
    // Listener de button "supprimer un fournisseur"
	public void gererbDelete() {
		bDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new SupprimerFournisseur(magasin);
			}
		});
	}
}
