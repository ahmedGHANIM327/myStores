import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class SupprimerArticle extends FenetrePersonnalisee{
	private Article article ;
	private JLabel path;
	private JButton bBack = new JButton("Retour");
	private JLabel instruction = new JLabel("\n Rechercher par Id");
	private JTextField barreRecherche = new JTextField();
	private JButton delete = new JButton("Supprimer");
	private JTextArea resultatRecherche = new JTextArea();
	
	
	Color c = new Color(110,90,120);
	
	public SupprimerArticle(Article article) {
		super();
		this.article = article ;
		path = new JLabel(article.getNomArticle() + " > Article > Supprimer", SwingConstants.CENTER);
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
		bBack.setBackground(c);
		bBack.setForeground(Color.WHITE);
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
            //TODO
        }
	}
	
	/*Listener du bouton Delete après la saisie du id de l'article */
	class ActionDelete implements ActionListener  {
        public void actionPerformed(ActionEvent ev){
        	//TODO
        }
	}
}
