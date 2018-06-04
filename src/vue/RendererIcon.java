package vue;

import java.awt.Component;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RendererIcon extends DefaultTableCellRenderer{
	

	  
	  
	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	      boolean hasFocus, int row, int column) {
		  //Permet de resize l'image pour éviter d'obtenir des images trop grandes ou trop petites tout en conservant le ratio
		  ImageIcon imageOrigIcon = new ImageIcon("images" + File.separator + "imageTest" + ".jpg");
		  Image imageOrig = imageOrigIcon.getImage().getScaledInstance(
				50,
				imageOrigIcon.getIconHeight()*50/imageOrigIcon.getIconWidth(), 
				Image.SCALE_SMOOTH);
		  //Création de l'image
		  JLabel image = new JLabel(new ImageIcon(imageOrig), JLabel.CENTER);
		  return image;
	  }
	
}