import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class ListerCarteFidelite extends FenetrePersonnalisee {
	private Magasin magasin;
	private JLabel path;
	private JButton bBack = new BoutonPersonnalise("Retour");
	private JLabel instruction = new JLabel("\n Rechercher par Identifiant de carte fidélité");
	private JTextField barreRecherche = new JTextField();
	private JButton search = new BoutonPersonnalise("Search");
	private JTextField resultatRecherche = new JTextField();
	
	public ListerCarteFidelite(Magasin magasin) {
		super();
		this.magasin = magasin;
		initComposant();
	}

	private void initComposant() {
		this.setLayout(new BorderLayout());
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.add(instruction,BorderLayout.CENTER);
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(bBack, BorderLayout.WEST);
		path = new JLabel(magasin.getNomMagasin() + " > Cartes de fidélités > Liste", SwingConstants.CENTER);
		panel1.add(path, BorderLayout.EAST);
		panel3.add(panel1, BorderLayout.NORTH);
		bBack.addActionListener(new ActionBack());
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(barreRecherche,  BorderLayout.CENTER);
		panel2.add(search,  BorderLayout.EAST);
		panel3.add(panel2, BorderLayout.SOUTH);
		this.add(panel3, BorderLayout.NORTH);
		JPanel panel4 = new JPanel(new BorderLayout());
		panel4.add(resultatRecherche, BorderLayout.CENTER);
		this.add(panel4, BorderLayout.CENTER);
		resultatRecherche.setEditable(false);
		search.addActionListener(new ActionSearch());
	}
	
	class ActionBack implements ActionListener  {
        public void actionPerformed(ActionEvent ev){
                dispose();
                new MenuCarte(magasin);
        }
    }
/*Listener du bouton search */
	class ActionSearch implements ActionListener  {
     	public void actionPerformed(ActionEvent ev) {
			if (!barreRecherche.getText().equals("")) {
				CarteFidelite carte = magasin.searchCarteFidelite(barreRecherche.getText());
				if (carte == null) {
					resultatRecherche.setText("\n\n\n  Aucun resultat trouvé !");
				} else {
					resultatRecherche.setText("\n\n    Id Carte de fidélité  : " + carte.getIdFidelite()
							+ "\n\n    Score  :  " + carte.getScore());
				}
			}
		}
	}
}
