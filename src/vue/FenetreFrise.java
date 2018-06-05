package vue;


import java.awt.Insets;

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
		Chronologie chrono = new Chronologie(1954, 2019, "Godzilla", "images");
		historique.ajout(chrono);
		try {
			chrono.ajout(new Evt(new Date(14, 5, 2014), "Godzilla 2014", "Sortie du film", 3, "imageTest.jpg"));
		} catch (ExceptionChronologie e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionEvt e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionDate e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new FenetreFrise(historique);
	}
}