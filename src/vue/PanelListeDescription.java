package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelListeDescription extends JPanel{

	
	private JButton boutonArriere;
	private JButton boutonSuivant;
	private CardLayout cardLayout = new CardLayout(5, 5);
	public PanelListeDescription()
	{
		setLayout(new BorderLayout(5, 5));
		JPanel panelDiapo = new JPanel();
		PanelDescription panelDesc = new PanelDescription();
		boutonArriere = new JButton("<");
		boutonSuivant = new JButton(">");
		

		panelDiapo.setLayout(cardLayout);
		
		add(panelDiapo, BorderLayout.CENTER);
		add(boutonArriere, BorderLayout.WEST);
		add(boutonSuivant, BorderLayout.EAST);
		
	}
}
