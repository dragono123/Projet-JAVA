package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * 
 * Ce panel est le panel contenant les deux formulaires d'ajout(chronologie et Evt)
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 */
public class PanelFormulaire extends JPanel {
	private PanelFormChrono chPanelChrono = new PanelFormChrono();
	private PanelFormEvt chPanelEvt = new PanelFormEvt();
	/**
	 * Constructeur permettant d'ajouter les deux panels formulaires
	 */
	public PanelFormulaire()
	{
		setLayout(new BorderLayout(0, 0));
		add(chPanelChrono, BorderLayout.WEST);
		add(chPanelEvt, BorderLayout.EAST);
	}
	
	/**
	 * Getter permettant d'accéder au formulaire de chronologie
	 * @return PanelFormChrono qui correspond au formulaire de chronologie
	 */
	public PanelFormChrono getPanelChrono()
	{
		return chPanelChrono;
	}

	/**
	 * Getter permettant d'accéder au formulaire d'évènements
	 * @return PanelFormEvt qui correspond au formulaire d'évènements
	 */
	public PanelFormEvt getPanelEvt()
	{
		return chPanelEvt;
	}
}