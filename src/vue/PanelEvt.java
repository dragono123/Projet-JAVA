package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modele.Chronologie;
import modele.Evt;
import modele.Historique;
/**
 * 
 * Ce panel est le panel contenant le PanelListeDescription, le titre de la chronologie, et la JComboBox permettant de naviguer entre les chronologies
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 */
public class PanelEvt extends JPanel implements ActionListener{
	private JLabel titreEvt = new JLabel("", SwingConstants.CENTER);
	private PanelListeDescription panelListeDesc;
	private JComboBox chChronoListe;
	private PanelAffichage chAffichage;
	private Historique chHistorique;
	/**
	 * Constructeur du panel
	 * @param parChrono correspond à la chronologie courante(choisie au hasard) qui sera d'abord affiché
	 * @param parHistorique correspond à l'historique 
	 * @param parPanelEvtListe parPanelEvtListe
	 */
	public PanelEvt(Chronologie parChrono, Historique parHistorique, PanelAffichage parAffichage)
	{
		chHistorique = parHistorique;
		chAffichage = parAffichage;
		setLayout(new BorderLayout(5, 5));
		JPanel panelNorth = new JPanel();
		panelNorth.add(titreEvt);
		panelListeDesc = new PanelListeDescription(parChrono);
		titreEvt.setFont(new Font("Courier", Font.BOLD, 14));
		chChronoListe = new JComboBox(chHistorique.listeNomChrono());
		chChronoListe.addActionListener(this);
		panelNorth.add(chChronoListe);
		if(parChrono != null)
		{
			titreEvt.setText(parChrono.getNom());
			chChronoListe.setSelectedItem(parChrono.getNom());
		}
		add(panelNorth, BorderLayout.NORTH);
		add(panelListeDesc, BorderLayout.SOUTH);
	}
	/**
	 * Permet de changer le titre de la chronologie courante
	 * @param nom correspond au nouveau titre de la chronologie courante
	 */
	public void setTitre(String nom)
	{
		titreEvt.setText(nom);
	}
	/**
	 * Permet de récupérer le panel les flèches permettant de naviguer entre les évènements et le panel détaillant avec précision l'évènement
	 * @return retourne le PanelListeDescription
	 */
	public PanelListeDescription getPanelListeDesc()
	{
		return panelListeDesc;
	}
	/**
	 * Permet d'actualiser le PanelDescription détaillant l'évènement courant avec un nouvelle évènement
	 * @param parEvt représente le nouvel évènement qu'on souhaite afficher
	 */
	public void updateActu(Evt parEvt)
	{
		panelListeDesc.updateActu(parEvt);
	}
	/**
	 * Permet la navigation entre les différentes chronologies via le JCombBox
	 */
	public void actionPerformed(ActionEvent parEvt)
	{
		if(parEvt.getSource() == chChronoListe)
		{
			Chronologie chrono = chHistorique.getChronologie((String) chChronoListe.getSelectedItem());
			chAffichage.getListeEvts().updateChronologie(chrono);
			panelListeDesc.updateChronologie(chrono);
			titreEvt.setText(chrono.getNom());
		}
	}
	/**
	 * Permet d'ajouter un nouvel item dans la JComboBox contenat toutes les chronologies
	 * @param parChronologie correspond à la chronologie qu'on souhaite ajouter dans la JComboBox
	 */
	public void updateChronologie(Chronologie parChronologie)
	{
		chChronoListe.addItem(parChronologie.getNom());
	}
}