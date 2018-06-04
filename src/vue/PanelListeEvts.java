package vue;

import java.awt.Dimension;

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
		friseTable.setModel(modele);

		for(int i = 0; i < modele.getColumnCount(); i++)
		{
			friseTable.getColumnModel().getColumn(i).setPreferredWidth(60);
			friseTable.getColumnModel().getColumn(i).setCellRenderer(new RendererIcon());
		}
		add(new JScrollPane(friseTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		friseTable.setRowHeight(60);

		friseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setPreferredSize(new Dimension(1100, 250));
		friseTable.setPreferredScrollableViewportSize(new Dimension(1100, 240));

	}
}