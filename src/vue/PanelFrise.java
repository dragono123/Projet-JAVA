package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelFrise extends JPanel
{
	public PanelFrise()
	{
		setLayout(new BorderLayout(10, 5));
		PanelEvt panelEvt = new PanelEvt();
		PanelListeEvts panelEvts = new PanelListeEvts();
		
		add(panelEvt, BorderLayout.NORTH);
		add(panelEvts, BorderLayout.CENTER);
	}
	
	
}