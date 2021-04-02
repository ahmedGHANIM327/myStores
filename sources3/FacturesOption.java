import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FacturesOption extends FenetrePersonnalisee{
	private Magasin magasin;
	private JLabel path;
	private JButton bAdd = new BoutonPersonnalise("Ajouter une facture");
    private JButton bDelete = new BoutonPersonnalise("Supprimer une facture");
	private JButton bBack = new BoutonPersonnalise("Retour" );
    //private JButton bListe = new BoutonPersonnalise("Liste des facture." );

	public FacturesOption(Magasin magasin) {
		super();
		this.magasin = magasin;
		this.InitComposant();
	}

	private void InitComposant() {
		
        this.setLayout(null);
		path = new JLabel(magasin.getNomMagasin() + " > Factures ", SwingConstants.CENTER);
		path.setHorizontalAlignment(JLabel.CENTER);
		path.setVerticalAlignment(JLabel.CENTER);
		this.add(path);
		path.setBounds(85, 0, 200, 30);
		
		this.add(bBack);
		gererBBack();
		bBack.setBounds(0, 0, 85, 30);
		
		this.add(bAdd);
		gererBAdd();
		bAdd.setBounds(this.getWidth()/4, this.getHeight()/3, this.getWidth()/2, 50);
		
		this.add(bDelete);
		gererBDelete();
		bDelete.setBounds(this.getWidth()/4, this.getHeight()/3 + 200, this.getWidth()/2, 50);
		/*
		this.add(bList);
		gererBList();
		bList.setBounds(this.getWidth()/4, this.getHeight()/3 + 100, this.getWidth()/2, 50);
		*/
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
	// Listener de button "ajouter une facture"
	public void gererBAdd() {
		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new AjouterFacture(magasin);
			}
		});
	}

/*
	// Listener de button "Liste des factures"
	public void gererBAdd() {
		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ListerFacture(magasin);
			}
		});
	} */

    // Listener de button "Supprimer un facture"
	public void gererBDelete() {
		bDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new SupprimerFacture(magasin);
			}
		});
	}

}
