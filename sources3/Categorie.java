import java.util.ArrayList;

public class Categorie {

	private String Nom;
	private ArrayList<Article> listProduit = new ArrayList<Article>();

	public Categorie(String Nom) {
		this.Nom = Nom;
	}

	public String getNomCategorie() {
		return this.Nom;
	}

	public void AjouterArticle(Article article) {
		listProduit.add(article);
	}

	public void SupprimerArticle(Article article) {
		for (int i = 0; i < listProduit.size(); i++) {
			if (listProduit.get(i).getIdArticle() == article.getIdArticle()) {
				listProduit.remove(i);
			}
		}
	}

	public int getNombreArticle() {
		return listProduit.size();
	}
}
