package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PanelFrise extends JPanel
{
	public PanelFrise()
	{
		setLayout(new BorderLayout(10, 5));
		PanelDescription panelDesc = new PanelDescription();
		PanelEvts panelEvts = new PanelEvts();
		
		
		setLayout(panelDesc, BorderLayout.NORTH);
		setLayout(panelEvts, BorderLayout.SOUTH);
	}
	
	
}