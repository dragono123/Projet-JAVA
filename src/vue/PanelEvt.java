package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelEvt extends JPanel {
	private JLabel titreEvt = new JLabel("", SwingConstants.CENTER);
	
	public PanelEvt()
	{
		setLayout(new BorderLayout(5, 5));
		PanelListeDescription panelListeDesc = new PanelListeDescription();
		titreEvt.setFont(new Font("Courier", Font.BOLD, 14));
		titreEvt.setText("Text de base");
		
		add(titreEvt, BorderLayout.NORTH);
		add(panelListeDesc, BorderLayout.SOUTH);
		
	}
}