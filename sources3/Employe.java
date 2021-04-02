public class Employe {

	private String Nom;
	private String Prenom;
	private String Sexe;
	private String Poste;
	private float Salaire;

	public Employe(String Nom, String Prenom, String Sexe, String Poste, float Salaire) {
		this.Nom = Nom;
		this.Prenom = Prenom;
		this.Sexe = Sexe;
		this.Poste = Poste;
		this.Salaire = Salaire;
	}

	public String getNom() {
		return this.Nom;
	}

	public String getPrenom() {
		return this.Prenom;
	}

	public String getSexe() {
		return this.Sexe;
	}

	public String getPoste() {
		return this.Poste;
	}

	public float getSalaire() {
		return this.Salaire;
	}

	public String toString() {
		return "\n\n    Nom : " + this.getNom() + "\n\n    Prenom  :  " + this.getPrenom() + "\n\n    Sexe  :  "
				+ this.getSexe() + "\n\n    Poste  :  " + this.getPoste() + "\n\n    Salaire  :  " + this.getSalaire();
	}
}
