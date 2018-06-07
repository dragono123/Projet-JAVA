package vue;

import java.awt.Component;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import data.Data;
import modele.Chronologie;
import modele.Evt;

/**
 * 
 * Cette classe représente le renderer qui gère l'affichage de chaque cellule
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 *
 */
public class RendererIcon extends DefaultTableCellRenderer{
	
	  private String chDossier;
	  /**
	   * Constructeur permettant au RenderIcon d'accéder au dossier d'image de la chronologie
	   * @param chrono est la chronologie dont on récupère le dossier d'image
	   */
	  public RendererIcon(Chronologie chrono)
	  {
		  super();
		  if(chrono != null)
			  chDossier = chrono.getDossier();
	  }
	  /**
	   * Permet l'affichage de l'image de l'Evt, du nom quand l'image n'existe pas et le tool tip text
	   */
	  @Override
	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	      boolean hasFocus, int row, int column) {
		  JLabel returnLabel = this;
		  if(value != null)
		  {
			  File imageFile = new File(Data.imageRepository + File.separator +chDossier + File.separator + ((Evt) value).getFichier());
			  if(imageFile.exists())
			  {
				  //Permet de resize l'image pour éviter d'obtenir des images trop grandes ou trop petites tout en conservant le ratio
				  ImageIcon imageOrigIcon = new ImageIcon(Data.imageRepository + File.separator + chDossier + File.separator + ((Evt) value).getFichier());
				  Image imageOrig;
				  if(imageOrigIcon.getIconHeight() > imageOrigIcon.getIconWidth())
					  imageOrig = imageOrigIcon.getImage().getScaledInstance(
							  60,
							  imageOrigIcon.getIconHeight()*60/imageOrigIcon.getIconWidth(), 
							  Image.SCALE_SMOOTH);
				  else
					  imageOrig = imageOrigIcon.getImage().getScaledInstance(
							  imageOrigIcon.getIconWidth()*60/imageOrigIcon.getIconHeight(), 
							  60,
							  Image.SCALE_SMOOTH);
				  
				  //Création de l'image
				  JLabel image = new JLabel(new ImageIcon(imageOrig), JLabel.CENTER);
				  returnLabel = image;
			  }
			  else
				  returnLabel = new JLabel(((Evt)value).getNom());
			  returnLabel.setToolTipText(((Evt)value).getNom());
		  }
		  return returnLabel;
	  }
	
}
