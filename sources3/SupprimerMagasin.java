import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SupprimerMagasin extends FenetrePersonnalisee{
	
	private JButton retour = new BoutonPersonnalise("Retour");
	private JPanel planmagasin = new JPanel();
	private JLabel labelAddr = new JLabel("  Cliquer sur le magasin à supprimer :");
	private String locationlist = "ListeMagasins";
	private JPanel plan = new JPanel();
	private JLabel labelvide ;
	private JPanel planest = new JPanel();
	private JPanel planwest = new JPanel();
	
	public SupprimerMagasin()  {
		super();
		this.InitComposant();
	}

	private void InitComposant() {
		plan.setLayout(new GridLayout(1, 1));
		plan.add(retour);
		//plan.add(labelAddr);
		this.add(plan,BorderLayout.NORTH);
		gererretour();
		this.add(planest,BorderLayout.EAST);
		this.add(planwest,BorderLayout.WEST);
		this.add(planmagasin,BorderLayout.CENTER);
		gererplan();
	}

	
	public void gererretour() {
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
	public void gererplan() {
		File file = new File(locationlist);
		String list[] = file.list();
		int n = list.length;
		if (n==0) {
			planmagasin.setBackground(Color.white);
		}else {
			planmagasin.setLayout(new GridLayout(n+7, 1));
			labelvide = new JLabel();
			planmagasin.add(labelvide);
			planmagasin.add(labelAddr);
			for (int i = 0; i < list.length; i++) {
				JButton b = new BoutonPersonnalise(list[i]);
				String s = list[i];
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							File filei = new File(locationlist+"/"+s);
							DeleteElement(s);
							File filei1 = new File(locationlist+"/"+s);
							filei1.delete();
							dispose();
							Magasins m = new Magasins();
							//filei.delete();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
				planmagasin.add(b);
			}
		}
	}

	public void DeleteElement(String s){
		File file1 = new File(locationlist+"/"+s+"/InfoMagasin.txt");
		File fileFournisseur = new File(locationlist+"/"+s+"/Fournisseur.txt");
		File filePromo = new File(locationlist+"/"+s+"/Promotions.txt");
		File fileEmploye = new File(locationlist+"/"+s+"/Employes.txt");
		File filefact = new File(locationlist+"/"+s+"/Factures");
		File filecomm = new File(locationlist+"/"+s+"/Commandes");
		File filecartefidel = new File(locationlist+"/"+s+"/CarteFidelite.txt");
		File fileStock = new File(locationlist+"/"+s+"/Stock.txt");
		file1.delete();
		fileFournisseur.delete();
		filePromo.delete();
		fileEmploye.delete();
		File[] factures = filefact.listFiles();
        	for (File file : factures) {
			File file10 = new File(locationlist+"/"+s+"/"+"a.txt");
			file.renameTo(file10);
            		file10.delete();
        	}
		filefact.delete();
        	File[] commandes = filecomm.listFiles();
        	for (File file : commandes) {
            		file.delete();
        	}
		filecomm .delete();
		filecartefidel.delete();
		fileStock.delete();
	}

}
