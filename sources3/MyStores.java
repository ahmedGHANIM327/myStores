import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class MyStores extends FenetrePersonnalisee{
	
	private JButton acceder = new BoutonPersonnalise("Accéder aux magasins");
	private JButton quitter = new BoutonPersonnalise("Quitter");
	private JLabel image = new JLabel(new ImageIcon("MyStore.png"));

	
	public MyStores() {
		super();
		this.InitComposant();
	}

	private void InitComposant() {

        this.setLayout(new BorderLayout());
        this.add(acceder, BorderLayout.SOUTH);
        gererAcceder();
        this.add(image, BorderLayout.CENTER);
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(quitter, BorderLayout.WEST);
        this.add(panel1, BorderLayout.NORTH);
        gererQuitter();
		
	}
		
	
	
	public void gererQuitter() {
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
	
	public void gererAcceder() {
		acceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					dispose();
					new Magasins();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	


	public static void main(String[] args) {
		new MyStores();
	}

}
