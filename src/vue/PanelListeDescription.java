package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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

public class PanelListeDescription extends JPanel{

	
	private JButton boutonArriere;
	private JButton boutonSuivant;
	private Chronologie chChronologie;
	private Evt evtCourant;
	private PanelDescription panelDesc;
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
	public void updateChronologie(Chronologie parChronologie)
	{
		chChronologie = parChronologie;
		if(chChronologie != null)
			updatePremier();
	}
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
	public void updateActu(Evt parEvt)
	{
		evtCourant = parEvt;
		panelDesc.updatePanel(parEvt.getFichier(), chChronologie.getDossier(), parEvt.getNom(), parEvt.getDate(), parEvt.getDescription());
	}
	public void enregistreEcouteur(Controleur controleur)
	{
		boutonArriere.addActionListener(controleur);
		boutonArriere.setActionCommand(Data.commandDiapoGauche);
		boutonSuivant.addActionListener(controleur);
		boutonSuivant.setActionCommand(Data.commandDiapoDroite);
		
	}
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
	
}
