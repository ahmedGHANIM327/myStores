import java.util.ArrayList;

public class ListPromo {

	private ArrayList<Promotions> listPromo = new ArrayList<Promotions>();

	public void AjouterProduitPromo(int Idarticle , float promo ){
		Promotions promotion = new Promotions(Idarticle,promo);
		listPromo.add(promotion);
	}

	public void SuprimerProduitPromo(int Idarticle){
		for (int i=0;i<listPromo.size(); i++){
			if(listPromo.get(i).getIdArticle()==Idarticle){
				this.listPromo.remove(i);
			}
		}
	}

	public void ModifierProduitPromo(int Idarticle , float promo ){
		Promotions promotion = new Promotions(Idarticle,promo);
		SuprimerProduitPromo(Idarticle);
		this.listPromo.add(promotion);
	}
}
