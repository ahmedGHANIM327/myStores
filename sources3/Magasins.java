import java.awt.Color;
import java.awt.GridLayout;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Magasins extends FenetrePersonnalisee {
	
	private JButton b1 = new BoutonPersonnalise("Ajouter Magasin");
	private JButton b2 = new BoutonPersonnalise("Supprimer Magasin");
	private JButton b3 = new BoutonPersonnalise("Retour");
	private JPanel planMagasin = new JPanel();
	private String locationlist = "ListeMagasins";
	private JPanel planNord = new JPanel();
	private JPanel planSud = new JPanel();
	private JLabel liste = new JLabel(" Voici la liste des magasins ");
	private JLabel Traitement = new JLabel(" Ajouter/Supprimer une magasin ");


	public Magasins() throws IOException{
		super();
		this.InitComposant();
	}

	private void InitComposant() {
		//Plan du Nord
		planNord.setPreferredSize(new Dimension(0, 40));
		planNord.setLayout(new GridLayout(2, 1));
		gererb3();
		planNord.add(b3);
		liste.setHorizontalAlignment(JLabel.CENTER);
		liste.setVerticalAlignment(JLabel.CENTER);
		planNord.add(liste);
		this.add(planNord,BorderLayout.NORTH);
		//Plan des magasin
		this.add(planMagasin,BorderLayout.CENTER);
		gererplan();
		//Plan du Sud
		planSud.setPreferredSize(new Dimension(0, 80));
		planSud.setLayout(new GridLayout(3, 1));
		Traitement.setHorizontalAlignment(JLabel.CENTER);
		Traitement.setVerticalAlignment(JLabel.CENTER);
		planSud.add(Traitement);
		planSud.add(b1);
		gererb1();
		planSud.add(b2);
		gererb2();
		this.add(planSud,BorderLayout.SOUTH);
	}
	
	public void gererb3() {
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new MyStores();
			}
		});
	}
	
	public void gererb1() {
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				try {
					AjoutMagasin m = new AjoutMagasin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void gererb2() {
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				SupprimerMagasin s = new SupprimerMagasin();
			}
		});
	}
	public void gererplan()  {
		File file = new File(locationlist);
		String list[] = file.list();
		int n = list.length;
		if (n==0) {
			planMagasin.setBackground(Color.white);
		}else {
			planMagasin.setLayout(new GridLayout(n+5, 1));
			for (int i = 0; i < n; i++) {
				JButton b = new BoutonPersonnalise(list[i]);
				String s = list[i];	
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							dispose();
							MenuPrincipal menu = new MenuPrincipal(s);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
				planMagasin.add(b);
			}
		}
		
	}
}
