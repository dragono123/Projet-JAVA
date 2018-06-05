package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import controleur.Controleur;

public class PanelFormulaire extends JPanel {
	private PanelFormChrono chPanelChrono = new PanelFormChrono();
	private PanelFormEvt chPanelEvt = new PanelFormEvt();
	public PanelFormulaire()
	{
		setLayout(new BorderLayout(0, 0));
		add(chPanelChrono, BorderLayout.WEST);
		add(chPanelEvt, BorderLayout.EAST);
	}
	
	public PanelFormChrono getPanelChrono()
	{
		return chPanelChrono;
	}
	public PanelFormEvt getPanelEvt()
	{
		return chPanelEvt;
	}
}
