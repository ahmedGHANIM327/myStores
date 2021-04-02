import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;


public class AjoutMagasin extends FenetrePersonnalisee{
	
	private Magasin nmagasin;
	private JButton retour = new BoutonPersonnalise("Retour");
	private JButton creer = new BoutonPersonnalise("Creer");
	private JPanel planNom = new JPanel();
	private JPanel planAddr = new JPanel();
	private JLabel labelcreer = new JLabel("Nom du magasin :");
	private JLabel labelAddr = new JLabel("Adresse du magasin:");
	private JTextField nom = new JTextField();
	private JTextField addr = new JTextField();
	private JLabel labelvide ;
	private JPanel plan = new JPanel();
	
	public AjoutMagasin() throws IOException {
		super();
		this.InitComposant();
	}

	private void InitComposant() throws IOException {
		this.add(retour,BorderLayout.NORTH);
		this.add(creer,BorderLayout.SOUTH);
		plan.setLayout(new GridLayout(12, 1));
		for(int i=0;i<3;i++){
			labelvide = new JLabel();
			plan.add(labelvide);
		}
		plan.add(labelcreer);
		plan.add(nom);
		plan.add(labelvide);
		plan.add(labelAddr);
		plan.add(addr);
		this.add(plan,BorderLayout.CENTER);
		gererCreer();
		gererretour();
		
	}
	
	public void gererCreer() throws IOException{
		creer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					nmagasin = new Magasin(nom.getText(), addr.getText());
					try {
						nmagasin.start();
						dispose();
						Magasins m = new Magasins();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
			}
		});
	}
	
	public void gererretour() throws IOException{
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				try {
						Magasins m = new Magasins();
				} catch (IOException e) {
						e.printStackTrace();
				}
			}
		});
	}
	
}
