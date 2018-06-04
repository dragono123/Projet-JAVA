package vue;

import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelDescription extends JPanel{
	private JPanel description = new JPanel();
	private ImageIcon imageOrigIcon;
	public PanelDescription()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		//Permet de resize l'image pour éviter d'obtenir des images trop grandes ou trop petites tout en conservant le ratio
		imageOrigIcon = new ImageIcon("images" + File.separator + "imageTest" + ".jpg");
		Image imageOrig = imageOrigIcon.getImage().getScaledInstance(
				imageOrigIcon.getIconWidth()*150/imageOrigIcon.getIconHeight(), 
				150,
				Image.SCALE_SMOOTH);
		//Création de l'image
		JLabel image = new JLabel(new ImageIcon(imageOrig), JLabel.CENTER);
		add(image);
		add(description);
	}
}