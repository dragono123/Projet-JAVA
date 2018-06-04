package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelEvt extends JPanel {
	private JLabel titreEvt = new JLabel();
	
	public PanelEvt()
	{
		setLayout(new BorderLayout(5, 5));
		PanelDescription panelDesc = new PanelDescription();
		PanelListeDescription panelListeDesc = new PanelListeDescription();
		
		
		add(titreEvt, BorderLayout.NORTH);
		add(panelListeDesc, BorderLayout.SOUTH);
		
	}
}