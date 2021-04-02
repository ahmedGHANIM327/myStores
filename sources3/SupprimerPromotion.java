import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SupprimerPromotion extends FenetrePersonnalisee{
	private Magasin magasin;
	private JLabel path;
	private JButton bBack = new BoutonPersonnalise("Retour");
	private JLabel instruction = new JLabel("\n Id du promotion");
	private JTextField barreRecherche = new JTextField();
	private JButton delete = new BoutonPersonnalise("Supprimer");
	private JTextArea resultatRecherche = new JTextArea();
	
	public SupprimerPromotion(Magasin magasin) {
		super();
		this.magasin = magasin;
		path = new JLabel(magasin.getNomMagasin() + " > Promotions > Supprimer", SwingConstants.CENTER);
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
                new PromotionOption(magasin);
        }
    }
    /*Listener du bouton Delete après la saisie du nom et prenom de l'employé */
	class ActionDelete implements ActionListener  {
        public void actionPerformed(ActionEvent ev){
	  try{
            String infos = barreRecherche.getText();
	    int Id = Integer.parseInt(infos);
	    Promotions promo = magasin.searchPromo(infos);
            if (promo !=null) {

                magasin.supprimerPromotion(infos);
                resultatRecherche.setText("\n\n" + String. valueOf(promo.getIdArticle()) + "  " + String. valueOf(promo.getPromo()) + " a bien été supprimé de la base des données !" );
                
            } else {
                resultatRecherche.setText("\n\n" + infos + " n'est pas enregistré dans la base des données !" );
            }
	  } catch(NumberFormatException e) {
	        JOptionPane.showMessageDialog(getComponent(0), "L'Id doit être saisi en chiffres !", "Warning", JOptionPane.WARNING_MESSAGE);
	        barreRecherche.setText("");
	 }
        }
    }
}
