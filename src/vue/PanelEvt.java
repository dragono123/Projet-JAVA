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

public class PanelEvt extends JPanel implements ActionListener{
	private JLabel titreEvt = new JLabel("", SwingConstants.CENTER);
	private PanelListeDescription panelListeDesc;
	private JComboBox chChronoListe;
	private PanelListeEvts chPanelEvtListe;
	private Historique chHistorique;
	public PanelEvt(Chronologie parChrono, Historique parHistorique, PanelListeEvts parPanelEvtListe)
	{
		chHistorique = parHistorique;
		chPanelEvtListe = parPanelEvtListe;
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
	public void setTitre(String nom)
	{
		titreEvt.setText(nom);
	}
	public PanelListeDescription getPanelListeDesc()
	{
		return panelListeDesc;
	}
	public void updateActu(Evt parEvt)
	{
		panelListeDesc.updateActu(parEvt);
	}
	public void actionPerformed(ActionEvent parEvt)
	{
		if(parEvt.getSource() == chChronoListe)
		{
			Chronologie chrono = chHistorique.getChronologie((String) chChronoListe.getSelectedItem());
			chPanelEvtListe.updateChronologie(chrono);
			panelListeDesc.updateChronologie(chrono);
			titreEvt.setText(chrono.getNom());
		}
	}
	public void updateChronologie(Chronologie parChronologie)
	{
		chChronoListe.addItem(parChronologie.getNom());
	}
}