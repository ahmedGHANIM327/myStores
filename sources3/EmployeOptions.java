import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class EmployeOptions extends FenetrePersonnalisee {
	private Magasin magasin;
	private JLabel path;
	private JButton bAdd = new BoutonPersonnalise("Ajouter un employé");
	private JButton bList = new BoutonPersonnalise("Liste des employés");
	private JButton bDelete = new BoutonPersonnalise("Supprimer un employé");
	private JButton bBack = new BoutonPersonnalise("Retour");

	public EmployeOptions(Magasin magasin) {
		super();
		this.magasin = magasin;
		this.InitComposant();
	}

	private void InitComposant() {

		this.setLayout(null);
		path = new JLabel(magasin.getNomMagasin() + " > Employés ", SwingConstants.CENTER);
		this.add(path);
		path.setBounds(85, 0, 200, 30);
		this.add(bBack);
		gererBBack();
		bBack.setBounds(0, 0, 85, 30);
		this.add(bAdd);
		gererBAdd();
		bAdd.setBounds(this.getWidth() / 4, this.getHeight() / 3, this.getWidth() / 2, 50);
		this.add(bList);
		gererBList();
		bList.setBounds(this.getWidth() / 4, this.getHeight() / 3 + 100, this.getWidth() / 2, 50);
		this.add(bDelete);
		gererBDelete();
		bDelete.setBounds(this.getWidth() / 4, this.getHeight() / 3 + 200, this.getWidth() / 2, 50);

	}

	// Listener de button "Retour"
	public void gererBBack() {
		bBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					dispose();
					MenuPrincipal m = new MenuPrincipal(magasin.getNomMagasin());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(getComponent(0), "Erreur d'accès à la base de données! Réessayez 		 ultérieurement.", "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	// Listener de button "liste des employés"
	public void gererBList() {
		bList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ListerEmployes(magasin);
			}
		});
	}

	// Listener de button "ajouter un employé"
	public void gererBAdd() {
		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new AjouterEmploye(magasin);
			}
		});
	}

	// Listener de button "Supprimer un employé"
	public void gererBDelete() {
		bDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new SupprimerEmploye(magasin);
			}
		});
	}

}
