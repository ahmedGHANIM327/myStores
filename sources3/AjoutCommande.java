import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
  
public class AjoutCommande extends FenetrePersonnalisee {

    DefaultTableModel tableur;
    JTable table;
    JScrollPane scroll;
    JButton plus;
    JLabel consigne;
    
    private Magasin magasin;
	private JLabel id_commande = new JLabel("Id Commande :");
	private JTextField saisie_id = new JTextField();
    private int id;
	private JLabel fournisseur = new JLabel("Fournisseur :");
    private JTextField saisie_fournisseur = new JTextField();
    private Fournisseur le_fournisseur;
    private JButton bBack = new BoutonPersonnalise("Retour");
    private JButton bfinaliser = new BoutonPersonnalise ("Finaliser");
	private JLabel path;
    

    public AjoutCommande(Magasin magasin){
        super();
        this.magasin = magasin;
        this.initComposant();
    }


    public void initComposant() {

    // Plan du haut
        JPanel northpane = new JPanel();
        northpane.setLayout(new GridLayout(1, 2));
        
        // Ajout du bouton Retour
	    bBack.setBounds(0, 0, 50, 30);
	    bBack.addActionListener(new ActionBack());;
        northpane.add(bBack);
        // Ajout du chemin
	    JLabel path = new JLabel(magasin.getNomMagasin() + " > Commandes > Ajouter", SwingConstants.CENTER);
        path.setBounds(85, 0, 200, 30);
        northpane.add(path);


    // Plan du bas
        // Intermediare est un plan intermediare avec les zones de saisies et le bouton ajouter
        JPanel intermediaire = new JPanel();
        intermediaire.setLayout(new GridLayout(1, 6));
        // Saisie de l'id de commande et le nom du fournisseur       
        intermediaire.setPreferredSize(new Dimension (0,50));
        intermediaire.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        intermediaire.add(id_commande,BorderLayout.NORTH);
        intermediaire.add(saisie_id ,BorderLayout.NORTH);
        intermediaire.add(fournisseur,BorderLayout.NORTH);
        intermediaire.add(saisie_fournisseur,BorderLayout.NORTH); 
        
        // Gestion du bouton "+"       
        plus = new JButton("+");
        consigne = new JLabel("Ajouter un Article");
        
        intermediaire.add(consigne);
        intermediaire.add(plus);            
        plus.addActionListener(new ActionAjouter());

        // On ajoute intermdaire au plan du bas (southpane)
        JPanel southpane = new JPanel();
        southpane.setLayout(new GridLayout(2, 1));
        southpane.add(intermediaire);
        

        
         //Ajout du bouton Finaliser
        bfinaliser.setBounds(0, 0, 50, 30);
	    southpane.add(bfinaliser);
	    bfinaliser.addActionListener(new ActionFinaliser());
        
        // Disposition des plans dans le plan principal
        this.add(northpane,BorderLayout.NORTH);
        this.add(southpane,BorderLayout.SOUTH);

        //Les données du tableau
        String[][] data = {};

        //Les titres des colonnes
        String  title[] = {"Id Article", "Nom Article", "Quantité", "Prix par unité"};
        tableur = new  DefaultTableModel(data, title);
        table = new JTable(tableur){};
        scroll = new JScrollPane(table); 
        this.add(scroll, BorderLayout.CENTER);
        
        
    }



    class ActionAjouter implements ActionListener { 
        public void actionPerformed(ActionEvent e) {
            boolean valide[] = {false, false, false, false};
            try {
                
                id = Integer.parseInt(saisie_id.getText());
                valide[0] = true;
                String nom_fournisseur = saisie_fournisseur.getText();                
                le_fournisseur = magasin.getFournisseur(nom_fournisseur);
                String id = JOptionPane.showInputDialog(null," L'Id de l'article","Infos Commande",JOptionPane.YES_NO_CANCEL_OPTION);
                int identifiant = Integer.parseInt(id);
                valide[1] = true;
                String nom = JOptionPane.showInputDialog(null," le nom de l'article","Infos Commande",JOptionPane.YES_NO_CANCEL_OPTION);
                String quantite = JOptionPane.showInputDialog(null," La quantite ","Infos Commande",JOptionPane.YES_NO_CANCEL_OPTION);                
                int quantite_article = Integer.parseInt(quantite);
                valide[2] = true;
                String prix = JOptionPane.showInputDialog(null," Le prix ","Infos Commande",JOptionPane.YES_NO_CANCEL_OPTION);
                float prix_article = Float.parseFloat(prix);
                valide[3] = true;
                if (valide[0] && valide[1] && valide[2] && valide[3]) {
                    if (table.getSelectedRow() != -1) {
                        tableur.insertRow(table.getSelectedRow(), new String[]{id,nom,"", ""});
                        table.setRowSelectionInterval(table.getSelectedRow(), table.getSelectedRow());
                    }
                    else { 
                        tableur.addRow(new String[]{id,nom,quantite, prix}); 
                    }
                }
            } catch (NumberFormatException e1) {
                if (!valide[0]) {
                    JOptionPane.showMessageDialog(getComponent(0), "L'identifiant de commande est un entier !", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (!valide[1]) {
                    JOptionPane.showMessageDialog(getComponent(0), "L'identifiant d'article est un entier !", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (!valide[2]) {
                    JOptionPane.showMessageDialog(getComponent(0), "La quantite est un entier !", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (!valide[3]) {
                    JOptionPane.showMessageDialog(getComponent(0), "Le prix est un flottant !", "Warning", JOptionPane.WARNING_MESSAGE);
                }

            } catch(FournisseurInconnuException e2) {
                JOptionPane.showMessageDialog(getComponent(0), "Ce fournisseur est inconnu ! Ajoutez-le d'abord à la liste des fournisseurs !", "Warning", JOptionPane.WARNING_MESSAGE);
            } catch(IOException e3) {
                e3.printStackTrace();
            }
        }
    };

    class ActionBack implements ActionListener  {
	        public void actionPerformed(ActionEvent ev){
	                dispose();
	                new MenuCommandes(magasin);
	        }
	}

    
    class ActionFinaliser implements ActionListener {
            public void actionPerformed(ActionEvent ev) {
                                       
                String donnees[] = new String[4];
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                            donnees[j] = String.valueOf(table.getValueAt(i,j));
                    }
                    // Construire la commande a partir des données
                    Commander commande = new Commander(id, String.valueOf(donnees[1]), Integer.parseInt(donnees[0]), le_fournisseur,  
                                                                     Integer.parseInt(donnees[2]), Float.parseFloat(donnees[3]));
                    magasin.ajouterCommande(commande);
                }
                dispose();
                new MenuCommandes(magasin);
               
            }
    }
                         
 
   
        
    

 
} 

