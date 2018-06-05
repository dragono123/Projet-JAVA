package vue;


import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import modele.Chronologie;
import modele.Date;
import modele.Evt;
import modele.ExceptionChronologie;
import modele.ExceptionDate;
import modele.ExceptionEvt;
import modele.Historique;

import data.Data;
import data.LectureEcriture;

public class FenetreFrise extends JFrame {

	public FenetreFrise(Historique historique)
	{
		super("Frise chronologique");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenuItem[] items = new JMenuItem[Data.items.length];
		
		for(int i = 0; i < Data.items.length - 1; i++)
		{
			items[i] = new JMenuItem(Data.items[i], Data.items[i].charAt(0));
			items[i].setAccelerator(KeyStroke.getKeyStroke(Data.items[i].charAt(0), java.awt.Event.CTRL_MASK));
			menuBar.add(items[i]);
		}
		items[Data.items.length - 1] = new JMenuItem(Data.items[Data.items.length - 1], Data.items[Data.items.length - 1].charAt(0));
		items[Data.items.length - 1].setAccelerator(KeyStroke.getKeyStroke('H', java.awt.Event.CTRL_MASK));
		menuBar.add(items[Data.items.length - 1]);
		
		
		PanelFrise contentPane = new PanelFrise(items, historique);
		
		setContentPane(contentPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200,550);
		setVisible(true);
		setLocation(200,300);
	}
	public static void main(String args[])
	{
		Historique historique = new Historique();
		/*Chronologie chrono = new Chronologie(1954, 2019, "Godzilla", "images", "godzilla.ser");
		historique.ajout(chrono);
		try {
			chrono.ajout(new Evt(new Date(14, 5, 2014), "Godzilla 2014", "Sortie du film", 3, "imageTest.jpg"));
			chrono.ajout(new Evt(new Date(14, 5, 2016), "Shin Godzilla", "Retour du grand Godzilla", 2, "shin_godzilla.jpg"));
		} catch (ExceptionChronologie e) {
			
			e.printStackTrace();
		} catch (ExceptionEvt e) {
			
			e.printStackTrace();
		} catch (ExceptionDate e) {
			
			e.printStackTrace();
		}*/
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
		new FenetreFrise(historique);
	}
}