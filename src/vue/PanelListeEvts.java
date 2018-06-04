package vue;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import modele.ModeleTable;


public class PanelListeEvts extends JPanel{
	
	public PanelListeEvts()
	{
		JTable friseTable = new JTable();
		ModeleTable modele = new ModeleTable();
		add(new JScrollPane(friseTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	}
}