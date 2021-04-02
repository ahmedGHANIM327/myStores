import java.awt.*;
import javax.swing.*;
public class BoutonPersonnalise extends JButton{

    /*couleur des boutons */
	private Color couleurPerso = new Color(110,90,120);

    public BoutonPersonnalise(String name) {
        super(name);
        this.setBackground(couleurPerso);
		this.setForeground(Color.WHITE);
    }
}
