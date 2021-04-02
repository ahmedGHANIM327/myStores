import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SupprimerFacture extends FenetrePersonnalisee{
	private Magasin magasin;
	private JLabel path;
	private JButton bBack = new BoutonPersonnalise("Retour");
	private JLabel instruction = new JLabel("\n Id facture :");
	private JTextField barreRecherche = new JTextField();
	private JButton delete = new BoutonPersonnalise("Supprimer");
	private JTextArea resultatRecherche = new JTextArea();
	
	public SupprimerFacture(Magasin magasin) {
		super();
		this.magasin = magasin;
		path = new JLabel(magasin.getNomMagasin() + " > Factures > Supprimer", SwingConstants.CENTER);
		path.setHorizontalAlignment(JLabel.CENTER);
		path.setVerticalAlignment(JLabel.CENTER);
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
	
    /*/*Listener du bouton Retour */
	class ActionBack implements ActionListener  {
        public void actionPerformed(ActionEvent ev){
                dispose();
                new FacturesOption(magasin);
        }
    }
    /*Listener du bouton Delete après la saisie de l'ID */
	class ActionDelete implements ActionListener  {
        public void actionPerformed(ActionEvent ev){
            String Id = barreRecherche.getText();
	    boolean action = magasin.suprimerFacture(Id);
            if (action) {
                resultatRecherche.setText("\n\n" + "La facture numéro "+Id+ " a bien été supprimé de la base des données !" );     
            } else {
                resultatRecherche.setText("\n\n" +"La facture numéro "+Id+ " n'est pas enregistré dans la base des données !" );
            }
        }
    }

}
