import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MenuStock extends FenetrePersonnalisee{
	private Magasin magasin;
	private JLabel path;
	/*private JButton bAdd = new BoutonPersonnalise("Ajouter un article");*/
	private JButton bList = new BoutonPersonnalise("Liste des articles");
	private JButton bBack = new BoutonPersonnalise("Retour");
	
	Color c = new Color(110,90,120);

	public MenuStock(Magasin magasin) {
		super();
        this.magasin = magasin;
		this.InitComposant();
	}
	private void InitComposant() {
		this.setLayout(null);
		path = new JLabel(magasin.getNomMagasin() + " > Stock ", SwingConstants.CENTER);
		this.add(path);
		path.setBounds(85, 0, 200, 30);
		this.add(bBack);
		gererBBack();
		bBack.setBounds(0, 0, 85, 30);
		/*
		this.add(bAdd);
		gererBAdd();
		bAdd.setBounds(50, 170, 200, 50);
		*/
		this.add(bList);
		gererBList();
		bList.setBounds(50, 250, 200, 50);
		
	}
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
	//  Listener de button "liste des articles"
	public void gererBList() {
		bList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ListerStock(magasin);
			}
		});
	}
	/*
	// Listener de button "ajouter article"
	public void gererBAdd() {
		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new AjouterStock(magasin);
			}
		});
	}
	*/
}
