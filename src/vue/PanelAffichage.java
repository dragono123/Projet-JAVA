package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import modele.Chronologie;
import modele.Historique;

/**
 * 
 * Cette classe représente le panel d'affichage où se trouve la frise chronologiques et le descriptif des évènements
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 *
 */
public class PanelAffichage extends JPanel
{
	private PanelListeEvts panelEvts;
	private PanelEvt panelEvt;
	/**
	 * Cette fonction permet de construire le panel d'affichage où se trouvent le panel contenant la frise chronologique et le panel détaillant chaque évènement.
	 * @param historique permet de transférer l'historique chargé dans PanelFrise dans le panel affichage.
	 */
	public PanelAffichage(Historique historique)
	{
		setLayout(new BorderLayout(10, 5));
		Chronologie chChronoCourante;
		if(historique.estVide())
		{
			panelEvts = new PanelListeEvts(null, this);
			panelEvt = new PanelEvt(null, historique, this);
		}
		else
		{
			chChronoCourante = historique.getRandomChronologie();
			panelEvts = new PanelListeEvts(chChronoCourante, this);
			panelEvt = new PanelEvt(chChronoCourante, historique, this);
		}
		add(panelEvt, BorderLayout.NORTH);
		add(panelEvts, BorderLayout.CENTER);
	}
	/**
	 * Retourne le panel contenant la frise chronologique
	 * @return correspond au panel où est situé la frise chronologique
	 */
	public PanelListeEvts getListeEvts()
	{
		return panelEvts;
	}
	/**
	 * Retourne le pnael contenant le détails des évènements
	 * @return correspond au panel où se situe les détails de l'Evt courant, le titre de la chronologie courante et la JComboBox permettant de naviguer entre les chronologies
	 */
	public PanelEvt getPanelEvt()
	{
		return panelEvt;
	}
}