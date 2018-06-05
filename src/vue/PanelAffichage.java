package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import modele.Chronologie;
import modele.Historique;

import controleur.Controleur;

public class PanelAffichage extends JPanel
{
	private PanelListeEvts panelEvts;
	private PanelEvt panelEvt;
	public PanelAffichage(Historique historique)
	{
		setLayout(new BorderLayout(10, 5));
		Chronologie chChronoCourante;
		if(historique.estVide())
		{
			panelEvts = new PanelListeEvts(null);
			panelEvt = new PanelEvt(null);
		}
		else
		{
			chChronoCourante = historique.getRandomChronologie();
			panelEvts = new PanelListeEvts(chChronoCourante);
			panelEvt = new PanelEvt(chChronoCourante);
		}
		add(panelEvt, BorderLayout.NORTH);
		add(panelEvts, BorderLayout.CENTER);
	}
	public PanelListeEvts getListeEvts()
	{
		return panelEvts;
	}
	public PanelEvt getPanelEvt()
	{
		return panelEvt;
	}
}