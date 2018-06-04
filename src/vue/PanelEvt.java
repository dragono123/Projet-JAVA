package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelEvt extends JPanel {
	private JLabel titreEvt = new JLabel();
	private CardLayout cardLayout = new CardLayout(5, 5);
	public PanelEvt()
	{
		setLayout(new BorderLayout(5, 5));
		JPanel panelDiapo = new JPanel();
		panelDiapo.setLayout(cardLayout);
		PanelDescription panelDesc = new PanelDescription();
		
		add(titreEvt, BorderLayout.NORTH);
		add(panelDiapo, BorderLayout.SOUTH);
		
	}
}