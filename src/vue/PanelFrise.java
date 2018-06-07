package vue;

import java.awt.CardLayout;
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

public class PanelFrise extends JPanel implements ActionListener{

	private CardLayout layout = new CardLayout(5,5);
	public PanelFrise(JMenuItem[] parItems)
	{
		setLayout(layout);

		Historique historique = new Historique();
		File dossier = new File(Data.saveFile);
		if(!dossier.exists())
		{
			try {
				dossier.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
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
	}
	
}
