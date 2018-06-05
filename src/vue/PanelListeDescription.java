package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
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
	private CardLayout cardLayout = new CardLayout(5, 5);
	private Chronologie chChronologie;
	private JPanel panelDiapo;
	
	public PanelListeDescription(Chronologie parChronologie)
	{
		setLayout(new BorderLayout(5, 5));
		panelDiapo = new JPanel();
		panelDiapo.setLayout(cardLayout);
		boutonArriere = new JButton("<");
		boutonSuivant = new JButton(">");
		setPreferredSize(new Dimension(1100, 150));
		
		chChronologie = parChronologie;
		
		if(chChronologie != null)
		{
			TreeMap<Integer, TreeMap<Integer, Evt>> listeMap = chChronologie.getEvtListe();
			
			Set<Integer> keysAn = listeMap.keySet();
			for(Integer keyAn : keysAn)
			{
				TreeMap<Integer, Evt> listeEvt = listeMap.get(keyAn);
				Set<Integer> keysPoids = listeEvt.keySet();
				for(Integer keyPoids : keysPoids)
					ajoutDiapo(listeEvt.get(keyPoids));
			}
			if(!listeMap.isEmpty())
				cardLayout.first(panelDiapo);
		}
		
		add(panelDiapo, BorderLayout.CENTER);
		add(boutonArriere, BorderLayout.WEST);
		add(boutonSuivant, BorderLayout.EAST);
	}
	
	public void ajoutDiapo(Evt parEvt)
	{
		PanelDescription panelDesc = new PanelDescription(parEvt.getFichier(), chChronologie.getDossier(), parEvt.getNom(), parEvt.getDate(), parEvt.getDescription());
		panelDiapo.add(parEvt.getNom(), panelDesc);
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
		cardLayout.next(panelDiapo);
	}
	public void afficherPrec()
	{
		cardLayout.previous(panelDiapo);
	}
	
}
