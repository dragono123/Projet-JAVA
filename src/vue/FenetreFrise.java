package vue;


import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import data.Data;

/**
 * 
 * Cette classe joue le rôle de la fenêtre mère de l'application
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 *
 */

public class FenetreFrise extends JFrame {
	
	/**
	 * Le constructeur ne demande aucun paramètre particulier.
	 */
	public FenetreFrise()
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
		
		
		PanelFrise contentPane = new PanelFrise(items);
		
		setContentPane(contentPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200,550);
		setVisible(true);
		setLocation(200,300);
	}
	public static void main(String args[])
	{
		new FenetreFrise();
	}
}