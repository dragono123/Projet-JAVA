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
import modele.Evt;

/**
 * 
 * Ce panel est le panel où se trouve l'image, et le la description de l'Evt
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 *
 */
public class PanelDescription extends JPanel{
	private JEditorPane description = new JEditorPane();
	private ImageIcon imageOrigIcon;
	private JLabel image;
	/**
	 * On construit les deux panels dont on instancie dans le constructeur aucune image/text
	 */
	public PanelDescription()
	{
		setLayout(new BorderLayout(2,0));
		//Création de l'image
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
	/**
	 * Permet de mettre à jour le panel description à partir d'un évènement et du dossier où se situe les images de la chronologie
	 * @param parEvt correspond à l'évènement affiché par le PanelDescription
	 * @param parDossier correspond au dossier où se situe l'image de l'évènement
	 */
	public void updatePanel(Evt parEvt, String parDossier)
	{
		imageOrigIcon = new ImageIcon(Data.imageRepository + File.separator + parDossier + File.separator + parEvt.getFichier());
		Image imageOrig = imageOrigIcon.getImage().getScaledInstance(
				imageOrigIcon.getIconWidth()*150/imageOrigIcon.getIconHeight(), 
				150,
				Image.SCALE_SMOOTH);

		image.setIcon(new ImageIcon(imageOrig));

		String strDate = "<h3>" + parEvt.getDate().toString() + "</h3>";
		String strNom = "<h3>" + parEvt.getNom() + "</h3>";
		String strDescription = "<p>";
		for(int i = 0; i < parEvt.getDescription().length(); i++)
		{
			if(parEvt.getDescription().charAt(i) == '\n')
				strDescription += "<br>";
			else
				strDescription += parEvt.getDescription().charAt(i);
		}
		strDescription += "</p>";
		
		description.setText("<html>" +
				strDate + strNom + strDescription +
				"</html>");
	}
	/**
	 * Permet d'enlever toutes les informations des deux panels(image et description)
	 */
	public void clearPanel()
	{
		image.setIcon(null);
		description.setText("");
	}
}