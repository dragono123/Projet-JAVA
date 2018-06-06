package vue;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import data.Data;
import modele.Chronologie;
import modele.Evt;
import modele.ModeleTable;


public class PanelListeEvts extends JPanel{
	private Chronologie chChronologie;
	private ModeleTable modele;
	private JTable friseTable;
	private PanelAffichage chAffichage;
	private JScrollPane chPaneTable;
	public PanelListeEvts(Chronologie parChronologie, PanelAffichage parAffichage)
	{
		chAffichage = parAffichage;
		friseTable = new JTable();
		chChronologie = parChronologie;
		modele = new ModeleTable(chChronologie);
		friseTable.setModel(modele);
		ajoutCellRenderer();
		chPaneTable = new JScrollPane(friseTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(chPaneTable);
		friseTable.setRowHeight(Data.cellHeight);
		friseTable.addMouseListener(new MouseAdapter(){
			  public void mousePressed(MouseEvent e)
			    {
			        JTable source = (JTable)e.getSource();
			        int row = source.rowAtPoint(e.getPoint());
			        int column = source.columnAtPoint(e.getPoint());
			        if(modele.getValueAt(row, column) != null)
			        {
			        	Evt evt = (Evt) modele.getValueAt(row, column);
			        	chAffichage.getPanelEvt().updateActu(evt);
			        }
			    }
		});
		friseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setPreferredSize(new Dimension(1100, 250));
		friseTable.setPreferredScrollableViewportSize(new Dimension(1100, 220));
	}
	public void updateChronologie(Chronologie parChronologie)
	{
		chChronologie = parChronologie;
		modele.updateChronologie(chChronologie);
		ajoutCellRenderer();
	}
	public void resetModele()
	{
		modele.afficherFrise();
		ajoutCellRenderer();
	}
	public void updateScrollBar(int anEvt)
	{
		int indexCol = anEvt - chChronologie.getAnDebut();
		JScrollBar scrollBar = chPaneTable.getHorizontalScrollBar();
		if(scrollBar.getValue() > indexCol*Data.cellWidth)
			scrollBar.setValue(indexCol*Data.cellWidth);
		else if(scrollBar.getValue()+1100 < indexCol * Data.cellWidth)
			scrollBar.setValue((indexCol+1)*Data.cellWidth - 1100);
	}
	private void ajoutCellRenderer()
	{
		for(int i = 0; i < modele.getColumnCount(); i++)
		{
			friseTable.getColumnModel().getColumn(i).setPreferredWidth(Data.cellWidth);
			friseTable.getColumnModel().getColumn(i).setCellRenderer(new RendererIcon(chChronologie));
		}
	}
}