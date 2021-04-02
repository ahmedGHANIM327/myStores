/**
 * CommandeInconnueException indique qu'une commande n'est pas enregistree dans
 * la base de donnees.
 */
public class CommandeInconnueException extends IllegalArgumentException {
	public CommandeInconnueException(String message) {
		super(message);
	}
}
