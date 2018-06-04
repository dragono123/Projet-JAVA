package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelListeDescription extends JPanel{

	
	private JButton boutonArriere;
	private JButton boutonSuivant;
	private CardLayout cardLayout = new CardLayout(5, 5);
	
	public PanelListeDescription()
	{
		setLayout(new BorderLayout(5, 5));
		JPanel panelDiapo = new JPanel();
		PanelDescription panelDescTest = new PanelDescription();
		boutonArriere = new JButton("<");
		boutonSuivant = new JButton(">");
		setPreferredSize(new Dimension(1100, 150));
		
		//A DELETE
		panelDiapo.setLayout(cardLayout);
		panelDiapo.add("test", panelDescTest);
		cardLayout.show(panelDiapo, "test");
		
		add(panelDiapo, BorderLayout.CENTER);
		add(boutonArriere, BorderLayout.WEST);
		add(boutonSuivant, BorderLayout.EAST);
	}
}
