package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import modele.Historique;

import controleur.Controleur;

public class PanelAffichage extends JPanel
{
	public PanelAffichage(Historique historique)
	{
		setLayout(new BorderLayout(10, 5));
		PanelEvt panelEvt = new PanelEvt();
		PanelListeEvts panelEvts;
		if(historique.estVide())
			panelEvts = new PanelListeEvts(null);
		else
			panelEvts = new PanelListeEvts(historique.getRandomChronologie());
		
		add(panelEvt, BorderLayout.NORTH);
		add(panelEvts, BorderLayout.CENTER);
	}
	
}