/**
 * CarteFideliteInconnuException indique qu'une carte n'est pas dans la 
liste des fournisseurs.
 */
public class CarteFideliteInconnuException extends IllegalArgumentException {
    public CarteFideliteInconnuException(String message) {
        super(message);
    }
}
