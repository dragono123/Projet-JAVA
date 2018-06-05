package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modele.Chronologie;

public class PanelEvt extends JPanel {
	private JLabel titreEvt = new JLabel("", SwingConstants.CENTER);
	private PanelListeDescription panelListeDesc;
	
	public PanelEvt(Chronologie parChrono)
	{
		setLayout(new BorderLayout(5, 5));
		panelListeDesc = new PanelListeDescription(parChrono);
		titreEvt.setFont(new Font("Courier", Font.BOLD, 14));
		titreEvt.setText(parChrono.getNom());
		
		add(titreEvt, BorderLayout.NORTH);
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
}