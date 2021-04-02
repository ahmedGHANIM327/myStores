public class Fournisseur {
    private int idFournisseur;
    private String nom;
    private String adresse ;
    private String mail;

    public Fournisseur(int id, String nom, String adresse , String mail){
	    this.idFournisseur = id;
	    this.nom = nom;
	    this.adresse = adresse;
	    this.mail = mail;
    }

    public int getIdFournisseur(){
	    return this.idFournisseur;
    }

    public String getNomFournisseur() {
        return nom;
    }

    public String getAdresseFournisseur() {
        return this.adresse;
    }

    public String getMailFournisseur() {
        return mail;
    }
}
