package vue;

import java.awt.CardLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controleur.Controleur;
import modele.Chronologie;
import modele.Historique;

import data.Data;
import data.LectureEcriture;

/**
 * 
 * Cette classe représente le panel principal qui contiendra tous les panels de l'application
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 *
 */
public class PanelFrise extends JPanel implements ActionListener{

	private CardLayout layout = new CardLayout(5,5);
	/**
	 * Cette fonction construit le panel principal et implèmente la gestion des JMenuItem
	 * @param parItems permet à ce que PanelFrise puisse se mettre à l'écoute des JMenuItem et changer de panel si besoin est-il.
	 */
	public PanelFrise(JMenuItem[] parItems)
	{
		setLayout(layout);

		Historique historique = new Historique();
		// Ouverture de l'ensemble des fichiers de save contenus dans le dossier saves qu'on rajoute dans Historique
		File dossier = new File(Data.saveFile);
		
		File[] listOfFiles = dossier.listFiles();
		
		for(int i = 0; i < listOfFiles.length; i++)
		{
			if(listOfFiles[i].isFile())
			{
				try {
					Chronologie chrono = (Chronologie) LectureEcriture.lecture(listOfFiles[i]);
					historique.ajout(chrono);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		PanelAffichage affiche = new PanelAffichage(historique);
		PanelFormulaire formulaire = new PanelFormulaire();
		// On instancie le contrôleur
		new Controleur(formulaire, affiche, historique);
		
		add(Data.items[1], affiche);
		add(Data.items[0], formulaire);
		layout.show(this, Data.items[1]);
		for(int i = 0; i < parItems.length; i++)
		{
			parItems[i].addActionListener(this);
			parItems[i].setActionCommand(Data.items[i]);
		}
	}
	/**
	 * On donne la possibilité de rajouté à l'utilisateur de pourvoir naviguer entre les panels affichage et formulaire à l'aide de la barre de menu
	 */
	public void actionPerformed(ActionEvent evt) {
		if(evt.getActionCommand().equals(Data.items[0]))
			layout.show(this, evt.getActionCommand());
		if(evt.getActionCommand().equals(Data.items[1]))
			layout.show(this, evt.getActionCommand());

    	if(evt.getActionCommand() == Data.items[2])
    	{
    		int choix = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment continuer ?", "Fermer la fenêtre", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
    		if(choix == JOptionPane.OK_OPTION)
    			System.exit(0);
    	}
    	if(evt.getActionCommand() == Data.items[3])
    	{
    		File file = new File("manuel.pdf");
    		if(!Desktop.isDesktopSupported()){
                System.out.println("Desktop is not supported");
            }
    		else
    		{

    	        Desktop desktop = Desktop.getDesktop();
    	        if(file.exists())
    	        {
					try {
						desktop.open(file);
					} catch (IOException e) {
					}
    	        }
    		}
    	}
	}
	
}
