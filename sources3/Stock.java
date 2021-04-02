import java.util.ArrayList;

public class Stock {

	private ArrayList<Article> listArticle = new ArrayList<Article>();

	public void AjouterArticle(int Idarticle, String nom, int quant, float prix) {
		Article newArticle = new Article(Idarticle, nom, quant, prix);
		listArticle.add(newArticle);
	}

	public void SuprimerArticle(int Idarticle) {
		for (int i = 0; i < listArticle.size(); i++) {
			if (listArticle.get(i).getIdArticle() == Idarticle) {
				this.listArticle.remove(i);
			}
		}
	}

	public void ModifierArticle(int Idarticle, String nom, int quant, float prix, int asupprimer) {
		Article newArticle = new Article(Idarticle, nom,  quant, prix);
		SuprimerArticle(asupprimer);
		this.listArticle.add(newArticle);
	}
}
