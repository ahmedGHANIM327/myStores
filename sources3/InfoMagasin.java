import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class InfoMagasin extends FenetrePersonnalisee {
	private Magasin magasin;
	private JLabel path;
	private JButton bBack = new BoutonPersonnalise("Retour");
	private JTextArea resultatRecherche = new JTextArea();

	Color c = new Color(110, 90, 120);

	public InfoMagasin(Magasin magasin) {
		super();
		this.magasin = magasin;
		path = new JLabel(magasin.getNomMagasin() + " > Info magasin", SwingConstants.CENTER);
		path.setHorizontalAlignment(JLabel.CENTER);
		path.setVerticalAlignment(JLabel.CENTER);
		initComposant();
	}

	private void initComposant() {
		this.setLayout(new BorderLayout());
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 2));
		panel1.add(bBack);
		panel1.add(path);
		this.add(panel1, BorderLayout.NORTH);
		bBack.addActionListener(new ActionBack());
		JPanel panel4 = new JPanel(new BorderLayout());
		panel4.add(resultatRecherche, BorderLayout.CENTER);
		this.add(panel4, BorderLayout.CENTER);
		resultatRecherche.setEditable(false);
		resultatRecherche.setFont(new java.awt.Font("Serif", 1, 14));
		resultatRecherche.setForeground(Color.BLUE);
		gererresultatRecherche();
	}

	/* Listener du bouton retour */
	class ActionBack implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				dispose();
				new MenuPrincipal(magasin.getNomMagasin());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void gererresultatRecherche() {
		try {
			resultatRecherche.setText("\n\n    Nom du magasin : " + magasin.getNomMagasin()
					+ "\n\n    addresse du magasin  :  " + magasin.getAddrMagasin() + "\n\n    nombre des employés  :  "
					+ magasin.getNombreEmploye());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
