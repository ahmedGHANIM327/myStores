import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class ListerStock extends FenetrePersonnalisee{
	private Magasin magasin;
	private JLabel path;
	private JButton bBack = new BoutonPersonnalise("Retour");
	private JLabel instruction = new JLabel("\n Rechercher par id article");
	private JTextField barreRecherche = new JTextField();
	private JButton search = new BoutonPersonnalise("Search");
	private JTextArea resultatRecherche = new JTextArea();
	
	public ListerStock(Magasin magasin) {
		super();
		this.magasin = magasin;
		path = new JLabel(magasin.getNomMagasin() + " > Stock > Liste", SwingConstants.CENTER);
		initComposant();
	}
	
	private void initComposant() {
		this.setLayout(new BorderLayout());
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.add(instruction,BorderLayout.CENTER);
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(bBack, BorderLayout.WEST);
		panel1.add(path, BorderLayout.EAST);
		panel3.add(panel1, BorderLayout.NORTH);
		bBack.addActionListener(new ActionBack());
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(barreRecherche,  BorderLayout.CENTER);
		panel2.add(search,  BorderLayout.EAST);
        	search.addActionListener(new ActionSearch());
		panel3.add(panel2, BorderLayout.SOUTH);
		this.add(panel3, BorderLayout.NORTH);
		JPanel panel4 = new JPanel(new BorderLayout());
		panel4.add(resultatRecherche, BorderLayout.CENTER);
		this.add(panel4, BorderLayout.CENTER);
		resultatRecherche.setEditable(false);
        resultatRecherche.setFont(new java.awt.Font("Serif", 1, 14));
        resultatRecherche.setForeground(Color.BLUE);
		
	}
	
    /*Listener du bouton retour */
	class ActionBack implements ActionListener  {
        public void actionPerformed(ActionEvent ev){
            dispose();
            try {
			dispose();
			MenuPrincipal m = new MenuPrincipal(magasin.getNomMagasin());
		    } catch (IOException e) {
			    JOptionPane.showMessageDialog(getComponent(0), 
                "Erreur d'accès à la base de données! Réessayez ultérieurement.",
                 "Warning", JOptionPane.ERROR_MESSAGE);
			}
        }
    }
	
    /*Listener du bouton search */
	class ActionSearch implements ActionListener  {
        public void actionPerformed(ActionEvent ev){
            if ( !barreRecherche.getText().equals("")) {
                Article article = magasin.searchArticle(barreRecherche.getText());
                if (article == null) {
                    resultatRecherche.setText("\n\n\n  Aucun resultat trouvé !");
                } else {
                    resultatRecherche.setText("\n\n\n  Id :   " + article.getIdArticle() + "\n\n Nom:   " + article.getNomArticle()
             + "\n\n Quantite:   " + article.getQuantite() + "\n\n Prix:   " +   article.getPrix());
                }
            }   
        }
    }

}
