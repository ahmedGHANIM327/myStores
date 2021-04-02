import java.awt.*;
import javax.swing.*;

public class FenetrePersonnalisee extends JFrame {


	public FenetrePersonnalisee() {
		super("MyStores");
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) dimension.getHeight();
		int width = (int) dimension.getWidth();
		//this.setLocation(width / 2, height );
		// this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width*2/3, height);//width / 2, height );
		this.setVisible(true);
	}

}
