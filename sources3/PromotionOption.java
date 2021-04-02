import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PromotionOption extends FenetrePersonnalisee{
	private Magasin magasin;
	private JLabel path;
	private JButton bAdd = new BoutonPersonnalise("Ajouter une promotion");
	private JButton bList = new BoutonPersonnalise("Liste des promotions");
    private JButton bDelete = new BoutonPersonnalise("Supprimer une promotion");
	private JButton bBack = new BoutonPersonnalise("Retour" );


	public PromotionOption(Magasin magasin) {
		super();
		this.magasin = magasin;
		this.InitComposant();
	}

	private void InitComposant() {
		
        this.setLayout(null);
		path = new JLabel(magasin.getNomMagasin() + " > Promotion ", SwingConstants.CENTER);
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
		this.add(bList);
		gererBList();
		bList.setBounds(this.getWidth()/4, this.getHeight()/3 + 100, this.getWidth()/2, 50);
        	//bDelete.setBounds(50, 220, 200, 50);
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
	//  Listener de button "liste des promotion"
	public void gererBList() {
		bList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new Listerpromotion(magasin);
			}
		});
	}
	// Listener de button "ajouter un promotion"
	public void gererBAdd() {
		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new AjouterPromotion(magasin);
			}
		});
	}

    // Listener de button "Supprimer un promotion"
	public void gererBDelete() {
		bDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new SupprimerPromotion(magasin);
			}
		});
	}
	/*public static void main(String[] args) {
		new EmployeOptions(new Magasin("Carrefour", "11 rue saint rome"));
	}*/

}
