public class Article {

    private int idarticle;
    private String nomarticle;
    private int quantite;
    private float prix;

    public Article(int id , String nom , int quant , float prix ){
	this.idarticle = id;
	this.nomarticle = nom;
	this.quantite = quant;
	this.prix = prix;
    }


	public int getIdArticle() {
        return this.idarticle;
    }

    public String getNomArticle() {
        return this.nomarticle;
    }


    public int getQuantite() {
        return this.quantite;
    }

    public void setIdArticle(int id_article) {
        this.idarticle = id_article;
    }

    public void setNomAricle(String nom_article) {
        this.nomarticle = nom_article;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix() {
        return this.prix;
    }

    public void setPrix(float Prix) {
        this.prix=Prix;
    }
    
	public String toString() {
		return "\n\n    id : " + this.getIdArticle() + "\n\n    Nom  :  " + this.getNomArticle() + "\n\n    quantité  :  "
				+ this.getQuantite() + "\n\n    Prix  :  " + this.getPrix() +" \n" ;
	}
}
