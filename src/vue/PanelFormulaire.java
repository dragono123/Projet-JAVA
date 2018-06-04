package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PanelFormulaire extends JPanel {
	public PanelFormulaire()
	{
		setLayout(new BorderLayout());
		PanelFormChrono panelChrono = new PanelFormChrono();
		PanelFormEvt panelEvt = new PanelFormEvt();
		add(panelChrono, BorderLayout.WEST);
		add(panelEvt, BorderLayout.CENTER);
	}
}
