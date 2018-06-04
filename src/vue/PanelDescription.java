package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class PanelDescription extends JPanel{
	private JEditorPane description = new JEditorPane();
	private ImageIcon imageOrigIcon;
	public PanelDescription()
	{
		setLayout(new BorderLayout(2,0));
		//Permet de resize l'image pour éviter d'obtenir des images trop grandes ou trop petites tout en conservant le ratio
		imageOrigIcon = new ImageIcon("images" + File.separator + "imageTest" + ".jpg");
		Image imageOrig = imageOrigIcon.getImage().getScaledInstance(
				imageOrigIcon.getIconWidth()*150/imageOrigIcon.getIconHeight(), 
				150,
				Image.SCALE_SMOOTH);
		//Création de l'image
		JLabel image = new JLabel(new ImageIcon(imageOrig), JLabel.CENTER);
		description.setContentType("text/html");
		description.setText("<html>" +
				"<h1>OUI</h1><br>" +
				"<br>OUI" +
				"<p>sqdqsdsqdsqfsqfq</p>" +
				"<p>fsqfsqfsqfsqfsqfsqfqfqsf</p>" +
				"<p>sqfqsfsqfsqfsqfq</p>" +
				"fqsfsqfsqdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd dddddddddddddddddddddddddddddddddd dddddddddddddddddddddddddddddddddddddddd" +
				" </html>");
		description.setEditable(false);
		description.setBackground(new Color(240, 240, 230));
		JPanel panel = new JPanel();
		panel.add(description);
		JScrollPane scrollPane = new JScrollPane(panel);

		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		scrollPane.setPreferredSize(new Dimension(900, 140));
		scrollPane.setViewportView(description);
		add(image, BorderLayout.WEST);
		add(scrollPane, BorderLayout.CENTER);
	}
}