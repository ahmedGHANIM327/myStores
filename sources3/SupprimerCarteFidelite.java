import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SupprimerCarteFidelite extends FenetrePersonnalisee{
	private Magasin magasin;
	private JLabel path;
	private JButton bBack = new BoutonPersonnalise("Retour");
	private JLabel instruction = new JLabel("\n Rechercher par Id de Carte de fidélité");
	private JTextField barreRecherche = new JTextField();
	private JButton delete = new BoutonPersonnalise("Supprimer");
	private JTextArea resultatRecherche = new JTextArea();
	
	public SupprimerCarteFidelite(Magasin magasin) {
		super();
		this.magasin = magasin;
		path = new JLabel(magasin.getNomMagasin() + " > Carte de fidélité > Supprimer", SwingConstants.CENTER);
		initComposant();
	}

	private void initComposant() {
		this.setLayout(new BorderLayout());
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.add(instruction,BorderLayout.CENTER);
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(bBack, BorderLayout.WEST);
		panel1.add(path, BorderLayout.CENTER);
		panel3.add(panel1, BorderLayout.NORTH);
		bBack.addActionListener(new ActionBack());
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(barreRecherche,  BorderLayout.CENTER);
		panel2.add(delete,  BorderLayout.EAST);
        delete.addActionListener(new ActionDelete());
		panel3.add(panel2, BorderLayout.SOUTH);
		this.add(panel3, BorderLayout.NORTH);
		JPanel panel4 = new JPanel(new BorderLayout());
		panel4.add(resultatRecherche, BorderLayout.CENTER);
		this.add(panel4, BorderLayout.CENTER);
		resultatRecherche.setEditable(false);
        resultatRecherche.setFont(new java.awt.Font("Serif", 1, 14));
        resultatRecherche.setForeground(Color.BLUE);
		

	}
	
    /*Listener du bouton Retour */
	class ActionBack implements ActionListener  {
        public void actionPerformed(ActionEvent ev){
                dispose();
                new MenuCarte(magasin);
        }
    }
    /*Listener du bouton Delete après la saisie du IdFidelite et score du client*/
	class ActionDelete implements ActionListener  {
        public void actionPerformed(ActionEvent ev){
            String infos = barreRecherche.getText();
	        CarteFidelite carte = magasin.searchCarteFidelite(infos);
            if (carte !=null) {

                magasin.supprimerCarteFidelite(infos);
                resultatRecherche.setText("\n\n" + String. valueOf(carte.getIdFidelite()) + " de score " + String. valueOf(carte.getScore()) + " points a bien été supprimé de la base des données !" );
                
            } else {
                resultatRecherche.setText("\n\n" + String. valueOf(carte.getIdFidelite()) + " de score " + String. valueOf(carte.getScore()) + " points n'est pas enregistré dans la base des données !" );
            }
        }
    }
}
