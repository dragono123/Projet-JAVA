package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PanelFrise extends JPanel
{
	public PanelFrise()
	{
		setLayout(new BorderLayout(10, 5));
		PanelEvt panelEvt = new PanelEvt();
		PanelListeEvts panelEvts = new PanelListeEvts();
		
		
		add(panelEvt, BorderLayout.NORTH);
		add(panelEvts, BorderLayout.SOUTH);
	}
	
	
}