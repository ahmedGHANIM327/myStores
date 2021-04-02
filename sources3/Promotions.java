
public class Promotions{

	private int Idarticle;
	private float PourcentagePromotion;

	public Promotions(int Idarticle , float pourcentage){
		this.Idarticle = Idarticle;
		this.PourcentagePromotion = pourcentage;
	}

	public float getPromo(){
		return this.PourcentagePromotion;
	}

	public int getIdArticle(){
		return this.Idarticle;
	}

	
}
