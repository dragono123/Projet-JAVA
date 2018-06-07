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


/**
 * 
 * Cette classe représente le panel contenant la frise chronologique
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 *
 */
public class PanelListeEvts extends JPanel{
	private Chronologie chChronologie;
	private ModeleTable modele;
	private JTable friseTable;
	private PanelAffichage chAffichage;
	private JScrollPane chPaneTable;
	/**
	 * Constructeur instanciant les composants du panel et les ajoute au panel
	 * @param parChronologie Permet d'accéder à la chronologie courante
	 * @param parAffichage Permet l'accès au panel d'affichage(parent)
	 */
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
	/**
	 * Permet de réactualiser la frise avec une nouvelle chronologie
	 * @param parChronologie correspond à la nouvelle chronologie qui sera affichée par la frise
	 */
	public void updateChronologie(Chronologie parChronologie)
	{
		chChronologie = parChronologie;
		modele.updateChronologie(chChronologie);
		ajoutCellRenderer();
	}
	/**
	 * Permet de réactualiser les valeurs de la chronologie
	 */
	public void resetModele()
	{
		modele.afficherFrise();
		ajoutCellRenderer();
	}
	/**
	 * Permet la synchronisation entre la JScrollBar de la frise et la diapo à partir de l'année du nouvel évènement 
	 * @param anEvt représente l'année du nouvelle évènement
	 */
	public void updateScrollBar(int anEvt)
	{
		int indexCol = anEvt - chChronologie.getAnDebut();
		JScrollBar scrollBar = chPaneTable.getHorizontalScrollBar();
		if(scrollBar.getValue() > indexCol*Data.cellWidth)
			scrollBar.setValue(indexCol*Data.cellWidth);
		else if(scrollBar.getValue()+1100 < indexCol * Data.cellWidth)
			scrollBar.setValue((indexCol+1)*Data.cellWidth - 1100);
	}
	/***
	 * Permet le rendering des cellules de la frise(en outre, l'affichage des images et/ou du nom des Evt)
	 */
	private void ajoutCellRenderer()
	{
		for(int i = 0; i < modele.getColumnCount(); i++)
		{
			friseTable.getColumnModel().getColumn(i).setPreferredWidth(Data.cellWidth);
			friseTable.getColumnModel().getColumn(i).setCellRenderer(new RendererIcon(chChronologie));
		}
	}
}