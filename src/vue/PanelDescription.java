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

import data.Data;
import modele.Date;

public class PanelDescription extends JPanel{
	private JEditorPane description = new JEditorPane();
	private ImageIcon imageOrigIcon;
	private JLabel image;
	public PanelDescription()
	{
		setLayout(new BorderLayout(2,0));
		//Permet de resize l'image pour éviter d'obtenir des images trop grandes ou trop petites tout en conservant le ratio
		//Création de l'image
		//image = new JLabel(new ImageIcon(imageOrig), JLabel.CENTER);
		image = new JLabel();
		
		description.setContentType("text/html");
		
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
	public void updatePanel(String parNomImage, String parDossier, String nom, Date date, String parDescription)
	{
		imageOrigIcon = new ImageIcon(Data.imageRepository + File.separator + parDossier + File.separator + parNomImage);
		Image imageOrig = imageOrigIcon.getImage().getScaledInstance(
				imageOrigIcon.getIconWidth()*150/imageOrigIcon.getIconHeight(), 
				150,
				Image.SCALE_SMOOTH);

		image.setIcon(new ImageIcon(imageOrig));

		String strDate = "<h3>" + date.toString() + "</h3>";
		String strNom = "<h3>" + nom + "</h3>";
		String strDescription = "<p>";
		for(int i = 0; i < parDescription.length(); i++)
		{
			if(parDescription.charAt(i) == '\n')
				strDescription += "<br>";
			else
				strDescription += parDescription.charAt(i);
		}
		strDescription += "</p>";
		
		description.setText("<html>" +
				strDate + strNom + strDescription +
				"</html>");
	}
	public void clearPanel()
	{
		image.setIcon(null);
		description.setText("");
	}
}