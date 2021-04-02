public class CarteFidelite{
	
	private int IdFidelite;
	private ListPromo Promo;
	private int score ;

	public CarteFidelite(int Id,int score){
		this.IdFidelite = Id;
		this.score = score;
	}

	public void AjouterProduit(int Idarticle ,float promo){
		Promo.AjouterProduitPromo(Idarticle ,promo);
	}

	public void ModifierCarteFidelite(int Idarticle ,float promo){
		Promo.ModifierProduitPromo(Idarticle ,promo);
	}

	public void SuprimerProduit(int Idarticle){
		Promo.SuprimerProduitPromo(Idarticle);
	}

	public void AjouterPoint(int nbPoint){
		this.score+=nbPoint;
	}

    public int getIdFidelite(){
        return this.IdFidelite;
    }

    public int getScore(){
        return this.score;
    }
   
    public void setScore(int point){
        this.score=point;
    }
    
}
