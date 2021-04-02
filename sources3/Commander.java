public class Commander {

	private int idcommande;
	private String nomarticle;
	private Fournisseur fournisseur;
	private int quantite;
	private int ref_article;
	private float prix;

	public Commander(int idcommande, String nomarticle, int ref, Fournisseur fournisseur, int quantite, float prix) {
		this.idcommande = idcommande;
		this.nomarticle = nomarticle;
		this.fournisseur = fournisseur;
		this.quantite = quantite;
		this.ref_article = ref;
		this.prix = prix;
	}

	public int getIdCommande() {
		return this.idcommande;
	}

	public String getNomArticle() {
		return this.nomarticle;
	}

	public Fournisseur getFournisseur() {
		return this.fournisseur;
	}

	public int getQuantite() {
		return this.quantite;
	}

	public int getref() {
		return this.ref_article;
	}

	public float getPrix() {
		return this.prix;
	}

	public void setIdCommande(int idcommande) {
		this.idcommande = idcommande;
	}

	public void setNomArticle(String nomarticle) {
		this.nomarticle = nomarticle;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public void setref(int ref) {
		this.ref_article = ref;
	}

	public void setPrix(float nv_prix) {
		this.prix = nv_prix;
	}
}
