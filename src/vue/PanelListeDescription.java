package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JPanel;

import controleur.Controleur;
import data.Data;

import modele.Chronologie;
import modele.Evt;

/**
 * 
 * Cette classe représente le panel contenant les boutons permettant de naviguer entre les différents évènements d'une même chronologie et le panel affichang l'évènement
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 *
 */
public class PanelListeDescription extends JPanel{

	
	private JButton boutonArriere;
	private JButton boutonSuivant;
	private Chronologie chChronologie;
	private Evt evtCourant;
	private PanelDescription panelDesc;
	/**
	 * Constructeur permettant d'instancier les différents composants du panel
	 * @param parChronologie correspond à la chronologie courante
	 */
	public PanelListeDescription(Chronologie parChronologie)
	{
		setLayout(new BorderLayout(5, 5));
		boutonArriere = new JButton("<");
		boutonSuivant = new JButton(">");
		setPreferredSize(new Dimension(1100, 150));
		
		chChronologie = parChronologie;
		panelDesc = new PanelDescription();
		if(chChronologie != null)
			updatePremier();
		
		add(panelDesc, BorderLayout.CENTER);
		add(boutonArriere, BorderLayout.WEST);
		add(boutonSuivant, BorderLayout.EAST);
	}
	/**
	 * Permet lors d'un changement de chronologie, de toujours revenir au premier élément de la chronologie
	 * @param parChronologie
	 */
	public void updateChronologie(Chronologie parChronologie)
	{
		chChronologie = parChronologie;
		if(chChronologie != null)
			updatePremier();
	}
	/**
	 * Permet d'afficher le premier élément d'une chronologie
	 */
	public void updatePremier()
	{
		TreeMap<Integer, TreeMap<Integer, Evt>> listeMap = chChronologie.getEvtListe();
		if(!listeMap.isEmpty())
			updateActu(listeMap.firstEntry().getValue().firstEntry().getValue());
		else
		{
			evtCourant = null;
			panelDesc.clearPanel();
		}
	}
	/**
	 * Permet de changer l'affichage d'évènement actuel par un nouvel évènement
	 * @param parEvt est le nouvel évènement qui sera affiché
	 */
	public void updateActu(Evt parEvt)
	{
		evtCourant = parEvt;
		panelDesc.updatePanel(evtCourant, chChronologie.getDossier());
	}
	/**
	 * Permet au controleur de se mettre à l'écoute des deux boutons suivant('>') et arrière ('<')
	 * @param controleur correspond au controleur se mettant à l'écoute
	 */
	public void enregistreEcouteur(Controleur controleur)
	{
		boutonArriere.addActionListener(controleur);
		boutonArriere.setActionCommand(Data.commandDiapoGauche);
		boutonSuivant.addActionListener(controleur);
		boutonSuivant.setActionCommand(Data.commandDiapoDroite);
		
	}
	/**
	 * Permet d'afficher l'évènement suivant dans la liste
	 */
	public void afficherSuivant()
	{

		if(chChronologie != null && evtCourant != null)
		{
			TreeMap<Integer, TreeMap<Integer, Evt>> listeMap = chChronologie.getEvtListe();
			if(listeMap.size() != 0)
			{
				Set<Integer> keysAn = listeMap.keySet();
				Iterator<Integer> iteAn = keysAn.iterator();
				int anEvt = evtCourant.getDate().getAn(), an = iteAn.next();
				while(iteAn.hasNext() && an != anEvt)
					an = iteAn.next();

				TreeMap<Integer, Evt> listeEvtAn = listeMap.get(an);
				Set<Integer> keysPoids = listeEvtAn.keySet();
				Iterator<Integer> itePoids = keysPoids.iterator();
				int poids = itePoids.next();
				while(poids != evtCourant.getPoids())
					poids = itePoids.next();
				
				if(itePoids.hasNext())
					updateActu(listeEvtAn.get(itePoids.next()));
				else if(iteAn.hasNext())
				{
					an = iteAn.next();
					updateActu(listeMap.get(an).firstEntry().getValue());
				}
			}
		}
		else if(chChronologie != null && evtCourant == null)
			updatePremier();
	}
	/**
	 * Permet d'afficher l'évènement précédent
	 */
	public void afficherPrec()
	{
		if(chChronologie != null && evtCourant != null)
		{
			TreeMap<Integer, TreeMap<Integer, Evt>> listeMap = chChronologie.getEvtListe();
			if(listeMap.size() != 0)
			{
				int poidsTampon, anTampon;
				Set<Integer> keysAn = listeMap.keySet();
				Iterator<Integer> iteAn = keysAn.iterator();
				int anEvt = evtCourant.getDate().getAn(), an = iteAn.next();
				anTampon = an;
				while(iteAn.hasNext() && an != anEvt)
				{
				 	anTampon = an;
					an = iteAn.next();
				}
				
				TreeMap<Integer, Evt> listeEvtAn = listeMap.get(an);
				Set<Integer> keysPoids = listeEvtAn.keySet();
				Iterator<Integer> itePoids = keysPoids.iterator();
				int poids = itePoids.next();
				poidsTampon = 0;
				while(poids != evtCourant.getPoids())
				{
					poidsTampon = poids;
					poids = itePoids.next();
				}
				
				if(poidsTampon == poids)
					updateActu(listeEvtAn.get(poidsTampon));
				else
					updateActu(listeMap.get(anTampon).descendingMap().firstEntry().getValue());
				
			}
		}
		else if(chChronologie != null && evtCourant == null)
			updatePremier();
	}
	/**
	 * Getter permettant de récupérer l'évènement courant
	 * @return l'évènement courant (Evt)
	 */
	public Evt getEvtCourant()
	{
		return evtCourant;
	}
}
