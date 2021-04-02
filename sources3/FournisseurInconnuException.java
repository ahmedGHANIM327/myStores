/**
 * FournisseurInconnuException indique qu'un fournisseur n'est pas dans la liste
 * des fournisseurs.
 */
public class FournisseurInconnuException extends IllegalArgumentException {
	public FournisseurInconnuException(String message) {
		super(message);
	}
}
