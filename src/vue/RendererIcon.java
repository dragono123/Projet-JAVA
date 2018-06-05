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

public class RendererIcon extends DefaultTableCellRenderer{
	
	  private String chDossier;
	  
	  public RendererIcon(Chronologie chrono)
	  {
		  super();
		  if(chrono != null)
			  chDossier = chrono.getDossier();
	  }
	  
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
		  }
		  return returnLabel;
	  }
	
}
