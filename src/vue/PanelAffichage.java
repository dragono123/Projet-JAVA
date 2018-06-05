package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import controleur.Controleur;

public class PanelAffichage extends JPanel
{
	public PanelAffichage()
	{
		setLayout(new BorderLayout(10, 5));
		PanelEvt panelEvt = new PanelEvt();
		PanelListeEvts panelEvts = new PanelListeEvts();
		
		add(panelEvt, BorderLayout.NORTH);
		add(panelEvts, BorderLayout.CENTER);
	}
	
}